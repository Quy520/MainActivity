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
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ComUserInfoActivity_ViewBinding implements Unbinder {
  private ComUserInfoActivity target;

  private View view7f09011c;

  private View view7f0902d9;

  private View view7f090141;

  private View view7f0904d0;

  private View view7f0901ea;

  @UiThread
  public ComUserInfoActivity_ViewBinding(ComUserInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ComUserInfoActivity_ViewBinding(final ComUserInfoActivity target, View source) {
    this.target = target;

    View view;
    target.real_name = Utils.findRequiredViewAsType(source, R.id.real_name, "field 'real_name'", EditText.class);
    view = Utils.findRequiredView(source, R.id.brith, "field 'brith' and method 'OnClick'");
    target.brith = Utils.castView(view, R.id.brith, "field 'brith'", TextView.class);
    view7f09011c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.next, "field 'next' and method 'OnClick'");
    target.next = Utils.castView(view, R.id.next, "field 'next'", TextView.class);
    view7f0902d9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.city, "field 'city' and method 'OnClick'");
    target.city = Utils.castView(view, R.id.city, "field 'city'", TextView.class);
    view7f090141 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sex, "field 'tv_sex' and method 'OnClick'");
    target.tv_sex = Utils.castView(view, R.id.sex, "field 'tv_sex'", TextView.class);
    view7f0904d0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.hand, "field 'hand' and method 'OnClick'");
    target.hand = Utils.castView(view, R.id.hand, "field 'hand'", CircleImageView.class);
    view7f0901ea = view;
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
    ComUserInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.real_name = null;
    target.brith = null;
    target.next = null;
    target.city = null;
    target.tv_sex = null;
    target.hand = null;

    view7f09011c.setOnClickListener(null);
    view7f09011c = null;
    view7f0902d9.setOnClickListener(null);
    view7f0902d9 = null;
    view7f090141.setOnClickListener(null);
    view7f090141 = null;
    view7f0904d0.setOnClickListener(null);
    view7f0904d0 = null;
    view7f0901ea.setOnClickListener(null);
    view7f0901ea = null;
  }
}
