package com.fm.designstar.views.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.utils.OssImageUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.Util;
import com.fm.designstar.utils.image.RequestOptionsUtil;

import java.util.List;

public class DesignervedioAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    List<String> dataList;
    List<String> time;
    private RequestOptions rOptions;

    public DesignervedioAdapter(Context context, List<String> dataList, List<String> time) {
        this.context = context;
        this.dataList = dataList;
        this.time = time;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (dataList.size()>2){
            return 2;
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
            convertView = inflater.inflate(R.layout.item_review_vdios, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivimage.getLayoutParams().height = ((Util.getScreenWidth(context )/4)+50);

        rOptions =  RequestOptionsUtil.getRoundedOptionsErr(context, R.mipmap.ico_default);
        Glide.with(context)
                .load(OssImageUtil.getThumbnailCut(dataList.get(position),(Util.getScreenWidth(context )/4)+50 ,Util.getScreenWidth(context )/2))
                .apply(rOptions)
                .into(viewHolder.ivimage);

        viewHolder.time.setText(TimeUtil.getSecondByFormat( new Double(Double.parseDouble(time.get(position))).longValue()*1000,""));

        return convertView;
    }

    public class ViewHolder {

        ImageView ivimage;
        TextView time;
        public final View root;

        public ViewHolder(View itemView) {
            ivimage = (ImageView) itemView.findViewById(R.id.im_item);
            time = (TextView) itemView.findViewById(R.id.time);
            this.root = itemView;
        }
    }

    public void notifyDataSetChanged(List<String> dataList) {
        this.dataList = dataList;

        this.notifyDataSetChanged();
    }

}
