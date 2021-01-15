// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.NoScrollViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DesignerFragment_ViewBinding implements Unbinder {
  private DesignerFragment target;

  private View view7f09045d;

  private View view7f090471;

  private View view7f09045c;

  private View view7f0904be;

  @UiThread
  public DesignerFragment_ViewBinding(final DesignerFragment target, View source) {
    this.target = target;

    View view;
    target.re_title = Utils.findRequiredViewAsType(source, R.id.re_title, "field 're_title'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.re_guanzhu, "field 're_guanzhu' and method 'OnClick'");
    target.re_guanzhu = Utils.castView(view, R.id.re_guanzhu, "field 're_guanzhu'", RelativeLayout.class);
    view7f09045d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.tv_guanzhu = Utils.findRequiredViewAsType(source, R.id.tv_guanzhu, "field 'tv_guanzhu'", TextView.class);
    target.im_guanzhu = Utils.findRequiredViewAsType(source, R.id.im_guanzhu, "field 'im_guanzhu'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.re_tuijain, "field 're_tuijain' and method 'OnClick'");
    target.re_tuijain = Utils.castView(view, R.id.re_tuijain, "field 're_tuijain'", RelativeLayout.class);
    view7f090471 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.tv_tuijain = Utils.findRequiredViewAsType(source, R.id.tv_tuijain, "field 'tv_tuijain'", TextView.class);
    target.im_tuijain = Utils.findRequiredViewAsType(source, R.id.im_tuijain, "field 'im_tuijain'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.re_find, "field 're_find' and method 'OnClick'");
    target.re_find = Utils.castView(view, R.id.re_find, "field 're_find'", RelativeLayout.class);
    view7f09045c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.tv_find = Utils.findRequiredViewAsType(source, R.id.tv_find, "field 'tv_find'", TextView.class);
    target.im_find = Utils.findRequiredViewAsType(source, R.id.im_find, "field 'im_find'", ImageView.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", NoScrollViewPager.class);
    view = Utils.findRequiredView(source, R.id.searchTv, "method 'OnClick'");
    view7f0904be = view;
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
    DesignerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.re_title = null;
    target.re_guanzhu = null;
    target.tv_guanzhu = null;
    target.im_guanzhu = null;
    target.re_tuijain = null;
    target.tv_tuijain = null;
    target.im_tuijain = null;
    target.re_find = null;
    target.tv_find = null;
    target.im_find = null;
    target.viewPager = null;

    view7f09045d.setOnClickListener(null);
    view7f09045d = null;
    view7f090471.setOnClickListener(null);
    view7f090471 = null;
    view7f09045c.setOnClickListener(null);
    view7f09045c = null;
    view7f0904be.setOnClickListener(null);
    view7f0904be = null;
  }
}
