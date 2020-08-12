package com.fm.designstar.views.Fabu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.OnClick;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.Encoder;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.PermissionsListener;
import com.fm.designstar.events.ImageDeleteEvent;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.events.UploadzpEvent;
import com.fm.designstar.map.Selectaddress;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.MultimediaListBean;
import com.fm.designstar.model.bean.MultimediabodyBean;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.utils.GlideLoader;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Fabu.activity.ChoseTagActivity;
import com.fm.designstar.views.Fabu.adapter.ReviewPhotoAdapter;
import com.fm.designstar.views.Fabu.contract.UploadContract;
import com.fm.designstar.views.Fabu.presenter.UploadPresenter;
import com.fm.designstar.views.mine.contract.UploadFileContract;
import com.fm.designstar.views.mine.presenter.UploadFilePresenter;
import com.fm.designstar.widget.imagePicker.ImagePicker;
import com.fm.designstar.widget.imagePicker.data.MediaFile;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static android.provider.MediaStore.Video.Thumbnails.FULL_SCREEN_KIND;

public class FabuActivity extends BaseActivity<UploadPresenter> implements UploadContract.View, UploadFileContract.View {
    public static final String SELECT_RESULT = "SELECT_RESULT";
    public static final String SELECT_RESULT2 = "SELECT_RESULT2";

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
    private String address;
    long latitude ;//获取纬度
    long longitude;
    private int mediatype=0;
    private int upPosition=0;
    private UploadFilePresenter uploadFilePresenter;
    private OssTokenResponse ossTokenResponse;
    List<MultimediabodyBean> multimediaList;
    MultimediabodyBean multimediaListBean=new MultimediabodyBean();
    List<DesignerBean.TagsListBean> tagsList;
    private ReviewPhotoAdapter photoAdapter;
    public static final int SELECT_CODE = 100;
    public static final int CHOOSE_HANDYMAN = 101;
    public static final int CHOOSE_TAG = 102;
    private int type;
    MediaFile mediaFile;
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

        type=getIntent().getIntExtra("type",1);
        re_title.getLayoutParams().height = Tool.dip2px(mContext, 34) + Util.getStatusBarH(mContext);
        ((ViewGroup.MarginLayoutParams) re_title.getLayoutParams()).topMargin = Util.getStatusBarH(mContext);
        photoRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        photoRecycler.addItemDecoration(new SpaceItemDecoration().setRight(Tool.dip2px(mContext, 5)).setBottom(Tool.dip2px(mContext, 8)));
        photoAdapter = new ReviewPhotoAdapter(mContext);
        photoRecycler.setAdapter(photoAdapter);

