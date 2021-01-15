// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.MyScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BeDesignerActivity_ViewBinding implements Unbinder {
  private BeDesignerActivity target;

  private View view7f09020a;

  private View view7f090152;

  private View view7f090451;

  private View view7f090454;

  @UiThread
  public BeDesignerActivity_ViewBinding(BeDesignerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BeDesignerActivity_ViewBinding(final BeDesignerActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.im_card, "field 'im_card' and method 'OnClick'");
    target.im_card = Utils.castView(view, R.id.im_card, "field 'im_card'", ImageView.class);
    view7f09020a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.commit, "field 'commit' and method 'OnClick'");
    target.commit = Utils.castView(view, R.id.commit, "field 'commit'", TextView.class);
    view7f090152 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.city = Utils.findRequiredViewAsType(source, R.id.city, "field 'city'", TextView.class);
    target.birth = Utils.findRequiredViewAsType(source, R.id.birth, "field 'birth'", TextView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", EditText.class);
    target.com = Utils.findRequiredViewAsType(source, R.id.com, "field 'com'", TextView.class);
    target.job = Utils.findRequiredViewAsType(source, R.id.job, "field 'job'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    target.scroll = Utils.findRequiredViewAsType(source, R.id.scroll, "field 'scroll'", MyScrollView.class);
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
  }

  @Override
  @CallSuper
  public void unbind() {
    BeDesignerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.im_card = null;
    target.commit = null;
    target.city = null;
    target.birth = null;
    target.name = null;
    target.com = null;
    target.job = null;
    target.phone = null;
    target.scroll = null;

    view7f09020a.setOnClickListener(null);
    view7f09020a = null;
    view7f090152.setOnClickListener(null);
    view7f090152 = null;
    view7f090451.setOnClickListener(null);
    view7f090451 = null;
    view7f090454.setOnClickListener(null);
    view7f090454 = null;
  }
}
