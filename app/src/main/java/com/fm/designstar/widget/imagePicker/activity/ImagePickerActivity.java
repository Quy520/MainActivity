package com.fm.designstar.widget.imagePicker.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.fm.designstar.R;
import com.fm.designstar.utils.ConvertUtil;
import com.fm.designstar.utils.ExtractVideoInfoUtil;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Fabu.FabuActivity;
import com.fm.designstar.widget.imagePicker.adapter.ImageFoldersAdapter;
import com.fm.designstar.widget.imagePicker.adapter.ImagePickerAdapter;
import com.fm.designstar.widget.imagePicker.data.MediaFile;
import com.fm.designstar.widget.imagePicker.data.MediaFolder;
import com.fm.designstar.widget.imagePicker.executors.CommonExecutor;
import com.fm.designstar.widget.imagePicker.listener.MediaLoadCallback;
import com.fm.designstar.widget.imagePicker.manager.ConfigManager;
import com.fm.designstar.widget.imagePicker.manager.SelectionManager;
import com.fm.designstar.widget.imagePicker.provider.ImagePickerProvider;
import com.fm.designstar.widget.imagePicker.task.ImageLoadTask;
import com.fm.designstar.widget.imagePicker.task.MediaLoadTask;
import com.fm.designstar.widget.imagePicker.task.VideoLoadTask;
import com.fm.designstar.widget.imagePicker.utils.DataUtil;
import com.fm.designstar.widget.imagePicker.utils.MediaFileUtil;
import com.fm.designstar.widget.imagePicker.utils.PermissionUtil;
import com.fm.designstar.widget.imagePicker.utils.Utils;
import com.fm.designstar.widget.imagePicker.view.ImageFolderPopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.fm.designstar.views.Fabu.FabuActivity.SELECT_RESULT;
import static com.fm.designstar.views.Fabu.FabuActivity.SELECT_RESULT2;


/**
 * 多图选择器主页面
 * Create by: chenWei.li
 * Date: 2018/8/23
 * Time: 上午1:10
 * Email: lichenwei.me@foxmail.com
 */
public class ImagePickerActivity extends BaseActivity implements ImagePickerAdapter.OnItemClickListener, ImageFoldersAdapter.OnImageFolderChangeListener {

    /**
     * 启动参数
     */
    private String mTitle;
    private boolean isShowCamera;
    private boolean isShowImage;
    private boolean isShowVideo;
    private boolean isSingleType;
    private int mMaxCount;
    private List<String> mImagePaths;

    /**
     * 界面UI
     */
    private TextView mTvTitle;
    private TextView mTvCommit;
    private TextView mTvImageTime;
    private RecyclerView mRecyclerView;
    private TextView mTvImageFolders;
    private ImageFolderPopupWindow mImageFolderPopupWindow;
    private ProgressDialog mProgressDialog;
    private RelativeLayout mRlBottom;

    private GridLayoutManager mGridLayoutManager;
    private ImagePickerAdapter mImagePickerAdapter;

    //图片数据源
    private List<MediaFile> mMediaFileList;
    //文件夹数据源
    private List<MediaFolder> mMediaFolderList;

    //是否显示时间
    private boolean isShowTime;

    //表示屏幕亮暗
    private static final int LIGHT_OFF = 0;
    private static final int LIGHT_ON = 1;
    MediaFile mediaFile;

