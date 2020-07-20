package com.fm.designstar.model.server;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import androidx.annotation.NonNull;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 11:28
 * @update : 2018/9/21
 */
public class BaseBody {
    @SerializedName("check")
    private String check;


    public void setCheck(String check) {
        this.check = check;
    }



    /**
     * 计算摘要
     */
    public void generateCheck() {
        String queryParams = concat(Object2Map(this));
        Log.e("123", queryParams);
        String md5 = "";
        if (queryParams.length() > 0) {
           // md5 = digest(queryParams);
            queryParams = String.format(Locale.CHINA, "%s&%s=%s", queryParams, "check", md5);
        }
  //      setCheck(md5);
     //  setToCommit(queryParams);
    }

    /*@NonNull
    private static String digest(@NonNull String text) {
        return DigestUtil.md5(text);
    }*/

    @NonNull
    private static String concat(TreeMap<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            stringBuilder.append(key).append("=").append(map.get(key)).append("&");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    @NonNull
    private static TreeMap<String, String> Object2Map(@NonNull Object o) {
        List<Field> fieldList = getFieldsWithAnnotation(o.getClass(), SerializedName.class);

        TreeMap<String, String> map = new TreeMap<>();
        for (Field field : fieldList) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null) {
                continue;
            }

            SerializedName serializedName = field.getAnnotation(SerializedName.class);
            if (value instanceof String) {
                map.put(serializedName.value(), (String) value);
            } else {
                String json = new Gson().toJson(value);
                if (json.startsWith("\"")) {
                    json = json.substring(1);
                }
                if (json.endsWith("\"")) {
                    json = json.substring(0, json.length() - 1);
                }
                //这里将回车转义字符去除
                //json = json.replaceAll("\\\\n", "\\\n");
                map.put(serializedName.value(), json);//通过gson处理非基本类型
            }
        }
        map.remove("check");//计算摘要前移除
//        map.remove("to_commit");//计算摘要前移除

        return map;
    }


    @NonNull
    private static List<Field> getFieldsWithAnnotation(@NonNull Class mClass,
                                                       @NonNull Class<? extends Annotation> annotationClass) {
        List<Field> fields = new ArrayList<>();

        for (Field field : mClass.getDeclaredFields()) {
            if (field.getAnnotation(annotationClass) != null) {
                fields.add(field);
            }
        }
        if (mClass.getSuperclass() != null) {//基类字段
            fields.addAll(getFieldsWithAnnotation(mClass.getSuperclass(), annotationClass));
        }

        return fields;
    }

    @Override
    public String toString() {
        return "BaseBody{" +
                "check='" + check + '\'' +
//                ", toCommit='" + toCommit + '\'' +
                '}';
    }
}
