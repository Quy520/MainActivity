package com.fm.designstar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class CostomGrideView extends GridView {
    //前三个为构造函数
    public CostomGrideView(Context context) {
        super(context);
    }

    public CostomGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CostomGrideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 改写gridview高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
