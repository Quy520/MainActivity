package com.fm.designstar.views.Detail.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.CommentsBean;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.main.adapter.HomeGuanzhuAdapter;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.JzvdStdTikTok;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.mob.MobSDK;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvdother.JZDataSource;
import cn.jzvdother.Jzvd;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.mob.tools.utils.Strings.getString;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/20 17:49
 * @update : 2018/8/20
 */
public class VedioAdapter extends BaseRecyclerAdapter<VedioAdapter.CommentViewHolder, HomeFindBean> {
    private RequestOptions myOptions;
    private List<String> imgs = new ArrayList<>();
   private List<String> videos = new ArrayList<>();

    public VedioAdapter() {
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext);

    }

    private OnClickListener listener;

    public interface OnClickListener {
        void onLikeClick(int position,boolean b,CompoundButton compoundButton);
        void oncommentClick(View view,int position);
        void ongaunzhutClick(int position);
        void back();


    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    @Override
    public CommentViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);

        return new CommentViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(CommentViewHolder holder, final int position) {
        HomeFindBean findBean=data.get(position);
        imgs.clear();
        videos.clear();
        if (!StringUtil.isBlank(findBean.getHeadUri())){
            Glide.with(mContext).load(findBean.getHeadUri()).error(R.mipmap.defu_hand).into(holder.hand);
        }
        if (data.get(position).getIsLike()==0){
            holder.check_like.setChecked(false);
        }else {
            holder.check_like.setChecked(true);
        }
        if (!StringUtil.isBlank(findBean.getContent())){
            holder.comment.setText(findBean.getContent());
        }
        holder.likenum.setText(findBean.getLikes()+"");
        holder.comenum.setText(findBean.getComments()+"");
        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.ongaunzhutClick(position);

                }
            }
        });
        if (findBean.getMomentType()==2){
          holder.  vedio_share.setVisibility(View.VISIBLE);
          holder.vedio_share.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  showShare(findBean);
              }
          });

        }else {
            holder.  vedio_share.setVisibility(View.GONE);

        }

        holder.check_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (listener != null) {
                    if (compoundButton.isPressed()) {
                        listener.onLikeClick(position, b, compoundButton);
                    }
                }
            }
        });

        holder.go_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                        listener.oncommentClick(view ,position);

                }
            }
        });
      /*  holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.back();

                }
            }
        });*/
    holder.re_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                        listener.oncommentClick(view,position);

                }
            }
        });


        if (findBean.getMultimediaList().size()>0) {
            for (int i=0;i<data.size();i++){
                    imgs.add(data.get(i).getMultimediaList().get(0).getPreUrl());
                    videos.add(data.get(i).getMultimediaList().get(0).getMultimediaUrl());
                }

/*
             Glide.with(mContext).load(imgs.get(position)).error(R.mipmap.defu_hand).into(holder.img_thumb);
            holder.video_view.setVideoURI(Uri.parse(videos.get(position)));*/
            JZDataSource jzDataSource = new JZDataSource(videos.get(position),
                   null);
            jzDataSource.looping = true;
            holder.videoplayer.setUp(jzDataSource, Jzvd.SCREEN_NORMAL);
            Glide.with(holder.videoplayer.getContext()).load(imgs.get(position)).into(holder.videoplayer.posterImageView);


        }





    }

    private void showShare(HomeFindBean findBean) {
        Log.e("qsd",""+"https://cde.laifuyun.com/moment/"+findBean.getMomentId());

        String title=findBean.getContent();
        if (StringUtil.isBlank(title)){
            title="星说短视频";
        }

        OnekeyShare oks = new OnekeyShare();

// title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("设计师给你分享了他的作品");
// titleUrl QQ和QQ空间跳转链接
       // oks.setTitleUrl("https://cde.laifuyun.com/moment/"+findBean.getMomentId());
// text是分享文本，所有平台都需要这个字段
        oks.setText(title);
// setImageUrl是网络图片的url
        oks.setImageUrl(findBean.getMultimediaList().get(0).getPreUrl());
// url在微信、Facebook等平台中使用
        oks.setUrl("https://cde.laifuyun.com/moment/"+findBean.getMomentId());
// 启动分享GUI
        oks.show(MobSDK.getContext());
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
   /*     @BindView(R.id.img_thumb)
        ImageView img_thumb;*/
      /*  @BindView(R.id.back)
        ImageView back;*/
    /*    @BindView(R.id.video_view)
        VideoView video_view;*/
        @BindView(R.id.videoplayer)
        JzvdStdTikTok videoplayer;
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.img_play)
        ImageView img_play;
        @BindView(R.id.vedio_share)
        ImageView vedio_share;
        @BindView(R.id.check_guanzhu)
        CheckBox check_like;
        @BindView(R.id.likenum)
        TextView likenum;
        @BindView(R.id.comenum)
        TextView comenum;
        @BindView(R.id.comment)
        TextView comment;
        @BindView(R.id.go_comment)
        TextView go_comment;



        @BindView(R.id.root_view)
        RelativeLayout root_view;
        @BindView(R.id.re_message)
        RelativeLayout re_message;


        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
