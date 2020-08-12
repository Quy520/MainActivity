package com.fm.designstar.views.Detail.adapter;

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
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


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
        void oncommentClick(int position);
        void ongaunzhutClick(int position);


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


        if (findBean.getMultimediaList().size()>0) {
            for (int i=0;i<data.size();i++){
                    imgs.add(data.get(i).getMultimediaList().get(0).getPreUrl());
                    videos.add(data.get(i).getMultimediaList().get(0).getMultimediaUrl());
                }


            Log.e("qsd",imgs+""+imgs.size()+"=="+videos+"==="+videos.size()+"'==''"+position);
            Glide.with(mContext).load(imgs.get(position)).error(R.mipmap.defu_hand).into(holder.img_thumb);
            holder.video_view.setVideoURI(Uri.parse(videos.get(position)));
        }



        holder.comment.setText(findBean.getComments()+"");


    }



    class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_thumb)
        ImageView img_thumb;
        @BindView(R.id.video_view)
        VideoView video_view;
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.img_play)
        ImageView img_play;
        @BindView(R.id.check_guanzhu)
        CheckBox check_like;
        @BindView(R.id.likenum)
        TextView likenum;

        @BindView(R.id.comenum)
        TextView comment;


        @BindView(R.id.root_view)
        RelativeLayout root_view;


        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
