package com.fm.designstar.views.main.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.utils.OssImageUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.CostomGrideView;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.fm.designstar.widget.viegroup.MyViewGroup;

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
public class HomeGuanzhuAdapter extends BaseRecyclerAdapter<HomeGuanzhuAdapter.LikeViewHolder, HomeFindBean> {
    private RequestOptions myOptions;
    private List<String> urlList=new ArrayList<>();
    private List<String> tagList=new ArrayList<>();
    private RequestOptions rOptions;
    private int oneWidth,Width;
    private int twoWidth;
    private int threeWidth;
    private int height;
    private ReviewImageAdapter reviewAdapter;

    public HomeGuanzhuAdapter() {

        rOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext);
    }
    public void setScreenWidth(int screenWidth, float dip2px) {
        oneWidth = (int) (screenWidth - (30 * dip2px + 0.5f));
        twoWidth = (int) ((oneWidth - (8 * dip2px + 0.5f)) / 2);
        threeWidth = (int) ((oneWidth - (16 * dip2px + 0.5f)) / 3);
        height = (int) (110 * dip2px + 0.5f);
        Width=(int) (210 * dip2px + 0.5f);
    }
    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_guanzhu, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {
        HomeFindBean findBean=    data.get(position);
        urlList=new ArrayList<>();
        tagList=new ArrayList<>();
        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mContext).load(findBean.getHeadUri()).error(R.mipmap.defu_hand).into(holder.hand);
        }

        holder.name.setText(findBean.getNickName());

        //holder.message_num.setText(data.get(position).getLikes()+"");
        holder.message_num.setText("23");
        holder.likenum.setText("123");
        holder.address.setText(findBean.getAddress());
        holder.content.setText(findBean.getComments());
        holder.time.setText(TimeUtil.getfriendlyTime(findBean.getCreateTimeStamp()));

        if (data.get(position).getIsLike()==0){
        holder.check_like.setChecked(false);
        }else {
            holder.check_like.setChecked(true);
        }

        holder.check_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });


        if (findBean.getMomentType()==2){//作品



        }else {//随手拍




        }
        for (int i=0;i<findBean.getTagsList().size();i++){
            tagList.add(findBean.getTagsList().get(i).getTagName());
        }
        if (findBean.getMediaType()==2){//视频
            holder.imgLay.setVisibility(View.VISIBLE);
            holder.oneImg.setVisibility(View.VISIBLE);
            holder.gw.setVisibility(View.GONE);
            Glide.with(mContext).load(OssImageUtil.getThumbnailCut(findBean.getMultimediaList().get(0).getMultimediaUrl(), height, Width)).apply(rOptions).into(holder.oneImg);

        }else {//图片

            if (findBean.getMultimediaList().size()>0){
                for (int i=0;i<findBean.getMultimediaList().size();i++){
                    urlList.add(findBean.getMultimediaList().get(i).getMultimediaUrl());
                }
            switch (findBean.getMultimediaList().size()) {


                case 0:
                   holder.imgLay.setVisibility(View.GONE);
                   break;
                case 1:
                    holder.imgLay.setVisibility(View.VISIBLE);
                    holder.oneImg.setVisibility(View.VISIBLE);
                    holder.gw.setVisibility(View.GONE);
                    Glide.with(mContext).load(OssImageUtil.getThumbnailCut(findBean.getMultimediaList().get(0).getMultimediaUrl(), height, Width)).apply(rOptions).into(holder.oneImg);

                    break;
                case 2:
                    holder.imgLay.setVisibility(View.GONE);
                    holder.ly_two_img.setVisibility(View.VISIBLE);
                    holder.gw.setVisibility(View.GONE);
                    Glide.with(mContext).load(findBean.getMultimediaList().get(0).getMultimediaUrl()).apply(rOptions).into(holder.oneImg2);
                    Glide.with(mContext).load(findBean.getMultimediaList().get(1).getMultimediaUrl()).apply(rOptions).into(holder.oneImg3);
                    break;
                case 4:
                    holder.imgLay.setVisibility(View.VISIBLE);
                    holder.oneImg.setVisibility(View.GONE);
                    holder.gw.setVisibility(View.GONE);
                    holder.gw2.setVisibility(View.VISIBLE);
                    if (findBean.getMultimediaList() == null) {
                        return;
                    }else {

                    }
                    reviewAdapter = new ReviewImageAdapter(mContext, urlList);
                    holder.gw2.setAdapter(reviewAdapter);
                    reviewAdapter.notifyDataSetChanged(urlList);
                    break;
                default:
                    holder.imgLay.setVisibility(View.VISIBLE);
                    holder.oneImg.setVisibility(View.GONE);
                    holder.gw.setVisibility(View.VISIBLE);
                    holder.gw2.setVisibility(View.VISIBLE);

                    reviewAdapter = new ReviewImageAdapter(mContext, urlList);
                    holder.gw.setAdapter(reviewAdapter);
                    reviewAdapter.notifyDataSetChanged(urlList);
                    break;
            }
            }

        }



        StffReviewGroupAdapter reviewGroupAdapter = new StffReviewGroupAdapter(mContext);
        if (urlList.size()>0){
            reviewGroupAdapter.addData(tagList);
        }
        reviewGroupAdapter.setMAX_SHOW(6);

        holder.myViewGroup.setAdapter(reviewGroupAdapter);
        holder.hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=  new Intent(mContext, InfoDetailActivity.class);
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
        @BindView(R.id.message_num)
        TextView message_num;
        @BindView(R.id.likenum)
        TextView likenum;
        @BindView(R.id.check_like)
        CheckBox check_like;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.content)
        TextView content;

        @BindView(R.id.time)
        TextView time;



        @BindView(R.id.imgLay)
        FrameLayout imgLay;
        @BindView(R.id.ly_two_img)
        LinearLayout ly_two_img;

        @BindView(R.id.oneImg)
        ImageView oneImg;

        @BindView(R.id.oneImg2)
        ImageView oneImg2;
        @BindView(R.id.oneImg3)
        ImageView oneImg3;
        @BindView(R.id.gw)
        CostomGrideView gw;
        @BindView(R.id.gw2)
        CostomGrideView gw2;

        @BindView(R.id.myViewGroup)
        MyViewGroup myViewGroup;
        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
