package com.fm.designstar.views.main.fragment;

import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.events.UpdatainfoEvent;
import com.fm.designstar.model.server.body.DesignerStatebody;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.mine.activity.BeDesignerActivity;
import com.fm.designstar.views.mine.activity.BlackListActivity;
import com.fm.designstar.views.mine.activity.DesignerMangerActivity;
import com.fm.designstar.views.mine.activity.DesignerRecordActivity;
import com.fm.designstar.views.mine.activity.FansListActivity;
import com.fm.designstar.views.mine.activity.MyActivitysActivity;
import com.fm.designstar.views.mine.activity.MyWorkActivity;
import com.fm.designstar.views.mine.activity.SettingActivity;
import com.fm.designstar.views.mine.activity.ShDesignerActivity;
import com.fm.designstar.views.mine.contract.FindDesignerContract;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.presenter.FindDesignerPresenter;
import com.fm.designstar.views.mine.presenter.GetInfoPresenter;
import com.fm.designstar.widget.CircleImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;


public class MineFragment extends BaseFragment<GetInfoPresenter> implements GetInfoContract.View , FindDesignerContract.View {
    @BindView(R.id.re_top)
    RelativeLayout re_top;
    @BindView(R.id.re_designer)
    RelativeLayout re_designer;
    @BindView(R.id.re_zp)
    RelativeLayout re_zp;
    @BindView(R.id.re_dt)
    RelativeLayout re_dt;
    @BindView(R.id.re_shdes)
    RelativeLayout re_shdes;
    @BindView(R.id.re_demanger)
    RelativeLayout re_demanger;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line2)
    View line2;


    @BindView(R.id.hand)
    CircleImageView hand;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.info2)
    TextView info2;
    @BindView(R.id.zp_num)
    TextView zp_num;
    @BindView(R.id.like_num)
    TextView like_num;
    @BindView(R.id.guanzhu_num)
    TextView guanzhu_num;
    @BindView(R.id.fans_num)
    TextView fans_num;
    @BindView(R.id.shtype)
    TextView shtype;

    @BindView(R.id.left)
    ImageView left;
    private FindDesignerPresenter designerPresenter;
    private int state=3;
    private DesignerStatebody mstatebody;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        designerPresenter=new FindDesignerPresenter();
        designerPresenter.init(this);

    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        ((ViewGroup.MarginLayoutParams) hand.getLayoutParams()).topMargin = Util.getStatusBarH(mContext)+ Tool.dip2px(mContext, 24);
        ((ViewGroup.MarginLayoutParams) re_top.getLayoutParams()).height =  Tool.dip2px(mContext, 210);
        ((ViewGroup.MarginLayoutParams) name.getLayoutParams()).topMargin = Util.getStatusBarH(mContext)+ Tool.dip2px(mContext, 24);
        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mActivity).load(App.getConfig().getUser_head()).error(R.mipmap.defu_hand).into(hand);
        }
        name.setText(App.getConfig().getUser_name());

        info.setText(App.getConfig().getAddress()+"|"+"中国设计星评委导师");
        if (StringUtil.isBlank(App.getConfig().getSingmarks())){
            info2.setText("我的征途是星辰大海");
        }else {
            info2.setText(App.getConfig().getSingmarks());
        }

        if (App.getConfig().getRole()==1){
            re_zp.setVisibility(View.GONE);
            re_dt.setVisibility(View.GONE);
            re_shdes.setVisibility(View.GONE);
            re_demanger.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            shtype.setVisibility(View.INVISIBLE);
            left.setVisibility(View.VISIBLE);
            re_designer.setVisibility(View.VISIBLE);
            shtype.setVisibility(View.INVISIBLE);
        }else if (App.getConfig().getRole()==2){
            re_shdes.setVisibility(View.GONE);
            re_demanger.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            shtype.setText("审核通过");
            left.setVisibility(View.INVISIBLE);
        }else {
            re_zp.setVisibility(View.GONE);
            re_dt.setVisibility(View.GONE);
            re_shdes.setVisibility(View.VISIBLE);
            re_demanger.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.GONE);
            re_designer.setVisibility(View.GONE);

        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter.GetuserLikeInfo();
            if (App.getConfig().getRole()!=3){
                designerPresenter.FindDesigner();
            }


        }
    }
    @OnClick({R.id.re_zp,R.id.zuopin,R.id.re_dt,R.id.re_guanzhu,R.id.re_fans, R.id.re_designer,R.id.re_shdes,R.id.re_demanger,R.id.re_black,R.id.re_setting})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.zuopin:
                if (App.getConfig().getRole()==1&&App.getConfig().getRole()==3){
                  return;
                }
                startActivity(MyWorkActivity.class);

                break;
                case R.id.re_guanzhu:
                    Intent intent=new Intent(mContext,FansListActivity.class);
                    intent.putExtra("UUID",App.getConfig().getUserid());
                    intent.putExtra("type",1);
                startActivity(intent);

                break;
               case R.id.re_fans:
                   Intent intent2=new Intent(mContext,FansListActivity.class);
                   intent2.putExtra("UUID",App.getConfig().getUserid());
                   intent2.putExtra("type",2);
                    startActivity(intent2);

                break;

            case R.id.re_zp:
                if (App.getConfig().getRole()==1&&App.getConfig().getRole()==3){
                    return;
                }
                startActivity(MyWorkActivity.class);

                break;

                case R.id.re_dt:
                    if (App.getConfig().getRole()==1&&App.getConfig().getRole()==3){
                        return;
                    }
                    startActivity(MyActivitysActivity.class);

                break;

            case R.id.re_designer:
                if (App.getConfig().getRole()==2){

                    return;

                }else  if (App.getConfig().getRole()==1){
                    if (state==0){
                        return;
                    }else if (state==2){
                        if (mstatebody!=null){
                            Intent intent3=new Intent(mContext,DesignerRecordActivity.class);
                            intent3.putExtra("Result",mstatebody);
                            startActivity(intent3);
                        }

                    } else {
                        startActivity(BeDesignerActivity.class);

                    }

                }



                break;


             case R.id.re_shdes:
                //
                 startActivity(DesignerMangerActivity.class);

                 break;

            case R.id.re_demanger:
                startActivity(ShDesignerActivity.class);


                break;

            case R.id.re_black:
                startActivity(BlackListActivity.class);


                break;
            case R.id.re_setting:
                startActivity(SettingActivity.class);

                break;

            default:
                break;
        }
    }

    @Override
    public void GetotherLikeInfoSuccess(UserlikeResponse userlikeResponse) {

    }

    @Override
    public void GetuserlikeInfoSuccess(UserlikeResponse userlikeResponse) {
        zp_num.setText(userlikeResponse.getMomentNum()+"");
        like_num.setText(userlikeResponse.getLikeNum()+"");
        guanzhu_num.setText(userlikeResponse.getFollowNum()+"");
        fans_num.setText(userlikeResponse.getFansNum()+"");

    }

    @Override
    public void getOtherUserInfoSuccess(UserinfoResponse response) {

    }

    @Override
    public void showLoading(String content, int code) {

    }

    @Override
    public void stopLoading(int code) {

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        ToastUtil.showToast(msg);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdatainfoEvent event) {
        if (!StringUtil.isBlank(App.getConfig().getUser_head())){
            Glide.with(mActivity).load(App.getConfig().getUser_head()).error(R.mipmap.defu_hand).into(hand);
        }
        name.setText(App.getConfig().getUser_name());
        info.setText(App.getConfig().getAddress()+"|"+"中国设计星评委导师");
        if (StringUtil.isBlank(App.getConfig().getSingmarks())){
            info2.setText("我的征途是星辰大海");
        }else {
            info2.setText(App.getConfig().getSingmarks());
        }
    }



    @Override
    public void DFindDesignerSuccess(DesignerStatebody statebody) {
        mstatebody=statebody;
        if (statebody.getImgUrl()==null){
            return;
        }
        state=statebody.getStatus();


        if (statebody.getStatus()==0){//审核中
            shtype.setVisibility(View.VISIBLE);
            shtype.setText("审核中");
            left.setVisibility(View.INVISIBLE);
        } else if (statebody.getStatus()==1) {//审核通过
            shtype.setVisibility(View.VISIBLE);


            re_shdes.setVisibility(View.GONE);
            re_demanger.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            shtype.setText("审核通过");
            left.setVisibility(View.INVISIBLE);
            App.getConfig().setRole(2);
        }else if (statebody.getStatus()==2) {//审核失败
            shtype.setVisibility(View.VISIBLE);
            shtype.setText("审核失败");
            left.setVisibility(View.VISIBLE);
        }

    }
}