// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DesignerMangerDetailActivity_ViewBinding implements Unbinder {
  private DesignerMangerDetailActivity target;

  @UiThread
  public DesignerMangerDetailActivity_ViewBinding(DesignerMangerDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DesignerMangerDetailActivity_ViewBinding(DesignerMangerDetailActivity target,
      View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.com_im = Utils.findRequiredViewAsType(source, R.id.com_im, "field 'com_im'", ImageView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_phone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tv_phone'", TextView.class);
    target.tv_birth = Utils.findRequiredViewAsType(source, R.id.tv_birth, "field 'tv_birth'", TextView.class);
    target.tv_city = Utils.findRequiredViewAsType(source, R.id.tv_city, "field 'tv_city'", TextView.class);
    target.tv_com = Utils.findRequiredViewAsType(source, R.id.tv_com, "field 'tv_com'", TextView.class);
    target.tv_job = Utils.findRequiredViewAsType(source, R.id.tv_job, "field 'tv_job'", TextView.class);
    target.pass = Utils.findRequiredViewAsType(source, R.id.pass, "field 'pass'", TextView.class);
    target.nopass = Utils.findRequiredViewAsType(source, R.id.nopass, "field 'nopass'", TextView.class);
    target.re_shenhe = Utils.findRequiredViewAsType(source, R.id.re_shenhe, "field 're_shenhe'", RelativeLayout.class);
    target.sure = Utils.findRequiredViewAsType(source, R.id.sure, "field 'sure'", TextView.class);
    target.cancle = Utils.findRequiredViewAsType(source, R.id.cancle, "field 'cancle'", TextView.class);
    target.re_nopass = Utils.findRequiredViewAsType(source, R.id.re_nopass, "field 're_nopass'", RelativeLayout.class);
    target.check_re = Utils.findRequiredViewAsType(source, R.id.check_re, "field 'check_re'", LinearLayout.class);
    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
    target.tv = Utils.findRequiredViewAsType(source, R.id.tv, "field 'tv'", TextView.class);
    target.lianxi = Utils.findRequiredViewAsType(source, R.id.lianxi, "field 'lianxi'", CheckBox.class);
    target.clear = Utils.findRequiredViewAsType(source, R.id.clear, "field 'clear'", CheckBox.class);
    target.other = Utils.findRequiredViewAsType(source, R.id.other, "field 'other'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DesignerMangerDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.com_im = null;
    target.time = null;
    target.name = null;
    target.tv_name = null;
    target.tv_phone = null;
    target.tv_birth = null;
    target.tv_city = null;
    target.tv_com = null;
    target.tv_job = null;
    target.pass = null;
    target.nopass = null;
    target.re_shenhe = null;
    target.sure = null;
    target.cancle = null;
    target.re_nopass = null;
    target.check_re = null;
    target.editText = null;
    target.tv = null;
    target.lianxi = null;
    target.clear = null;
    target.other = null;
  }
}
