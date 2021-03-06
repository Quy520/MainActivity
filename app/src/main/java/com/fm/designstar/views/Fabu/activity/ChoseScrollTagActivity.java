package com.fm.designstar.views.Fabu.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.AudioManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.GetTagsEvent;
import com.fm.designstar.events.HomeEvent;
import com.fm.designstar.model.bean.TagsBean;
import com.fm.designstar.model.bean.TagsInfoVoBean;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Fabu.contract.GetTagContract;
import com.fm.designstar.views.Fabu.presenter.GetTagPresenter;
import com.fm.designstar.widget.scrollchange.ScrollBean;
import com.fm.designstar.widget.scrollchange.ScrollLeftAdapter;
import com.fm.designstar.widget.scrollchange.ScrollRightAdapter;
import com.fm.designstar.widget.viegroup.MyViewGroup;
import com.google.gson.Gson;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ChoseScrollTagActivity extends BaseActivity<GetTagPresenter>  /*implements GetTagContract.View */{


    @BindView(R.id.rec_left)
     RecyclerView recLeft;
    @BindView(R.id.rec_right)
     RecyclerView recRight;
    @BindView(R.id.right_title)

     TextView rightTitle;

    private List<String> left;
    private List<ScrollBean> right;
    private List<String> right2=new ArrayList<>();
    private List<ScrollBean> right3;
    private ScrollLeftAdapter leftAdapter;
    private ScrollRightAdapter rightAdapter;
    //右侧title在数据中所对应的position集合
    private List<Integer> tPosition = new ArrayList<>();
    private GridLayoutManager rightManager;
    //title的高度
    private int tHeight;
    //记录右侧当前可见的第一个item的position
    private int first = 0;
    private List<TagsInfoVoBean> list=new ArrayList<>(); //保存数据的集合
    private ArrayList<TagsInfoVoBean> resule=new ArrayList<>(); //保存数据的集合
    private List<String> list3=new ArrayList<>(); //保存数据的集合


    @Override
    public int getLayoutId() {
        return R.layout.activity_chose_tag;
    }

    @Override
    public void initPresenter() {
      //  mPresenter.init(this);

    }

    @Override
    public void loadData() {
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
        mTitle.setTitle(R.string.bq);
        mPresenter.GetTag(2);

    mTitle.setRightTitle("确认", new View.OnClickListener() {
    @Override
    public void onClick(View view) {

            for (int i=0;i<right2.size();i++){
            for (int j=0;j<list3.size();j++){
                if (right2.get(i).equals(list3.get(j))){

                    resule.add(list.get(j));
                }
            }

        }
        Log.e("qsd","resule"+resule.size());
        ArrayList<TagsInfoVoBean> tagsInfoVoBeans = Util.removeDuplicteUsers(resule);

        Log.e("qsd","tagsInfoVoBeans"+tagsInfoVoBeans.size());


     /*   if (right2.size()!=resule.size()){


            resule.remove(0);
        }*/


        Intent intent = new Intent();
        intent.putExtra("resule", (Serializable) tagsInfoVoBeans);
        setResult(RESULT_OK, intent);
        finish();

    }
});





    }


    private void initLeft() {
        if (leftAdapter == null) {
            leftAdapter = new ScrollLeftAdapter(R.layout.scroll_left, null);
            recLeft.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            recLeft.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
            recLeft.setAdapter(leftAdapter);
        } else {
            leftAdapter.notifyDataSetChanged();
        }

        leftAdapter.setNewData(left);

        leftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    //点击左侧列表的相应item,右侧列表相应的title置顶显示
                    //(最后一组内容若不能填充右侧整个可见页面,则显示到右侧列表的最底端)
                    case R.id.item:
                        leftAdapter.selectItem(position);
                        rightManager.scrollToPositionWithOffset(tPosition.get(position), 0);
                        break;
                }
            }
        });
    }
    private void initRight() {

        rightManager = new GridLayoutManager(mContext, 3);

        if (rightAdapter == null) {
            rightAdapter = new ScrollRightAdapter(R.layout.scroll_right, R.layout.layout_right_title, null);
            recRight.setLayoutManager(rightManager);
            recRight.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.set(dpToPx(mContext, getDimens(mContext, R.dimen.dp3))
                            , dpToPx(mContext, getDimens(mContext, R.dimen.dp3))
                            , dpToPx(mContext, getDimens(mContext, R.dimen.dp3))
                            , dpToPx(mContext, getDimens(mContext, R.dimen.dp3)));
                }
            });
            recRight.setAdapter(rightAdapter);
        } else {
            rightAdapter.notifyDataSetChanged();
        }

        rightAdapter.setNewData(right);

        //设置右侧初始title
        if (right.get(first).isHeader) {
            rightTitle.setText(right.get(first).header);
        }

        recRight.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取右侧title的高度
                tHeight = rightTitle.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //判断如果是header
                if (right.get(first).isHeader) {
                    //获取此组名item的view
                    View view = rightManager.findViewByPosition(first);
                    if (view != null) {
                        //如果此组名item顶部和父容器顶部距离大于等于title的高度,则设置偏移量
                        if (view.getTop() >= tHeight) {
                            rightTitle.setY(view.getTop() - tHeight);
                        } else {
                            //否则不设置
                            rightTitle.setY(0);
                        }
                    }
                }

                //因为每次滑动之后,右侧列表中可见的第一个item的position肯定会改变,并且右侧列表中可见的第一个item的position变换了之后,
                //才有可能改变右侧title的值,所以这个方法内的逻辑在右侧可见的第一个item的position改变之后一定会执行
                int firstPosition = rightManager.findFirstVisibleItemPosition();
                if (first != firstPosition && firstPosition >= 0) {
                    //给first赋值
                    first = firstPosition;
                    //不设置Y轴的偏移量
                    rightTitle.setY(0);

                    //判断如果右侧可见的第一个item是否是header,设置相应的值
                    if (right.get(first).isHeader) {
                        rightTitle.setText(right.get(first).header);
                    } else {
                        rightTitle.setText(right.get(first).t.getType());
                    }
                }
                //遍历左边列表,列表对应的内容等于右边的title,则设置左侧对应item高亮
                for (int i = 0; i < left.size(); i++) {
                    if (left.get(i).equals(rightTitle.getText().toString())) {
                        leftAdapter.selectItem(i);
                    }
                }

                //如果右边最后一个完全显示的item的position,等于bean中最后一条数据的position(也就是右侧列表拉到底了),
                //则设置左侧列表最后一条item高亮
                if (rightManager.findLastCompletelyVisibleItemPosition() == right.size() - 1) {
                    leftAdapter.selectItem(left.size() - 1);
                }
            }
        });



    }

