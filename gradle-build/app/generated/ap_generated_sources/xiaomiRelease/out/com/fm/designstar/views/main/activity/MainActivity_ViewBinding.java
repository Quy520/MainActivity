// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f0901f8;

  private View view7f09016c;

  private View view7f0902a8;

  private View view7f0902b5;

  private View view7f09006b;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.home = Utils.findRequiredViewAsType(source, R.id.home, "field 'home'", ImageView.class);
    target.location = Utils.findRequiredViewAsType(source, R.id.location, "field 'location'", ImageView.class);
    target.coupon = Utils.findRequiredViewAsType(source, R.id.coupon, "field 'coupon'", ImageView.class);
    target.me = Utils.findRequiredViewAsType(source, R.id.me, "field 'me'", ImageView.class);
    target.tv_home = Utils.findRequiredViewAsType(source, R.id.tv_home, "field 'tv_home'", TextView.class);
    target.tv_des = Utils.findRequiredViewAsType(source, R.id.tv_des, "field 'tv_des'", TextView.class);
    target.tv_message = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tv_message'", TextView.class);
    target.tv_my = Utils.findRequiredViewAsType(source, R.id.tv_my, "field 'tv_my'", TextView.class);
    target.interNo = Utils.findRequiredViewAsType(source, R.id.interNo, "field 'interNo'", TextView.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", NoScrollViewPager.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.homeLay, "method 'OnClick'");
    view7f0901f8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.couponLay, "method 'OnClick'");
    view7f09016c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.locationLay, "method 'OnClick'");
    view7f0902a8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.meLay, "method 'OnClick'");
    view7f0902b5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.addLay, "method 'OnClick'");
    view7f09006b = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.home = null;
    target.location = null;
    target.coupon = null;
    target.me = null;
    target.tv_home = null;
    target.tv_des = null;
    target.tv_message = null;
    target.tv_my = null;
    target.interNo = null;
    target.viewPager = null;
    target.tabLayout = null;

    view7f0901f8.setOnClickListener(null);
    view7f0901f8 = null;
    view7f09016c.setOnClickListener(null);
    view7f09016c = null;
    view7f0902a8.setOnClickListener(null);
    view7f0902a8 = null;
    view7f0902b5.setOnClickListener(null);
    view7f0902b5 = null;
    view7f09006b.setOnClickListener(null);
    view7f09006b = null;
  }
}