    private Handler mMyHandler = new Handler();
    private Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hideImageTime();
        }
    };


    /**
     * 大图预览页相关
     */
    private static final int REQUEST_SELECT_IMAGES_CODE = 100;//用于在大图预览页中点击提交按钮标识


    /**
     * 拍照相关
     */
    private String mFilePath;
    private static final int REQUEST_CODE_CAPTURE =100;//点击拍照标识

    /**
     * 权限相关
     */
    private static final int REQUEST_PERMISSION_CAMERA_CODE = 0x03;

    private int type;
    @Override
    protected int bindLayout() {
        return R.layout.activity_imagepicker;
    }


    /**
     * 初始化配置
     */
    @Override
    protected void initConfig() {
        mTitle = ConfigManager.getInstance().getTitle();
        isShowCamera = ConfigManager.getInstance().isShowCamera();
        isShowImage = ConfigManager.getInstance().isShowImage();
        isShowVideo = ConfigManager.getInstance().isShowVideo();
        isSingleType = ConfigManager.getInstance().isSingleType();
        mMaxCount = ConfigManager.getInstance().getMaxCount();
        SelectionManager.getInstance().setMaxCount(mMaxCount);

        //载入历史选择记录
        mImagePaths = ConfigManager.getInstance().getImagePaths();
        if (mImagePaths != null && !mImagePaths.isEmpty()) {
            SelectionManager.getInstance().addImagePathsToSelectList(mImagePaths);
        }
    }


    /**
     * 初始化布局控件
     */
    @Override
    protected void initView() {

        mProgressDialog = ProgressDialog.show(this, null, getString(R.string.scanner_image));

        //顶部栏相关
        mTvTitle = findViewById(R.id.tv_actionBar_title);
        if (TextUtils.isEmpty(mTitle)) {
            mTvTitle.setText(getString(R.string.all));
        } else {
            mTvTitle.setText(mTitle);
        }
        RelativeLayout layout_actionBar=findViewById(R.id.layout_actionBar);

        ((ViewGroup.MarginLayoutParams) layout_actionBar.getLayoutParams()).topMargin = Util.getStatusBarH(this);
        mTvCommit = findViewById(R.id.myChooseNum);//提交

        //滑动悬浮标题相关
        mTvImageTime = findViewById(R.id.tv_image_time);

        //底部栏相关
        mRlBottom = findViewById(R.id.rl_main_bottom);
        mTvImageFolders = findViewById(R.id.tv_main_imageFolders);

        //列表相关
        mRecyclerView = findViewById(R.id.rv_main_images);
        mGridLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //注释说当知道Adapter内Item的改变不会影响RecyclerView宽高的时候，可以设置为true让RecyclerView避免重新计算大小。
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(60);

        mMediaFileList = new ArrayList<>();
        mImagePickerAdapter = new ImagePickerAdapter(this, mMediaFileList);
        mImagePickerAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mImagePickerAdapter);

      type=  getIntent().getIntExtra("type",0);
    }

    /**
     * 初始化控件监听事件
     */
    @Override
    protected void initListener() {

        findViewById(R.id.iv_actionBar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        mRlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitSelection();
            }
        });

        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mImageFolderPopupWindow != null) {
                    setLightMode(LIGHT_OFF);
                    mImageFolderPopupWindow.showAsDropDown(mTvTitle, 0, 0);
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                updateImageTime();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                updateImageTime();
            }
        });

    }

    /**
     * 获取数据源
     */
    @Override
    protected void getData() {
        //进行权限的判断
        boolean hasPermission = PermissionUtil.checkPermission(this);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CAMERA_CODE);
        } else {
            startScannerTask();
        }
    }

    /**
     * 权限申请回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CAMERA_CODE) {
            if (grantResults.length >= 1) {
                int cameraResult = grantResults[0];//相机权限
                int sdResult = grantResults[1];//sd卡权限
                boolean cameraGranted = cameraResult == PackageManager.PERMISSION_GRANTED;//拍照权限
                boolean sdGranted = sdResult == PackageManager.PERMISSION_GRANTED;//拍照权限
                if (cameraGranted && sdGranted) {
                    //具有拍照权限，sd卡权限，开始扫描任务
                    startScannerTask();
                } else {
                    //没有权限
                    Toast.makeText(this, getString(R.string.permission_tip), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }


    /**
     * 开启扫描任务
     */
    private void startScannerTask() {
        Runnable mediaLoadTask = null;

        //照片、视频全部加载
        if (isShowImage && isShowVideo) {
            mediaLoadTask = new MediaLoadTask(this, new MediaLoader());
        }

        //只加载视频
        if (!isShowImage && isShowVideo) {
            mediaLoadTask = new VideoLoadTask(this, new MediaLoader());
        }

        //只加载图片
        if (isShowImage && !isShowVideo) {
            mediaLoadTask = new ImageLoadTask(this, new MediaLoader());
        }

        //不符合以上场景，采用照片、视频全部加载
        if (mediaLoadTask == null) {
            mediaLoadTask = new MediaLoadTask(this, new MediaLoader());
        }

        CommonExecutor.getInstance().execute(mediaLoadTask);
    }


    /**
     * 处理媒体数据加载成功后的UI渲染
     */
    class MediaLoader implements MediaLoadCallback {

        @Override
        public void loadMediaSuccess(final List<MediaFolder> mediaFolderList) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!mediaFolderList.isEmpty()) {
                        //默认加载全部照片
                        mMediaFileList.addAll(mediaFolderList.get(0).getMediaFileList());
                        mImagePickerAdapter.notifyDataSetChanged();

                        //图片文件夹数据
                        mMediaFolderList = new ArrayList<>(mediaFolderList);
                        mImageFolderPopupWindow = new ImageFolderPopupWindow(ImagePickerActivity.this, mMediaFolderList);
                        mImageFolderPopupWindow.setAnimationStyle(R.style.imageFolderAnimator);
                        mImageFolderPopupWindow.getAdapter().setOnImageFolderChangeListener(ImagePickerActivity.this);
                        mImageFolderPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                setLightMode(LIGHT_ON);
                            }
                        });
                        updateCommitButton();
                    }
                    mProgressDialog.cancel();
                }
            });
        }
    }


    /**
     * 隐藏时间
     */
    private void hideImageTime() {
        if (isShowTime) {
            isShowTime = false;
            ObjectAnimator.ofFloat(mTvImageTime, "alpha", 1, 0).setDuration(300).start();
        }
    }

    /**
     * 显示时间
     */
    private void showImageTime() {
        if (!isShowTime) {
            isShowTime = true;
            ObjectAnimator.ofFloat(mTvImageTime, "alpha", 0, 1).setDuration(300).start();
        }
    }

    /**
     * 更新时间
     */
    private void updateImageTime() {
        int position = mGridLayoutManager.findFirstVisibleItemPosition();
        if (position != RecyclerView.NO_POSITION) {
             mediaFile = mImagePickerAdapter.getMediaFile(position);
            if (mediaFile != null) {
                if (mTvImageTime.getVisibility() != View.VISIBLE) {
                    mTvImageTime.setVisibility(View.VISIBLE);
                }
                String time = Utils.getImageTime(mediaFile.getDateToken());
                mTvImageTime.setText(time);
                showImageTime();
                mMyHandler.removeCallbacks(mHideRunnable);
                mMyHandler.postDelayed(mHideRunnable, 1500);
            }
        }
    }

    /**
     * 设置屏幕的亮度模式
     *
     * @param lightMode
     */
    private void setLightMode(int lightMode) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        switch (lightMode) {
            case LIGHT_OFF:
                layoutParams.alpha = 0.7f;
                break;
            case LIGHT_ON:
                layoutParams.alpha = 1.0f;
                break;
        }
        getWindow().setAttributes(layoutParams);
    }

    /**
     * 点击图片 视频
     *
     * @param view
     * @param position
     */
    @Override
    public void onMediaClick(View view, int position) {
        if (isShowCamera) {
            if (position == 0) {
                if (!SelectionManager.getInstance().isCanChoose()) {
                    Toast.makeText(this, String.format(getString(R.string.select_image_max), mMaxCount), Toast.LENGTH_SHORT).show();
                    return;
                }
                showCamera();
                return;
            }
        }

        if (mMediaFileList != null) {
            DataUtil.getInstance().setMediaData(mMediaFileList);
            Intent intent = new Intent(this, ImagePreActivity.class);
            if (isShowCamera) {
                intent.putExtra(ImagePreActivity.IMAGE_POSITION, position - 1);
            } else {
                intent.putExtra(ImagePreActivity.IMAGE_POSITION, position);
            }
            startActivityForResult(intent, REQUEST_SELECT_IMAGES_CODE);
        }
    }

    /**
     * 选中/取消选中图片 视频
     *
     * @param view
     * @param position
     */
    @Override
    public void onMediaCheck(View view, int position) {
        if (isShowCamera) {
            if (position == 0) {
                if (!SelectionManager.getInstance().isCanChoose()) {
                    Toast.makeText(this, String.format(getString(R.string.select_image_max), mMaxCount), Toast.LENGTH_SHORT).show();
                    return;
                }
                showCamera();
                return;
            }
        }

        //执行选中/取消操作
         mediaFile = mImagePickerAdapter.getMediaFile(position);//视频或者图片地址
        if (mediaFile != null) {
            String imagePath = mediaFile.getPath();
            if (isSingleType) {
                //如果是单类型选取，判断添加类型是否满足（照片视频不能共存）
                ArrayList<String> selectPathList = SelectionManager.getInstance().getSelectPaths();
                if (!selectPathList.isEmpty()) {
                    //判断选中集合中第一项是否为视频
                    if (!SelectionManager.isCanAddSelectionPaths(imagePath, selectPathList.get(0))) {
                        //类型不同
                        Toast.makeText(this, getString(R.string.single_type_choose), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            boolean addSuccess = SelectionManager.getInstance().addImageToSelectList(imagePath);
            if (addSuccess) {
                mImagePickerAdapter.notifyItemChanged(position);
            } else {
                Toast.makeText(this, String.format(getString(R.string.select_image_max), mMaxCount), Toast.LENGTH_SHORT).show();
            }
        }
        updateCommitButton();
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
            String orientation = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);//视频方向
Log.e("qsd","qsd==orientation"+orientation);//横屏 是0.竖屏 90
//宽
            String width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
//高
            String height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
            mediaFile.setDuration(duration);
            mediaFile.setPath(videoPath);
            Log.e("qsd",videoPath+"qsd"+orientation+"width"+width+"height"+height);
            mediaFile.setW(width);
            mediaFile.setH(height);
       /*     if (orientation.equals("0")){
                if (Integer.parseInt(height)>Integer.parseInt(width)){
                    mediaFile.setW(height);
                    mediaFile.setH(width);
                }else {
                    mediaFile.setW(width);
                    mediaFile.setH(height);
                }

            }else {
                if (Integer.parseInt(height)>Integer.parseInt(width)){
                    mediaFile.setW(width);
                    mediaFile.setH(height);
                }else {
                    mediaFile.setW(height);
                    mediaFile.setH(width);
                }
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mediaFile;
    }
    /**
     * 更新确认按钮状态
     */
    private void updateCommitButton() {
        //改变确定按钮UI
        int selectCount = SelectionManager.getInstance().getSelectPaths().size();

        if (selectCount < mMaxCount) {

            mTvCommit.setText(String.format(getString(R.string.confirm_msg), selectCount, mMaxCount));
            return;
        }
        if (selectCount == mMaxCount) {

            mTvCommit.setText(String.format(getString(R.string.confirm_msg), selectCount, mMaxCount));
            return;
        }
    }

    /**
     * 跳转相机拍照
     */
    private void showCamera() {

        if (isSingleType) {
            //如果是单类型选取，判断添加类型是否满足（照片视频不能共存）
            ArrayList<String> selectPathList = SelectionManager.getInstance().getSelectPaths();
            if (!selectPathList.isEmpty()) {
                if (MediaFileUtil.isVideoFileType(selectPathList.get(0))) {
                    //如果存在视频，就不能拍照了
                    Toast.makeText(this, getString(R.string.single_type_choose), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        //拍照存放路径
        File fileDir = new File(Environment.getExternalStorageDirectory(), "Pictures");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        Intent intent = new Intent();
        if (type==1){
            mFilePath = fileDir.getAbsolutePath() + "/IMG_" + System.currentTimeMillis() + ".jpg";

            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        }else {
            mFilePath = fileDir.getAbsolutePath() + "/vedio_" + System.currentTimeMillis() + ".mp4";

            intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra (MediaStore.EXTRA_DURATION_LIMIT,1200);

        }
       /* Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);*/
        //设置视频录制的最长时间
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(this, ImagePickerProvider.getFileProviderName(this), new File(mFilePath));
        } else {
            uri = Uri.fromFile(new File(mFilePath));
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAPTURE);
    }

    /**
     * 当图片文件夹切换时，刷新图片列表数据源
     *
     * @param view
     * @param position
     */
    @Override
    public void onImageFolderChange(View view, int position) {
        Log.e("qsd","p"+position);
        MediaFolder mediaFolder = mMediaFolderList.get(position);
        //更新当前文件夹名
        String folderName = mediaFolder.getFolderName();
        if (!TextUtils.isEmpty(folderName)) {
            mTvImageFolders.setText(folderName);
        }
        //更新图片列表数据源
        mMediaFileList.clear();
        mMediaFileList.addAll(mediaFolder.getMediaFileList());
        mImagePickerAdapter.notifyDataSetChanged();

        mImageFolderPopupWindow.dismiss();
    }

    /**
     * 拍照回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CAPTURE) {
                Log.e("qsd","REQUEST_CODE_CAPTUR+11");
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + mFilePath)));


                //添加到选中集合

                    SelectionManager.getInstance().addImageToSelectList(mFilePath);
                ArrayList<String> list = new ArrayList<>(SelectionManager.getInstance().getSelectPaths());
                ArrayList<String> images = new ArrayList<>();
                if (mImagePaths != null && !mImagePaths.isEmpty()) {
                    for (int j = 0; j < mImagePaths.size(); j++) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).equals(mImagePaths.get(j))) {

                                list.remove(i);
                            }
                        }
                    }
                }
                for (String image : list) {
                    images.add(image);
                }
                if (type==1){
                Intent intent = new Intent();
                intent.putStringArrayListExtra(SELECT_RESULT, images);
                setResult(RESULT_OK, intent);

                SelectionManager.getInstance().removeAll();//清空选中记录
                finish();
                }
            }

            if (requestCode == REQUEST_SELECT_IMAGES_CODE) {
              //  if (type==1){
                Log.e("qsd","REQUEST_SELECT_IMAGES_CODE");

                    commitSelection();
                /*}else {
                        mediaFile=   getLocalVideo(mFilePath);
                        if (MediaFileUtil.isVideoFileType( mediaFile.getPath())){
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("MEDIA", mediaFile);
                            intent.putStringArrayListExtra(SELECT_RESULT2, images);
                            intent.putExtras(bundle);
                            setResult(RESULT_OK, intent);

                    }
                }*/

            }
        }
    }

    /**
     * 选择图片完毕，返回
     */
    private void commitSelection() {
        ArrayList<String> list = new ArrayList<>(SelectionManager.getInstance().getSelectPaths());
        ArrayList<String> images = new ArrayList<>();

        if (mImagePaths != null && !mImagePaths.isEmpty()) {
            for (int j = 0; j < mImagePaths.size(); j++) {
                for (int i=0;i<list.size();i++){
                    if (list.get(i).equals(mImagePaths.get(j))) {
                        // Log.e("qsd","path"+selectImages.get(i).getPath()+"===="+i);
                        list.remove(i);
                    }
                }
            }
        }
        for (String image : list) {
            images.add(image);
        }


        if (mediaFile!=null){
            Log.e("qsd","path"+"===="+mediaFile.getPath());
            if (MediaFileUtil.isVideoFileType( mediaFile.getPath())){
                mediaFile=   getLocalVideo( mediaFile.getPath());
                Intent intent = new Intent(this,FabuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MEDIA", mediaFile);//视频对象
                intent.putStringArrayListExtra(SELECT_RESULT2, images);//封面图 礼盒
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
            }  else {
                Intent intent = new Intent();
                intent.putStringArrayListExtra(SELECT_RESULT, images);
                setResult(RESULT_OK, intent);
            }
        }else {
            if (images.size()>0){

                if (MediaFileUtil.isVideoFileType( images.get(0))){
                    mediaFile=   getLocalVideo( images.get(0));
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MEDIA", mediaFile);
                    intent.putStringArrayListExtra(SELECT_RESULT2, images);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                }
            }
        }






        SelectionManager.getInstance().removeAll();//清空选中记录
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mImagePickerAdapter.notifyDataSetChanged();
        updateCommitButton();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            ConfigManager.getInstance().getImageLoader().clearMemoryCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
