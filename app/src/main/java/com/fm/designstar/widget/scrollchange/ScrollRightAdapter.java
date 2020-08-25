package com.fm.designstar.widget.scrollchange;

import android.util.Log;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fm.designstar.R;
import com.fm.designstar.events.GetTagsEvent;
import com.fm.designstar.events.HomeEvent;
import com.fm.designstar.utils.ToastUtil;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul_lsj on 2018/3/28.
 */

public class ScrollRightAdapter extends BaseSectionQuickAdapter<ScrollBean, BaseViewHolder> {
    private List<String >  limt=new ArrayList<>();



    public ScrollRightAdapter(int layoutResId, int sectionHeadResId, List<ScrollBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ScrollBean item) {
        helper.setText(R.id.right_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScrollBean item) {
        ScrollBean.ScrollItemBean t = item.t;


        helper.setText(R.id.right_text, t.getText());

        helper.setOnCheckedChangeListener(R.id.right_text, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (limt.size()>4){
                    helper.setChecked(R.id.right_text,false);
                    ToastUtil.showToast("最多五个标签");
                }
                if (b){
                    if (limt.size()>4){
                        helper.setChecked(R.id.right_text,false);
                    }else {
                        limt.add(t.getText());
                    }
                }else {
                    for (int i=0;i<limt.size();i++){
                        if (limt.get(i).equals(t.getText())){
                            limt.remove(i);
                        }
                    }
                }

                Log.e("qsd","bbbb"+b+"value"+t.getText()+limt.size());
                EventBus.getDefault().removeStickyEvent(GetTagsEvent.class);
                EventBus.getDefault().post(new GetTagsEvent(b,limt));

            }
        });

    }
}
