package com.fm.designstar.views.main.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.SearchDesignerBean;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TextViewUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.CostomGrideView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

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
public class SearchDesignerAdapter extends BaseRecyclerAdapter<SearchDesignerAdapter.LikeViewHolder, SearchDesignerBean> {
    private RequestOptions myOptions;
    private String mySearch;



    private OnClickListener listener;
    public interface OnClickListener {
        void onGuamzhuClick(int position);

    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public SearchDesignerAdapter() {
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext, 0, R.mipmap.ico_default_3_2);


    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_designer, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {
        SearchDesignerBean findBean=    data.get(position);
        String address="";
        String tag="暂无头衔";


            Glide.with(mContext).load(findBean.getAvatar()).error(R.mipmap.defu_hand).into(holder.hand);

        holder.name.setText(findBean.getUserName());
        if (StringUtil.isBlank(findBean.getAddress())){
            address="上海";
        }else {
            address=findBean.getAddress();
        }
        if (findBean.getTagInfo()!=null){
            tag=findBean.getTagInfo().getTagName();
        }

        holder.type.setText(address +"·"+tag);//地址
        if (findBean.getStatus()==1){
         holder.   tv_guanzhu.setText("已关注");
            holder.check_guanzhu.setChecked(true);
            holder.tv_guanzhu.setTextColor(mContext.getResources().getColor(R.color.home_color2));


        }else {
            holder.   tv_guanzhu.setText("关注");
            holder.check_guanzhu.setChecked(false);
            holder.tv_guanzhu.setTextColor(mContext.getResources().getColor(R.color.black3));


        }
         if (!StringUtil.isBlank(mySearch)) {
            TextViewUtil.setPartialColor(mContext, holder.type, 0, mySearch.length(), R.color.notice);
        }
        if (findBean.getStringCode().equals(App.getConfig().getUserid())){
            holder.   tv_guanzhu.setVisibility(View.GONE);
            holder.check_guanzhu.setVisibility(View.GONE);
        }else {
            holder.   tv_guanzhu.setVisibility(View.VISIBLE);
            holder.check_guanzhu.setVisibility(View.VISIBLE);

        }
        holder.   check_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {

                        listener.onGuamzhuClick(position);

                }
            }
        });

        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent=  new Intent(mContext,InfoDetailActivity.class);
              intent.putExtra("UUID",findBean.getStringCode());
                mContext.startActivity(intent);
            }
        });
        holder.imgLay.setVisibility(View.GONE);
    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tv_type)
        TextView tv_guanzhu;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.check_guanzhu)
        CheckBox check_guanzhu;

        @BindView(R.id.gw2)
        CostomGrideView gw2;

        @BindView(R.id.im_item)
        ImageView ivimage;
        @BindView(R.id.re_item)
        RelativeLayout re_item;
        @BindView(R.id.imgLay)
        FrameLayout imgLay;
        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
