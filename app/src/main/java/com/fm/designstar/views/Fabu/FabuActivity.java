package com.fm.designstar.views.Fabu;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliyun.apsara.alivclittlevideo.activity.AlivcLittlePreviewActivity;
import com.aliyun.apsara.alivclittlevideo.constants.LittleVideoParamConfig;
import com.aliyun.svideo.common.utils.PermissionUtils;
import com.aliyun.svideo.editor.EditorMediaActivity;
import com.aliyun.svideo.editor.bean.AlivcEditInputParam;
import com.aliyun.svideo.recorder.activity.AlivcSvideoRecordActivity;
import com.aliyun.svideo.recorder.bean.AlivcRecordInputParam;
import com.aliyun.svideo.recorder.bean.RenderingMode;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.PermissionsListener;
import com.fm.designstar.dialog.ChoseDialogUtil;
import com.fm.designstar.events.ImageDeleteEvent;
import com.fm.designstar.events.UploadzpEvent;
import com.fm.designstar.map.Selectaddress;
import com.fm.designstar.model.bean.MultimediabodyBean;
import com.fm.designstar.model.bean.TagsInfoVoBean;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.utils.GlideLoader;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Fabu.activity.ChoseScrollTagActivity;
import com.fm.designstar.views.Fabu.activity.EditVedioActivity;
import com.fm.designstar.views.Fabu.adapter.ReviewPhotoAdapter;
import com.fm.designstar.views.Fabu.adapter.ReviewVedioAdapter;
import com.fm.designstar.views.Fabu.contract.UploadContract;
import com.fm.designstar.views.Fabu.presenter.UploadPresenter;
import com.fm.designstar.views.main.adapter.StffReviewGroupAdapter;
import com.fm.designstar.views.mine.contract.UploadFileContract;
import com.fm.designstar.views.mine.presenter.UploadFilePresenter;
import com.fm.designstar.widget.imagePicker.ImagePicker;
import com.fm.designstar.widget.imagePicker.data.MediaFile;
import com.fm.designstar.widget.viegroup.MyViewGroup;
import com.google.gson.Gson;
import com.hw.videoprocessor.VideoProcessor;
import com.hw.videoprocessor.util.VideoProgressListener;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.aliyun.svideo.editor.publish.UploadActivity.KEY_UPLOAD_THUMBNAIL;
import static com.aliyun.svideo.editor.publish.UploadActivity.KEY_UPLOAD_h;
import static com.aliyun.svideo.editor.publish.UploadActivity.KEY_UPLOAD_w;
import static io.rong.common.LibStorageUtils.MEDIA;

public class FabuActivity extends BaseActivity<UploadPresenter> implements UploadContract.View, UploadFileContract.View {
    public static final String SELECT_RESULT = "SELECT_RESULT";
    public static final String KEY_PARAM_CONFIG = "project_json_path";
    public static final String KEY_PARAM_THUMBNAIL = "svideo_thumbnail";
    public static final String KEY_PARAM_DESCRIBE = "svideo_describe";
    public static final String KEY_PARAM_VIDEO_RATIO = "key_param_video_ratio";
    private static final String KEY_PARAM_VIDEO_PARAM = "videoParam";
    public static final String SELECT_RESULT2 = "SELECT_RESULT2";
    public static final String KEY_UPLOAD_VIDEO = "video_path";
    public static final String KEY_UPLOAD_THUMBNAIL = "video_thumbnail";
    public static final String KEY_UPLOAD_DESC = "video_desc";

    private static final int PERMISSION_REQUEST_CODE = 1001;

