// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.login.activitys;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PwdActivity_ViewBinding implements Unbinder {
  private PwdActivity target;

  private View view7f0904fc;

  private View view7f0900dc;

  @UiThread
  public PwdActivity_ViewBinding(PwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PwdActivity_ViewBinding(final PwdActivity target, View source) {
    this.target = target;

    View view;
    target.pwd_top = Utils.findRequiredViewAsType(source, R.id.pwd_top, "field 'pwd_top'", TextView.class);
    target.reg_notice = Utils.findRequiredViewAsType(source, R.id.long_notice, "field 'reg_notice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.sure, "field 'sure' and method 'OnClick'");
    target.sure = Utils.castView(view, R.id.sure, "field 'sure'", TextView.class);
    view7f0904fc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.pwd = Utils.findRequiredViewAsType(source, R.id.pwd, "field 'pwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.back, "method 'OnClick'");
    view7f0900dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pwd_top = null;
    target.reg_notice = null;
    target.sure = null;
    target.pwd = null;

    view7f0904fc.setOnClickListener(null);
    view7f0904fc = null;
    view7f0900dc.setOnClickListener(null);
    view7f0900dc = null;
  }
}
