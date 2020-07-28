package com.fm.designstar.utils;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/3/19 13:17
 * @update : 2018/3/19
 */

public class OssImageUtil {
    public static String getThumbnailCut(String url, int h, int w) {
        if (StringUtil.isBlank(url)) {
            return "";
        }
        if (url.contains("aliyuncs.com")) {
            if (!url.contains("?")) {
                url = url + "?x-oss-process=image/resize,m_fill,h_" + h + ",w_" + w + ",limit_0,p_50";
            }
        }
        return url;
    }
}
