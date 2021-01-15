package com.fm.designstar.photo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.bean.EnvBean;
import com.fm.designstar.model.bean.MultimediaListBean;
import com.fm.designstar.model.bean.TagsInfoVoBean;
import com.fm.designstar.utils.StatusBarUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 查看大图
 *
 * @author DELL
 */
public class ShowPictureActivity extends BaseActivity {
    @BindView(R.id.tv_title_content)
    TextView mTvTitleContent;
    @BindView(R.id.ll_title)
    RelativeLayout mLlTitle;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.tips)
    ImageView tips;
    private List<String> urls;//传入url集合，则只展示图片


    private ShowPicturePagerAdapter mAdapter;

    private String handUrl;
    private String name;
    private long commentTime;
    private int position,sum;
    private ArrayList<EnvBean> envList;
    private ArrayList<MultimediaListBean> tagsList;

    /**
     * 入口
     */
    public static void startAction(Context mContext, View view, List<EnvBean> envList, int position) {
        Intent intent = new Intent(mContext, ShowPictureActivity.class);
        intent.putParcelableArrayListExtra("envList", (ArrayList<? extends Parcelable>) envList);
        intent.putExtra("position", position);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContext.startActivity(intent);
//            if (TextUtils.isEmpty(view.getTransitionName())) {
//                view.setTransitionName(Constant.TRANSITION_ANIMATION_SHOW_PIC + position);
//            }
//            ActivityOptionsCompat options = ActivityOptionsCompat
//                    .makeSceneTransitionAnimation((Activity) mContext, view, view.getTransitionName());
//            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(mContext, intent, options.toBundle());
        }
    }

    /**
     * 入口
     */
    public static void startAction(Context mContext, View view, List<MultimediaListBean> urls, String handUrl, String name, long commentTime, int position) {
        Intent intent = new Intent(mContext, ShowPictureActivity.class);
        intent.putExtra("result", (Serializable) urls);
        intent.putExtra("position", position);
        intent.putExtra("handUrl", handUrl);
        intent.putExtra("name", name);
        intent.putExtra("commentTime", commentTime);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContext.startActivity(intent);
//            if (TextUtils.isEmpty(view.getTransitionName())) {
//                view.setTransitionName(Constant.TRANSITION_ANIMATION_SHOW_PIC + position);
//            }
//            ActivityOptionsCompat options = ActivityOptionsCompat
//                    .makeSceneTransitionAnimation((Activity) mContext, view, view.getTransitionName());
//            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(mContext, intent, options.toBundle());
        }
    }

    public static void startAction(Context mContext, View view, ArrayList<String> urls, String handUrl, String name, long commentTime, int position, int sum) {
        Intent intent = new Intent(mContext, ShowPictureActivity.class);
        intent.putStringArrayListExtra("urls", urls);
        intent.putExtra("position", position);
        intent.putExtra("handUrl", handUrl);
        intent.putExtra("name", name);
        intent.putExtra("commentTime", commentTime);
        intent.putExtra("sum",sum);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContext.startActivity(intent);
//            if (TextUtils.isEmpty(view.getTransitionName())) {
//                view.setTransitionName(Constant.TRANSITION_ANIMATION_SHOW_PIC + position);
//            }
//            ActivityOptionsCompat options = ActivityOptionsCompat
//                    .makeSceneTransitionAnimation((Activity) mContext, view, view.getTransitionName());
//            mContext.startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            ActivityCompat.startActivity(mContext, intent, options.toBundle());
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_show_picture;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((ViewGroup.MarginLayoutParams) mLlTitle.getLayoutParams()).topMargin = Util.getStatusBarH(mContext);
        }

        StatusBarUtil.setStatusBarColor(this, R.color.edit_color);

        urls = getIntent().getStringArrayListExtra("urls"); //url集合
        position = getIntent().getIntExtra("position", 0); //当前下标
        sum = getIntent().getIntExtra("sum", 0); //当前下标
        tagsList= ( ArrayList<MultimediaListBean>) getIntent().getSerializableExtra("result");


        envList = getIntent().getParcelableArrayListExtra("envList");
if (tagsList!=null){
    urls = new ArrayList<>();
        if (tagsList.size()>0) {
            for (int i = 0; i < tagsList.size(); i++) {
                urls.add(tagsList.get(i).getMultimediaUrl());
            }
        }
}
      /*  if (envList == null) {
           // handUrl = getIntent().getStringExtra("handUrl");
            name = getIntent().getStringExtra("name");
           // commentTime = getIntent().getLongExtra("commentTime", 0);
            if ( StringUtil.isBlank(name)) {
                tips.setVisibility(View.GONE);
            } else {
                tips.setVisibility(View.VISIBLE);
            }
        } else {
            urls = new ArrayList<>();
            for (EnvBean envBean : envList) {
                urls.add(envBean.getImageUrl());
            }
            setData(position);
        }*/

        mAdapter = new ShowPicturePagerAdapter(this);
        mViewpager.setAdapter(mAdapter);
        mAdapter.setImageViewOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLlTitle.getVisibility() == View.VISIBLE) {
                    mLlTitle.setVisibility(View.GONE);
                } else {
                    mLlTitle.setVisibility(View.VISIBLE);
                }
            }
        });
        Log.e("urls", urls + "");
        mAdapter.setData(urls);
        mViewpager.setCurrentItem(position);
        if (sum>0){
            mTvTitleContent.setText(mViewpager.getCurrentItem() + 1 + "/" + sum);
        }else {

            mTvTitleContent.setText(mViewpager.getCurrentItem() + 1 + "/" + urls.size());
        }
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int positio) {
                position = positio;
                if (sum>0){
                    mTvTitleContent.setText(mViewpager.getCurrentItem() + 1 + "/" + sum);
                }else {
                    mTvTitleContent.setText(positio + 1 + "/" + urls.size());
                }
                setData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void setData(int position){
        if (envList != null) {
            if (StringUtil.isBlank(envList.get(position).getImageDesc())) {
                tips.setVisibility(View.GONE);
            } else {
                tips.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick({R.id.viewpager, R.id.iv_title_left, R.id.tips})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                onBackPressed();
                break;
            case R.id.viewpager:
                if (mLlTitle.getVisibility() == View.VISIBLE) {
                    mLlTitle.setVisibility(View.GONE);
                } else {
                    mLlTitle.setVisibility(View.VISIBLE);
                }
                break;

            default:
                break;
        }
    }

    private RequestOptions options;


}
