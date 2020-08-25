package com.fm.designstar.views.main.adapter;

import android.content.Intent;
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
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/14 16:38
 * @update : 2018/8/14
 */
public class HomeRecomAdapter extends BaseRecyclerAdapter<HomeRecomAdapter.LikeViewHolder, HomeFindBean> {
    private RequestOptions myOptions;

    private OnClickListener listener;
    public interface OnClickListener {
        void onLikeClick(int position,boolean b,CompoundButton compoundButton);

    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public HomeRecomAdapter() {
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
                        findBean.getMultimediaList().get(0).getMultimediaUrl(),
                         JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                Glide.with(holder.jzvdStd.getContext()).load(findBean.getMultimediaList().get(0).getPreUrl()).into(holder.jzvdStd.thumbImageView);
                Glide.with(mContext).load(findBean.getMultimediaList().get(0).getPreUrl()).error(R.mipmap.ico_default_3_2).into(holder.imageView);
            }
        }
        holder.name.setText(findBean.getNickName());
        holder.title.setText(findBean.getContent());
        holder.likenum.setText(findBean.getLikes()+"");
        holder.message_num.setText(findBean.getComments()+"");
             if (String.valueOf(findBean.getUserId()).equals(App.getConfig().getUserid())){
                holder.check_guanzhu.setVisibility(View.GONE);
            }else {
                holder.check_guanzhu.setVisibility(View.VISIBLE);

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



        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=  new Intent(mContext,InfoDetailActivity.class);
                intent.putExtra("UUID",data.get(position).getUserId()+"");
                mContext.startActivity(intent);
            }
        });
    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foodImg)
        ImageView imageView;
        @BindView(R.id.video_player)
        JZVideoPlayerStandard jzvdStd;
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.check_like)
        CheckBox check_like;
        @BindView(R.id.check_guanzhu)
        CheckBox check_guanzhu;
        @BindView(R.id.likenum)
        TextView likenum;

        @BindView(R.id.message_num)
        TextView message_num;


        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
