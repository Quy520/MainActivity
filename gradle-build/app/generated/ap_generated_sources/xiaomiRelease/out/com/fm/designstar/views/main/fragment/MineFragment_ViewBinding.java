// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  private View view7f090459;

  private View view7f090472;

  private View view7f09045a;

  private View view7f09046b;

  private View view7f090458;

  private View view7f0901ea;

  private View view7f0905f1;

  private View view7f0901dc;

  private View view7f09045d;

  private View view7f09045b;

  private View view7f090452;

  private View view7f090469;

  private View view7f090450;

  private View view7f090455;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    target.re_top = Utils.findRequiredViewAsType(source, R.id.re_top, "field 're_top'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.re_designer, "field 're_designer' and method 'OnClick'");
    target.re_designer = Utils.castView(view, R.id.re_designer, "field 're_designer'", RelativeLayout.class);
    view7f090459 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_zp, "field 're_zp' and method 'OnClick'");
    target.re_zp = Utils.castView(view, R.id.re_zp, "field 're_zp'", RelativeLayout.class);
    view7f090472 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_dt, "field 're_dt' and method 'OnClick'");
    target.re_dt = Utils.castView(view, R.id.re_dt, "field 're_dt'", RelativeLayout.class);
    view7f09045a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_shdes, "field 're_shdes' and method 'OnClick'");
    target.re_shdes = Utils.castView(view, R.id.re_shdes, "field 're_shdes'", RelativeLayout.class);
    view7f09046b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_demanger, "field 're_demanger' and method 'OnClick'");
    target.re_demanger = Utils.castView(view, R.id.re_demanger, "field 're_demanger'", RelativeLayout.class);
    view7f090458 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.line1 = Utils.findRequiredView(source, R.id.line1, "field 'line1'");
    target.line2 = Utils.findRequiredView(source, R.id.line2, "field 'line2'");
    view = Utils.findRequiredView(source, R.id.hand, "field 'hand' and method 'OnClick'");
    target.hand = Utils.castView(view, R.id.hand, "field 'hand'", CircleImageView.class);
    view7f0901ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.info = Utils.findRequiredViewAsType(source, R.id.info, "field 'info'", TextView.class);
    target.info2 = Utils.findRequiredViewAsType(source, R.id.info2, "field 'info2'", TextView.class);
    target.zp_num = Utils.findRequiredViewAsType(source, R.id.zp_num, "field 'zp_num'", TextView.class);
    target.like_num = Utils.findRequiredViewAsType(source, R.id.like_num, "field 'like_num'", TextView.class);
    target.guanzhu_num = Utils.findRequiredViewAsType(source, R.id.guanzhu_num, "field 'guanzhu_num'", TextView.class);
    target.fans_num = Utils.findRequiredViewAsType(source, R.id.fans_num, "field 'fans_num'", TextView.class);
    target.shtype = Utils.findRequiredViewAsType(source, R.id.shtype, "field 'shtype'", TextView.class);
    target.tv_cash = Utils.findRequiredViewAsType(source, R.id.cash, "field 'tv_cash'", TextView.class);
    target.left = Utils.findRequiredViewAsType(source, R.id.left, "field 'left'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.zuopin, "method 'OnClick'");
    view7f0905f1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.getlike, "method 'OnClick'");
    view7f0901dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_guanzhu, "method 'OnClick'");
    view7f09045d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_fans, "method 'OnClick'");
    view7f09045b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_black, "method 'OnClick'");
    view7f090452 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_setting, "method 'OnClick'");
    view7f090469 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_advise, "method 'OnClick'");
    view7f090450 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.re_clear, "method 'OnClick'");
    view7f090455 = view;
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
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.re_top = null;
    target.re_designer = null;
    target.re_zp = null;
    target.re_dt = null;
    target.re_shdes = null;
    target.re_demanger = null;
    target.line1 = null;
    target.line2 = null;
    target.hand = null;
    target.name = null;
    target.info = null;
    target.info2 = null;
    target.zp_num = null;
    target.like_num = null;
    target.guanzhu_num = null;
    target.fans_num = null;
    target.shtype = null;
    target.tv_cash = null;
    target.left = null;

    view7f090459.setOnClickListener(null);
    view7f090459 = null;
    view7f090472.setOnClickListener(null);
    view7f090472 = null;
    view7f09045a.setOnClickListener(null);
    view7f09045a = null;
    view7f09046b.setOnClickListener(null);
    view7f09046b = null;
    view7f090458.setOnClickListener(null);
    view7f090458 = null;
    view7f0901ea.setOnClickListener(null);
    view7f0901ea = null;
    view7f0905f1.setOnClickListener(null);
    view7f0905f1 = null;
    view7f0901dc.setOnClickListener(null);
    view7f0901dc = null;
    view7f09045d.setOnClickListener(null);
    view7f09045d = null;
    view7f09045b.setOnClickListener(null);
    view7f09045b = null;
    view7f090452.setOnClickListener(null);
    view7f090452 = null;
    view7f090469.setOnClickListener(null);
    view7f090469 = null;
    view7f090450.setOnClickListener(null);
    view7f090450 = null;
    view7f090455.setOnClickListener(null);
    view7f090455 = null;
  }
}
