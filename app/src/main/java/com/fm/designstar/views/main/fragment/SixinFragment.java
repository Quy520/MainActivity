package com.fm.designstar.views.main.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;


public class SixinFragment extends BaseFragment<GetInfoPresenter>  implements GetInfoContract.View {


private int pagenum=1;
    UserInfo userInfo;
    private String uuid="";

    private String icon="https://ttmsocial-1256411278.cos.ap-shanghai.myqcloud.com/b_coldfish.png";

        @Override
        public int getLayoutId() {
                return R.layout.fragment_sin;
                }

        @Override
        public void initPresenter() {
            mPresenter.init(this);
                }

            @Override
            public void loadData() {
                boolean isCacheUserInfo = true;
                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

                    /**
                     * 获取设置用户信息. 通过返回的 userId 来封装生产用户信息.
                     * @param userId 用户 ID
                     */
                    @Override
                    public UserInfo getUserInfo(String userId) {
                        Log.e("qsd","getUserInfo"+userId);

                        return getUserInfoFromServer(userId);
                    }

                }, isCacheUserInfo);
                ConversationListFragment conversationListFragment=new ConversationListFragment();
                // 此处设置 Uri. 通过 appendQueryParameter 去设置所要支持的会话类型. 例如
                // .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(),"false")
                // 表示支持单聊会话, false 表示不聚合显示, true 则为聚合显示
                Uri uri = Uri.parse("rong://" +
                        getActivity().getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();

                conversationListFragment.setUri(uri);
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, conversationListFragment);
                transaction.commit();



                    }

    private UserInfo getUserInfoFromServer(String userId) {
        mPresenter.getOtherUserInfo(userId);
          /*  if (!userId.equals(App.getConfig().getUserid())){
                mPresenter.getOtherUserInfo(userId);
            }else {
                Log.e("qsd","=="+ App.getConfig().getUser_name()+Uri.parse(App.getConfig().getUser_head()));
                RongIM.getInstance().refreshUserInfoCache(new UserInfo(App.getConfig().getUserid(), App.getConfig().getUser_name(), Uri.parse(App.getConfig().getUser_head())));
            }*/


        return null;
    }


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