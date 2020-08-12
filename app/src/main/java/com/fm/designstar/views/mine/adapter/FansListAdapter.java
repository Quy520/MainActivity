package com.fm.designstar.views.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.FansBean;
import com.fm.designstar.utils.StringUtil;
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
public class FansListAdapter extends BaseRecyclerAdapter<FansListAdapter.LikeViewHolder, FansBean> {


    public FansListAdapter() {


    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fanslist, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {
        FansBean fansBean = data.get(position);
        Glide.with(mContext).load(fansBean.getAvatar()).error(R.mipmap.defu_hand).into(holder.hand);
        holder.name.setText(fansBean.getUserName());



    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;

        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
