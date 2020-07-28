package com.fm.designstar.views.main.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.utils.image.RequestOptionsUtil;
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
public class MainLikeAdapter extends BaseRecyclerAdapter<MainLikeAdapter.LikeViewHolder, String> {
    private RequestOptions myOptions;

    public MainLikeAdapter() {
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext, 5, R.mipmap.ico_default_3_2);
    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_like_, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {


    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foodImg)
        ImageView imageView;
        @BindView(R.id.name)
        TextView name;


        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
