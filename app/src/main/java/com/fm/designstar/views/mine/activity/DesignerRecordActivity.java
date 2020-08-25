package com.fm.designstar.views.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.server.body.DesignerStatebody;
import com.fm.designstar.model.server.response.findPageResponse;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.views.mine.contract.DesignerRecordContract;
import com.fm.designstar.views.mine.presenter.DesignerRecordPresenter;

public class DesignerRecordActivity extends BaseActivity {

@BindView(R.id.com_im)
    ImageView hand;
@BindView(R.id.time)
TextView time;
@BindView(R.id.resons)
    TextView resons;
@BindView(R.id.re_check)
    TextView re_check;
private DesignerStatebody designerStatebody;
    @Override
    public int getLayoutId() {
        return R.layout.activity_designer_record;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        mTitle.setTitle("审核结果");
        designerStatebody=  (DesignerStatebody) getIntent().getSerializableExtra("Result");

        if (!StringUtil.isBlank(designerStatebody.getImgUrl())){
            Glide.with(mActivity).load(designerStatebody.getImgUrl()).error(R.mipmap.defu_hand).into(hand);
        }
        time.setText("认证于："+designerStatebody.getCreateTime());
        re_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(BeDesignerActivity.class);
                finish();
            }
        });
        if (designerStatebody.getType()==1){
            resons.setText("联系不上");
        }else if (designerStatebody.getType()==2){
            resons.setText("资料不清晰");
        }else {
            resons.setText(designerStatebody.getContent());
        }


    }

}