    @BindView(R.id.re_title)
    RelativeLayout re_title;
    @BindView(R.id.photoRecycler)
    RecyclerView photoRecycler;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.huati)
    TextView huati;
    @BindView(R.id.zb)
    TextView zb;
    @BindView(R.id.delete)
    ImageView delete;
    @BindView(R.id.myViewGroup)
    MyViewGroup myViewGroup;
    private String address;
    long latitude ;//获取纬度
    long longitude;
    private int mediatype=0;
    private int upPosition=0;
    private UploadFilePresenter uploadFilePresenter;
    private OssTokenResponse ossTokenResponse;
    List<MultimediabodyBean> multimediaList;
    MultimediabodyBean multimediaListBean=new MultimediabodyBean();
    List<TagsInfoVoBean> tagsList;
    private ReviewPhotoAdapter photoAdapter;
    private ReviewVedioAdapter photoAdapter2;
    public static final int SELECT_CODE = 100;
    public static final int SELECT_CODE2 = 1002;
    public static final int CHOOSE_HANDYMAN = 101;
    public static final int CHOOSE_TAG = 102;
    int outHeight=680;
    int outWidth= 360;
    int bianji= 0;
    private int type;
    MediaFile mediaFile;

    List<String > strings=new ArrayList<>();
    String destPath;
    private ChoseDialogUtil choseDialogUtil;
    String[] permission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private String mVideoPath;
    private String mThumbnailPath;
    private String mThumbnailPath2;
    private String mDesc;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fabu;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        uploadFilePresenter=new UploadFilePresenter();
        uploadFilePresenter.init(this);
    }

    @Override
    public void loadData() {
        destPath  = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator + "VID_" + new SimpleDateFormat("yyyyMMdd_HHmmss", getLocale()).format(new Date()) + ".mp4";

        type=getIntent().getIntExtra("type",1);
        //mThumbnailPath = getIntent().getStringExtra(KEY_UPLOAD_THUMBNAIL);
        mediaFile=(MediaFile)getIntent().getSerializableExtra(MEDIA);
        re_title.getLayoutParams().height = Tool.dip2px(mContext, 34) + Util.getStatusBarH(mContext);
        ((ViewGroup.MarginLayoutParams) re_title.getLayoutParams()).topMargin = Util.getStatusBarH(mContext);
        photoRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        photoRecycler.addItemDecoration(new SpaceItemDecoration().setRight(Tool.dip2px(mContext, 5)).setBottom(Tool.dip2px(mContext, 8)));
        photoAdapter = new ReviewPhotoAdapter(mContext);
        photoAdapter2 = new ReviewVedioAdapter(mContext);
        if (type==1){
            photoRecycler.setAdapter(photoAdapter);

            huati.setVisibility(View.GONE);
        }else {
            huati.setVisibility(View.GONE);

            ImagePicker.getInstance()
                    .setTitle("标题")//设置标题
                    .showCamera(true)//设置是否显示拍照按钮
                    .showImage(false)//设置是否展示图片
                    .showVideo(true)//设置是否展示视频
                    .filterGif(true)//设置是否过滤gif图片
                    .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                    .setSingleType(true)//设置图片视频不能同时选择
                    .setImagePaths(null)//设置历史选择记录
                    .setImageLoader(new GlideLoader())//设置自定义图片加载器
                    .start(mActivity, SELECT_CODE,2);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
            photoRecycler.setAdapter(photoAdapter2);
            huati.setVisibility(View.VISIBLE);

        }

        photoAdapter.setListener(new ReviewPhotoAdapter.AddPhotoListener() {
            @Override
            public void addPhoto() {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, mListener);
            }
        });
        photoAdapter2.setListener(new ReviewVedioAdapter.AddPhotoListener() {
            @Override
            public void addPhoto() {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, mListener);
            }

            @Override
            public void pre() {
                if (type==1){
                  //  startPreview();

                }
            }

            @Override
            public void toEDIT(int p) {
                Intent intent=  new Intent(mContext, EditVedioActivity.class);
                intent.putExtra("path",mediaFile.getPath());
                intent.putExtra("url",photoAdapter2.getData().get(p));
                startActivityForResult(intent, SELECT_CODE2);
            }
        });
       latitude= SpUtil.getLong("latitude");
        longitude= SpUtil.getLong("longitude");

    }
    private PermissionsListener mListener = new PermissionsListener() {
        @Override
        public void onGranted() {

          if (type==1){
            ArrayList<String>  d=  photoAdapter.getData();
            if (photoAdapter.getData().size()>0){
                ImagePicker.getInstance()
                        .setTitle("标题")//设置标题
                        .showCamera(true)//设置是否显示拍照按钮
                        .showImage(true)//设置是否展示图片
                        .showVideo(false)//设置是否展示视频
                        .filterGif(true)//设置是否过滤gif图片
                        .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                        .setSingleType(true)//设置图片视频不能同时选择
                        .setImagePaths(photoAdapter.getData())//设置历史选择记录
                        .setImageLoader(new GlideLoader())//设置自定义图片加载器
                        .start(FabuActivity.this, SELECT_CODE,1);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
            }else {
                choseDialogUtil=new ChoseDialogUtil(mContext,mActivity,d);
                choseDialogUtil.setOnClickListener(new ChoseDialogUtil.OnSuccess() {
                    @Override
                    public void success() {
                        // 打开视频录制,短视频sdk，暂时只支持api 18以上的版本
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            jumpToEditor();
                        } else {
                            ToastUtil.showToast("手机版本过低，暂不支持编辑");
                        }

                    }

                    @Override
                    public void success2() {
                        bianji=0;
                    }
                });
                choseDialogUtil.showDialog();
            }



          }else {
              ImagePicker.getInstance()
                      .setTitle("标题")//设置标题
                      .showCamera(true)//设置是否显示拍照按钮
                      .showImage(false)//设置是否展示图片
                      .showVideo(true)//设置是否展示视频
                      .filterGif(true)//设置是否过滤gif图片
                      .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                      .setSingleType(true)//设置图片视频不能同时选择
                      .setImagePaths(photoAdapter2.getData())//设置历史选择记录
                      .setImageLoader(new GlideLoader())//设置自定义图片加载器
                      .start(FabuActivity.this, SELECT_CODE,2);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode

          }

        }


        @Override
        public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {

        }
    };
    private void jumpToRecorder() {
        final AlivcRecordInputParam recordInputParam = new AlivcRecordInputParam.Builder()
                .setResolutionMode(LittleVideoParamConfig.Recorder.RESOLUTION_MODE)
                .setRatioMode(LittleVideoParamConfig.Recorder.RATIO_MODE)
                .setMaxDuration(LittleVideoParamConfig.Recorder.MAX_DURATION)
                .setMinDuration(LittleVideoParamConfig.Recorder.MIN_DURATION)
                .setVideoQuality(LittleVideoParamConfig.Recorder.VIDEO_QUALITY)
                .setGop(LittleVideoParamConfig.Recorder.GOP)
                .setVideoCodec(LittleVideoParamConfig.Recorder.VIDEO_CODEC)
                .setVideoRenderingMode(RenderingMode.Race)
                .build();
        AlivcSvideoRecordActivity.startRecord(this, recordInputParam);
    }
    /**
     * 跳转到视频编辑 在{@link LittleVideoParamConfig.Editor} 中进行配置
     */
    private void jumpToEditor() {
        boolean checkResult = PermissionUtils.checkPermissionsGroup(this, permission);
        if (!checkResult) {
            PermissionUtils.requestPermissions(this, permission, PERMISSION_REQUEST_CODE);
            return;
        }
        AlivcEditInputParam param = new AlivcEditInputParam.Builder()
                .setRatio(LittleVideoParamConfig.Editor.VIDEO_RATIO)
                .setScaleMode(LittleVideoParamConfig.Editor.VIDEO_SCALE)
                .setVideoQuality(LittleVideoParamConfig.Editor.VIDEO_QUALITY)
                .setFrameRate(LittleVideoParamConfig.Editor.VIDEO_FRAMERATE)
                .setGop(LittleVideoParamConfig.Editor.VIDEO_GOP)
                .build();
        EditorMediaActivity.startImport(this, param);

    }
    @OnClick({R.id.close,R.id.delete,R.id.zb,R.id.huati,R.id.fabu})
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.fabu:

                if (photoAdapter.getData().size()==0&& StringUtil.isBlank(editText.getText().toString())&&photoAdapter2.getData().size()==0){
                    return;
                }
                if (photoAdapter.getData().size()==0&&photoAdapter2.getData().size()==0){
                    mediatype=0;
                }

                if (type==1){
                    if (photoAdapter2.getData().size()>0){
                        if (mediaFile!=null){
                            if (mediaFile.getDuration()>60900){
                                ToastUtil.showToast("随手拍视屏不能超过1分钟");
                                return;
                            }

                        }

                    }


                }else {
                    if (photoAdapter2.getData().size()>0){
                        if (mediaFile!=null){
                            if (mediaFile.getDuration()>1200000){
                                ToastUtil.showToast("作品视屏不能超过20分钟");
                                return;
                            }
                        }

                    }
                }

                multimediaList=new ArrayList<>();

                if (photoAdapter.getData().size()>0){
                    if (ossTokenResponse == null) {
                        uploadFilePresenter.getOssToken();
                        return;
                    }else {
                        uploadFilePresenter.uploadImage(ossTokenResponse, photoAdapter.getData().get(upPosition));

                    }

                }else if (photoAdapter2.getData().size()>0) {
                    if (ossTokenResponse == null) {
                        uploadFilePresenter.getOssToken();
                        return;
                    } else {
                        if (bianji==1){
                            Log.e("qsd","上传编辑视频ossTokenResponse");
                            uploadFilePresenter.uploadImage(ossTokenResponse, mThumbnailPath);
                        }else {
                            uploadFilePresenter.uploadImage(ossTokenResponse, mediaFile.getPath());
                        }



                    }
                } else {
                    type=1;
                    Log.e("qsd","===latitude+"+latitude+"=="+longitude);
                        mPresenter.Upload(address,editText.getText().toString(),latitude,longitude,mediatype,type,null,tagsList);


                }

                break;

                case R.id.delete:
                    address="";
                    zb.setText("您在哪里？");
                    delete.setVisibility(View.GONE);
                    zb.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.zb), null, null, null);
                break;
                case R.id.zb:

                startActivityForResult(Selectaddress.class, CHOOSE_HANDYMAN);

                break;
                case R.id.huati:
                    startActivityForResult(ChoseScrollTagActivity.class, CHOOSE_TAG);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("qsd","SELECT_CODE");


        if (resultCode != RESULT_OK) {
            return;
        }
        if (data == null) {
            return;
        }

        if (requestCode == SELECT_CODE) {

            if (data.getStringArrayListExtra(SELECT_RESULT2)!=null){
                photoRecycler.setAdapter(photoAdapter2);
                photoAdapter2.addData(data.getStringArrayListExtra(SELECT_RESULT2));
                Log.e("qsd","size"+data.getStringArrayListExtra(SELECT_RESULT2));

            }else {
                photoRecycler.setAdapter(photoAdapter);
                photoAdapter.addData(data.getStringArrayListExtra(SELECT_RESULT));
                Log.e("qsd","SELECT_CODE"+data.getStringArrayListExtra(SELECT_RESULT));
            }

                mediaFile=(MediaFile)data.getSerializableExtra("MEDIA");
            if (mediaFile!=null){
               // mediaFile = getLocalVideo(mediaFile.getPath());
                photoRecycler.setAdapter(photoAdapter2);
                photoAdapter2.addData2(mediaFile);
              //  Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(mediaFile.getPath(), FULL_SCREEN_KIND);
                Log.e("qsd","hight"+mediaFile.getH()+"hight"+mediaFile.getW()+"=="+mediaFile.getDuration()+""+mediaFile.getPath());
            }


        }

        if (requestCode == SELECT_CODE2) {

            Log.e("qsd","SELECT_CODE2"+data.getStringArrayListExtra(SELECT_RESULT2));
            if (data.getStringArrayListExtra(SELECT_RESULT2)!=null){
                photoAdapter2.clearData();
                photoRecycler.setAdapter(photoAdapter2);

                photoAdapter2.addData(data.getStringArrayListExtra(SELECT_RESULT2));
                Log.e("qsd","size"+data.getStringArrayListExtra(SELECT_RESULT2));

            }else {
                photoRecycler.setAdapter(photoAdapter);
                photoAdapter.addData(data.getStringArrayListExtra(SELECT_RESULT));
            }

        }
        if (requestCode == CHOOSE_TAG) {
            tagsList= ( List<TagsInfoVoBean>) data.getSerializableExtra("resule");
            if (tagsList!=null){
                List<String> list=new ArrayList<>();
                Log.e("qsd","SELECT_CODE2"+tagsList+"=="+new Gson().toJson(tagsList));

                StffReviewGroupAdapter reviewGroupAdapter = new StffReviewGroupAdapter(mContext);
                for (int i=0;i<tagsList.size();i++){
                    list.add(tagsList.get(i).getTagName());

                }
                reviewGroupAdapter.addData(list);
                reviewGroupAdapter.setMAX_SHOW(5);

                myViewGroup.setAdapter(reviewGroupAdapter);
            }

        }
        if (requestCode == CHOOSE_HANDYMAN) {
            address=data.getStringExtra("address");
            zb.setText(data.getStringExtra("address"));
            latitude= (long) data.getDoubleExtra("latitude",0.0);
            longitude=(long) data.getDoubleExtra("longitude",0.0);
            zb.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.home_zb), null, null, null);
            delete.setVisibility(View.VISIBLE);
           // zb.setTextColor(getResources().getColor(R.color.notice));
            Log.e("qsd","===CHOOSE_HANDYMAN+"+latitude+"=="+longitude);

        }
    }

    public  MediaFile getLocalVideo(String videoPath) {
        MediaFile mediaFile=new MediaFile();

//除以 1000 返回是秒
        int duration;
        try {
            MediaMetadataRetriever mmr = new  MediaMetadataRetriever();
            mmr.setDataSource(videoPath);
            duration = Integer.parseInt(mmr.extractMetadata
                    (MediaMetadataRetriever.METADATA_KEY_DURATION));


//宽
            String width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
//高
            String height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
            mediaFile.setDuration(duration);
            mediaFile.setPath(videoPath);
            mediaFile.setW(width);
            mediaFile.setH(height);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mediaFile;
    }
    /***********
     * eventBus 监听
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ImageDeleteEvent event) {
        photoAdapter.remove(event.getUrl());

    }

    @Override
    public void UploadSuccess() {
        App.hideLoading();
        ToastUtil.showToast("上传成功");
        if (bianji==1){
            SpUtil.putString("MEDIA","");
            SpUtil. putString("mThumbnailPath","");
        }
        new Handler().postDelayed(new Runnable(){

            public void run() {
                EventBus.getDefault().removeStickyEvent(UploadzpEvent.class);
                EventBus.getDefault().post(new UploadzpEvent());

            }

        }, 1000);


        finish();
    }
    @Override
    public void showLoading(String content, int code) {
        App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {


    }

    @Override
    public void showErrorMsg(String msg, int code) {
        App.hideLoading();
        ToastUtil.showToast(msg);

    }

    @Override
    public void getOssTokenSuccess(OssTokenResponse response) {
        ossTokenResponse=response;
        if (type==1){
             if (0 < photoAdapter.getData().size()) {
                 Bitmap bitmap= BitmapFactory.decodeFile(photoAdapter.getData().get(upPosition));
                 outHeight= bitmap.getHeight();
                 outWidth= bitmap.getWidth();
                 Log.i("qsd","通过Options获取到的图片大小"+ "width:"+ outWidth +" height: " + outHeight);

                 uploadFilePresenter.uploadImage(ossTokenResponse, photoAdapter.getData().get(upPosition));


                 }
             if (photoAdapter2.getData().size()>0){
                 if (bianji==1){

                     uploadFilePresenter.uploadImage(ossTokenResponse, mThumbnailPath);

                     Log.e("qsd","上传编辑视频");
                 }else {
                     uploadFilePresenter.uploadImage(ossTokenResponse, mediaFile.getPath());
                 }

             }
        }else {

    uploadFilePresenter.uploadImage(ossTokenResponse, mediaFile.getPath());

}



    }

    @Override
    public void uploadImageSuccess(String url) {
        if (bianji==1) {
            if (url.endsWith(".jpg")||url.endsWith(".jpeg")){
                mThumbnailPath2=url;
                Log.e("qsd","上传视频"+mediaFile.getPath());
                uploadFilePresenter.uploadImage(ossTokenResponse, mediaFile.getPath());
            }else if (url.endsWith(".mp4")) {
                multimediaListBean=new MultimediabodyBean();
                multimediaListBean.setMultimediaUrl(url);
                if (mediaFile!=null){
                    if (mediaFile.getH()!=null){
                        multimediaListBean.setHeight(Integer.parseInt(mediaFile.getH()));
                        multimediaListBean.setWidth(Integer.parseInt(mediaFile.getW()));
                    }else {

                        multimediaListBean.setHeight(680);
                        multimediaListBean.setWidth(360);

                    }

                }else {

                    multimediaListBean.setHeight(outHeight);
                    multimediaListBean.setWidth(outWidth);

                }
                mediatype=2;
                multimediaListBean.setMultimediaType(2);
                multimediaListBean.setDuration(mediaFile.getDuration()/1000);
                multimediaListBean.setPreUrl(mThumbnailPath2);
                multimediaList.add(multimediaListBean);
                App.loadingDefault(mActivity);
                mPresenter.Upload(address,editText.getText().toString(),latitude,longitude,mediatype,type,multimediaList,tagsList);

            }

        }else {
            multimediaListBean=new MultimediabodyBean();
            multimediaListBean.setMultimediaUrl(url);
            if (mediaFile!=null){
                if (mediaFile.getH()!=null){
                    multimediaListBean.setHeight(Integer.parseInt(mediaFile.getH()));
                    multimediaListBean.setWidth(Integer.parseInt(mediaFile.getW()));
                }else {

                    multimediaListBean.setHeight(680);
                    multimediaListBean.setWidth(360);

                }

            }else {

                multimediaListBean.setHeight(outHeight);
                multimediaListBean.setWidth(outWidth);

            }
        if (url.endsWith(".mp4")){
            mediatype=2;
            multimediaListBean.setMultimediaType(2);
            multimediaListBean.setDuration(mediaFile.getDuration()/1000);
            if (photoAdapter2.getData().get(0).endsWith(".jpg")){
                multimediaListBean.setPreUrl(photoAdapter2.getData().get(0));

            }else {
               int w= Integer.parseInt(mediaFile.getW())/2;
               int h= Integer.parseInt(mediaFile.getH())/2;
                multimediaListBean.setPreUrl(url+"?x-oss-process=video/snapshot,t_1000,f_jpg,w_"+w+",h_"+h+",m_fast");
            }
        }else {
            mediatype=1;
            multimediaListBean.setMultimediaType(1);

        }
        multimediaList.add(multimediaListBean);
        if (type==1){
            if (multimediaList.size()<photoAdapter.getData().size()){
                App.loadingDefault(mActivity);
                upPosition++;
                uploadFilePresenter.uploadImage(ossTokenResponse, photoAdapter.getData().get(upPosition));

            }else {
                App.loadingDefault(mActivity);
                mPresenter.Upload(address,editText.getText().toString(),latitude,longitude,mediatype,type,multimediaList,tagsList);

            }
        }else {
            App.loadingDefault(mActivity);
            mPresenter.Upload(address,editText.getText().toString(),latitude,longitude,mediatype,type,multimediaList,tagsList);

        }


        }



    }
    private Locale getLocale() {
        Configuration config = getResources().getConfiguration();
        Locale sysLocale = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = getSystemLocale(config);
        } else {
            sysLocale = getSystemLocaleLegacy(config);
        }

        return sysLocale;
    }

    @SuppressWarnings("deprecation")
    public static Locale getSystemLocaleLegacy(Configuration config){
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config){
        return config.getLocales().get(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (type==1){
        String media = SpUtil.getString("MEDIA");
        mediaFile = new Gson().fromJson(media, MediaFile.class);
        mThumbnailPath = SpUtil.getString("mThumbnailPath");

        Log.e("qsd","触发onResume"+media+"=="+mThumbnailPath+"mediaFile"+new Gson().toJson(mediaFile));
        if (mediaFile !=null&&!StringUtil.isBlank(mThumbnailPath)){
            bianji= 1;
            strings.clear();
            strings.add(mediaFile.getPath());
            // mediaFile = getLocalVideo(mediaFile.getPath());
            photoRecycler.setAdapter(photoAdapter2);
            photoAdapter2.addData(strings);
            photoAdapter2.addData2(mediaFile);
            //  Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(mediaFile.getPath(), FULL_SCREEN_KIND);
            Log.e("qsd","hight"+ this.mediaFile.getH()+"hight"+ this.mediaFile.getW()+"=="+ mediaFile.getDuration()+""+ mediaFile.getPath());
        }

        }
      /*  SpUtil.putString("MEDIA","");
        SpUtil. putString("mThumbnailPath","");*/

    }
    private void startPreview() {
        Intent intent = new Intent(this, AlivcLittlePreviewActivity.class);
        intent.putExtra(KEY_PARAM_CONFIG, mediaFile.getPath());
        intent.putExtra(KEY_PARAM_VIDEO_PARAM, mThumbnailPath);
        //传入视频比列
        startActivity(intent);
    }
}