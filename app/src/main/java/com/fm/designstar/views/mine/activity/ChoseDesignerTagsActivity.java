package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.UpdataEvent;
import com.fm.designstar.model.bean.TagBean;
import com.fm.designstar.model.bean.TagsBean;
import com.fm.designstar.model.bean.TagsInfoVoBean;
import com.fm.designstar.model.bean.TagsLogBean;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.Fabu.contract.GetTagContract;
import com.fm.designstar.views.Fabu.presenter.GetTagPresenter;
import com.fm.designstar.views.mine.adapter.DesignerPageAdapter;
import com.fm.designstar.views.mine.adapter.TagsListAdapter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChoseDesignerTagsActivity extends BaseActivity<GetTagPresenter>  implements GetTagContract.View {

    @BindView(R.id.recy_tagmansger)
    RecyclerView recy_mansger;
    @BindView(R.id.addTags)
    RelativeLayout addTags;
    private TagsListAdapter listAdapter;
    private int pagenum;
    private boolean hasnext;
    private TagsLogBean tagsLogBean;

    private List<TagsInfoVoBean> list=new ArrayList<>(); //保存数据的集合
    private List<TagsLogBean> logBeanList=new ArrayList<>(); //保存数据的集合
    private List<TagsInfoVoBean> list2=new ArrayList<>(); //保存数据的集合
    private List<String> list3=new ArrayList<>(); //保存数据的集合
    private List<Integer> list4=new ArrayList<>(); //保存数据的集合
    private  String tag,uuid;
  private   List<TagBean> tagsList=new ArrayList<>();
  private TagBean tagBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_chose_designer_tags;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        mTitle.setTitle(R.string.bq);
        uuid=getIntent().getStringExtra("uuid");

        mPresenter.GetTag(1);
        mTitle.setRightTitle("确认", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tagsList=new ArrayList<>();
                tagBean=new TagBean();
                tagBean.setTagId(list.get(pagenum).getId());
                tagBean.setTagName(list.get(pagenum).getTagName());
                tagBean.setTop(0);
                tagsList.add(tagBean);

                mPresenter.setTag(uuid,tagsList);
            }
        });



        recy_mansger.setLayoutManager(new LinearLayoutManager(mContext));
        recy_mansger.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));

        recy_mansger.setNestedScrollingEnabled(false);
        listAdapter=new TagsListAdapter();
        recy_mansger.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                        pagenum=position;
                    for (int i=0;i<logBeanList.size();i++){
                        if (position==i){
                            logBeanList.get(i).setSelect(1);
                        }else {
                            logBeanList.get(i).setSelect(0);

                        }
                    }
                         listAdapter.notifyDataSetChanged();

                tag=listAdapter.getData().get(position).getParentTag();


            }
        });
        addTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ChangInfoActivity.class);
                intent.putExtra("type",3);

                startActivityForResult(intent, 300);
            }
        });


    }

    @Override
    public void GetTagSuccess(TagInfoResponse infoResponse) {

        logBeanList.clear();
        listAdapter.clearData();
        list3=new ArrayList<>();
        list4=new ArrayList<>();
        for (int i=0;i<infoResponse.getList().size();i++){
            TagsBean tagsBean = infoResponse.getList().get(i);
            list2 = tagsBean.getTagInfoVoList();//标签对象集合
            list.addAll(tagsBean.getTagInfoVoList());

            for (int j=0;j<list2.size();j++){
                tagsLogBean=new TagsLogBean();
                tagsLogBean.setParentTag(list2.get(j).getTagName());
                tagsLogBean.setSelect(0);
                list3.add(list2.get(j).getTagName());//标签民资
                list4.add(list2.get(j).getId());//标签id
                logBeanList.add(tagsLogBean);
            }
        }

        listAdapter.addData(logBeanList);

    }

    @Override
    public void setTagSucess() {
        Intent intent = new Intent();
        intent.putExtra("resule", tag);
        setResult(RESULT_OK, intent);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdataEvent event) {

        mPresenter.GetTag(1);
    }
}