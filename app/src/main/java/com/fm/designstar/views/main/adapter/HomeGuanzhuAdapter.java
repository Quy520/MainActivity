package com.fm.designstar.views.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.utils.OssImageUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.image.RequestOptionsUtil;
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
public class HomeGuanzhuAdapter extends BaseRecyclerAdapter<HomeGuanzhuAdapter.LikeViewHolder, String> {
    private RequestOptions myOptions;
    private List<String> stringList=new ArrayList<>();
    private RequestOptions rOptions;
    private int oneWidth,Width;
    private int twoWidth;
    private int threeWidth;
    private int height;
    private ReviewImageAdapter reviewAdapter;

    public HomeGuanzhuAdapter() {
        stringList.add("qsd");
        stringList.add("efsefef");
        stringList.add("fwefesf");
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

        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mContext).load(App.getConfig().getUser_head()).error(R.mipmap.defu_hand).into(holder.hand);
        }

            switch (position) {

                case 0:
                    holder.imgLay.setVisibility(View.VISIBLE);
                    holder.oneImg.setVisibility(View.VISIBLE);
                    holder.gw.setVisibility(View.GONE);
                    Glide.with(mContext).load(OssImageUtil.getThumbnailCut(data.get(0), height, Width)).apply(rOptions).into(holder.oneImg);
                    break;
                case 1:
                    holder.imgLay.setVisibility(View.VISIBLE);
                    holder.oneImg.setVisibility(View.GONE);
                    holder.gw.setVisibility(View.GONE);
                    holder.gw2.setVisibility(View.VISIBLE);

                    reviewAdapter = new ReviewImageAdapter(mContext, data);
                    holder.gw2.setAdapter(reviewAdapter);
                    reviewAdapter.notifyDataSetChanged(data);
                    break;
                default:
                     holder.imgLay.setVisibility(View.VISIBLE);
                    holder.oneImg.setVisibility(View.GONE);
                    holder.gw.setVisibility(View.VISIBLE);
                    holder.gw2.setVisibility(View.VISIBLE);

                    reviewAdapter = new ReviewImageAdapter(mContext, data);
                    holder.gw.setAdapter(reviewAdapter);
                    reviewAdapter.notifyDataSetChanged(data);
                    break;
            }

        StffReviewGroupAdapter reviewGroupAdapter = new StffReviewGroupAdapter(mContext);
        if (stringList.size()>0){
            reviewGroupAdapter.addData(stringList);
        }
        reviewGroupAdapter.setMAX_SHOW(6);

        holder.myViewGroup.setAdapter(reviewGroupAdapter);

    }

    class LikeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hand)
        CircleImageView hand;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.imgLay)
        FrameLayout imgLay;

        @BindView(R.id.oneImg)
        ImageView oneImg;
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
