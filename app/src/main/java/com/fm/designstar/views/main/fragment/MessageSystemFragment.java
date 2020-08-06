package com.fm.designstar.views.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.model.server.response.MessageResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.main.adapter.MessageAdapter;
import com.fm.designstar.views.main.contract.MessageContract;
import com.fm.designstar.views.main.presenter.MessagePresenter;

import java.util.ArrayList;
import java.util.List;


public class MessageSystemFragment extends BaseFragment<MessagePresenter> implements MessageContract.View {


    private int pagenum=0;
    @BindView(R.id.all_message)
    TextView all_message;
    @BindView(R.id.all_pl)
    TextView all_pl;
    @BindView(R.id.all_zan)
    TextView all_zan;
    @BindView(R.id.all_notice)
    TextView all_notice;
    @BindView(R.id.message_recy)
    RecyclerView message_recy;
        private MessageAdapter messageAdapter;


    private List<String> urls=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_system_mesg;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mPresenter.Message(pagenum,10,null);

        message_recy.setLayoutManager(new LinearLayoutManager(mContext));
        message_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        message_recy.setNestedScrollingEnabled(false);
        messageAdapter=new MessageAdapter();
        message_recy.setAdapter(messageAdapter);
        message_recy.setHasFixedSize(true);
        message_recy.setFocusable(false);

    }

    @OnClick({R.id.all_message, R.id.all_pl, R.id.all_zan,R.id.all_notice
    })
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.all_message:
                mPresenter.Message(pagenum,10,null);

                setItem();
                all_message.setBackground(getResources().getDrawable(R.drawable.shape_yellow));
                all_message.setTextColor(getResources().getColor(R.color.notice));

                break;
            case R.id.all_pl:
                mPresenter.Message(pagenum,10,"3");

                setItem();
                all_pl.setBackground(getResources().getDrawable(R.drawable.shape_yellow));

                all_pl.setTextColor(getResources().getColor(R.color.notice));

                break;
            case R.id.all_zan:
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

        if( pagenum==0){
            messageAdapter.clearData();
        }
        messageAdapter.addData(response.getResult());




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
}