package com.fm.designstar.views.mine.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.model.bean.DesignerPageBean;
import com.fm.designstar.model.bean.RecordBean;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
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
public class DesignerPageAdapter extends BaseRecyclerAdapter<DesignerPageAdapter.LikeViewHolder, DesignerPageBean> {


    public DesignerPageAdapter() {


    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pagelist, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {
        DesignerPageBean messageBean=data.get(position);

        Glide.with(mContext).load(messageBean.getAvatar()).error(R.mipmap.defu_hand).into(holder.hand);
        holder.name.setText(messageBean.getUserName());
        if (messageBean.getTagInfo()!=null){
            holder.content.setText(messageBean.getTagInfo().getTagName());
        }else {
            holder.content.setText("暂未设置标签");

        }




    }

    class LikeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.content)
        TextView content;


        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
