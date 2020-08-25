package com.fm.designstar.views.mine.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.fm.designstar.BuildConfig;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.PermissionsListener;
import com.fm.designstar.config.Constant;
import com.fm.designstar.dialog.ActionSheetDialog;
import com.fm.designstar.dialog.ShareDialogUtil;
import com.fm.designstar.events.LogoutEvent;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.model.bean.JsonBean;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.utils.ConvertUtil;
import com.fm.designstar.utils.FileUtils;
import com.fm.designstar.utils.GetJsonDataUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Util;
import com.fm.designstar.utils.UtilFinal;
import com.fm.designstar.views.login.contract.ComInfoContract;
import com.fm.designstar.views.login.presenter.CominfoPresenter;
import com.fm.designstar.views.mine.contract.LoginOutContract;
import com.fm.designstar.views.mine.contract.UploadFileContract;
import com.fm.designstar.views.mine.presenter.LoginOutPresenter;
import com.fm.designstar.views.mine.presenter.UploadFilePresenter;
import com.fm.designstar.widget.CircleImageView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SettingActivity extends BaseActivity<LoginOutPresenter> implements LoginOutContract.View, ComInfoContract.View, UploadFileContract.View  {
    TimePickerView pvTime;
    @BindView(R.id.birth)
    TextView brith;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sing)
    TextView sing;
    @BindView(R.id.sex)
    TextView tv_sex;
    @BindView(R.id.vesion)
    TextView vesion;
    @BindView(R.id.hand)
    CircleImageView hand;
    private UploadFilePresenter uploadFilePresenter;
    private CominfoPresenter cominfoPresenter;
    private OssTokenResponse ossTokenResponse;
    private String upload;
    private String icon="https://ttmsocial-1256411278.cos.ap-shanghai.myqcloud.com/b_coldfish.png";
    private File newFile;
    private int sex=1;
    ShareDialogUtil shareDialogUtil;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        uploadFilePresenter=new UploadFilePresenter();
        uploadFilePresenter.init(this);
        cominfoPresenter=new CominfoPresenter();
        cominfoPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.my_seeting2);
        mTitle.setRightTitleColor(R.color.notice);

        vesion.setText((Util.getAppVersion(mActivity) + "(" + Util.getAppVersionCode(mActivity) + ")"));
        mTitle.setRightTitle("完成", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtil.isBlank(name.getText().toString())) {
                    ToastUtil.showToast(R.string.name_err);
                    return;
                }
                if (brith.getText().toString().equals(R.string.brth)) {
                    ToastUtil.showToast(R.string.birth_err);
                    return;
                }
                if (city.getText().toString().equals("请选择地址")) {
                    ToastUtil.showToast(R.string.city_err);
                    return;
                }
                if (shareDialogUtil==null){
                    sex=1;
                }else {
                sex=    shareDialogUtil.getSex();
                }

                cominfoPresenter.ComInfo(icon,name.getText().toString(),brith.getText().toString(),sex,city.getText().toString(),sing.getText().toString());
            }
        });
        initJsonData();
        if (StringUtil.isBlank(App.getConfig().getBirthday())){
            brith.setText(R.string.no_birth);
            brith.setTextColor(getResources().getColor(R.color.hint_color));
        }else {
            brith.setText(App.getConfig().getBirthday());
            brith.setTextColor(getResources().getColor(R.color.edit_color));
        }
        if (StringUtil.isBlank(App.getConfig().getAddress())){
            city.setText(R.string.no_city);
            city.setTextColor(getResources().getColor(R.color.hint_color));
        }else {
            city.setText(App.getConfig().getAddress());
            city.setTextColor(getResources().getColor(R.color.edit_color));
        }
        if (StringUtil.isBlank(App.getConfig().getSingmarks())){
            sing.setText(R.string.no_sing);
            sing.setTextColor(getResources().getColor(R.color.hint_color));
        }else {
            sing.setText(App.getConfig().getSingmarks());
            sing.setTextColor(getResources().getColor(R.color.edit_color));
        }
        icon=App.getConfig().getUser_head();
        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mActivity).load(App.getConfig().getUser_head()).error(R.mipmap.defu_hand).into(hand);
        }
        name.setText(App.getConfig().getUser_name());
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 11, 28);
        pvTime= new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                brith.setText(getTimes(date));
                brith.setTextColor(ContextCompat.getColor(mContext, R.color.edit_color));

            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setSubCalSize(14)//确定和取消文字大小
                .setLineSpacingMultiplier(1.3f)
                .setDividerColor(Color.DKGRAY)
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();



    }

    @OnClick({R.id.re_name, R.id.re_hand, R.id.re_sex,R.id.re_birth,R.id.re_city,R.id.re_sing,R.id.longout})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.re_name:
                Intent intent2 = new Intent(mContext, ChangInfoActivity.class);
                intent2.putExtra("type",2);
                startActivityForResult(intent2, 200);

                break;
            case R.id.re_hand:
                imageAction();

                break;
            case R.id.re_sex:
                 shareDialogUtil = new ShareDialogUtil(mContext);
                 shareDialogUtil.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         sex=shareDialogUtil.getSex();
                        if (sex==1){
                            tv_sex.setText("男");
                        }else {
                            tv_sex.setText("女");
                        }
                     }
                 });

                shareDialogUtil.showDialog();

                break;
                case R.id.re_birth:
                    pvTime.show();

                break;
                case R.id.re_city:

                    OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                            //返回的分别是三个级别的选中位置
                            String tx = options2Items.get(options1).get(option2);
                            city.setText(tx);
                        }
                    })
                            .setCancelText("取消")
                            .setSubmitText("确认")

                            .build();
                    pvOptions.setPicker(options1Items, options2Items, options3Items);
                    pvOptions.show();

                    break;
                case R.id.re_sing:
                    Intent intent = new Intent(mContext, ChangInfoActivity.class);
                    intent.putExtra("type",1);

                    startActivityForResult(intent, 100);


                break;
                case R.id.longout:
                mPresenter.LoginOut();
                break;




            default:
                break;
        }
    }
    private String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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

        if (data != null) {
            if (requestCode == 200) {
                String info = data.getStringExtra("SELECT_RESULT");
                name.setText(info);
                name.setTextColor(getResources().getColor(R.color.edit_color));

            }
            if (requestCode == 100) {
                String info = data.getStringExtra("SELECT_RESULT");
                sing.setText(info);
                sing.setTextColor(getResources().getColor(R.color.edit_color));

            }
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
                        Glide.with(mActivity).load(s).into(hand);
                        upload=s;
                        if (ossTokenResponse == null) {
                            uploadFilePresenter.getOssToken();
                        } else {
                            uploadFilePresenter.uploadImage(ossTokenResponse, s);
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
                        //上传的map，里面存的都是本地图片路径
                        Glide.with(mActivity).load(s).into(hand);
                        upload = s;
                        if (ossTokenResponse == null) {
                            uploadFilePresenter.getOssToken();
                        } else {
                            uploadFilePresenter.uploadImage(ossTokenResponse, s);
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
    public void LoginOutSuccess() {
        ToastUtil.showToast(R.string.log_out_success);
        EventBus.getDefault().removeStickyEvent(LogoutEvent.class);
        EventBus.getDefault().post(new LogoutEvent(1));
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
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return detail;
    }

    @Override
    public void ComInfoSuccess() {
        App.getConfig().setBirthday(brith.getText().toString());//shengeri
        App.getConfig().setUser_name(name.getText().toString());//zhengshixingm
        App.getConfig().setAddress(city.getText().toString());//zhengshixingm
        App.getConfig().setSingmarks(sing.getText().toString());//zhengshixingm
        App.getConfig().setUser_head(icon);
        App.getConfig().setSex(sex);
        EventBus.getDefault().removeStickyEvent(UpdatainfoEvent.class);
        EventBus.getDefault().post(new UpdatainfoEvent());
finish();

    }

    @Override
    public void getOssTokenSuccess(OssTokenResponse response) {
        ossTokenResponse=response;
        if (!StringUtil.isBlank(upload)) {
            uploadFilePresenter.uploadImage(ossTokenResponse, upload);
        }
    }

    @Override
    public void uploadImageSuccess(String url) {
        icon=url;

    }
}