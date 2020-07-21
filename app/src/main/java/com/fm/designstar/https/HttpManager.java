package com.fm.designstar.https;

import android.text.TextUtils;

import com.fm.designstar.app.App;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Retrofit请求管理类<p>
 *
 * @author DELL
 */
public class HttpManager {

    private HttpApi mHttpApi;

    private static HttpManager instance = null;

    /**
     * 获取单例
     *
     * @return 实例
     */
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public static HttpApi getApi() {
        return getInstance().mHttpApi;
    }

    private HttpManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.getConfig().getBaseUrl())
                .client(createOkHttpClient())
                //.addConverterFactory(ScalarsConverterFactory.create()) 返回类型转成String
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mHttpApi = retrofit.create(HttpApi.class);
    }

    public void setBaseUrl() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(App.getConfig().getBaseUrl())
                .client(createOkHttpClient())
//                .addConverterFactory(ScalarsConverterFactory.create()) //返回类型转成String
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mHttpApi = retrofit.create(HttpApi.class);
    }

    public OkHttpClient createOkHttpClient() {
        final Cache cache = new Cache(new File(App.getContext().getCacheDir(), "ReviewCache"),
                1024 * 1024 * 100);
        //添加全局统一请求头
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                /*处理body*/
//                if (originalRequest.body() != null) {
//                    final Buffer buffer = new Buffer();
//                    originalRequest.body().writeTo(buffer);
//                    MediaType mediaType = originalRequest.body().contentType();
//                    String content = buffer.readUtf8();
//                    JsonParser parser = new JsonParser();
//                    JsonObject jsonObject = parser.parse(content).getAsJsonObject();
//                    if (jsonObject.has("to_commit")) {
//                        content = jsonObject.get("to_commit").getAsString();
//                        builder.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//                        mediaType = MediaType.parse("Content-Type: application/x-www-form-urlencoded;charset=utf-8");
//                    }
//                    RequestBody requestBody = RequestBody.create(mediaType, content);
//                    builder.method(originalRequest.method(), requestBody);
//                } else {
//                    builder.method(originalRequest.method(), originalRequest.body());
//                }
                builder.header("Content-Type", "application/json;charset=utf-8");
                return chain.proceed(builder.build());
            }
        };
//        Interceptor showDialogInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                String showDialog = request.header("showDialog");
//                Response response = chain.proceed(chain.request());
//                String body = response.body().string();
//                JSONObject jsonResponse = null;
//                try {
//                    jsonResponse = new JSONObject(body);
//                    String code = jsonResponse.getString("code");
//                    //需与服务器协商,返回-3
//                    if ("-3".equals(code)) {
//                        if (TextUtils.isEmpty(showDialog) || !("true").equals(showDialog)) {
//                            jsonResponse.put("code", "-1");
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                return response.newBuilder().body(ResponseBody.create(MediaType.parse("UTF-8"), jsonResponse.toString())).build();
//            }
//        };
        //日志拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.t("http").e(message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //CookieManager管理器
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                //缓存
                .cache(cache)
                .addInterceptor(headerInterceptor)
//                .addInterceptor(showDialogInterceptor)
                //设置持续化cookie
                .cookieJar(new JavaNetCookieJar(cookieManager))
                //打印日志
                .addInterceptor(logging)
                //失败重连
                .retryOnConnectionFailure(true)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
        return mOkHttpClient;
    }

    /**
     * 给url添加全局统一请求参数信息
     *
     * @param url
     * @return
     */
    public static String getUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }

            return url.replace(" ", "");

    }

    /**
     * 处理线程调度
     *
     * @param <T>
     * @return
     */
    public <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io());
            }
        };
    }
}
