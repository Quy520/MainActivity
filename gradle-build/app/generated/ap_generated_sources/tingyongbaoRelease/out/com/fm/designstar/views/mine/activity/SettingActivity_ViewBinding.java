// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view7f090463;

  private View view7f09045e;

  private View view7f09046a;

  private View view7f090451;

  private View view7f090454;

  private View view7f09046d;

  private View view7f0902ae;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    target.brith = Utils.findRequiredViewAsType(source, R.id.birth, "field 'brith'", TextView.class);
    target.city = Utils.findRequiredViewAsType(source, R.id.city, "field 'city'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.sing = Utils.findRequiredViewAsType(source, R.id.sing, "field 'sing'", TextView.class);
    target.tv_sex = Utils.findRequiredViewAsType(source, R.id.sex, "field 'tv_sex'", TextView.class);
    target.vesion = Utils.findRequiredViewAsType(source, R.id.vesion, "field 'vesion'", TextView.class);
    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.re_name, "method 'OnClick'");
    view7f090463 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_hand, "method 'OnClick'");
    view7f09045e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_sex, "method 'OnClick'");
    view7f09046a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_birth, "method 'OnClick'");
    view7f090451 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_city, "method 'OnClick'");
    view7f090454 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_sing, "method 'OnClick'");
    view7f09046d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.longout, "method 'OnClick'");
    view7f0902ae = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.brith = null;
    target.city = null;
    target.name = null;
    target.sing = null;
    target.tv_sex = null;
    target.vesion = null;
    target.hand = null;
    target.phone = null;

    view7f090463.setOnClickListener(null);
    view7f090463 = null;
    view7f09045e.setOnClickListener(null);
    view7f09045e = null;
    view7f09046a.setOnClickListener(null);
    view7f09046a = null;
    view7f090451.setOnClickListener(null);
    view7f090451 = null;
    view7f090454.setOnClickListener(null);
    view7f090454 = null;
    view7f09046d.setOnClickListener(null);
    view7f09046d = null;
    view7f0902ae.setOnClickListener(null);
    view7f0902ae = null;
  }
}