/*

    @Override
    public void GetTagSuccess(TagInfoResponse infoResponse) {
        list3=new ArrayList<>();
        list=new ArrayList<>();
        left=new ArrayList<>();
        right = new ArrayList<>();
          for (int i=0;i<infoResponse.getList().size();i++) {
              TagsBean tagsBean = infoResponse.getList().get(i);//每一个父类对象
              list.addAll(tagsBean.getTagInfoVoList());
              left.add(tagsBean.getParentTag());
              for (int j=0;j<tagsBean.getTagInfoVoList().size();j++){
                  list3.add(tagsBean.getTagInfoVoList().get(j).getTagName());

              }

          }
      for (int s=0;s<left.size();s++){
          right.add(new ScrollBean(true, left.get(s)));
          TagsBean tagsBean = infoResponse.getList().get(s);//每一个父类对象
          for (int j=0;j<tagsBean.getTagInfoVoList().size();j++){
              right.add(new ScrollBean(new ScrollBean.ScrollItemBean(tagsBean.getTagInfoVoList().get(j).getTagName(), left.get(s),s,j)));

          }
      }



        for (int i = 0; i < right.size(); i++) {
            if (right.get(i).isHeader) {
                //遍历右侧列表,判断如果是header,则将此header在右侧列表中所在的position添加到集合中
                tPosition.add(i);
            }
        }



        initLeft();
        initRight();



    }
*/
/*

    @Override
    public void setTagSucess() {

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
*/

    /**
     * 获得资源 dimens (dp)
     *
     * @param context
     * @param id      资源id
     * @return
     */
    public float getDimens(Context context, int id) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float px = context.getResources().getDimension(id);
        return px / dm.density;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5f);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetTagsEvent event) {

        right2=event.getName();


    }
}