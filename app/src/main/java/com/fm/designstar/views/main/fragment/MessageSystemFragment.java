package com.fm.designstar.views.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.messageEvent;
import com.fm.designstar.events.messageupdataEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.MessageBean;
import com.fm.designstar.model.server.response.MessageResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.Detail.activity.DTDetailsActivity;
import com.fm.designstar.views.Detail.activity.VedioPlayActivity;
import com.fm.designstar.views.Detail.contract.FindBidContract;
import com.fm.designstar.views.Detail.presenter.FindByIdPresenter;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.main.adapter.MessageAdapter;
import com.fm.designstar.views.main.contract.MessageContract;
import com.fm.designstar.views.main.presenter.MessagePresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MessageSystemFragment extends BaseFragment<MessagePresenter> implements MessageContract.View ,XRecyclerView.LoadingListener, FindBidContract .View{


    private int pagenum=1;
    @BindView(R.id.all_message)
    TextView all_message;
    @BindView(R.id.all_pl)
    TextView all_pl;
    @BindView(R.id.all_zan)
    TextView all_zan;
    @BindView(R.id.all_notice)
    TextView all_notice;
    @BindView(R.id.message_recy)
    XRecyclerView message_recy;
    private MessageAdapter messageAdapter;

    private String type;
    private List<String> urls=new ArrayList<>();
    private boolean isindex=false;
    MessageBean findBean;
    private FindByIdPresenter findByIdPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_system_mesg;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        findByIdPresenter=new FindByIdPresenter();
        findByIdPresenter.init(this);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            EventBus.getDefault().removeStickyEvent(messageEvent.class);
            EventBus.getDefault().post(new messageEvent(0,2));

        }
    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        mPresenter.Message(pagenum,10,type);
        message_recy.setPullRefreshEnabled(true);
        message_recy.setLoadingMoreEnabled(true);
        message_recy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        message_recy.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        message_recy.setLayoutManager(new LinearLayoutManager(mContext));
        message_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
   
        messageAdapter=new MessageAdapter();
        message_recy.setAdapter(messageAdapter);
   
        //4)实现 下拉刷新和加载更多 接口
        message_recy.setLoadingListener(this);

        messageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                findBean = messageAdapter.getData().get(position-1);
                Log.e("qsd","messageAdapter"+position+"==="+new Gson().toJson(findBean));
                findByIdPresenter.FindBid(findBean.getMomentId());

            }
        });
    }

    @OnClick({R.id.all_message, R.id.all_pl, R.id.all_zan,R.id.all_notice
    })
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.all_message:
                mPresenter.Message(pagenum,10,null);
                type="";
                setItem();
                all_message.setBackground(getResources().getDrawable(R.drawable.shape_yellow));
                all_message.setTextColor(getResources().getColor(R.color.notice));

                break;
            case R.id.all_pl:
                mPresenter.Message(pagenum,10,"3");
                type="3";
                setItem();
                all_pl.setBackground(getResources().getDrawable(R.drawable.shape_yellow));

                all_pl.setTextColor(getResources().getColor(R.color.notice));

                break;
            case R.id.all_zan:
                type="1";
                mPresenter.Message(pagenum,10,"1");
                setItem();
                all_zan.setBackground(getResources().getDrawable(R.drawable.shape_yellow));
                all_zan.setTextColor(getResources().getColor(R.color.notice));



                break;
            case R.id.all_notice:
                setItem();
                all_notice.setBackground(getResources().getDrawable(R.drawable.shape_yellow));

                all_notice.setTextColor(getResources().getColor(R.color.notice));


                break;




            default:
                break;
        }
    }

    private void setItem() {

        all_message.setBackground(getResources().getDrawable(R.drawable.btn_round_huise_shape));
        all_pl.setBackground(getResources().getDrawable(R.drawable.btn_round_huise_shape));
        all_zan.setBackground(getResources().getDrawable(R.drawable.btn_round_huise_shape));
        all_notice.setBackground(getResources().getDrawable(R.drawable.btn_round_huise_shape));
        all_message.setTextColor(getResources().getColor(R.color.edit_color));
        all_pl.setTextColor(getResources().getColor(R.color.edit_color));
        all_zan.setTextColor(getResources().getColor(R.color.edit_color));
        all_notice.setTextColor(getResources().getColor(R.color.edit_color));


    }


    @Override
    public void MessageSuccess(MessageResponse response) {

        if( pagenum==1){
            messageAdapter.clearData();
        }
        messageAdapter.addData(response.getResult());




    }

    @Override
    public void showLoading(String content, int code) {
        //  App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        message_recy.refreshComplete(); //下拉刷新完成
        message_recy.loadMoreComplete();
    }

    @Override
    public void showErrorMsg(String msg, int code) {

        ToastUtil.showToast(msg);
        message_recy.refreshComplete(); //下拉刷新完成
        message_recy.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        EventBus.getDefault().removeStickyEvent(messageEvent.class);
        EventBus.getDefault().post(new messageEvent(0,2));

        pagenum=1;
        mPresenter.Message(pagenum,10,type);



    }

    @Override
    public void onLoadMore() {
        pagenum++;
        mPresenter.Message(pagenum,10,type);



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(messageupdataEvent event) {
        pagenum=1;
        mPresenter.Message(pagenum,10,type);

    }

    @Override
    public void FindBidSuccess(HomeFindBean homeFindBean) {
           if (homeFindBean.getMediaType()==2){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info",homeFindBean);
                    startActivity(VedioPlayActivity.class, bundle);

                }else {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", homeFindBean);
                    startActivity(DTDetailsActivity.class, bundle);
                }
    }
}