package com.fm.designstar.views.main.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;

import java.math.BigDecimal;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;
import cn.jzvdother.Jzvd;
import cn.jzvdother.JzvdStd;

import static android.widget.ImageView.ScaleType.CENTER_CROP;
import static android.widget.ImageView.ScaleType.CENTER_INSIDE;
import static android.widget.ImageView.ScaleType.FIT_CENTER;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/14 16:38
 * @update : 2018/8/14
 */
public class HomeRecomAdapter extends BaseRecyclerAdapter<HomeRecomAdapter.LikeViewHolder, HomeFindBean> {
    private RequestOptions myOptions;
    private int type;
    private OnClickListener listener;
    public interface OnClickListener {
        void onLikeClick(int position,boolean b,CompoundButton compoundButton);
        void onGuanClick(int position,boolean b,CompoundButton compoundButton);

    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public HomeRecomAdapter(int type) {
       this.type=type;
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext, 0, R.mipmap.ico_default_3_2);
    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recome, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {

        HomeFindBean findBean = data.get(position);
        Glide.with(mContext).load(findBean.getHeadUri()).error(R.mipmap.defu_hand).into(holder.hand);
        if (findBean.getMultimediaList()!=null){
            if (findBean.getMultimediaList().size()>0){
                 holder.jzvdStd.setUp(
                        findBean.getMultimediaList().get(0).getMultimediaUrl(),"",
                         Jzvd.SCREEN_NORMAL);
                Glide.with(holder.jzvdStd.getContext()).load(findBean.getMultimediaList().get(0).getPreUrl()).apply(myOptions).into(holder.jzvdStd.posterImageView);
               // Glide.with(mContext).load(findBean.getMultimediaList().get(0).getPreUrl()).error(R.mipmap.ico_default_3_2).into(holder.imageView);
                JzvdStd .setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE);
                if (findBean.getMultimediaList().get(0).getWidth()<findBean.getMultimediaList().get(0).getHeight()){
                    holder.jzvdStd.posterImageView.setScaleType(CENTER_CROP);

                }

            }
        }
        holder.jzvdStd.titleTextView.setTextSize(16);
        holder.name.setText(findBean.getNickName());
        holder.title.setText(findBean.getContent());
        holder.likenum.setText(findBean.getLikes()+"");
        holder.views.setText(findBean.getViews()+"次播放");
        holder.message_num.setText(findBean.getComments()+"");
        if (StringUtil.isBlank(findBean.getTagName())){
            holder.tag_name.setText("暂无头衔");

        }else {
            holder.tag_name.setText(findBean.getTagName()+"");
        }
        if (findBean.getMultimediaList()!=null){
            if (!StringUtil.isBlank(findBean.getMultimediaList().get(0).getDuration())){
                BigDecimal bd = new BigDecimal(findBean.getMultimediaList().get(0).getDuration());
                holder.time.setText(TimeUtil.getSecondByFormat( bd.longValue()*1000,""));

            }
        }

        if (String.valueOf(findBean.getUserId()).equals(App.getConfig().getUserid())){
                holder.check_guanzhu.setVisibility(View.GONE);
                holder.tv_type.setVisibility(View.GONE);
            }else {
                holder.check_guanzhu.setVisibility(View.VISIBLE);
            holder.tv_type.setVisibility(View.VISIBLE);


        }
        if (data.get(position).getIsLike()==0){
            holder.check_like.setChecked(false);
        }else {
            holder.check_like.setChecked(true);
        }
        if (type==1){
            holder.check_guanzhu.setVisibility(View.GONE);
            holder.tv_type.setVisibility(View.GONE);
        }else {


        if (data.get(position).isFollow()){
            holder.check_guanzhu.setChecked(true);
            holder.tv_type.setText("已关注");
            holder.tv_type.setTextColor(mContext.getResources().getColor(R.color.home_color2));


        }else {
            holder.check_guanzhu.setChecked(false);
            holder.tv_type.setText("关注");
            holder.tv_type.setTextColor(mContext.getResources().getColor(R.color.black3));

        }
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
        holder.check_guanzhu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (listener != null) {
                    if (compoundButton.isPressed()) {
                        listener.onGuanClick(position, b, compoundButton);
                    }
                }
            }
        });

        holder.jzvdStd.fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("qsd",position+"ppp"+new Gson().toJson(findBean));
                Intent intent=  new Intent(mContext, VedioPlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info",findBean);
                intent.putExtras(bundle);
                mContext. startActivity(intent);
            }
        });

        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type!=1){
              Intent intent=  new Intent(mContext,InfoDetailActivity.class);
                intent.putExtra("UUID",data.get(position).getUserId()+"");
                mContext.startActivity(intent);
                }
            }
        });


    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foodImg)
        ImageView imageView;
        @BindView(R.id.video_player)
        JzvdStd jzvdStd;
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.views)
        TextView views;

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.check_like)
        CheckBox check_like;
        @BindView(R.id.check_guanzhu)
        CheckBox check_guanzhu;
        @BindView(R.id.likenum)
        TextView likenum;

        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.tag_name)
        TextView tag_name;

        @BindView(R.id.tv_type)
        TextView tv_type;

        @BindView(R.id.message_num)
        TextView message_num;


        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
