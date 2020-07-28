package com.fm.designstar.utils.image;

import android.content.Context;

import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/20 17:09
 * @update : 2018/8/20
 */
public class RequestOptionsUtil {



    public static RequestOptions getRoundedOptions(Context mContext) {
        //设置图片并且去除换成防止下次加载显示上张图片禁用磁盘缓存
        return new RequestOptions()
                .centerCrop()
                .transform(new RoundedTransformation(mContext))
                .fallback(R.mipmap.ico_default)
                .placeholder(R.mipmap.ico_default)
                .error(R.mipmap.ico_default);
    }

    public static RequestOptions getRoundedOptions(Context mContext, int dp) {
        //设置图片并且去除换成防止下次加载显示上张图片禁用磁盘缓存
        return new RequestOptions()
                .centerCrop()
                .transform(new RoundedTransformation(mContext, dp))
                .fallback(R.mipmap.ico_default)
                .placeholder(R.mipmap.ico_default)
                .error(R.mipmap.ico_default);
    }

    public static RequestOptions getRoundedOptionsErr(Context mContext) {
        //设置图片并且去除换成防止下次加载显示上张图片禁用磁盘缓存
        return new RequestOptions()
                .centerCrop()
                .transform(new RoundedTransformation(mContext));
    }

    public static RequestOptions getRoundedOptionsErr(Context mContext, int mipmap) {
        //设置图片并且去除换成防止下次加载显示上张图片禁用磁盘缓存
        return new RequestOptions()
                .centerCrop()
                .transform(new RoundedTransformation(mContext))
                .fallback(mipmap)
                .placeholder(mipmap)
                .error(mipmap);
    }



    public static RequestOptions getRoundedOptionsErr(Context mContext, int dp, int mipmap) {
        //设置图片并且去除换成防止下次加载显示上张图片禁用磁盘缓存
        return new RequestOptions()
                .centerCrop()
                .transform(new RoundedTransformation(mContext, dp))
                .fallback(mipmap)
                .placeholder(mipmap)
                .error(mipmap);
    }

    public static RequestOptions getOptionsErr(int mipmap) {
        //设置图片并且去除换成防止下次加载显示上张图片禁用磁盘缓存
        return new RequestOptions()
                .centerCrop()
                .fallback(mipmap)
                .placeholder(mipmap)
                .error(mipmap);
    }

    public static RequestOptions getCircleOptionsErr(Context mContext, int mipmap) {
        //设置图片并且去除换成防止下次加载显示上张图片禁用磁盘缓存
        return new RequestOptions()
                .centerCrop()
                .transform(new CircleTransform(mContext))
                .fallback(mipmap)
                .placeholder(mipmap)
                .error(mipmap);
    }
}
