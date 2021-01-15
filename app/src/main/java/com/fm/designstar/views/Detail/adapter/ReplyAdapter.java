package com.fm.designstar.views.Detail.adapter;

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
import com.fm.designstar.model.bean.CommentsBean;
import com.fm.designstar.utils.OssImageUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;


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
public class ReplyAdapter extends BaseRecyclerAdapter<ReplyAdapter.CommentViewHolder, CommentsBean> {
    private RequestOptions myOptions;

    public ReplyAdapter() {
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext);

    }

    @Override
    public CommentViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(CommentViewHolder holder, final int position) {
        CommentsBean bean=data.get(position);

        if (!StringUtil.isBlank(bean.getAvatar())){
            Glide.with(mContext).load(bean.getAvatar()).error(R.mipmap.defu_hand).into(holder.hand);
        }

       holder. name.setText(bean.getUserName());
        holder.content.setText(bean.getContent());
        holder.date.setText(TimeUtil.getfriendlyTime(bean.getCreateTimestamp()));
        String uuid=bean.getUserId()+"";
        if (uuid.equals(App.getConfig().getUserid())){
        holder.delete.setVisibility(View.VISIBLE);
        }else {
            holder.delete.setVisibility(View.GONE);
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onClick(position);
                }
            }
        });
        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=  new Intent(mContext, InfoDetailActivity.class);
                intent.putExtra("UUID",bean.getUserId()+"");
                mContext.startActivity(intent);
            }
        });

    }

    public interface OnClickListener {
        void onClick(int position);
    }

    private OnClickListener listener;

    public void setOnClickListener(OnClickListener likeListener) {
        this.listener = likeListener;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hand)
        CircleImageView hand;

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.delete)
        TextView delete;
        @BindView(R.id.cons)
        ConstraintLayout constraintLayout;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
