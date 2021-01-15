package com.fm.designstar.views.main.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.PermissionsListener;
import com.fm.designstar.utils.StatusBarUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TitleUtil;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.login.activitys.LoginActivity;

import org.greenrobot.eventbus.EventBus;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 18:56
 * @update : 2018/9/21
 */
public class WebActivity extends AppCompatActivity {
    /**
     * 地理位置 articleId
     */
    private static final int GET_POI_REQUEST_CODE = 1111;
    private AppCompatActivity mActivity;
    @BindView(R.id.toolLay)
    RelativeLayout toolLay;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.tv_tag_content)
    TextView mTvTagContent;
    @BindView(R.id.dialog_view)
    LinearLayout mDialogView;
    @BindView(R.id.progressbar)
    ProgressBar mProgressBar;
    protected TitleUtil mTitle;


    private String title;
    private String mUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        ButterKnife.bind(this);
        mActivity = this;
        mTitle = new TitleUtil(this, getWindow().getDecorView());
        StatusBarUtil.immersive(this);
        loadData();
    }


    public int getLayoutId() {
        return R.layout.act_main_web;
    }


    @SuppressLint("JavascriptInterface")
    public void loadData() {
        if (getIntent() != null) {
            if (!StringUtil.isBlank(getIntent().getStringExtra("title"))) {
                title = getIntent().getStringExtra("title");
                //层垫式状态栏
                mTitle.setTitle(title);
                toolbar.setVisibility(View.VISIBLE);
            } else {
//                StatusBarUtil.setStatusBarColor(mActivity, R.color.statusBar);
                toolbar.setVisibility(View.GONE);
                toolLay.getLayoutParams().height = Util.getStatusBarH(mActivity);
                toolLay.setBackground(null);
                toolLay.setBackgroundColor(Color.WHITE);
                StatusBarUtil.setAndroidNativeLightStatusBar(mActivity, true);
            }
            Log.e("qsd","传递过来的参数"+getIntent().getStringExtra("url"));
            if (!StringUtil.isBlank(getIntent().getStringExtra("url"))) {
                //该链接是为了提额的改动
                mUrl = getIntent().getStringExtra("url");
            }
        }

        mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        //WebView属性设置！！！
        WebSettings settings = mWebView.getSettings();

        settings.setBlockNetworkImage(false);//解决图片不显示
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setTextZoom(100); // 通过百分比来设置文字的大小，默认值是100

// 设置可以支持缩放
        settings.setSupportZoom(true);
// 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
//扩大比例的缩放
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        settings.setAllowFileAccessFromFileURLs(false);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        settings.setAllowUniversalAccessFromFileURLs(false);
        //webview在安卓5.0之前默认允许其加载混合网络协议内容
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.addJavascriptInterface(new JavaMethod(), "nativeMethod");
        mWebView.setDownloadListener(new MyWebViewDownLoadListener());
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                Log.e("qsd","onJsAlert");
                return super.onJsAlert(view, url, message, result);
            }
        });
       // mWebView.setWebChromeClient(new MyWebChromeClient());
        Log.e("qsd","111加载url"+mUrl);
        mWebView.loadUrl(mUrl);

    }



    public class JavaMethod {

        @JavascriptInterface
        public void finish() {
            mActivity.finish();
        }

        @JavascriptInterface
        public void goToLogin() {
            startActivity(LoginActivity.class);
        }



    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.cancel(); // Android默认的处理方式
           // handler.proceed();  // 接受所有网站的ssl证书  解决https拦截问题
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e("qsd","onPageStarted加载url"+url);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(final WebView view, String url) {
            super.onPageFinished(view, url);

            mProgressBar.setVisibility(View.GONE);
            Log.e("qsd","onPageFinished加载url"+url);
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (App.loadingIsShowing()) {
            App.hideLoading();
        }
        if (resultCode != RESULT_OK) {

            return;
        }


    }



    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.activity_start_open, R.anim.activity_start_close);
    }

    private PermissionsListener mListener;

    /**
     * 请求权限封装
     *
     * @param permissions
     * @param listener
     */
    public void requestPermissions(String[] permissions, PermissionsListener listener) {
        mListener = listener;
        List<String> requestPermissions = new ArrayList<>();
        for (String permission : permissions) {
            try {
                if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions.add(permission);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        if (!requestPermissions.isEmpty() && (Build.VERSION.SDK_INT >= 23)) {
            ActivityCompat.requestPermissions(this, requestPermissions.toArray(new String[requestPermissions.size()]), 1);
        } else {
            if (mListener != null) {
                mListener.onGranted();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                List<String> deniedPermissions = new ArrayList<>();
                //当所有拒绝的权限都勾选不再询问，这个值为true,这个时候可以引导用户手动去授权。
                boolean isNeverAsk = true;
                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    String permission = permissions[i];
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        deniedPermissions.add(permissions[i]);
                        // 点击拒绝但没有勾选不再询问
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                            isNeverAsk = false;
                        }
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    try {
                        if (mListener != null) {
                            mListener.onGranted();
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        if (mListener != null) {
                            mListener.onDenied(Arrays.asList(permissions), true);
                        }
                    }
                } else {
                    if (mListener != null) {
                        mListener.onDenied(deniedPermissions, isNeverAsk);
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        mWebView.clearCache(true);
        mWebView.destroy();
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void onBackPressed() {

        finish();
        Log.i("qsd", "onBackPressed");
    }
}
