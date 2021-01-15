package com.fm.designstar.views.main.fragment;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.messageEvent;
import com.fm.designstar.events.messageupdataEvent;
import com.fm.designstar.utils.FormatUtil;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.main.contract.AddContract;
import com.fm.designstar.views.main.presenter.AddPresenter;
import com.fm.designstar.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends BaseFragment<AddPresenter>  implements AddContract.View {


    @BindView(R.id.re_title)
    LinearLayout re_title;


    @BindView(R.id.re_guanzhu)
    RelativeLayout re_guanzhu;
    @BindView(R.id.tv_guanzhu)
    TextView tv_guanzhu;
    @BindView(R.id.im_guanzhu)
    ImageView im_guanzhu;

    @BindView(R.id.re_tuijain)
    RelativeLayout re_tuijain;
    @BindView(R.id.tv_tuijain)
    TextView tv_tuijain;
    @BindView(R.id.im_tuijain)
    ImageView im_tuijain;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;

    @BindView(R.id.interNo)
    TextView interNo;
    private List<BaseFragment> fragmentList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        re_title.getLayoutParams().height = Tool.dip2px(mContext, 35) + Util.getStatusBarH(mContext);
        ((ViewGroup.MarginLayoutParams) re_title.getLayoutParams()).topMargin = Util.getStatusBarH(mContext)-10;
        fragmentList.add(new MessageSystemFragment());
        fragmentList.add(new MessageSixinFragment());
        viewPager.setEnabled(false);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(1);
       // initronhgyun(App.getConfig().getRongyuntoken());

        mPresenter.Add(FormatUtil.getIMEI(mContext),App.getConfig().getUserToken(),"android");


    }

    private void initronhgyun(String rongyuntoken) {
        Log.d("qsd",rongyuntoken);
        RongIM.connect(rongyuntoken, new RongIMClient.ConnectCallback() {
            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                Log.e("qsd","onDatabaseOpened"+code);
                //消息数据库打开，可以进入到主页面
            }

            @Override
            public void onSuccess(String s) {
                Log.e("qsd","connectonSuccess"+s);

                //连接成功
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode errorCode) {
                if(errorCode.equals(RongIMClient.ConnectionErrorCode.RC_CONN_TOKEN_INCORRECT)) {
                    //从 APP 服务获取新 token，并重连
                //    mPresenter.Add(FormatUtil.getIMEI(mContext),App.getConfig().getUserid(),"android");

                } else {
                    //无法连接 IM 服务器，请根据相应的错误码作出对应处理
                }
            }
        });
    }

    @OnClick({R.id.re_guanzhu, R.id.re_tuijain
    })
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.re_guanzhu:
                interNo.setVisibility(View.GONE);
                setItem();
                tv_guanzhu.setTextSize(18);
                im_guanzhu.setVisibility(View.VISIBLE);
                 viewPager.setCurrentItem(0);
                tv_guanzhu.setTextColor(ContextCompat.getColor(getContext(),R.color.black3));

                EventBus.getDefault().removeStickyEvent(messageupdataEvent.class);
                EventBus.getDefault().post(new messageupdataEvent(0));
                break;
            case R.id.re_tuijain:
                initronhgyun(App.getConfig().getRongyuntoken());
                setItem();
                tv_tuijain.setTextSize(18);
                im_tuijain.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(1);
                tv_tuijain.setTextColor(ContextCompat.getColor(getContext(),R.color.black3));

                EventBus.getDefault().removeStickyEvent(messageupdataEvent.class);
                EventBus.getDefault().post(new messageupdataEvent(1));
                break;





            default:
                break;
        }
    }

    private void setItem() {
        tv_guanzhu.setTextSize(16);
        im_guanzhu.setVisibility(View.GONE);
        tv_guanzhu.setTextColor(ContextCompat.getColor(getContext(), com.aliyun.svideo.base.R.color.alivc_common_font_gray_999));

        tv_tuijain.setTextSize(16);
        im_tuijain.setVisibility(View.GONE);
        tv_tuijain.setTextColor(ContextCompat.getColor(getContext(), com.aliyun.svideo.base.R.color.alivc_common_font_gray_999));



    }

    @Override
    public void AddSuccess(String Response) {
       App.getConfig().setRongyuntoken(Response);
     initronhgyun(Response);
    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {

    }

    @Override
    public void showErrorMsg(String msg, int code) {

    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(messageEvent event) {
        Log.e("qsd","messageEvent"+event.getType());
        if (event.getType()==2){
            if (event.getTAG()>0){
                interNo.setVisibility(View.VISIBLE);

            }else {
                interNo.setVisibility(View.INVISIBLE);

            }

        }

    }
}