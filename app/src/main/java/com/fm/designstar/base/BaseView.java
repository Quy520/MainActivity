package com.fm.designstar.base;

/**
 * baseview
 * @author DELL
 */
public interface BaseView {
    /*******内嵌加载*******/
    /**
     * 开始加载dialog
     *
     * @param content dialog显示的内容
     */
    void showLoading(String content, int code);

    /**
     * 停止加载dialog
     */
    void stopLoading(int code);

    /**
     * 请求失败
     *
     * @param msg  请求异常信息
     * @param code 若有多个请求，用于区分不同请求（不同请求失败或有不同的处理）
     *             PS：无需区分则可传null
     */
    void showErrorMsg(String msg, int code);


}
