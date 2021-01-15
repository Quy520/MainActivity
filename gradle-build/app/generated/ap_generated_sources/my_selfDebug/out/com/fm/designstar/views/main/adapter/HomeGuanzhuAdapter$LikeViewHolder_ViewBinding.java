// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.jzvd.JZVideoPlayerStandard;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.CostomGrideView;
import com.fm.designstar.widget.viegroup.MyViewGroup;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeGuanzhuAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private HomeGuanzhuAdapter.LikeViewHolder target;

  @UiThread
  public HomeGuanzhuAdapter$LikeViewHolder_ViewBinding(HomeGuanzhuAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.more = Utils.findRequiredViewAsType(source, R.id.more, "field 'more'", ImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.message_num = Utils.findRequiredViewAsType(source, R.id.message_num, "field 'message_num'", TextView.class);
    target.likenum = Utils.findRequiredViewAsType(source, R.id.likenum, "field 'likenum'", TextView.class);
    target.check_like = Utils.findRequiredViewAsType(source, R.id.check_like, "field 'check_like'", CheckBox.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", TextView.class);
    target.longmip = Utils.findRequiredViewAsType(source, R.id.longmip, "field 'longmip'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.imgLay = Utils.findRequiredViewAsType(source, R.id.imgLay, "field 'imgLay'", FrameLayout.class);
    target.ly_two_img = Utils.findRequiredViewAsType(source, R.id.ly_two_img, "field 'ly_two_img'", LinearLayout.class);
    target.oneImg = Utils.findRequiredViewAsType(source, R.id.oneImg, "field 'oneImg'", ImageView.class);
    target.im = Utils.findRequiredViewAsType(source, R.id.im, "field 'im'", ImageView.class);
    target.oneImg2 = Utils.findRequiredViewAsType(source, R.id.oneImg2, "field 'oneImg2'", ImageView.class);
    target.oneImg3 = Utils.findRequiredViewAsType(source, R.id.oneImg3, "field 'oneImg3'", ImageView.class);
    target.gw = Utils.findRequiredViewAsType(source, R.id.gw, "field 'gw'", CostomGrideView.class);
    target.gw2 = Utils.findRequiredViewAsType(source, R.id.gw2, "field 'gw2'", CostomGrideView.class);
    target.myViewGroup = Utils.findRequiredViewAsType(source, R.id.myViewGroup, "field 'myViewGroup'", MyViewGroup.class);
    target.video_player = Utils.findRequiredViewAsType(source, R.id.video_player, "field 'video_player'", JZVideoPlayerStandard.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeGuanzhuAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.more = null;
    target.name = null;
    target.message_num = null;
    target.likenum = null;
    target.check_like = null;
    target.address = null;
    target.content = null;
    target.longmip = null;
    target.time = null;
    target.tv_name = null;
    target.imgLay = null;
    target.ly_two_img = null;
    target.oneImg = null;
    target.im = null;
    target.oneImg2 = null;
    target.oneImg3 = null;
    target.gw = null;
    target.gw2 = null;
    target.myViewGroup = null;
    target.video_player = null;
  }
}
