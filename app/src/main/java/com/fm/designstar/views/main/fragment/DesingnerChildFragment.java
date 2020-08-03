package com.fm.designstar.views.main.fragment;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseFragment;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.views.main.adapter.DesignerAdapter;
import com.fm.designstar.views.main.adapter.MessageAdapter;
import com.fm.designstar.views.main.contract.DesignerContract;
import com.fm.designstar.views.main.presenter.DesignerPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class DesingnerChildFragment extends BaseFragment<DesignerPresenter>  implements DesignerContract.View {


    private int pagenum=0;
    @BindView(R.id.designer_recy)
    RecyclerView designer_recy;
    private DesignerAdapter designerAdapter;

    private List<String> urls=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_designer_child;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);

    }

    @Override
    public void loadData() {
        designer_recy.setLayoutManager(new LinearLayoutManager(mContext));
        designer_recy.addItemDecoration(new SpaceItemDecoration().setBottom(Tool.dip2px(mContext, 1)));
        designer_recy.setNestedScrollingEnabled(false);
        designerAdapter=new DesignerAdapter();
        designer_recy.setAdapter(designerAdapter);
        designer_recy.setHasFixedSize(true);
        designer_recy.setFocusable(false);

        mPresenter.Designer(pagenum,10);
    }

    @Override
    public void DesignerSuccess(DesignerResponse homeFindResponse) {
        designerAdapter.addData(homeFindResponse.getResult());
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