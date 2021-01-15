// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DesignerRecordActivity_ViewBinding implements Unbinder {
  private DesignerRecordActivity target;

  @UiThread
  public DesignerRecordActivity_ViewBinding(DesignerRecordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DesignerRecordActivity_ViewBinding(DesignerRecordActivity target, View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.com_im, "field 'hand'", ImageView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.resons = Utils.findRequiredViewAsType(source, R.id.resons, "field 'resons'", TextView.class);
    target.re_check = Utils.findRequiredViewAsType(source, R.id.re_check, "field 're_check'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DesignerRecordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.time = null;
    target.resons = null;
    target.re_check = null;
  }
}
