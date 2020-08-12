package com.fm.designstar.views.main.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> items;
    private ClickListener clickListener;
    private LifecycleOwner mowner ;
    private MainLikeAdapter likeAdapter;

    public NewsListRecycleAdapter(Context context, LifecycleOwner mowner, List<String> items) {
        this.context = context;
        this.items = items;
        this.mowner=mowner;

    }

    public interface ClickListener {
        void onItemClick(View v, int position, List<String> items);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recome1, parent, false);

                //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list_style_1, parent, false);
                viewHolder = new ViewHolderOne(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recome2, parent, false);
                viewHolder = new ViewHolderThree(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final XRecyclerView.ViewHolder holder, int position) {
        Log.e("qsd","3张图 情况"+position);
        switch (getItemViewType(position)) {
            case 0: //1张图 情况
                Log.e("qsd","1张图 情况"+position);
                ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
                ImageAdapter imageAdapter =new ImageAdapter(items);

                viewHolderOne. banner2.setAdapter(imageAdapter)
                        .addBannerLifecycleObserver(mowner)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(context))//设置指示器
                        .setOnBannerListener((data, i) -> {

                        });
                viewHolderOne. banner2.setDatas(items);
                break;
            case 1: //3张图 情况
                Log.e("qsd","3张图 情况"+position);

                ViewHolderThree viewHoldertwo = (ViewHolderThree) holder;

                viewHoldertwo .likeRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                viewHoldertwo . likeRecycler.addItemDecoration(new SpaceItemDecoration().setRight(Tool.dip2px(context, 7)));
                likeAdapter = new MainLikeAdapter();
                viewHoldertwo .likeRecycler.setAdapter(likeAdapter);
             //   likeAdapter.addData(items);
                likeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                clickListener.onItemClick(holder.itemView, pos, items);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //1张图
    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.banner2)
        Banner banner2;


        public ViewHolderOne(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //3张图
    public static class ViewHolderThree extends RecyclerView.ViewHolder {
        @BindView(R.id.recy_gusslike)
        RecyclerView likeRecycler;


        public ViewHolderThree(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}