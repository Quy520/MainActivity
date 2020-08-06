package com.fm.designstar.views.Fabu.activity;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;



import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.Fabu.adapter.RvLeftAdapter;
import com.fm.designstar.views.Fabu.adapter.RvRightAdapter;
import com.fm.designstar.views.Fabu.contract.GetTagContract;
import com.fm.designstar.views.Fabu.presenter.GetTagPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;

import java.util.ArrayList;

public class ChoseTagActivity extends BaseActivity<GetTagPresenter>  implements GetTagContract.View {


    @BindView(R.id.rv_rvliandong_Left)
    RecyclerView mRvLeft;
    @BindView(R.id.rv_rvliandong_Right)
    RecyclerView mRvRight;

    private RvLeftAdapter mAdapterLeft;
    private RvRightAdapter mAdapterRight;

    private LinearLayoutManager mManagerLeft;
    private LinearLayoutManager mManagerRight;
    private boolean mIsFromClick;

    /**
     * 是否来自点击
     */


    @Override
    public int getLayoutId() {
        return R.layout.activity_chose_tag;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.bq);
        mPresenter.GetTag();
        mRvLeft.setLayoutManager(new LinearLayoutManager(this));
        mAdapterLeft = new RvLeftAdapter();
        mRvLeft.setAdapter(mAdapterLeft);

        mManagerRight = new LinearLayoutManager(this);
        mRvRight.setLayoutManager(mManagerRight);
        mAdapterRight = new RvRightAdapter();
        mRvRight.setAdapter(mAdapterRight);

        //造数据
        ArrayList<String> listLeft = new ArrayList<>();
        ArrayList<String> listRight = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listLeft.add("左边第" + i);
            listRight.add("右边第" + i);
        }
        mAdapterLeft.addData(listLeft);//就是设置list然后刷新
        mAdapterRight.addData(listRight);
        mAdapterLeft.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                mAdapterLeft.notifyDataSetChanged();
                mIsFromClick = true;//不走onScrolled，防止来回调
                //此方法可以置顶
                mManagerRight.scrollToPositionWithOffset(position, 0);
                mIsFromClick = false;//放开
            }
        });





    }






    @Override
    public void GetTagSuccess(TagInfoResponse infoResponse) {

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