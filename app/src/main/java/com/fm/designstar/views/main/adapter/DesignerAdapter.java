package com.fm.designstar.views.main.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
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
public class DesignerAdapter extends BaseRecyclerAdapter<DesignerAdapter.LikeViewHolder, DesignerBean> {
    private RequestOptions myOptions;
    private DesignervedioAdapter reviewAdapter;
    private List<String> urlList=new ArrayList<>();
    private List<String> urlList2=new ArrayList<>();

    private OnClickListener listener;
    public interface OnClickListener {
        void onGuamzhuClick(int position);

    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public DesignerAdapter() {
        myOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext, 0, R.mipmap.ico_default_3_2);


    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_designer, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {
        DesignerBean findBean=    data.get(position);



            Glide.with(mContext).load(findBean.getHeadUri()).error(R.mipmap.defu_hand).into(holder.hand);

        holder.name.setText(findBean.getNickName());
        if (findBean.isFollow()){
         holder.   tv_guanzhu.setText("已关注");
        }else {
            holder.   tv_guanzhu.setText("关注");

        }
        if (findBean.getUserId().equals(App.getConfig().getUserid())){
            holder.   tv_guanzhu.setVisibility(View.GONE);
        }else {
            holder.   tv_guanzhu.setVisibility(View.VISIBLE);
        }
        holder.   tv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {

                        listener.onGuamzhuClick(position);

                }
            }
        });

        if (findBean.getDesignerMomentVos()!=null){
            urlList=new ArrayList<>();
          urlList2=new ArrayList<>();

            for (int i=0;i<findBean.getDesignerMomentVos().size();i++){
                if (findBean.getDesignerMomentVos().get(i).getMultimediaList().size()>0){
                    urlList.add(findBean.getDesignerMomentVos().get(i).getMultimediaList().get(0).getPreUrl());
                    urlList2.add(findBean.getDesignerMomentVos().get(i).getMultimediaList().get(0).getDuration());

                }

            }
            holder.gw2.setVisibility(View.VISIBLE);
            reviewAdapter = new DesignervedioAdapter(mContext, urlList,urlList2);


            holder.gw2.setAdapter(reviewAdapter);
            reviewAdapter.notifyDataSetChanged(urlList);

            holder.gw2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=  new Intent(mContext, VedioPlayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", findBean.getDesignerMomentVos().get(i));
                    intent.putExtras(bundle);
                    mContext. startActivity(intent);
                }
            });
        }else {
            holder.gw2.setVisibility(View.GONE);
        }

        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent=  new Intent(mContext,InfoDetailActivity.class);
              intent.putExtra("UUID",findBean.getUserId());
                mContext.startActivity(intent);
            }
        });
    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tv_guanzhu)
        TextView tv_guanzhu;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.check_guanzhu)
        CheckBox check_guanzhu;

        @BindView(R.id.gw2)
        CostomGrideView gw2;
        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
