package com.fm.designstar.views.Fabu.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Util;
import com.fm.designstar.utils.image.RoundedTransformation;
import com.fm.designstar.views.Fabu.activity.EditVedioActivity;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.imagePicker.data.MediaFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/21 15:44
 * @update : 2018/8/21
 */
public class ReviewVedioAdapter extends RecyclerView.Adapter<ReviewVedioAdapter.ReviewPhotoViewHolder>  {
    private Context mContext;
    private ArrayList<String> mData = new ArrayList<>();

    private RequestOptions myOptions;
    private int Width,posion;
private MediaFile mediaFile;


    public ReviewVedioAdapter(Context context) {
        mContext = context;
        myOptions = new RequestOptions()
                .transform(new RoundedTransformation(mContext))
                .error(R.mipmap.ico_default)
                .placeholder(R.mipmap.ico_default)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Width = getWidth();
    }

    @NonNull
    @Override
    public ReviewPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_vedio, parent, false);
        return new ReviewPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewPhotoViewHolder holder, final int position) {
     /*   if (mediaFile!=null){

        holder. re_image.getLayoutParams().width = (Width -100) ;
        holder. re_image.getLayoutParams().height = ((Width -100) * Integer.parseInt(mediaFile.getH()))/Integer.parseInt(mediaFile.getW());
        holder .image.getLayoutParams().height =((Width -100) * Integer.parseInt(mediaFile.getH()))/Integer.parseInt(mediaFile.getW());
        holder. image.getLayoutParams().width = (Width -100);

        }*/
        if (position < mData.size()) {
            Log.e("qsd","mData"+mData.get(position)+"mediaFile"+mediaFile.getDuration());

            if (mData.get(position).contains("http")) {
                Glide.with(mContext).load(mData.get(position))

                        .into(holder.image);
                holder. close.setVisibility(View.VISIBLE);

                holder.time.setVisibility(View.VISIBLE);
                holder.biaji.setVisibility(View.VISIBLE);

                if (mediaFile!=null){
                    holder.time.setText(TimeUtil.getSecondByFormat( mediaFile.getDuration(),""));
                }

            }
             else if (mData.get(position).contains("mp4")){
                Glide.with(mContext).load(mData.get(position))

                        .into(holder.image);
                if (mData.get(position).contains("compose.mp4")){
                    holder.biaji.setVisibility(View.GONE);
                }else {
                    holder.biaji.setVisibility(View.VISIBLE);
                }
                holder. close.setVisibility(View.VISIBLE);
                holder.time.setVisibility(View.VISIBLE);


                if (mediaFile!=null){
                    holder.time.setText(TimeUtil.getSecondByFormat( mediaFile.getDuration(),""));
                }
            }else {
                Glide.with(mContext).load(new File(mData.get(position)))

                        .into(holder.image);
                holder. close.setVisibility(View.VISIBLE);
                holder.time.setVisibility(View.VISIBLE);
                holder.biaji.setVisibility(View.GONE);
                if (mediaFile!=null){
                    holder.time.setText(TimeUtil.getSecondByFormat( mediaFile.getDuration(),""));
                }

            }
        } else {


        }
        if (mData.size() == 0) {
            Glide.with(mContext).load(R.mipmap.icon_photo)

                    .into(holder.image);
           holder. close.setVisibility(View.GONE);
            holder.biaji.setVisibility(View.GONE);
            holder.time.setVisibility(View.GONE);
        }
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(mData.get(position));
                SpUtil.putString("MEDIA","");
                SpUtil. putString("mThumbnailPath","");
            }
        });
        holder.biaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.toEDIT(position);
                }


            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("qsd",position+"=="+mData.size()+"==="+holder.getLayoutPosition()+"==");
                if (holder.getLayoutPosition() < mData.size()) {
                   // NewShowPictureActivity.startAction(mContext, view, mData, holder.getLayoutPosition() );
                    if (listener != null) {
                        listener.pre();
                    }
                } else {
                    if (listener != null) {
                        listener.addPhoto();
                    }
                }
            }
        });
    }

    private AddPhotoListener listener;
    private DeletePhotoListener delistener;



    public interface AddPhotoListener {
        void addPhoto();
        void pre();
        void toEDIT(int p);

    }
    public interface DeletePhotoListener {
        void remove(String url);
    }

    public void addData(List<String> data) {
        if (data == null) {
            return;
        }
        //mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    } public void addData2( MediaFile mediaFile) {
        if (mediaFile == null) {
            return;
        }
        this.mediaFile=mediaFile;
        notifyDataSetChanged();
    }

    public void remove(String url) {
        if (mData != null) {
            mData.remove(url);
        }
        notifyDataSetChanged();
    }

    public ArrayList<String> getData() {
        return mData;
    }

    public void setListener(AddPhotoListener listener) {
        this.listener = listener;
    }  public void setdeListener(DeletePhotoListener listener) {
        this.delistener = listener;
    }
    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mData.size()>0){
           if (mData.get(0).endsWith(".mp4")){
                return 1;
            }
        }
        if (mData.size() < 9) {
            return 1;
        }
        if (mData.size()>9){
            return 1;
        }
        return 1;
    }

    class ReviewPhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.close)
        ImageView close;

        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.biaji)
        TextView biaji;
        @BindView(R.id.re_image)
        RelativeLayout re_image;

        public ReviewPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            re_image.getLayoutParams().width = (Width ) ;
            re_image.getLayoutParams().height = (Width ) / 3*2;
            image.getLayoutParams().height = (Width ) / 3*2;
            image.getLayoutParams().width = (Width );
        }
    }



    private int getWidth() {
        return Math.min(Util.getScreenWidth(mContext), Util.getScreenHeight(mContext));
    }
}
