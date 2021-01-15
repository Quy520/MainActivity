package com.fm.designstar.views.Fabu.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.utils.ConvertUtil;
import com.fm.designstar.utils.FileUtils;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.mine.contract.UploadFileContract;
import com.fm.designstar.views.mine.presenter.UploadFilePresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.media.MediaMetadataRetriever.OPTION_PREVIOUS_SYNC;
import static com.fm.designstar.views.Fabu.FabuActivity.SELECT_RESULT;
import static com.fm.designstar.views.Fabu.FabuActivity.SELECT_RESULT2;

public class EditVedioActivity extends BaseActivity <UploadFilePresenter>  implements UploadFileContract.View {

    @BindView(R.id.vv_player)
    VideoView videoView;
    @BindView(R.id.sb_select)
    SeekBar sbVideo;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    boolean isTouch = false;
    int totalTime;
    int currentTime;
    Bitmap bitmap;
    private OssTokenResponse ossTokenResponse;
    private File newFile;
    private String upload;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_vedio;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {

    mTitle.setTitle("编辑封面");
    mTitle.setRightTitle("确认", new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (bitmap!=null){
            upload = ConvertUtil.saveImageToGallery(mContext, bitmap);
            Log.e("qsd","=="+bitmap+"=="+upload);
            if (ossTokenResponse == null) {
                mPresenter.getOssToken();
                return;
            }else {
                mPresenter.uploadImage(ossTokenResponse, upload);

            }
            }
        }
    });

        String mp4Path = getIntent().getStringExtra("path");
        String url = getIntent().getStringExtra("url");
        Log.e("qsd","mp4Path===="+Environment.getExternalStorageDirectory().getPath()+getIntent().getStringExtra("path"));
        Log.e("qsd","mp4Path"+getIntent().getStringExtra("path"));
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(mp4Path);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                totalTime = videoView.getDuration();//毫秒
            }
        });
        Glide.with(mContext).load(url)
                .into(ivHead);
        sbVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(isTouch){
                    currentTime = (int)(((float) progress / 100) * totalTime);
                    videoView.seekTo(currentTime);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTouch = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isTouch = false;
                //获取第一帧图像的bitmap对象 单位是微秒
                 bitmap = mmr.getFrameAtTime((long) (currentTime * 1000), OPTION_PREVIOUS_SYNC);

                ivHead.setImageBitmap(bitmap);
                //upload = ConvertUtil.saveBitmap2("FM", bitmap);
                Log.e("qsd","=="+bitmap+"=="+upload);
            }
        });
        videoView.setVideoPath(mp4Path);
        videoView.seekTo(1);

    }

    @Override
    public void getOssTokenSuccess(OssTokenResponse response) {
        ossTokenResponse=response;

            mPresenter.uploadImage(ossTokenResponse, upload);



    }

    @Override
    public void uploadImageSuccess(String url) {
        ArrayList<String> images = new ArrayList<>();
        ToastUtil.showToast("编辑成功");
        images.add(url);
        Log.e("qsd","编辑成功"+url);
        Intent intent = new Intent();
        intent.putExtra(SELECT_RESULT2, images);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {

    }

    @Override
    public void showErrorMsg(String msg, int code) {

    }
}