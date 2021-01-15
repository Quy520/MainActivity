package com.fm.designstar.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.views.login.activitys.LoginActivity;
import com.fm.designstar.views.main.activity.WebActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.core.content.ContextCompat;

/**
 * class description: 文字处理类
 * date: 2017/10/13 11:23
 *
 * @author DELL
 */
public class TextViewUtil {
    private Context context;

    /**
     * 给TextView设置部分大小
     *
     * @param tv       TextView 组件
     * @param start    字符开始位置
     * @param end      字符结束位置
     * @param textSize 字体大小
     */
    public static void setPartialSize(TextView tv, int start, int end, int textSize) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new AbsoluteSizeSpan(textSize), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    /**
     * 给TextView设置部分颜色
     *
     * @param mContext  Context
     * @param tv        TextView 组件
     * @param start     字符开始位置
     * @param end       字符结束位置
     * @param textColor 字体颜色ID
     */
    public static void setPartialColor(Context mContext, TextView tv, int start, int end, int textColor) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        if (start < end) {
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, textColor)), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(spannable);
    }

    /**
     * 设置不同字体颜色
     */
    public static void setPartialColors2(TextView textView, String str, int[] start, int[] end, int colorResId) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        for (int i = 0; i < start.length; i++) {
            ForegroundColorSpan spanColor = new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), colorResId));
            builder.setSpan(spanColor, start[i], end[i], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(builder);
    }

    /**
     * 设置不同字体颜色
     */
    public static void setPartialColors(Context mContext, TextView textView, String str, int color) {

        SpannableStringBuilder spannableString = new SpannableStringBuilder(str.replace(" ", ""));
        spannableString.setSpan(new TextAgreementClick(), 13, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextPrivacyClick(), 22, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置点击事件，加上这句话才有效果
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        //设置点击后的颜色为透明（有默认背景）
        textView.setHighlightColor(mContext.getResources().getColor(color));
        textView.setText(spannableString);


    }  /**
     * 设置不同字体颜色
     */
    public static void setPartialColors3(Context mContext, TextView textView, String str, int color) {

        SpannableStringBuilder spannableString = new SpannableStringBuilder(str.replace(" ", ""));
        spannableString.setSpan(new TextAgreementClick(), 40, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextPrivacyClick(), 47, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置点击事件，加上这句话才有效果
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        //设置点击后的颜色为透明（有默认背景）
        textView.setHighlightColor(mContext.getResources().getColor(color));
        textView.setText(spannableString);


    }
    static class TextAgreementClick extends ClickableSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            //设置文本的颜色
            ds.setColor(App.getContext().getResources().getColor(R.color.notice));
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            // 执行点击逻辑
            Intent intent  = new Intent(App.getContext(),WebActivity.class);
            Bundle bundle3 = new Bundle();
            bundle3.putString("url", "https://cde.laifuyun.com/userLicenseAgreement.html");

            intent.putExtras(bundle3);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            App.getContext(). startActivity(intent);

        }
    }
    static class TextPrivacyClick extends ClickableSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            //设置文本的颜色
            ds.setColor(App.getContext().getResources().getColor(R.color.notice));
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            // 执行点击逻辑
            Intent intent  = new Intent(App.getContext(),WebActivity.class);
            Bundle bundle3 = new Bundle();
           bundle3.putString("url", "https://cde.laifuyun.com/privacyPolicy.html");
            //bundle3.putString("url", "http://120.27.163.36:8686/m/Dingding/Order/OrderManage.aspx?empId=1637C94DCB827E27&isApp=1");

            intent.putExtras(bundle3);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            App.getContext(). startActivity(intent);
        }
    }
    /**
     * 给TextView设置部分样式
     *
     * @param tv       TextView 组件
     * @param start    字符开始位置
     * @param end      字符结束位置
     * @param typeface 字体样式
     */
    public static void setPartialTypeface(TextView tv, int start, int end, int typeface) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new StyleSpan(typeface), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //给TextView设置部分字体大小和颜色
    public static void setPartialSizeAndColor(TextView tv, int start, int end, int textSize, int textColor) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new AbsoluteSizeSpan(textSize, false), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(textColor), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }    //给TextView设置部分字体大小和颜色

    //给TextView设置下划线
    public static void setUnderLine(TextView tv) {
        if (tv.getText() != null) {
            String udata = tv.getText().toString();
            SpannableString content = new SpannableString(udata);
            content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
            tv.setText(content);
            content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
        } else {
            tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        }
    }

    /**
     * 取消TextView的置下划线
     *
     * @param tv TextView组件
     */
    public static void clearUnderLine(TextView tv) {
        tv.getPaint().setFlags(0);
    }

    //半角转换为全角
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str 需要处理字符串
     * @return
     */
    public static String replaceCharacter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!")
                .replaceAll("：", ":").replaceAll("（", "(").replaceAll("（", ")");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
