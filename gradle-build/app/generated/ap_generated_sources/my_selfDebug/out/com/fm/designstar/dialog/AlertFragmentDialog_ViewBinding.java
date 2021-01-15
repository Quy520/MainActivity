// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.dialog;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlertFragmentDialog_ViewBinding implements Unbinder {
  private AlertFragmentDialog target;

  private View view7f090566;

  private View view7f09055c;

  @UiThread
  public AlertFragmentDialog_ViewBinding(final AlertFragmentDialog target, View source) {
    this.target = target;

    View view;
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mTvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'mTvContent'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_cancel, "field 'mTvCancel' and method 'onClick'");
    target.mTvCancel = Utils.castView(view, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
    view7f090566 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_accomplish, "field 'mTvAccomplish' and method 'onClick'");
    target.mTvAccomplish = Utils.castView(view, R.id.tv_accomplish, "field 'mTvAccomplish'", TextView.class);
    view7f09055c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AlertFragmentDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvTitle = null;
    target.mTvContent = null;
    target.mTvCancel = null;
    target.mTvAccomplish = null;

    view7f090566.setOnClickListener(null);
    view7f090566 = null;
    view7f09055c.setOnClickListener(null);
    view7f09055c = null;
  }
}
