package com.fm.designstar.photo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.widget.PhotoView;


import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

/**
 * @author DELL
 */
public class ShowPicturePagerAdapter extends PagerAdapter {
    //    private List<View> views = new ArrayList<>();
    private List<String> urlList = new ArrayList<>();
    private Activity mActivity;
    private View.OnClickListener listener;
    private int shopType = 1;
    private RequestOptions myOptions;


    public ShowPicturePagerAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        myOptions = new RequestOptions().fallback(R.mipmap.ico_default)
                .placeholder(R.mipmap.ico_default)
                .error(R.mipmap.ico_default);
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;

            myOptions = new RequestOptions()
                    .fallback(R.mipmap.ico_default)
                    .placeholder(R.mipmap.ico_default)
                    .error(R.mipmap.ico_default);

    }

    public void setImageViewOnclick(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<String> urls) {
        urlList.clear();
        urlList.addAll(urls);
        notifyDataSetChanged();
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return urlList.size();
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_circular_img, container, false);
        final PhotoView imageView = (PhotoView) view.findViewById(R.id.tiv_content);
        imageView.enable();
        imageView.setOnClickListener(listener);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(Constant.TRANSITION_ANIMATION_SHOW_PIC + position);
        }*/
        Glide.with(mActivity).load(urlList.get(position)).apply(myOptions).into(imageView);
        container.addView(view);
        return view;
    }
}