        photoAdapter.setListener(new ReviewPhotoAdapter.AddPhotoListener() {
            @Override
            public void addPhoto() {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, mListener);
            }
        });

       latitude= SpUtil.getLong("latitude");
        longitude= SpUtil.getLong("longitude");


    }
    private PermissionsListener mListener = new PermissionsListener() {
        @Override
        public void onGranted() {

          if (type==1){
              ImagePicker.getInstance()
                      .setTitle("标题")//设置标题
                      .showCamera(true)//设置是否显示拍照按钮
                      .showImage(true)//设置是否展示图片
                      .showVideo(true)//设置是否展示视频
                      .filterGif(true)//设置是否过滤gif图片
                      .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                      .setSingleType(true)//设置图片视频不能同时选择
                      .setImagePaths(photoAdapter.getData())//设置历史选择记录
                      .setImageLoader(new GlideLoader())//设置自定义图片加载器
                      .start(FabuActivity.this, SELECT_CODE,3);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode

          }else {
              ImagePicker.getInstance()
                      .setTitle("标题")//设置标题
                      .showCamera(true)//设置是否显示拍照按钮
                      .showImage(false)//设置是否展示图片
                      .showVideo(true)//设置是否展示视频
                      .filterGif(true)//设置是否过滤gif图片
                      .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                      .setSingleType(true)//设置图片视频不能同时选择
                      .setImagePaths(photoAdapter.getData())//设置历史选择记录
                      .setImageLoader(new GlideLoader())//设置自定义图片加载器
                      .start(FabuActivity.this, SELECT_CODE,3);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode

          }

        }


        @Override
        public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {

        }
    };

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
                if (photoAdapter.getData().size()==0&& StringUtil.isBlank(editText.getText().toString())){
                    return;
                }
                if (photoAdapter.getData().size()==0){
                    mediatype=0;
                }

                multimediaList=new ArrayList<>();

                if (photoAdapter.getData().size()>0){
                    if (ossTokenResponse == null) {
                        uploadFilePresenter.getOssToken();
                        return;
                    }else {
                        uploadFilePresenter.uploadImage(ossTokenResponse, photoAdapter.getData().get(upPosition));

                    }

                }else {
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
                    startActivityForResult(ChoseTagActivity.class, CHOOSE_TAG);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (data == null) {
            return;
        }
        if (requestCode == SELECT_CODE) {
            Log.e("qsd","SELECT_CODE"+data.getStringArrayListExtra(SELECT_RESULT));
            Log.e("qsd","SELECT_CODE2"+data.getStringArrayListExtra(SELECT_RESULT2));
            if (data.getStringArrayListExtra(SELECT_RESULT2)!=null){
                photoAdapter.addData(data.getStringArrayListExtra(SELECT_RESULT2));

            }else {
                photoAdapter.addData(data.getStringArrayListExtra(SELECT_RESULT));
            }

           mediaFile=(MediaFile)data.getSerializableExtra("MEDIA");
            if (mediaFile!=null){
                photoAdapter.addData2(mediaFile);
              //  Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(mediaFile.getPath(), FULL_SCREEN_KIND);
                Log.e("qsd","MEDIA"+mediaFile.getFolderName()+"=="+mediaFile.getDuration()+""+mediaFile.getPath());
            }


        }

        if (requestCode == CHOOSE_HANDYMAN) {
            address=data.getStringExtra("address");
            zb.setText(data.getStringExtra("address"));
            latitude=data.getLongExtra("latitude",0);
            longitude=data.getLongExtra("longitude",0);

            zb.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.home_zb), null, null, null);
            delete.setVisibility(View.VISIBLE);
            zb.setTextColor(getResources().getColor(R.color.notice));
        }
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

        EventBus.getDefault().removeStickyEvent(UploadzpEvent.class);
        EventBus.getDefault().post(new UploadzpEvent());
        finish();
    }
    @Override
    public void showLoading(String content, int code) {
        App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        App.hideLoading();

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        App.hideLoading();
        ToastUtil.showToast(msg);

    }

    @Override
    public void getOssTokenSuccess(OssTokenResponse response) {
        ossTokenResponse=response;

        if (0 < photoAdapter.getData().size()) {
            uploadFilePresenter.uploadImage(ossTokenResponse, photoAdapter.getData().get(upPosition));


        }


    }

    @Override
    public void uploadImageSuccess(String url) {
        multimediaListBean=new MultimediabodyBean();
        multimediaListBean.setMultimediaUrl(url);
        multimediaListBean.setHeight(640);
        multimediaListBean.setWidth(320);
        if (url.endsWith(".mp4")){
            multimediaListBean.setMultimediaType(2);
            multimediaListBean.setDuration(mediaFile.getDuration()/1000);
           multimediaListBean.setPreUrl(url+"?spm=a2c4g.11186623.2.1.yjOb8V&x-oss-process=video/snapshot,t_2000,f_jpg,w_520,h_800,m_fast");
        }else {
            multimediaListBean.setMultimediaType(1);

        }
        multimediaList.add(multimediaListBean);
        if (url.endsWith(".mp4")){
            mediatype=2;
        }else {
            mediatype=1;
        }
        if (multimediaList.size()<photoAdapter.getData().size()){
            upPosition++;
            uploadFilePresenter.uploadImage(ossTokenResponse, photoAdapter.getData().get(upPosition));

        }else {
            App.loadingDefault(mActivity);
            mPresenter.Upload(address,editText.getText().toString(),latitude,longitude,mediatype,type,multimediaList,tagsList);

        }





    }
}