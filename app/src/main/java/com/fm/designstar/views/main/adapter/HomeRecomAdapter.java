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
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.utils.StringUtil;
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
public class HomeRecomAdapter extends BaseRecyclerAdapter<HomeRecomAdapter.LikeViewHolder, HomeFindBean> {
    private RequestOptions myOptions;

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

        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mContext).load(App.getConfig().getUser_head()).error(R.mipmap.defu_hand).into(holder.hand);
        }
        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent=  new Intent(mContext,InfoDetailActivity.class);
                intent.putExtra("UUID",App.getConfig().getUser_head());
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


        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
