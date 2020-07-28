package com.fm.designstar.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.fm.designstar.R;

import androidx.recyclerview.widget.RecyclerView;


/**
 * @author Administrator
 * @date 2017/5/15
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int left;
    private int top;
    private int right;
    private int bottom;

    public SpaceItemDecoration() {
    }

    public SpaceItemDecoration(Context context) {
        this.context = context;
    }

    public SpaceItemDecoration setLeft(int left) {
        this.left = left;
        return this;
    }

    public SpaceItemDecoration setTop(int top) {
        this.top = top;
        return this;
    }

    public SpaceItemDecoration setRight(int right) {
        this.right = right;
        return this;
    }

    public SpaceItemDecoration setBottom(int bottom) {
        this.bottom = bottom;
        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = left;
        outRect.top = top;
        outRect.right = right;
        outRect.bottom = bottom;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (context != null) {
            mDivider = context.getResources().getDrawable(R.drawable.line);
            drawVerticalLine(c, parent, state);
            drawHorizontalLine(c, parent, state);
        }
    }


    private Drawable mDivider;

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
            //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
        }
    }

    //画竖线
    public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
