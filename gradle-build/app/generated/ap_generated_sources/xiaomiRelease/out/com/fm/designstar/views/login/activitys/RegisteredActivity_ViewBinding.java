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

public class RegisteredActivity_ViewBinding implements Unbinder {
  private RegisteredActivity target;

  private View view7f0902d9;

  private View view7f0900dc;

  @UiThread
  public RegisteredActivity_ViewBinding(RegisteredActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisteredActivity_ViewBinding(final RegisteredActivity target, View source) {
    this.target = target;

    View view;
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.next, "field 'next' and method 'OnClick'");
    target.next = Utils.castView(view, R.id.next, "field 'next'", TextView.class);
    view7f0902d9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.reg_notice = Utils.findRequiredViewAsType(source, R.id.reg_notice, "field 'reg_notice'", TextView.class);
    target.login_top = Utils.findRequiredViewAsType(source, R.id.login_top, "field 'login_top'", TextView.class);
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
    RegisteredActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.phone = null;
    target.next = null;
    target.reg_notice = null;
    target.login_top = null;

    view7f0902d9.setOnClickListener(null);
    view7f0902d9 = null;
    view7f0900dc.setOnClickListener(null);
    view7f0900dc = null;
  }
}
