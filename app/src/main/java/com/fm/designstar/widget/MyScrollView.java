package com.fm.designstar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.core.widget.NestedScrollView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/15 11:38
 * @update : 2018/8/15
 */
public class MyScrollView extends NestedScrollView {
    private int slop;
    private int touch;
    private boolean isLoading = false, isPreLoading=false;
    private boolean isCanLoad = true;
    private OnScrollListener listener;
    private OnLoadMoreListener onLoadMoreListener;
    private OnPreLoadMoreListener onpreLoadMoreListener;
    public static final int SCROLL_UP = 0x01;
    /**
     * ScrollView正在向下滑动
     */
    public static final int SCROLL_DOWN = 0x10;
    /**
     * 最小的滑动距离
     */
    private static final int SCROLLLIMIT = 40;
    private ScrollListener mListener;
    public  interface ScrollListener {
         void scrollOritention(int oritention);
    }
    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }
    public void setScrollListener(ScrollListener l) {
        this.mListener = l;
    }
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        onLoadMoreListener = listener;
    }

    public void setOnPreLoadMoreListener(OnPreLoadMoreListener listener) {
        onpreLoadMoreListener = listener;
    }

    public MyScrollView(Context context) {
        super(context);
        setSlop(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSlop(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSlop(context);
    }

    public interface OnScrollListener {
        void onScroll(int scrollY);



    }

//    public interface OnScrollStatusChangeListener {
//        void onScrollStatusChange(boolean isStop);
//    }
//
//    private OnScrollStatusChangeListener onScrollStatusChangeListener;
//
//    public void setOnScrollStatusChangeListener(OnScrollStatusChangeListener onScrollStatusChangeListener) {
//        this.onScrollStatusChangeListener = onScrollStatusChangeListener;
//    }

    public interface OnLoadMoreListener {
        /**
         * 加载更多
         */
        void onLoadMore();
    }

    public interface OnPreLoadMoreListener {
        /**
         * 加载更多
         */
        void onPreLoadMore();
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //  保存当前touch的纵坐标值
                touch = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //  滑动距离大于slop值时，返回true
                if (Math.abs((int) ev.getRawY() - touch) > slop) return true;
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    private void setSlop(Context context) {
        slop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //上拉加载更多
      //  Log.e("qsd", "isLoading" + isLoading + "isCanLoad" + isCanLoad);
        if (getHeight() + getScrollY() >= getChildAt(0).getMeasuredHeight()) {
            if (onLoadMoreListener != null && !isLoading && isCanLoad) {
                isLoading = true;
                onLoadMoreListener.onLoadMore();
            }
        }


        if (getHeight() + getScrollY() >= getChildAt(0).getMeasuredHeight() - 950) {
            if (onpreLoadMoreListener != null && !isPreLoading && isCanLoad) {
                isPreLoading = true;
                onpreLoadMoreListener.onPreLoadMore();
            }
        }
        if (listener != null) {
            listener.onScroll(t);
        }

        if (oldt > t && oldt - t > SCROLLLIMIT) {// 向下
            if (mListener != null)
                mListener.scrollOritention(SCROLL_DOWN);
        } else if (oldt < t && t - oldt > SCROLLLIMIT) {// 向上
            if (mListener != null)
                mListener.scrollOritention(SCROLL_UP);
//        if (onScrollStatusChangeListener != null) {
//            handler.removeMessages(touchEventId);
//            handler.sendMessageDelayed(handler.obtainMessage(touchEventId), 5);
//        }
        }
    }

//    private int lastY = 0;
//    private int touchEventId = -9983761;
//
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == touchEventId) {
//                if (lastY == getScrollY()) {
//                    onScrollStatusChangeListener.onScrollStatusChange(true);
//                } else {
//                    handler.sendMessageDelayed(handler.obtainMessage(touchEventId), 20);
//                    lastY = getScrollY();
//                    onScrollStatusChangeListener.onScrollStatusChange(false);
//                }
//            }
//        }
//    };

    public void stopLoadMore() {
        isLoading = false;
        isPreLoading=false;
    }

    public boolean isCanLoad() {
        return isCanLoad;
    }

    public void setCanLoad(boolean canLoad) {
        isCanLoad = canLoad;
    }


}