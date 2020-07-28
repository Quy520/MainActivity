package com.fm.designstar.views.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.utils.image.RequestOptionsUtil;

import java.util.List;

public class ReviewImageAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    List<String> dataList;
    private RequestOptions rOptions;

    public ReviewImageAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (dataList.size()>9){
            return 9;
        }else{
            return dataList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_review_image, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        rOptions =  RequestOptionsUtil.getRoundedOptionsErr(context, R.mipmap.ico_default);
        Glide.with(context)
                .load(dataList.get(position))
                .apply(rOptions)
                .into(viewHolder.ivimage);

        return convertView;
    }

    public class ViewHolder {

        ImageView ivimage;
        public final View root;

        public ViewHolder(View itemView) {
            ivimage = (ImageView) itemView.findViewById(R.id.im_item);
            this.root = itemView;
        }
    }

    public void notifyDataSetChanged(List<String> dataList) {
        this.dataList = dataList;

        this.notifyDataSetChanged();
    }

}
