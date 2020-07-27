package com.fm.designstar.views.mine.presenter;

import android.util.Log;


import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.CannedAccessControlList;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.views.mine.contract.UploadFileContract;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.alibaba.sdk.android.oss.common.RequestParameters.SUBRESOURCE_ACL;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/26 10:55
 * @update : 2018/9/26
 */
public class UploadFilePresenter extends BasePresenter<UploadFileContract.View> implements UploadFileContract.Presenter {
    public static final int GET_OSS_TOKEN = 5;
    public static final int UPLOAD_IMAGE = 4;
    private String imageBaseUrl = "https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/";
    private String bucketName = "yuxuanlin";
    private String endpoint = "https://oss-cn-shanghai.aliyuncs.com/";

    private String ossCallBack = "oss/callback";

    private OSSAsyncTask task;

    @Override
    public void getOssToken() {
        toSubscribe(HttpManager.getApi().ossToken(App.getConfig().getUserToken()), new AbstractHttpSubscriber<OssTokenResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", GET_OSS_TOKEN);
            }

            @Override
            protected void onHttpNext(OssTokenResponse response) {
                if (response != null) {
                    mView.getOssTokenSuccess(response);
                }
            }

            @Override
            protected void onHttpError(String message) {
                mView.showErrorMsg(message, GET_OSS_TOKEN);
            }

            @Override
            protected void onHttpCompleted() {
                mView.stopLoading(GET_OSS_TOKEN);
            }
        });
    }
    @Override
    public void uploadImage(OssTokenResponse response, final String url) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader("x-oss-object-acl", "public-read");
        //if null , ico_default will be init
        ClientConfiguration conf = new ClientConfiguration();
        // connction time out ico_default 15s
        conf.setConnectionTimeout(30 * 1000);
        // socket timeout，ico_default 15s
        conf.setSocketTimeout(30 * 1000);
        // synchronous request number，ico_default 5
        conf.setMaxConcurrentRequest(5);
        // retry，ico_default 2
        conf.setMaxErrorRetry(2);

//        OSSLog.enableLog(); //write local log file ,path is SDCard_path\OSSLog\logs.csv
         imageBaseUrl = "https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/";
         bucketName = "yuxuanlin";
        endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(response.getAccessKeyId(), response.getAccessKeySecret(), response.getSecurityToken());
        OSS oss = new OSSClient(App.getContext(), endpoint, credentialProvider, conf);
        final String uid = App.getConfig().getLoginStatus() ? App.getConfig().getUserToken() + "" : "";
        final String urlType = url.substring(url.lastIndexOf("."), url.length());
        final String objectKey = "1-1-" + uid + "-" + (System.currentTimeMillis() / 1000) + "-" + (int) ((Math.random() * 9 + 1) * 100000) + urlType;
        // Construct an upload request
        PutObjectRequest put = new PutObjectRequest(bucketName, objectKey, url,metadata);

        // You can set progress callback during asynchronous upload
//        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
//            @Override
//            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                Log.e("123", "currentSize: " + currentSize + " totalSize: " + totalSize);
//            }
//        });
//        if (App.getConfig().getBaseUrl().contains("https://api.pinjamduit.co.id")) {
//            put.setCallbackParam(new HashMap<String, String>() {
//                {
//                    put("callbackUrl", App.getConfig().getBaseUrl() + ossCallBack);
//                    put("callbackBodyType", "application/json");
//                    JSONObject object = new JSONObject();
//                    try {
//                        object.put("bucket", bucketName);
//                        object.put("object", objectKey);
//                        if (MediaFileUtil.isImageFileType(url)) {
//                            String mUrlType = urlType.replace(".", "");
//                            object.put("mimeType", "image/" + mUrlType);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    put("callbackBody", object.toString());
//                }
//            });
//        }
        task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(final PutObjectRequest request, PutObjectResult result) {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(request.getObjectKey());
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mView.uploadImageSuccess(imageBaseUrl + s);
                                mView.stopLoading(UPLOAD_IMAGE);
                            }
                        });
            }

            @Override
            public void onFailure(PutObjectRequest request, final ClientException clientExcepion, final ServiceException serviceException) {
                Observable.create(new Observable.OnSubscribe<Exception>() {
                    @Override
                    public void call(Subscriber<? super Exception> subscriber) {
                        Exception e = serviceException;
                        if (e == null) {
                            e = clientExcepion;
                        }
                        if (e == null) {
                            e = new Exception("上传失败");
                        }
                        subscriber.onNext(e);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io()).
                        subscribe(new Action1<Exception>() {
                            @Override
                            public void call(Exception s) {
                                Log.e("123", s.getMessage());
                                if (s.getMessage().contains("上传取消")) {
                                    mView.showErrorMsg("上传失败", 5);
                                    return;
                                }
                                mView.showErrorMsg(s.getMessage(), UPLOAD_IMAGE);
                            }
                        });
            }
        });
    }

    public void cancel() {
        try {
            if (task != null) {
                task.cancel();
            }
            mView.showErrorMsg("上传失败", 5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
