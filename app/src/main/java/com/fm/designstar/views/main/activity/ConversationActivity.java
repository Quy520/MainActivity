package com.fm.designstar.views.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;

public class ConversationActivity extends AppCompatActivity {


    LinearLayout re_title;

    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
          re_title=  findViewById(R.id.re_title);
        tv_title=  findViewById(R.id.tv_title);
        String sName = getIntent().getData().getQueryParameter("title");//获取昵称
//与我聊天的人的Uid
        String targetId = getIntent().getData().getQueryParameter("targetId");//获取对方id
        re_title.getLayoutParams().height = Tool.dip2px(this, 38);
       // ((ViewGroup.MarginLayoutParams) re_title.getLayoutParams()).topMargin = Util.getStatusBarH(this);
        tv_title.setText("在与"+sName+"聊天中");

    }

}