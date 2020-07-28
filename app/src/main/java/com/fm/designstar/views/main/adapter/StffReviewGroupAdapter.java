package com.fm.designstar.views.main.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.widget.viegroup.BaseGroupAdapter;

import androidx.core.content.ContextCompat;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/19 11:19
 * @update : 2018/9/19
 */
public class StffReviewGroupAdapter extends BaseGroupAdapter<String> {
    private Context mContext;
    private int MAX_SHOW;

    private int childHeight;// = Util.INSTANCE.getSR(20);
    private int textModePaddingLift = 14;
    private int textModePaddingRight = 14;
    //正常样式
    private float itemTextSize = 12;
    private int itemBGResNor = R.drawable.bank_close_shape;
    private int itemTextColorNor = R.color.edit_color;


    public StffReviewGroupAdapter(Context context) {
        mContext = context;
        childHeight = Tool.dip2px(mContext, 22);
    }


    @Override
    public int getCount() {
        if (MAX_SHOW == 0) {
            return mData.size();
        } else {
            return Math.min(MAX_SHOW, mData.size());
        }
    }

    public void setMAX_SHOW(int MAX_SHOW) {
        this.MAX_SHOW = MAX_SHOW;
    }
    public interface OnClickListener {
        void onClick(int position);
    }
    private OnClickListener listener;
    public void setOnClickListener(OnClickListener likeListener) {
        this.listener = likeListener;
    }
    @Override
    public View getView(ViewGroup viewGroup, final int position) {
        TextView childView = new TextView(mContext);
        childView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                childHeight));
        childView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Tool.dip2px(mContext, itemTextSize));
        childView.setBackgroundResource(itemBGResNor);
        childView.setPadding(Tool.dip2px(mContext, textModePaddingLift), 0, Tool.dip2px(mContext, textModePaddingRight), 0);
        childView.setTextColor(ContextCompat.getColor(mContext, itemTextColorNor));
        childView.setGravity(Gravity.CENTER);
        childView.setText(mData.get(position));
        childView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });


        return childView;
    }
}
