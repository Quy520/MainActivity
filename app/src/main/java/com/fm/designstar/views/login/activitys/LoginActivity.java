package com.fm.designstar.views.login.activitys;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.dialog.NoSercertDialog;
import com.fm.designstar.dialog.SercertDialog;
import com.fm.designstar.dialog.UpgradeDialog;
import com.fm.designstar.model.server.response.LoginResponse;
import com.fm.designstar.utils.FormatUtil;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TextViewUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.activity.MainActivity;
import com.fm.designstar.views.login.contract.LoginContract;
import com.fm.designstar.views.login.presenter.LoginPresenter;
import com.fm.designstar.views.main.activity.WebActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends BaseActivity<LoginPresenter>  implements LoginContract.View {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.longin)
    Button longin;
    @BindView(R.id.long_notice)
    TextView reg_notice;
    @BindView(R.id.pwd_open)
    CheckBox pwd_open;
    private SercertDialog  dialog;
    private NoSercertDialog nodialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        SpUtil.putBoolean("open",SpUtil.getBoolean("open"));
Log.e("qsd",""+SpUtil.getBoolean("open"));
        TextViewUtil.setPartialColors(mContext,reg_notice,reg_notice.getText().toString(),R.color.transparent);
        /*SpannableStringBuilder spannableString = new SpannableStringBuilder(reg_notice.getText().toString().replace(" ", ""));
        spannableString.setSpan(new TextAgreementClick(), 13, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new TextPrivacyClick(), 22, reg_notice.getText().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置点击事件，加上这句话才有效果
        reg_notice.setMovementMethod(LinkMovementMethod.getInstance());
        //设置点击后的颜色为透明（有默认背景）
        reg_notice.setHighlightColor(getResources().getColor(R.color.transparent));
        reg_notice.setText(spannableString);*/

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>10&&pwd.getText().length()>5){
                    longin.setBackground(getResources().getDrawable(R.drawable.btn_round_click_shape));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>5&&phone.getText().length()>10){
                    longin.setBackground(getResources().getDrawable(R.drawable.btn_round_click_shape));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        pwd_open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (buttonView.isPressed()) {
                        pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        pwd .setSelection(pwd.getText().length());
                    }
                    return;
                } else {
                    pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pwd .setSelection(pwd.getText().length());
                }


            }});
