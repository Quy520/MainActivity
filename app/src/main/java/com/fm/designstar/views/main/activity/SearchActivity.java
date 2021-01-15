package com.fm.designstar.views.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.FllowEvent;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.model.server.response.SearchDesignerResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.main.adapter.DesignerAdapter;
import com.fm.designstar.views.main.adapter.SearchDesignerAdapter;
import com.fm.designstar.views.main.contract.FindDesignerContract;
import com.fm.designstar.views.main.presenter.FindDesignerPresenter;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.views.mine.contract.followContract;
import com.fm.designstar.views.mine.presenter.followPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

public class SearchActivity extends BaseActivity <FindDesignerPresenter> implements FindDesignerContract.View , XRecyclerView.LoadingListener, followContract.View{

    @BindView(R.id.titleLayout)
    RelativeLayout titleLayout;
    @BindView(R.id.topLayout)
    LinearLayout topLayout;
    @BindView(R.id.cancle)
    TextView cancle;
    @BindView(R.id.searchEt)
    EditText searchEt;
    @BindView(R.id.clear)
    ImageView clear;
    @BindView(R.id.recy_designer)
    XRecyclerView recy_designer;
    private String keyWord = "";// 要输入关键字
    private SearchDesignerAdapter designerAdapter;
    private followPresenter presenter;
    private int positiona;
    private boolean idnext;
    private int pagenum=1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        presenter=new followPresenter();
        presenter.init(this);
    }

    @Override
    public void loadData() {
      //  titleLayout.getLayoutParams().height = Tool.dip2px(mContext, 44) + Util.getStatusBarH(mContext);
        ((ViewGroup.MarginLayoutParams) topLayout.getLayoutParams()).topMargin = Util.getStatusBarH(mContext)+20;
        searchEt.setFocusable(true);
        searchEt.requestFocus();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recy_designer.setPullRefreshEnabled(true);
        recy_designer.setLoadingMoreEnabled(true);
        recy_designer.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recy_designer.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        recy_designer.setLayoutManager(new LinearLayoutManager(mContext));
        recy_designer.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        recy_designer.setNestedScrollingEnabled(false);
        designerAdapter=new SearchDesignerAdapter();
        recy_designer.setAdapter(designerAdapter);
        recy_designer.setHasFixedSize(true);
        recy_designer.setFocusable(false);
        //4)实现 下拉刷新和加载更多 接口
        recy_designer.setLoadingListener(this);


        designerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent=  new Intent(mContext, InfoDetailActivity.class);
                intent.putExtra("UUID",designerAdapter.getData().get(position-1).getStringCode());
                mContext.startActivity(intent);
            }
        });
        designerAdapter.setOnClickListener(new SearchDesignerAdapter.OnClickListener() {
            @Override
            public void onGuamzhuClick(int position) {
                Log.e("qsd","qsd"+position);
                positiona=position;
                if (designerAdapter.getData().get(position).getStatus()==1){
                    presenter.canclefollow(designerAdapter.getData().get(position).getStringCode());
                }else {
                    presenter.follow(designerAdapter.getData().get(position).getStringCode());

                }

            }
        });
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>1){
                   clear.setVisibility(View.VISIBLE);
                }else {
                    clear.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEt.setText("");
                keyWord="";
            }
        });

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //这里注意要作判断处理，ActionDown、ActionUp都会回调到这里，不作处理的话就会调用两次
        if (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction()) {
            //处理事件
            if (!StringUtil.isBlankEdit(searchEt)) {
                keyWord=searchEt.getText().toString();
                mPresenter.FindDesigner(keyWord,1,10);
                closeKeyboard();
            } else {
                ToastUtil.showToast("搜索不能为空");
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void FindDesignerSuccess(SearchDesignerResponse homeFindResponse) {


        idnext=homeFindResponse.isHasNextPage();
        if (pagenum==1){
            designerAdapter.clearData();
        }
        if (pagenum==1){
            if (homeFindResponse.getResult()!=null){
         if (homeFindResponse.getResult().size()==0){
             ToastUtil.showToast("搜索无结果");
         }
            }
        }
        designerAdapter.addData(homeFindResponse.getResult());
        designerAdapter.notifyDataSetChanged();


    }

    @Override
    public void showLoading(String content, int code) {
        //  App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        recy_designer.refreshComplete(); //下拉刷新完成
        recy_designer.loadMoreComplete();
    }

    @Override
    public void showErrorMsg(String msg, int code) {

        ToastUtil.showToast(msg);
        recy_designer.refreshComplete(); //下拉刷新完成
        recy_designer.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        pagenum=1;
        if (pagenum==1){
            designerAdapter.clearData();
        }

        mPresenter.FindDesigner(keyWord,pagenum,10);
    }

    @Override
    public void onLoadMore() {
        if (idnext){
            pagenum++;
            mPresenter.FindDesigner(keyWord,pagenum,10);

        }else {
            recy_designer.loadMoreComplete();
        }

    }

    @Override
    public void followSuccess() {
        pagenum=1;

        mPresenter.FindDesigner(keyWord,pagenum,10);
        EventBus.getDefault().removeStickyEvent(FllowEvent.class);
        EventBus.getDefault().post(new FllowEvent());
    }

    @Override
    public void canclefollowSuccess() {
        pagenum=1;

        mPresenter.FindDesigner(keyWord,pagenum,10);
        EventBus.getDefault().removeStickyEvent(FllowEvent.class);
        EventBus.getDefault().post(new FllowEvent());
    }
}