# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5

# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses

    -keep class com.amap.api.location.**{*;}
    -keep class com.amap.api.fence.**{*;}
    -keep class com.loc.**{*;}
    -keep class com.autonavi.aps.amapapi.model.**{*;}


    -keep   class com.amap.api.services.**{*;}
    # 极光推送
    -dontoptimize
    -dontpreverify

    -dontwarn cn.jpush.**
    -keep class cn.jpush.** { *; }
    -keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

    -dontwarn cn.jiguang.**
    -keep class cn.jiguang.** { *; }
    -dontwarn com.xiaomi.mipush.sdk.**
    -keep public class com.xiaomi.mipush.sdk.* {*; }
    -ignorewarnings

    #crashreporter
    -keep class com.alibaba.motu.crashreporter.MotuCrashReporter{*;}
    -keep class com.alibaba.motu.crashreporter.ReporterConfigure{*;}
    -keep class com.alibaba.motu.crashreporter.IUTCrashCaughtListener{*;}
    -keep class com.ut.mini.crashhandler.IUTCrashCaughtListener{*;}
    -keep class com.alibaba.motu.crashreporter.utrestapi.UTRestReq{*;}
    -keep class com.alibaba.motu.crashreporter.handler.nativeCrashHandler.NativeCrashHandler{*;}
    -keep class com.alibaba.motu.crashreporter.handler.nativeCrashHandler.NativeExceptionHandler{*;}
    -keep interface com.alibaba.motu.crashreporter.handler.nativeCrashHandler.NativeExceptionHandler{*;}
    #crashreporter3.0以后 一定要加这个
    -keep class com.uc.crashsdk.JNIBridge{*;}

    #防止混淆AliRtcSDK公共类名称
    -keep class com.serenegiant.**{*;}
    -keep class org.webrtc.**{*;}

    ####################友盟数据统计############
    -keep class com.umeng.** {*;}
    -keepclassmembers class * {
       public <init> (org.json.JSONObject);
    }
    -keepclassmembers enum * {
        public static **[] values();
        public static ** valueOf(java.lang.String);
    }
    ######################conan混淆配置#########################
    -keepattributes *Annotation*
    -keep class com.alivc.conan.DoNotProguard
    -keep,allowobfuscation @interface com.alivc.conan.DoNotProguard
    -keep @com.alivc.conan.DoNotProguard class *
    -keepclassmembers class * {
        @com.alivc.conan.DoNotProguard *;
    }
    ######################短视频混淆配置#########################
    -keep class com.aliyun.**{*;}
    -keep class com.duanqu.**{*;}
    -keep class com.qu.**{*;}
    -keep class com.alibaba.**{*;}
    -keep class component.alivc.**{*;}
    -keep class com.alivc.**{*;}
    -keep enum com.aliyun.editor.AudioEffectType { *; }
