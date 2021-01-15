package com.fm.designstar.views.mine.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.FansBean;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
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
public class FansListAdapter extends BaseRecyclerAdapter<FansListAdapter.LikeViewHolder, FansBean> {
private int mtype;
    private OnClickListener listener;
    public interface OnClickListener {

        void onGuanClick(int position,boolean b,CompoundButton compoundButton);

    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public FansListAdapter(int type) {
        this.mtype=type;

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
        if (data.get(position).getStatus()==1){
            holder.check_guanzhu.setChecked(true);
            holder.tv_type.setText("已关注");
            holder.tv_type.setTextColor(mContext.getResources().getColor(R.color.home_color2));
        }else {
            holder.check_guanzhu.setChecked(false);
            holder.tv_type.setText("关注");
            holder.tv_type.setTextColor(mContext.getResources().getColor(R.color.black3));

        }
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

        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=  new Intent(mContext, InfoDetailActivity.class);
                if (mtype==1){
                    intent.putExtra("UUID",fansBean.getFollowedUserId()+"");
                }else {
                    intent.putExtra("UUID",fansBean.getUserId()+"");
                }
                mContext.startActivity(intent);
            }
        });

    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tv_type)
        TextView tv_type;

        @BindView(R.id.check_guanzhu)
        CheckBox check_guanzhu;
        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
