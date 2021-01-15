// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.photo;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShowPictureActivity_ViewBinding implements Unbinder {
  private ShowPictureActivity target;

  private View view7f0905dc;

  private View view7f09053a;

  private View view7f090265;

  @UiThread
  public ShowPictureActivity_ViewBinding(ShowPictureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShowPictureActivity_ViewBinding(final ShowPictureActivity target, View source) {
    this.target = target;

    View view;
    target.mTvTitleContent = Utils.findRequiredViewAsType(source, R.id.tv_title_content, "field 'mTvTitleContent'", TextView.class);
    target.mLlTitle = Utils.findRequiredViewAsType(source, R.id.ll_title, "field 'mLlTitle'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.viewpager, "field 'mViewpager' and method 'onClick'");
    target.mViewpager = Utils.castView(view, R.id.viewpager, "field 'mViewpager'", ViewPager.class);
    view7f0905dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tips, "field 'tips' and method 'onClick'");
    target.tips = Utils.castView(view, R.id.tips, "field 'tips'", ImageView.class);
    view7f09053a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_title_left, "method 'onClick'");
    view7f090265 = view;
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
    ShowPictureActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvTitleContent = null;
    target.mLlTitle = null;
    target.mViewpager = null;
    target.tips = null;

    view7f0905dc.setOnClickListener(null);
    view7f0905dc = null;
    view7f09053a.setOnClickListener(null);
    view7f09053a = null;
    view7f090265.setOnClickListener(null);
    view7f090265 = null;
  }
}
