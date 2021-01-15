// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.CostomGrideView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DesignerAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private DesignerAdapter.LikeViewHolder target;

  @UiThread
  public DesignerAdapter$LikeViewHolder_ViewBinding(DesignerAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.tv_guanzhu = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tv_guanzhu'", TextView.class);
    target.type = Utils.findRequiredViewAsType(source, R.id.type, "field 'type'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.check_guanzhu = Utils.findRequiredViewAsType(source, R.id.check_guanzhu, "field 'check_guanzhu'", CheckBox.class);
    target.gw2 = Utils.findRequiredViewAsType(source, R.id.gw2, "field 'gw2'", CostomGrideView.class);
    target.ivimage = Utils.findRequiredViewAsType(source, R.id.im_item, "field 'ivimage'", ImageView.class);
    target.re_item = Utils.findRequiredViewAsType(source, R.id.re_item, "field 're_item'", RelativeLayout.class);
    target.imgLay = Utils.findRequiredViewAsType(source, R.id.imgLay, "field 'imgLay'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DesignerAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.name = null;
    target.tv_guanzhu = null;
    target.type = null;
    target.time = null;
    target.check_guanzhu = null;
    target.gw2 = null;
    target.ivimage = null;
    target.re_item = null;
    target.imgLay = null;
  }
}
