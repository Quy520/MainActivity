package com.fm.designstar.utils;

import java.util.regex.Pattern;

/**
 *正则表达式定义规则
 * @author yinsujun
 * @date 2017/10/24
 */

public class PatternUtil {
    /**
     * 特殊字符正则
     */
    public final static Pattern SPECIAL_CHARACTERS= Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
    /**
     * 是否全数字
     */
    public final static Pattern DIGIT = Pattern.compile("[0-9]*");
    /**
     * 定义判别用户身份证号的正则表达式（要么是15位或18位，最后一位可以为字母）
     */
    public final static Pattern ID_CARD = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
    /**
     * java默认的为贪婪匹配模式
     */
    public final static Pattern NUMBER_PATTERN = Pattern.compile("[0-9.]{4,6}");
    /**
     * 手机号
     */
    public final static Pattern MOBILE_PATTERN = Pattern.compile("^1[0-9]{10}$");



}
