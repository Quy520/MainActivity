package com.fm.designstar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/29 13:51
 * @update : 2018/9/29
 */
public class NoScrollViewPager extends ViewPager {
    //是否可以进行滑动
    private boolean isSlide = false;

    public void setSlide(boolean slide) {
        isSlide = slide;
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isSlide;
    }

}
