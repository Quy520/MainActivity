package com.fm.designstar.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.fm.designstar.R;
import com.fm.designstar.widget.DrawableCenterTextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


/**
 * @author DELL
 */
public class TitleUtil {
    private View toolLay;
    private Toolbar mToolbar;
    private DrawableCenterTextView mLeft;
    private DrawableCenterTextView mClose;
    private TextView mTitle;
    private DrawableCenterTextView mRight;
    private AppCompatActivity mActivity;

    //activity构造
    public TitleUtil(AppCompatActivity activity, View view) {
        toolLay = view.findViewById(R.id.toolLay);
        mToolbar =  view.findViewById(R.id.toolbar);
        if (mToolbar == null) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams()).topMargin = Util.getStatusBarH(activity);
        mLeft = (DrawableCenterTextView) view.findViewById(R.id.tv_left);
        mClose = (DrawableCenterTextView) view.findViewById(R.id.tv_close);
        mTitle = (TextView) view.findViewById(R.id.tv_title);
        mRight = (DrawableCenterTextView) view.findViewById(R.id.tv_right);
        this.mActivity = activity;
        mActivity.setSupportActionBar(mToolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setFocusable(true);
        mToolbar.requestFocus();
        mToolbar.setFocusableInTouchMode(true);
        mToolbar.requestFocusFromTouch();
    }

    /**
     * 设置title与返回键
     *
     * @param isShowBack 是否显示返回按钮
     * @param listener   返回按钮的点击事件，默认为传入null  销毁当前activity
     * @param title      标题
     */
    public void setTitle(boolean isShowBack, View.OnClickListener listener, String title) {
        mTitle.setText(title);
        if (isShowBack) {
            Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.back_b);
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mLeft.setCompoundDrawables(left, null, null, null);
            if (listener != null) {
                mLeft.setOnClickListener(listener);
            } else {
                mLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
            mLeft.setClickable(false);
        }
    }

    /**
     * 设置title与返回键
     *
     * @param isShowBack 是否显示返回按钮
     * @param listener   返回按钮的点击事件，默认为传入null  销毁当前activity
     * @param title      标题
     */
    public void setTitle(boolean isShowBack, View.OnClickListener listener, int title) {
        mTitle.setText(title);
        if (isShowBack) {
            Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.back);
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mLeft.setCompoundDrawables(left, null, null, null);
            if (listener != null) {
                mLeft.setOnClickListener(listener);
            } else {
                mLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
            mLeft.setClickable(false);
        }
    }

    public void setTitle( View.OnClickListener listener) {

            if (listener != null) {
                mTitle.setOnClickListener(listener);
            } else {
                mTitle.setClickable(false);
            }

    }
    /**
     * 设置title与返回键
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        setTitle(true, null, title);
    }

    public void setTitle(int title) {
        setTitle(true, null, title);
    }

    /**
     * 设置title与返回键
     *
     * @param isShowBack
     * @param title
     */
    public void setTitle(boolean isShowBack, String title) {
        setTitle(isShowBack, null, title);
    }

    /**
     * 设置title与返回键
     *
     * @param isShowBack
     * @param titleRes
     */
    public void setTitle(boolean isShowBack, @StringRes int titleRes) {
        setTitle(isShowBack, null, mActivity.getString(titleRes));
    }

    /**
     * 显示关闭按钮
     */
    public void showClose(View.OnClickListener listener) {
        mClose.setVisibility(View.VISIBLE);
        if (listener != null) {
            mClose.setOnClickListener(listener);
        } else {
            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        }
    }

    /**
     * 隐藏关闭按钮
     */
    public void hintClose() {
        mClose.setVisibility(View.GONE);
    }

    public void setLiftTitle(int DrawableId, View.OnClickListener listener) {
        Drawable left = ContextCompat.getDrawable(mActivity, DrawableId);
        left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
        mLeft.setCompoundDrawables(left, null, null, null);
        if (listener != null) {
            mLeft.setOnClickListener(listener);
        } else {
            mLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        }
    }

    /**
     * 设置右边文字
     *
     * @param right
     * @param rightOnClick
     */
    public void setRightTitle(String right, View.OnClickListener rightOnClick) {
        mRight.setText(right);
        if (rightOnClick == null) {
            mRight.setClickable(false);
            return;
        }
        mRight.setOnClickListener(rightOnClick);
    }

    public void setRightVisiable(boolean v) {
        if (v){
            mRight.setVisibility(View.VISIBLE);
        }else{
            mRight.setVisibility(View.GONE);
        }
    }
    public void setLeftVisiable(boolean v) {
        if (v){
            mLeft.setVisibility(View.VISIBLE);
        }else{
            mLeft.setVisibility(View.GONE);
        }
    }


    /**
     * 设置右边文字
     *
     * @param right
     * @param rightOnClick
     */
    public void setRightTitle(SpannableStringBuilder right, View.OnClickListener rightOnClick) {
        mRight.setText(right);
        if (rightOnClick == null) {
            mRight.setClickable(false);
            return;
        }
        mRight.setOnClickListener(rightOnClick);
    }

    /**
     * 设置标题右边图片
     *
     * @param rightRes
     * @param rightOnClick
     */
    @SuppressLint("ResourceType")
    public void setRightTitle(@DrawableRes int rightRes, View.OnClickListener rightOnClick) {
        if (rightRes > 0) {
            Drawable img = ContextCompat.getDrawable(mActivity, rightRes);
            img.setBounds(0, 0, Tool.dip2px(mActivity, 22), Tool.dip2px(mActivity, 22));
            mRight.setCompoundDrawables(img, null, null, null);
        } else {
            mRight.setCompoundDrawables(null, null, null, null);
        }
        if (rightOnClick != null) {
            mRight.setOnClickListener(rightOnClick);
        } else {
            mRight.setClickable(false);
        }
    }

    public DrawableCenterTextView getLeft() {
        return mLeft;
    }

    public DrawableCenterTextView getRight() {
        return mRight;
    }

    public void setBackgroundColor(int color) {
        if (toolLay != null) {
            toolLay.setBackgroundColor(ContextCompat.getColor(mActivity, color));
        }
    }

    public void setTitleColor(int color) {
        if (toolLay != null) {
            mTitle.setTextColor(ContextCompat.getColor(mActivity, color));
        }
    }

    /**
     * 设置右边文字
     *
     * @param color
     *
     */
    public void setRightTitleColor(int color) {
        if (toolLay != null) {
            mRight.setTextColor(ContextCompat.getColor(mActivity, color));
        }
    }
}
