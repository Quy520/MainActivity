package com.fm.designstar.views.mine.activity;



import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fm.designstar.BuildConfig;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.PermissionsListener;
import com.fm.designstar.config.Constant;
import com.fm.designstar.dialog.ActionSheetDialog;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.utils.ConvertUtil;
import com.fm.designstar.utils.FileUtils;
import com.fm.designstar.utils.FormatUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.utils.UtilFinal;
import com.fm.designstar.views.login.activitys.RegisteredActivity;
import com.fm.designstar.views.mine.contract.UploadFileContract;
import com.fm.designstar.views.mine.presenter.UploadFilePresenter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class BeDesignerActivity extends BaseActivity<UploadFilePresenter>  implements UploadFileContract.View {

@BindView(R.id.im_card)
    ImageView im_card;
@BindView(R.id.im_card2)
    ImageView im_card2;
@BindView(R.id.im_card3)
    ImageView im_card3;
    private OssTokenResponse ossTokenResponse;
    private File newFile;
    private String upload;
    private int type=1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_be_designer;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.be_designer);

    }
    @OnClick({R.id.im_card,R.id.im_card2, R.id.im_card3})
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {

            case R.id.im_card:
                type=1;
                imageAction();
                break;
            case R.id.im_card2:
                type=2;
                imageAction();
                break;
            case R.id.im_card3:
                type=3;
                imageAction();
                break;
            default:
                break;
        }
    }
    private void imageAction() {
        boolean isShowImage = false;
        ActionSheetDialog dialog = new ActionSheetDialog(mActivity).builder();
        //是否实名认证
        dialog.addSheetItem(R.string.open_camera, ActionSheetDialog.SheetItemColor.Black,
                new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        // 拍照
                        camera();
                    }
                });
        dialog.addSheetItem(R.string.open_album, ActionSheetDialog.SheetItemColor.Black,
                new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        selectPhoto();
                    }
                });
        dialog.show();
    }

    /**
     * 相册选择
     */
    public void selectPhoto() {
        // 照相 和 读取 内存权限
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionsListener() {
            @Override
            public void onGranted() {
                try {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    if (mActivity.getPackageManager().resolveActivity(intent, 0) == null) {

                        ToastUtil.showToast("系统不存在");
                    } else {
                        startActivityForResult(intent, UtilFinal.CODE_CHOOSE_ALBUM);
                    }
                } catch (ActivityNotFoundException e) {
                    ToastUtil.showToast("系统不存在");
                }
            }

            @Override
            public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
                if (isNeverAsk) {

                    mActivity.toAppSettings("权限已被禁止", false);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UtilFinal.CODE_CHOOSE_ALBUM) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            if (data != null) {
                String selectPath = ConvertUtil.getPath(mContext, data.getData());
                if (!TextUtils.isEmpty(selectPath) && new File(selectPath).exists()) {
                    saveFileToOtherPath(selectPath, Util.getPath() + System.currentTimeMillis() + ".jpg");
                }
            }
        }
        if (requestCode == UtilFinal.CODE_TAKE_PHOTO) {
            if (resultCode != RESULT_OK) {
                newFile.delete();
                return;
            }
            if (newFile != null) {
                String cameImage = newFile.getAbsolutePath();
                if (!StringUtil.isBlank(cameImage)) {
                    saveFile(cameImage);
                    return;
                }
            }
            //照片获取失败，请重试
            ToastUtil.showToast(R.string.get_photo_fail);
        }
    }

    /**
     * 文件压缩上传
     *
     * @param imagePath 图片地址
     */
    private void saveFile(final String imagePath) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //图片是否存在
                if (new File(imagePath).exists()) {
                    //获取图片旋转度数
                    int degree = ConvertUtil.getBitmapDegree(imagePath);
                    //压缩图片
                    Bitmap bitmap = ConvertUtil.getCompressedBmp(imagePath, 400, 400);
                    //如果旋转度数大于0则进行校正
                    if (degree > 0) {
                        bitmap = ConvertUtil.rotateBitmapByDegree(bitmap, degree);
                    }
                    //保存压缩、校正旋转之后的图片(覆盖掉所拍的图片)
                    ConvertUtil.saveBitmap(imagePath, bitmap);
                    if (bitmap != null) {
                        bitmap.recycle();//释放内存
                    }
                    subscriber.onNext(imagePath);
                    subscriber.onCompleted();
                } else {
                    //subscriber.onError(new IOException("图片保存失败"));
                    // App.hideLoading();
                    subscriber.onError(new IOException("图片保存失败"));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (type==1){
                            Glide.with(mActivity).load(s).into(im_card);

                        }else if (type==2){
                            Glide.with(mActivity).load(s).into(im_card2);

                        }else {
                            Glide.with(mActivity).load(s).into(im_card3);

                        }
                        upload=s;
                        if (ossTokenResponse == null) {
                            mPresenter.getOssToken();
                        } else {
                            mPresenter.uploadImage(ossTokenResponse, s);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        App.hideLoading();
                        ToastUtil.showToast(throwable.getMessage());
                    }
                });
    }
    private void saveFileToOtherPath(final String imagePath, final String otherPath) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //图片是否存在
                if (new File(imagePath).exists()) {
                    //获取图片旋转度数
                    int degree = ConvertUtil.getBitmapDegree(imagePath);
                    //压缩图片
                    Bitmap bitmap = ConvertUtil.getCompressedBmp(imagePath, 400, 400);
                    //空判断 如果压缩是空的bitmap，不进行处理，直接上传。
                    if (bitmap == null) {
                        subscriber.onNext(imagePath);
                        subscriber.onCompleted();
                    } else {
                        //如果旋转度数大于0则进行校正
                        if (degree > 0) {
                            bitmap = ConvertUtil.rotateBitmapByDegree(bitmap, degree);
                        }
                        //保存压缩、校正旋转之后的图片(覆盖掉所拍的图片)
                        ConvertUtil.saveBitmap(otherPath, bitmap);
                        if (bitmap != null) {
                            bitmap.recycle();//释放内存
                        }
                        subscriber.onNext(otherPath);
                        subscriber.onCompleted();
                    }
                } else {
                    //subscriber.onError(new IOException("图片保存失败"));
                    // App.hideLoading();
                    subscriber.onError(new IOException("图片保存失败"));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (type==1){
                            Glide.with(mActivity).load(s).into(im_card);

                        }else if (type==2){
                            Glide.with(mActivity).load(s).into(im_card2);

                        }else {
                            Glide.with(mActivity).load(s).into(im_card3);

                        }
                        upload = s;
                        if (ossTokenResponse == null) {
                            mPresenter.getOssToken();
                        } else {
                            mPresenter.uploadImage(ossTokenResponse, s);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        App.hideLoading();
                        ToastUtil.showToast(throwable.getMessage());
                    }
                });

    }
    /**
     * 照相的方法
     */
    private void camera() {
        // 照相 和 读取 内存权限
        requestPermissions(new String[]{Manifest.permission.CAMERA}, new PermissionsListener() {
            //授权
            @Override
            public void onGranted() {
                //7.0 处理 重新构造Uri：content://
                try {
                    newFile = FileUtils.createTmpFile(mContext);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        if (newFile.exists()) {
                            Uri contentUri;
                            if (Build.VERSION.SDK_INT >= Constant.SDK_INT_N) {
                                contentUri = FileProvider.getUriForFile(mContext, BuildConfig.AUTHORITY, newFile);
                            } else {
                                contentUri = Uri.fromFile(newFile);
                            }
                            if (contentUri == null) {

                                return;
                            }
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                            // 授予目录临时共享权限
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            startActivityForResult(intent, UtilFinal.CODE_TAKE_PHOTO);
                        } else {
                            ToastUtil.showToast("系统错误文件不存在");
                        }
                    } else {
                        ToastUtil.showToast("系统错误文件不存在");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //拒绝
            @Override
            public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {
                if (isNeverAsk) {
                    ToastUtil.showToast("打开相关权限");
                }
            }
        });
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
        App.hideLoading();

        ossTokenResponse=response;
        if (!StringUtil.isBlank(upload)) {
            mPresenter.uploadImage(ossTokenResponse, upload);
        }
    }

    @Override
    public void uploadImageSuccess(String url) {
        Log.e("qsd","url"+url);
        App.hideLoading();

    }
}