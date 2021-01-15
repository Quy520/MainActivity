package com.fm.designstar.views.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.LocationMessage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;

public class ConversationActivity extends AppCompatActivity {


    LinearLayout re_title;

    TextView tv_title;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
          re_title=  findViewById(R.id.re_title);
        tv_title=  findViewById(R.id.tv_title);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String sName = getIntent().getData().getQueryParameter("title");//获取昵称
        //与我聊天的人的Uid
        String targetId = getIntent().getData().getQueryParameter("targetId");//获取对方id
        re_title.getLayoutParams().height = Tool.dip2px(this, 38);
       // ((ViewGroup.MarginLayoutParams) re_title.getLayoutParams()).topMargin = Util.getStatusBarH(this);
        tv_title.setText("在与"+sName+"聊天中");

        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {

            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {


                Intent intent=  new Intent(context, InfoDetailActivity.class);
                intent.putExtra("UUID",userInfo.getUserId());
                context.startActivity(intent);
                return false;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {


                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });
    }

}