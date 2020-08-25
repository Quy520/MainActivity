package com.fm.designstar.events;

import android.content.Intent;

import com.fm.designstar.app.App;
import com.fm.designstar.app.AppManager;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.views.main.activity.MainActivity;
import com.fm.designstar.views.login.activitys.LoginActivity;
import com.fm.designstar.views.mine.contract.LoginOutContract;
import com.fm.designstar.views.mine.presenter.LoginOutPresenter;

import io.rong.imkit.RongIM;


/**
 * @author DELL
 */
public class EventController {

    private static volatile EventController instance = null;
    private long uploadCallLogTime = 0;
    private long LastUpLoadTime = 0;
    private String jsonContact;
    private String jsonApp;

    private int uid;

    private EventController() {
    }

    /******
     * 获取单例
     *
     * @return
     */
    public static EventController getInstance() {
        if (instance == null) {
            synchronized (EventController.class) {
                if (instance == null) {
                    instance = new EventController();
                }
            }
        }
        return instance;
    }


    /**********
     * eventBus 事件派发
     *
     * @param event
     */
    public void handleMessage(final BaseEvent event) {
        if (event.getUiEvent() == null) {
            //登陆
            if (event instanceof LogoutEvent) {
                //退出
                clearLoginStatus((LogoutEvent) event);
            }
            if (event instanceof VersionEvent) {
                if (!AppManager.getInstance().hasActivity(MainActivity.class)) {
                    Intent intent = new Intent(App.getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    App.getContext().startActivity(intent);
                    AppManager.getInstance().finishAllActivityButMain();
                } else {
                    AppManager.getInstance().finishAllActivityButMain();
                }
            }
        }
    }

    /************
     * 清除登录状态
     */
    private static void clearLoginStatus(LogoutEvent event) {
        App.getConfig().setUserToken("");
        App.getConfig().setUserPhone("");
        App.getConfig().setUser_head("");
        App.getConfig().setUser_name("");
        RongIM.getInstance().logout();
        App.getConfig().setIsgoHome(0);
        AppManager.getInstance().finishAllActivity();
        Intent loginIntent = new Intent(App.getContext(), LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(loginIntent);
      /*  if (event.getTAG() == 0) {
            LoginOutPresenter loginOutPresenter = new LoginOutPresenter();
            loginOutPresenter.init(new   LoginOutContract.View() {
                @Override
                public void showLoading(String content, int code) {

                }

                @Override
                public void stopLoading(int code) {

                }

                @Override
                public void showErrorMsg(String msg, int code) {

                }

                @Override
                public void   LoginOutSuccess() {

                }
            });
            loginOutPresenter.LoginOut();
        }*/
    }
}
