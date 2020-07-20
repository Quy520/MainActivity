package com.fm.designstar.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.fm.designstar.app.App;
import com.fm.designstar.utils.TUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

import static com.fm.designstar.app.App.getContext;

/**
 * fragment基类
 *
 * @author admin
 */
public abstract class BaseNoTitleFragment<T extends BasePresenter> extends Fragment {
    protected View mView;
    public T mPresenter;
    public Context mContext;
    public BaseActivity mActivity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, mView);
        mContext = getContext();
        mActivity = (BaseActivity) getActivity();
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter != null) {
            //mPresenter.mContext = mContext;
        }
        //google分析初始化

        initPresenter();
        loadData();
        return mView;
    }


    /*********************
     * 子类实现
     *****************************/
    /**
     * 获取布局文件
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public abstract void initPresenter();

    /**
     * 加载View、设置数据
     */
    public abstract void loadData();

    /**
     * 请求权限封装
     *
     * @param permissions
     * @param listener
     */
    public void requestPermissions(String[] permissions, PermissionsListener listener) {
        mActivity.requestPermissions(permissions, listener);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.hideLoading();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
