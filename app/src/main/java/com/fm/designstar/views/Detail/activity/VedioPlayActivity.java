package com.fm.designstar.views.Detail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.Detail.adapter.MyLayoutManager;
import com.fm.designstar.views.Detail.adapter.OnViewPagerListener;
import com.fm.designstar.views.Detail.adapter.VedioAdapter;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.fm.designstar.widget.FullWindowVideoView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VedioPlayActivity extends BaseActivity<UseMomentPresenter> implements UseMomentContract.View {
    private static final String TAG = "douyin";
    private HomeFindBean findBean;

  private RecyclerView mRecyclerView;
  private MyLayoutManager myLayoutManager;
  private VedioAdapter mAdapter;
  private List<HomeFindBean> findBeanList;
  private int pagenum=0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vedio_play;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        findBeanList=new ArrayList<>();
        findBean= (HomeFindBean) getIntent().getSerializableExtra("info");
        Log.e("qsd","findBean"+new Gson().toJson(findBean));
        findBeanList.add(findBean);
        mRecyclerView = findViewById(R.id.recycler);
        myLayoutManager = new MyLayoutManager(this, OrientationHelper.VERTICAL, false);
        mAdapter = new VedioAdapter();
        mRecyclerView.setLayoutManager(myLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addData(findBeanList);
        initListener();
        AudioManager audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 50, AudioManager.FLAG_PLAY_SOUND);
        mPresenter.UseMoment(pagenum,50,null,2,null);

    }
    private void initListener() {
        myLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean bottom) {
                Log.e(TAG, "选择位置:" + position + " 下一页:" + bottom+"rexy"+mAdapter.getData().size());
                playVideo(0);
                if (position==findBeanList.size()-1){

                }

            }
        });
    }


    private void releaseVideo(int index) {
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void playVideo(int position) {
        View itemView =mRecyclerView.getChildAt(position);
        Log.e("qsd",mRecyclerView+"p"+position+"=="+itemView);
        final FullWindowVideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });

        videoView.start();

        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    imgPlay.animate().alpha(0.7f).start();
                    videoView.pause();
                    isPlaying = false;
                } else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    @Override
    public void UseMomentSuccess(HomeFindResponse homeFindResponse) {
        mAdapter.addData(homeFindResponse.getResult());

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