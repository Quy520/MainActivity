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
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.HomeRecomAdapter;
import com.fm.designstar.views.main.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;


public class MessageSystemFragment extends BaseFragment {


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

    }

    @Override
    public void loadData() {
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=2400807498,1022710002&fm=15&gp=0.jpg");
        urls.add("https://ss1.baidu.com/6ON1bjeh1BF3odCf/it/u=3848182578,3212131776&fm=15&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179792&di=64dfa2fcbaed252126d9182ae67e053c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171107%2F62a8b9b47e6f41708416c4eb5f44fc6a.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595928179787&di=578959ca7cecfc13376805894ff6e52d&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F5377154223%2F0");
        message_recy.setLayoutManager(new LinearLayoutManager(mContext));
        message_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        message_recy.setNestedScrollingEnabled(false);
        messageAdapter=new MessageAdapter();
        message_recy.setAdapter(messageAdapter);
        message_recy.setHasFixedSize(true);
        message_recy.setFocusable(false);
        messageAdapter.addData(urls);

    }

    @OnClick({R.id.all_message, R.id.all_pl, R.id.all_zan,R.id.all_notice
    })
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.all_message:
                setItem();
                all_message.setBackground(getResources().getDrawable(R.drawable.shape_yellow));
                all_message.setTextColor(getResources().getColor(R.color.notice));

                break;
            case R.id.all_pl:
                setItem();
                all_pl.setBackground(getResources().getDrawable(R.drawable.shape_yellow));

                all_pl.setTextColor(getResources().getColor(R.color.notice));

                break;
            case R.id.all_zan:
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


}