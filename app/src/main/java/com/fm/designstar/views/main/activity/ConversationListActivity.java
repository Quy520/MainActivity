package com.fm.designstar.views.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.messageEvent;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;

import org.greenrobot.eventbus.EventBus;

public class ConversationListActivity extends BaseActivity<GetInfoPresenter>  implements GetInfoContract.View  {



    UserInfo userInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_conversation_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        Log.e("qsd","getUserInfo"+"qqqqq");
        boolean isCacheUserInfo = true;
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            /**
             * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
             * @param userId 用户 ID
             */
            @Override
            public UserInfo getUserInfo(String userId) {


                return getUserInfoFromServer(userId);
            }

        }, isCacheUserInfo);

        ConversationListFragment conversationListFragment=new ConversationListFragment();
        // 此处设置 Uri. 通过 appendQueryParameter 去设置所要支持的会话类型. 例如
        // .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(),"false")
        // 表示支持单聊会话, false 表示不聚合显示, true 则为聚合显示
        Uri uri = Uri.parse("rong://" +
               getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();
        conversationListFragment.setUri(uri);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, conversationListFragment);
        transaction.commit();

        RongIM.getInstance().addUnReadMessageCountChangedObserver(observer, Conversation.ConversationType.PRIVATE);

    }

    private UserInfo getUserInfoFromServer(String userId) {
        mPresenter.getOtherUserInfo(userId);



        return null;
    }

    /**
     * 未读消息监听回调
     * @param i
     */
    private IUnReadMessageObserver observer = new IUnReadMessageObserver() {
        @Override
        public void onCountChanged(int i) {

            EventBus.getDefault().removeStickyEvent(messageEvent.class);
            EventBus.getDefault().post(new messageEvent(i,1));
        }
    };
    @Override
    public void GetotherLikeInfoSuccess(UserlikeResponse userlikeResponse) {


    }

    @Override
    public void GetuserlikeInfoSuccess(UserlikeResponse userlikeResponse) {

    }

    @Override
    public void getOtherUserInfoSuccess(UserinfoResponse response) {
        userInfo = new UserInfo(response.getCode()+"", response.getUserName(), Uri.parse(response.getAvatar()));

        RongIM.getInstance().refreshUserInfoCache(userInfo);

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