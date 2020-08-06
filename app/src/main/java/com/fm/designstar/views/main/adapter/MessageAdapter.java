package com.fm.designstar.views.main.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.MessageBean;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/14 16:38
 * @update : 2018/8/14
 */
public class MessageAdapter extends BaseRecyclerAdapter<MessageAdapter.LikeViewHolder, MessageBean> {
    private RequestOptions myOptions;

    public MessageAdapter() {
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext, 0, R.mipmap.ico_default);
    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {
        MessageBean messageBean = data.get(position);


            Glide.with(mContext).load(messageBean.getHeadUri()).error(R.mipmap.defu_hand).into(holder.hand);

        if (StringUtil.isBlank(messageBean.getUrl())){
            holder.imageView.setVisibility(View.GONE);
        }else {
            Glide.with(mContext).load(messageBean.getUrl()).error(R.mipmap.defu_hand).into(holder.hand);
        }
        holder.name.setText(messageBean.getNickName());
        holder.content.setText(messageBean.getContent());
        holder.time.setText(TimeUtil.getfriendlyTime(messageBean.getCreateTimeStamp()));


        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent=  new Intent(mContext,InfoDetailActivity.class);
             intent.putExtra("UUID",messageBean.getUserId());
                mContext.startActivity(intent);
            }
        });
    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foodImg)
        ImageView imageView;
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;


        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
