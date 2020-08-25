package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.UpdataEvent;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.model.bean.RecordBean;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.mine.contract.UptaDesignerContract;
import com.fm.designstar.views.mine.presenter.UpdataDesignerPresenter;
import com.fm.designstar.widget.CircleImageView;

import org.greenrobot.eventbus.EventBus;

public class DesignerMangerDetailActivity extends BaseActivity<UpdataDesignerPresenter> implements UptaDesignerContract.View {

private RecordBean recordBean;

    @BindView(R.id.hand)
    CircleImageView hand;
    @BindView(R.id.com_im)
    ImageView com_im;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.resons)
    TextView resons;
    @BindView(R.id.pass)
    TextView pass;
    @BindView(R.id.nopass)
    TextView nopass;
    @BindView(R.id.re_shenhe)
    RelativeLayout re_shenhe;
    @BindView(R.id.sure)
    TextView sure;
    @BindView(R.id.cancle)
    TextView cancle;
    @BindView(R.id.re_nopass)
    RelativeLayout re_nopass;
    @BindView(R.id.check_re)
    LinearLayout check_re;

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.lianxi)
    CheckBox lianxi;
    @BindView(R.id.clear)
    CheckBox clear;
    @BindView(R.id.other)
    CheckBox other;
    private int type=3;



    @Override
    public int getLayoutId() {
        return R.layout.activity_designer_manger_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setTitle("审核设计师");
        recordBean= (RecordBean) getIntent().getSerializableExtra("info");

        if (!StringUtil.isBlank(recordBean.getAvatar())){
            Glide.with(mActivity).load(recordBean.getAvatar()).error(R.mipmap.defu_hand).into(hand);
        } if (!StringUtil.isBlank(recordBean.getImgUrl())){
            Glide.with(mActivity).load(recordBean.getImgUrl()).error(R.mipmap.defu_hand).into(com_im);
        }
        name.setText(recordBean.getUserName());
        time.setText("认证于："+recordBean.getCreateTime());
        resons.setText("手机号："+recordBean.getMobile());
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.UptaDesigner("",true,recordBean.getId(),null);

            }
        });
        nopass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                re_shenhe.setVisibility(View.GONE);
                re_nopass.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                check_re.setVisibility(View.VISIBLE);
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type==3){
                    if (StringUtil.isBlank(editText.getText().toString())){
                        ToastUtil.showToast("填写拒绝理由。");
                        return;
                    }

                }
                mPresenter.UptaDesigner(editText.getText().toString(),false,recordBean.getId(),type);

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                re_shenhe.setVisibility(View.VISIBLE);
                re_nopass.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                check_re.setVisibility(View.GONE);
            }
        });

lianxi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    type=1;
                    lianxi.setChecked(true);
                    clear.setChecked(false);
                    other.setChecked(false);
                }else {
                    lianxi.setChecked(false);

                }

            }
        });
        clear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    type=2;
                    lianxi.setChecked(false);
                    clear.setChecked(true);
                    other.setChecked(false);
                }else {
                    clear.setChecked(false);

                }
            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    type=3;
                    lianxi.setChecked(false);
                    clear.setChecked(false);
                    other.setChecked(true);
                }else {
                    other.setChecked(false);

                }
            }
        });

    }

    @Override
    public void UptaDesignerSuccess() {
        EventBus.getDefault().removeStickyEvent(UpdataEvent.class);
        EventBus.getDefault().post(new UpdataEvent());
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