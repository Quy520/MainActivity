package com.fm.designstar.widget.imageview;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.utils.OssImageUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.utils.image.RequestOptionsUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/9 10:27
 * @update : 2018/8/9
 */
public class ImageCycleViewBanner extends LinearLayout implements View.OnTouchListener {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 图片轮播视图
     */
    private ViewPager viewPager;

    private ImagePagerAdapter adapter;

    private List<String> myUrls;
    //    private int maxHeight = 400;
    private int screenWidth;
    /**
     * 图片轮播指示器控件
     */
    private ViewGroup mGroup;

    /**
     * 图片轮播指示器-个图
     */
//    private ImageView mImageView = null;

    /**
     * 滚动图片指示器-视图列表
     */
    private ImageView[] mImageViews = null;

    /**
     * 图片滚动当前图片下标
     */
    private int mImageIndex = 0;

    private int emptyBitmap = R.mipmap.ico_default_3_2;

    /**
     * 切换下一张图片的标志
     */
    private static final int NEXT = 99;
    /**
     * 是否自动轮播的标志，默认不自动轮播
     */
    private boolean isRunning = false;


    private OnPageChangeListener pageChangeListener;

    private RequestOptions myOptions;

    private int weith;
    private int height;



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) motionEvent.getX();
                downY = (int) motionEvent.getY();
                downTime = (int) System.currentTimeMillis();
                //停止轮播
                isRunning = false;
                handler.removeMessages(NEXT);
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) motionEvent.getX();
                int upY = (int) motionEvent.getY();
                int disX = Math.abs(upX - downX);
                int disY = Math.abs(upY - downY);
                int upTime = (int) System.currentTimeMillis();
                if (upTime - downTime < 500 && disX - disY < 5) {
                    if (pageChangeListener != null) {
                        pageChangeListener.onItemClick(viewPager.getCurrentItem() % myUrls.size());
                    }
                }
                //开启轮播
                startRoll();
                break;
            case MotionEvent.ACTION_CANCEL:
                startRoll();
                break;
            default:
                break;
        }
        return false;
    }

    public interface OnPageChangeListener {
        void onItemClick(int position);
    }

    /**
     * @param context
     */
    public ImageCycleViewBanner(Context context) {
        super(context);
        mContext = context;
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public ImageCycleViewBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.pageChangeListener = onPageChangeListener;
    }

    private void init() {
        screenWidth= Util.getScreenWidth(mContext);
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext, 10, emptyBitmap);
        inflate(mContext, R.layout.ad_cycle_view, this);
        viewPager = findViewById(R.id.adv_pager);
        // 滚动图片右下指示器视图
        mGroup = findViewById(R.id.viewGroup);
        if (myUrls == null) {
            myUrls = new ArrayList<>();
        }
        viewPager.setOnTouchListener(this);
    }

    public void addUrls(List<String> urls) {
        myUrls.clear();
        if (urls != null) {
            myUrls.addAll(urls);
        }
        initView();
    }

    public void initView() {
        setImageResources();
        initViewPager();
    }

    private void initViewPager() {
        if (viewPager.getAdapter() == null) {
            if (adapter == null) {
                adapter = new ImagePagerAdapter();
            }
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    for (int i = 0; i < mImageViews.length; i++) {
                        if (i == position % myUrls.size()) {
                            mImageViews[i].setImageResource(R.drawable.shape_black_cycle);
                        } else {
                            mImageViews[i].setImageResource(R.drawable.shape_white_cycle);
                        }
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onPageScrollStateChanged(int state) {
                    if (viewPager.getChildCount() == 1) {
                        return;
                    }
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        if (viewPager.getCurrentItem() == Objects.requireNonNull(viewPager.getAdapter()).getCount() - 1) {
                            viewPager.setCurrentItem(myUrls.size() - 1, false);
                        }
                        if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(myUrls.size(), false);
                        }
                    }
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
        if (myUrls.size() > 1) {
            isRunning = false;
            handler.removeMessages(NEXT);
            startRoll();
        }
    }

    /**
     * 装填图片数据
     */
    public void setImageResources() {
        // 清除所有子视图
        mGroup.removeAllViews();
        // 图片广告数量

        if (myUrls.size() <= 1) {
            mGroup.setVisibility(GONE);
        } else {
            mGroup.setVisibility(VISIBLE);
        }
        mImageViews = new ImageView[myUrls.size()];
        for (int i = 0; i < myUrls.size(); i++) {
            ImageView mImageView = new ImageView(mContext);
            int imageParams = Tool.dip2px(mContext, 5);
            LayoutParams params = new LayoutParams(imageParams, imageParams);
            params.rightMargin = Tool.dip2px(mContext, 4);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageView.setLayoutParams(params);
            mImageViews[i] = mImageView;
            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.drawable.shape_black_cycle);
            } else {
                mImageViews[i].setBackgroundResource(R.drawable.shape_white_cycle);
            }
            mGroup.addView(mImageViews[i]);
        }
    }

    private class ImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            if (myUrls.size() == 1) {
                return myUrls.size();
            }
            return myUrls.size() * 3;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);
            if (StringUtil.isBlank(myUrls.get(position % myUrls.size()))) {
                Glide.with(mContext)
                        .load("")
                        .apply(myOptions).into(imageView);
            } else {
                if (height==0){
                    height=screenWidth / 2 * 3;
                }
                if (weith<0||weith==0){
                    weith=screenWidth;
                }

                Glide.with(mContext)
                        .load(OssImageUtil.getThumbnailCut(myUrls.get(position % myUrls.size()), height, weith))
                        .apply(myOptions).into(imageView);
              //  Log.e("qsd","uir===="+OssImageUtil.getThumbnailCut(myUrls.get(position % myUrls.size()), height, weith));
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case NEXT:
                    if (isRunning) {
                        if (viewPager.getCurrentItem() + 1 < Objects.requireNonNull(viewPager.getAdapter()).getCount()) {
                            //设置当前item+1；相当于设置下一个item，然后余图片数量；
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            //然后发送空消息延时2秒
                            handler.sendEmptyMessageDelayed(NEXT, 4000);
                        }
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    });
    public void setweith(int we){
        this.weith=we;

    }
    public void setHe(int he){
        this.height=he;

    }

    /**
     * 开始轮播
     */
    public void startRoll() {
        //一个数据或者没有不进行轮播
        if (myUrls.size() <= 1) {
            return;
        }
        //开启轮播
        isRunning = true;
        //发送handler延时2秒
        handler.sendEmptyMessageDelayed(NEXT, 4000);
    }

    private int downTime = 0;//按下时间
    //按下的XY坐标
    private int downX = 0;
    private int downY = 0;


    public void removerpll(){
        mGroup.setVisibility(GONE);
    }
}
