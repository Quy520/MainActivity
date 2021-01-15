// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.login.activitys;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f0902ac;

  private View view7f0902ab;

  private View view7f0900dc;

  private View view7f0901d1;

  private View view7f09048a;

  private View view7f09033f;

  private View view7f0905ec;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    target.pwd = Utils.findRequiredViewAsType(source, R.id.pwd, "field 'pwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.longin, "field 'longin' and method 'OnClick'");
    target.longin = Utils.castView(view, R.id.longin, "field 'longin'", Button.class);
    view7f0902ac = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.long_notice, "field 'reg_notice' and method 'OnClick'");
    target.reg_notice = Utils.castView(view, R.id.long_notice, "field 'reg_notice'", TextView.class);
    view7f0902ab = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.pwd_open = Utils.findRequiredViewAsType(source, R.id.pwd_open, "field 'pwd_open'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.back, "method 'OnClick'");
    view7f0900dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.forgot, "method 'OnClick'");
    view7f0901d1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.regist, "method 'OnClick'");
    view7f09048a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.qq, "method 'OnClick'");
    view7f09033f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.wx, "method 'OnClick'");
    view7f0905ec = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.phone = null;
    target.pwd = null;
    target.longin = null;
    target.reg_notice = null;
    target.pwd_open = null;

    view7f0902ac.setOnClickListener(null);
    view7f0902ac = null;
    view7f0902ab.setOnClickListener(null);
    view7f0902ab = null;
    view7f0900dc.setOnClickListener(null);
    view7f0900dc = null;
    view7f0901d1.setOnClickListener(null);
    view7f0901d1 = null;
    view7f09048a.setOnClickListener(null);
    view7f09048a = null;
    view7f09033f.setOnClickListener(null);
    view7f09033f = null;
    view7f0905ec.setOnClickListener(null);
    view7f0905ec = null;
  }
}
