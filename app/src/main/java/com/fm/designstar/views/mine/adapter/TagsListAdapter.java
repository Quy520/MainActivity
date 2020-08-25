package com.fm.designstar.views.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.model.bean.TagsLogBean;
import com.fm.designstar.utils.StringUtil;
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
public class TagsListAdapter extends BaseRecyclerAdapter<TagsListAdapter.LikeViewHolder, TagsLogBean> {


    public TagsListAdapter() {


    }

    @Override
    public LikeViewHolder mOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tagslist, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void mOnBindViewHolder(LikeViewHolder holder, int position) {
        TagsLogBean tagsLogBean=data.get(position);
        holder.tags.setText(tagsLogBean.getParentTag());
        if (tagsLogBean.getSelect()==1){
         holder.   chosed.setVisibility(View.VISIBLE);
        }else {
            holder.   chosed.setVisibility(View.GONE);

        }





    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tags)
        TextView tags;
        @BindView(R.id.chosed)
        ImageView chosed;

        public LikeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
