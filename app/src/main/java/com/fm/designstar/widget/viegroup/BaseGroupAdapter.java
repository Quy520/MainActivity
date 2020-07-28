package com.fm.designstar.widget.viegroup;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/22 14:09
 * @update : 2018/8/22
 */
public abstract class BaseGroupAdapter<X> {
    public ArrayList<X> mData = new ArrayList<>();

    public void addData(List<X> datas) {
        mData.clear();
        mData.addAll(datas);
    }

    public abstract int getCount();

    public abstract View getView(ViewGroup viewGroup, int position);
}