if ( !SpUtil.getBoolean("open")){
        if (dialog == null) {
            dialog = new SercertDialog(mContext);
            dialog.setOnClickListener(new SercertDialog.OnClickListener() {
                @Override
                public void up() {
                    dialog.dismiss();
                    if (nodialog == null) {
                        nodialog = new NoSercertDialog(mContext);
                        nodialog.setOnClickListener(new NoSercertDialog.OnClickListener() {
                            @Override
                            public void up() {
                                nodialog.dismiss();

                                SpUtil.putBoolean("open",false);
                                finish();
                                Log.e("qsd",""+SpUtil.getBoolean("open"));

                            }

                            @Override
                            public void close() {
                                SpUtil.putBoolean("open",true);
                                Log.e("qsd",""+SpUtil.getBoolean("open"));


                            }
                        });
                        nodialog.show();
                    }
                }

                @Override
                public void close() {
                    SpUtil.putBoolean("open",true);

                    Log.e("qsd",""+SpUtil.getBoolean("open"));

                }
            });
            dialog.show();
        }
}

    }
    @OnClick({R.id.back,R.id.longin, R.id.forgot, R.id.regist, R.id.qq,R.id.wx,R.id.long_notice})
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.back:
               finish();
                break;
                case R.id.longin:
                    if (StringUtil.isBlank(phone.getText().toString())) {
                        ToastUtil.showToast(R.string.phone_err);
                        return;
                    }
                    if (StringUtil.isBlank(pwd.getText().toString())) {
                        ToastUtil.showToast(R.string.pwd_err);
                        return;
                    }
                    if (pwd.getText().toString().contains(" ")) {
                        ToastUtil.showToast(R.string.pwd_err);
                        return;
                    }
                    if (pwd.getText().length() < 6 || pwd.getText().length() > 16) {
                        ToastUtil.showToast(R.string.pwd_err2);
                        return;
                    }

                    mPresenter.login(phone.getText().toString(),pwd.getText().toString(), FormatUtil.getIMEI(mContext));
                    closeKeyboard();
                break;
            case R.id.regist:
                Bundle bundle = new Bundle();
                bundle.putInt("Rode", 1);
                startActivity(RegisteredActivity.class,bundle);
                break;
            case R.id.forgot:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("Rode", 2);
                startActivity(RegisteredActivity.class,bundle2);
                break;
            case R.id.wx:
                Platform plat = ShareSDK.getPlatform(Wechat.NAME);
                ShareSDK.setActivity(this);//抖音登录适配安卓9.0
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                plat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        //遍历Map
                        Iterator ite =arg2.entrySet().iterator();
                        while (ite.hasNext()) {
                            Map.Entry entry = (Map.Entry) ite.next();
                            Object key = entry.getKey();
                            Object value = entry.getValue();
                            System.out.println(key + "： " + value);
                            Log.e("qsd","HashMap<String, Object>"+key + "： " + value);
                        }

                        if (arg1 == Platform.ACTION_USER_INFOR) {
                            PlatformDb platDB = arg0.getDb();//获取数平台数据DB
                            //通过DB获取各种数据
                            platDB.getToken();
                            platDB.getUserGender();
                            platDB.getUserIcon();
                            platDB.getUserId();
                            platDB.getUserName();
                            Log.e("qsd","Platform"+new Gson().toJson(platDB));

                        }
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub
                    }
                });
//执行登录，登录后在回调里面获取用户资料
                plat.showUser(null);
//获取账号为“3189087725”的资料
//weibo.showUser(“3189087725”);
                break;
           /* case R.id.long_notice:
                Bundle bundle3 = new Bundle();
                bundle3.putString("url", "https://cde.laifuyun.com/userLicenseAgreement");

                startActivity(WebActivity.class, bundle3);
                break;*/
            default:
                break;
        }
    }

    @Override
    public void loginSuccess(LoginResponse userInfoResponse) {
        App.getConfig().setUserToken(userInfoResponse.getToken());//token
        App.getConfig().setUser_head(userInfoResponse.getAvatar());//touxiang
        App.getConfig().setUser_name(userInfoResponse.getUserName());//nicheng
        App.getConfig().setUserPhone(userInfoResponse.getMobile());//phone
        App.getConfig().setAddress(userInfoResponse.getAddress());//dizhi
        App.getConfig().setBirthday(userInfoResponse.getBirthday());//shengeri
        App.getConfig().setEmail(userInfoResponse.getEmail());//youdjian
        App.getConfig().setRealName(userInfoResponse.getRealName());//zhengshixingm
        App.getConfig().setSingmarks(userInfoResponse.getSignature());//zhengshixingm
        App.getConfig().setRole(userInfoResponse.getRole());
        App.getConfig().setSex(userInfoResponse.getSex());
        App.getConfig().setIsgoHome(userInfoResponse.getCertificationMark());
        App.getConfig().setContactNumber(userInfoResponse.getContactNumber());

        if (userInfoResponse.getTagBean()!=null){
            App.getConfig().setTagname(userInfoResponse.getTagBean().getTagName());
        }
        App.getConfig().setUserid(userInfoResponse.getCode()+"");
        if (userInfoResponse.getCertificationMark()==0){
            startActivity(ComUserInfoActivity.class);
        }else {
            startActivity(MainActivity.class);
            ToastUtil.showToast("登录成功");
        }
        finish();

    }

    @Override
    public void showLoading(String content, int code) {
        App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        App.hideLoading();

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        App.hideLoading();
        ToastUtil.showToast(msg);

    }


}