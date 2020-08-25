package com.fm.designstar.views.Detail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvdother.Jzvd;
import cn.sharesdk.onekeyshare.OnekeyShare;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.GetCommetsEvent;
import com.fm.designstar.events.GetLikesEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.response.CommentsResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.utils.AndroidBug5497Workaround;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Detail.adapter.MyLayoutManager;
import com.fm.designstar.views.Detail.adapter.OnViewPagerListener;
import com.fm.designstar.views.Detail.adapter.ReplyAdapter;
import com.fm.designstar.views.Detail.adapter.VedioAdapter;
import com.fm.designstar.views.Detail.contract.DelCommentContract;
import com.fm.designstar.views.Detail.contract.GetCommentContract;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.contract.SendCommentContract;
import com.fm.designstar.views.Detail.presenter.DelCommentPresenter;
import com.fm.designstar.views.Detail.presenter.GetCommentPresenter;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.Detail.presenter.SendCommentPresenter;
import com.fm.designstar.views.login.activitys.LoginActivity;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.views.mine.contract.UseMomentContract;
import com.fm.designstar.views.mine.presenter.UseMomentPresenter;
import com.fm.designstar.widget.FullWindowVideoView;
import com.fm.designstar.widget.JzvdStdTikTok;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mob.MobSDK;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class VedioPlayActivity extends BaseActivity<UseMomentPresenter> implements UseMomentContract.View   , GetCommentContract.View, SendCommentContract.View, DelCommentContract.View ,XRecyclerView.LoadingListener , LikeContract.View{
    private static final String TAG = "douyin";
    private HomeFindBean findBean;

  private RecyclerView mRecyclerView;
  private MyLayoutManager myLayoutManager;
  private VedioAdapter mAdapter;
  private List<HomeFindBean> findBeanList;
  private int pagenum=0;
  private int pagenum2=0;
    PopupWindow popupWindow;
    WindowManager.LayoutParams lp;
    private ReplyAdapter commentAdapter;
    private SendCommentPresenter sendCommentPresenter;
    private DelCommentPresenter delCommentPresenter;
    private LikePresenter likePresenter;
    private GetCommentPresenter getCommentPresenter;
    View  mview;
    private int like=0;
    private int index=0;
    private boolean hasnext;
    private String id;
    XRecyclerView commentRecycler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vedio_play;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        getCommentPresenter=new GetCommentPresenter();
        getCommentPresenter.init(this);
        sendCommentPresenter=new SendCommentPresenter();
        sendCommentPresenter.init(this);
        delCommentPresenter=new DelCommentPresenter();
        delCommentPresenter.init(this);
        likePresenter=new LikePresenter();
        likePresenter.init(this);
    }

    @Override
    public void loadData() {
    //    AndroidBug5497Workaround.assistActivity(this);

        findBeanList=new ArrayList<>();
        findBean= (HomeFindBean) getIntent().getSerializableExtra("info");


        findBeanList.add(findBean);
        mRecyclerView = findViewById(R.id.recycler);
        myLayoutManager = new MyLayoutManager(this, OrientationHelper.VERTICAL, false);
        mAdapter = new VedioAdapter();
        mRecyclerView.setLayoutManager(myLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addData(findBeanList);
        initListener();
       // initListener2();
        AudioManager audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        int mCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (mCurrentVolume==0){
            mCurrentVolume=5;
        }
       // Log.e("qsd","手机音量"+mCurrentVolume);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVolume, AudioManager.FLAG_PLAY_SOUND);
        mPresenter.UseMoment(pagenum2,50,null,2,null);

        mAdapter.setOnClickListener(new VedioAdapter.OnClickListener() {
            @Override
            public void onLikeClick(int position, boolean b, CompoundButton compoundButton) {

                if (b){
                    if (compoundButton.isPressed()){
                        index=position;
                        like=1;
                        likePresenter.Like(1,mAdapter.getData().get(position).getMomentId());
                    }
                }else {
                    index=position;
                    like=0;
                    likePresenter.Like(1,mAdapter.getData().get(position).getMomentId());

                }


            }

            @Override
            public void oncommentClick(View  view,int position) {
                Log.e("qsd","oncommentClick");
                mview=view;
                index=position;
                getCommentPresenter.GetComment(pagenum,10,mAdapter.getData().get(position).getMomentId()+"");

            }

            @Override
            public void ongaunzhutClick(int position) {
               Intent intent=  new Intent(mContext, InfoDetailActivity.class);
                intent.putExtra("UUID",mAdapter.getData().get(position).getUserId()+"");
                startActivity(intent);
                finish();

            }

            @Override
            public void back() {
                Log.e("qsd","back");

                finish();

            }
        });

        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.videoplayer);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null && jzvd.jzDataSource != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos();
                    }
                }
            }
        });


    }

    private void initListener2() {

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
                Jzvd.releaseAllVideos();
               // releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean bottom) {
                Log.e(TAG, "选择位置:" + position + " 下一页:" + bottom+"rexy"+mAdapter.getData().size());
               // playVideo(0);
                autoPlayVideo(0);
                if (position==findBeanList.size()-1){
                  /*  pagenum++;
                    getCommentPresenter.GetComment(pagenum,10,mAdapter.getData().get(position).getMomentId()+"");
*/
                }

            }
        });
    }
    private void autoPlayVideo(int postion) {
        if (mRecyclerView == null || mRecyclerView.getChildAt(0) == null) {
            return;
        }
        JzvdStdTikTok player = mRecyclerView.getChildAt(0).findViewById(R.id.videoplayer);
        if (player != null) {
            player.startVideoAfterPreloading();
        }
    }

   /* private void releaseVideo(int index) {
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
*/
    @SuppressLint("WrongConstant")
    private void showWindow(View view,CommentsResponse commentsResponse) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.review_comment, null);

         commentRecycler=contentView.findViewById(R.id.commentRecycler);
        TextView go_comment=contentView.findViewById(R.id.go_comment);
        RelativeLayout inputLay=contentView.findViewById(R.id.inputLay);
        TextView send=contentView.findViewById(R.id.send);
        EditText editText=contentView.findViewById(R.id.editText);



        ImageView close=contentView.findViewById(R.id.close);

        commentRecycler.setPullRefreshEnabled(true);
        commentRecycler.setLoadingMoreEnabled(true);
        commentRecycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        commentRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        commentRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        commentRecycler.setNestedScrollingEnabled(false);
        commentAdapter = new ReplyAdapter();
        commentRecycler.setAdapter(commentAdapter);
        commentRecycler.setLoadingListener(this);

        commentAdapter.setOnClickListener(new ReplyAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                if (Tool.isFastDoubleClick()) {
                    return;
                }
                if (!App.getConfig().getLoginStatus()) {
                    startActivity(LoginActivity.class);
                    return;
                }

            }
        });

        commentAdapter.setOnClickListener(new ReplyAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                id=findBean.getMomentId()+"";

                delCommentPresenter.DelComment(commentAdapter.getData().get(position).getCode()+"",mAdapter.getData().get(index).getMomentId()+"");

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        go_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_comment.setVisibility(View.INVISIBLE);
                inputLay.setVisibility(View.VISIBLE);
                showKeyboard(editText);
                //showShare();
            }
        });

        inputLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_comment.setVisibility(View.VISIBLE);
                inputLay.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!App.getConfig().getLoginStatus()) {
                    startActivity(LoginActivity.class);
                    return;
                }
                if (StringUtil.isBlank(editText.getText().toString())) {
                    ToastUtil.showToast(R.string.reply_empty);
                    return;
                }
                id=mAdapter.getData().get(index).getMomentId()+"";
                 sendCommentPresenter.SendComment(editText.getText().toString(),2,mAdapter.getData().get(index).getMomentId()+"");
                inputLay.setVisibility(View.GONE);
                go_comment.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


        if (pagenum==0){
            commentAdapter.clearData();
        }


        commentAdapter.addData(commentsResponse.getResult());


        lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        mActivity.getWindow().setAttributes(lp);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setOnDismissListener(mDismissListener);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        int height = Util.getScreenHeight(mContext);
        popupWindow.setHeight(height *2/3);
        popupWindow.setSoftInputMode(popupWindow.INPUT_METHOD_NEEDED);

      //  popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        //当需要点击返回键,或者点击空白时,需要设置下面两句代码.
        //如果有背景，则会在contentView外面包一层PopupViewContainer之后作为mPopupView，如果没有背景，则直接用contentView作为mPopupView。
        //而这个PopupViewContainer是一个内部私有类，它继承了FrameLayout，在其中重写了Key和Touch事件的分发处理
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
        //为PopupWindow设置透明背景.
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(false);
        //设置PopupWindow进入和退出动画
        //设置PopupWindow显示的位置
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }



    private PopupWindow.OnDismissListener mDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            lp.alpha = 1f;
            mActivity.getWindow().setAttributes(lp);
            popupWindow.dismiss();
        }
    };


    @Override
    public void UseMomentSuccess(HomeFindResponse homeFindResponse) {
        mAdapter.addData(homeFindResponse.getResult());

    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {
        if (commentRecycler!=null){
            commentRecycler.refreshComplete(); //下拉刷新完成
            commentRecycler.loadMoreComplete();
        }


    }

    @Override
    public void showErrorMsg(String msg, int code) {
        if (commentRecycler!=null){
            commentRecycler.refreshComplete(); //下拉刷新完成
            commentRecycler.loadMoreComplete();
        }

    }

    @Override
    public void DelCommentSuccess() {
        pagenum=0;
        ToastUtil.showToast("评论删除成功");
        getCommentPresenter.GetComment(pagenum,10,mAdapter.getData().get(index).getMomentId()+""+"");

    }

    @Override
    public void GetCommentSuccess(CommentsResponse commentsResponse) {
        hasnext=commentsResponse.isHasNextPage();
        if (!StringUtil.isBlank(id)){
            EventBus.getDefault().removeStickyEvent(GetCommetsEvent.class);
            EventBus.getDefault().post(new GetCommetsEvent(id,commentsResponse.getTotal()));
        }
              if (mview!=null){
                    if (popupWindow != null) {
                        popupWindow.showAtLocation(mview, Gravity.BOTTOM, 0, 0);
                        popupWindow.setFocusable(true);
                        if (popupWindow.isShowing()) {
                            if (pagenum==0){
                                commentAdapter.clearData();
                            }
                            commentAdapter.addData(commentsResponse.getResult());
                        }
                    }else {
                        showWindow(mview, commentsResponse);
                    }

                }




    }

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
         findBean .setLikes(likeResponse.getLikes());
        findBean.setIsLike(like);
        mAdapter.notifyItemChanged(index);
    }

    @Override
    public void SendCommentSuccess() {
       pagenum=0;
         ToastUtil.showToast("评论成功");
        getCommentPresenter.GetComment(pagenum,10,mAdapter.getData().get(index).getMomentId()+"");



    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onRefresh() {
        pagenum=0;
        hasnext=true;
        getCommentPresenter.GetComment(pagenum,10,mAdapter.getData().get(index).getMomentId()+"");

    }

    @Override
    public void onLoadMore() {
        if (hasnext){
            pagenum++;
            getCommentPresenter.GetComment(pagenum,10,mAdapter.getData().get(index).getMomentId()+"");
        }else {
            commentRecycler.loadMoreComplete();

        }


    }
}