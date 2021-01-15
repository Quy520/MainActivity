package com.fm.designstar.utils;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import cn.jzvdother.JZUtils;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;


/**
 * 列表自动播放工具类
 *
 * @author Liberations
 */
public class AutoPlayUtils {
    public static int positionInList = -1;//记录当前播放列表位置

    private AutoPlayUtils() {
    }

    /**
     * @param firstVisiblePosition 首个可见item位置
     * @param lastVisiblePosition  最后一个可见item位置
     */
    public static void onScrollPlayVideo(RecyclerView recyclerView, int jzvdId, int firstVisiblePosition, int lastVisiblePosition) {
        if (JZUtils.isWifiConnected(recyclerView.getContext())) {
            Log.e("qsd","滑动播放1");
            for (int i = 0; i <= lastVisiblePosition - firstVisiblePosition; i++) {
                View child = recyclerView.getChildAt(i);
                View view = child.findViewById(jzvdId);
                Log.e("qsd","滑动播放2");

                if (view != null && view instanceof JZVideoPlayerStandard) {
                    JZVideoPlayerStandard player = (JZVideoPlayerStandard) view;
                    if (getViewVisiblePercent(player) == 1f) {
                       // Log.e("qsd","滑动播放3"+positionInList+"=="+i + firstVisiblePosition);
                        Log.e("qsd","滑动播放===="+positionInList+"=i="+i +"=firstVisiblePosition=="+firstVisiblePosition);


                        if (positionInList != i + firstVisiblePosition) {
                            player.startButton.performClick();
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * @param firstVisiblePosition 首个可见item位置
     * @param lastVisiblePosition  最后一个可见item位置
     * @param percent              当item被遮挡percent/1时释放,percent取值0-1
     */
    public static void onScrollReleaseAllVideos(int firstVisiblePosition, int lastVisiblePosition, float percent) {
        if (JZVideoPlayerManager.getCurrentJzvd() == null) return;
        if (positionInList >= 0) {
            Log.e("qsd","滑动停止播放"+positionInList);

            if ((positionInList <= firstVisiblePosition || positionInList >= lastVisiblePosition - 1)) {
                Log.e("qsd","滑动停止播放"+positionInList+"=="+firstVisiblePosition);

                if (getViewVisiblePercent(JZVideoPlayerManager.getCurrentJzvd()) < percent) {
                    JZVideoPlayerManager.getCurrentJzvd().releaseAllVideos();
                }
            }
        }
    }

    /**
     * @param view
     * @return 当前视图可见比列
     */
    public static float getViewVisiblePercent(View view) {
        if (view == null) {
            return 0f;
        }
        float height = view.getHeight();
        Rect rect = new Rect();
        if (!view.getLocalVisibleRect(rect)) {
            return 0f;
        }
        float visibleHeight = rect.bottom - rect.top;
        return visibleHeight / height;
    }
}
