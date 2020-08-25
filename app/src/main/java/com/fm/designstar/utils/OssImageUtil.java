package com.fm.designstar.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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


    /**
     * 获取服务器上的图片尺寸
     */
    public static int[] getImgWH(String urls) throws Exception {
        Log.e("qsd","qqq"+urls);
        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.connect();
        InputStream is = conn.getInputStream();
        Bitmap image = BitmapFactory.decodeStream(is);

        int srcWidth = image.getWidth();      // 源图宽度
        int srcHeight = image.getHeight();    // 源图高度
        int[] imgSize = new int[2];
        imgSize[0] = srcWidth;
        imgSize[1] = srcHeight;
        Log.e("qsd","qqq"+imgSize[0]+"==="+imgSize[1]);

        //释放资源
        image.recycle();
        is.close();
        conn.disconnect();

        return imgSize;

    }
    }
