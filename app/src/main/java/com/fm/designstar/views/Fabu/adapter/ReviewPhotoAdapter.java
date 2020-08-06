package com.fm.designstar.views.Fabu.adapter;

import android.content.Context;

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
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.utils.image.RoundedTransformation;
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
public class ReviewPhotoAdapter extends RecyclerView.Adapter<ReviewPhotoAdapter.ReviewPhotoViewHolder>  {
    private Context mContext;
    private ArrayList<String> mData = new ArrayList<>();

    private RequestOptions myOptions;
    private int Width,posion;
private MediaFile mediaFile;


    public ReviewPhotoAdapter(Context context) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_photo, parent, false);
        return new ReviewPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewPhotoViewHolder holder, final int position) {

        if (position < mData.size()) {
            if (mData.get(position).contains("http")) {
                Glide.with(mContext).load(mData.get(position))

                        .into(holder.image);
                holder. close.setVisibility(View.VISIBLE);
                holder.time.setVisibility(View.GONE);
            }
             else if (mData.get(position).contains("mp4")){
                Glide.with(mContext).load(mData.get(position))

                        .into(holder.image);
                holder. close.setVisibility(View.VISIBLE);
                holder.time.setVisibility(View.VISIBLE);
                if (mediaFile!=null){
                    holder.time.setText(TimeUtil.getSecondByFormat( mediaFile.getDuration(),""));
                }
            }else {
                Glide.with(mContext).load(new File(mData.get(position)))

                        .into(holder.image);
                holder. close.setVisibility(View.VISIBLE);
                holder.time.setVisibility(View.GONE);

            }
        } else {
            Glide.with(mContext).load(R.mipmap.icon_photo)

                    .into(holder.image);
            holder. close.setVisibility(View.GONE);
            holder.time.setVisibility(View.GONE);
        }
        if (mData.size() == 0) {
            Glide.with(mContext).load(R.mipmap.icon_photo)

                    .into(holder.image);
           holder. close.setVisibility(View.GONE);

            holder.time.setVisibility(View.GONE);
        }
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(mData.get(position));

            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("qsd",position+"=="+mData.size()+"==="+holder.getLayoutPosition()+"==");
                if (holder.getLayoutPosition() < mData.size()) {
                   // NewShowPictureActivity.startAction(mContext, view, mData, holder.getLayoutPosition() );
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
    } public interface DeletePhotoListener {
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

    @Override
    public int getItemCount() {
        if (mData.size()>0){
           if (mData.get(0).endsWith(".mp4")){
                return 1;
            }
        }
        if (mData.size() < 9) {
            return mData.size() + 1;
        }
        if (mData.size()>9){
            return 9;
        }
        return mData.size();
    }

    class ReviewPhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.close)
        ImageView close;

        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.re_image)
        RelativeLayout re_image;

        public ReviewPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            re_image.getLayoutParams().height = (Width ) / 3;
            re_image.getLayoutParams().width = (Width ) / 3;
            image.getLayoutParams().height = (Width ) / 3;
            image.getLayoutParams().width = (Width ) / 3;
        }
    }



    private int getWidth() {
        return Math.min(Util.getScreenWidth(mContext), Util.getScreenHeight(mContext));
    }
}
