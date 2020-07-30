package com.fm.designstar.views.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

import android.net.Uri;
import android.os.Bundle;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;

public class ConversationActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_conversation;
    }

    @Override
    public void initPresenter() {
       // RongIM.getInstance().setCurrentUserInfo(new UserInfo(UserUtils.getUid(), UserUtils.getName(), Uri.parse(UserUtils.getHeader())));
//与我聊天的人的名字
        String sName = getIntent().getData().getQueryParameter("title");//获取昵称
//与我聊天的人的Uid
        String targetId = getIntent().getData().getQueryParameter("targetId");//获取对方id
        mTitle.setTitle("与" + sName + "聊天中");
    }

    @Override
    public void loadData() {

    }
}