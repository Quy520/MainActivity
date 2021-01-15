// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InfoDetailActivity_ViewBinding implements Unbinder {
  private InfoDetailActivity target;

  private View view7f090581;

  private View view7f0904d9;

  private View view7f090472;

  private View view7f09045a;

  @UiThread
  public InfoDetailActivity_ViewBinding(InfoDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InfoDetailActivity_ViewBinding(final InfoDetailActivity target, View source) {
    this.target = target;

    View view;
    target.my_recy = Utils.findRequiredViewAsType(source, R.id.my_recy, "field 'my_recy'", RecyclerView.class);
    target.zp_num = Utils.findRequiredViewAsType(source, R.id.zp_num, "field 'zp_num'", TextView.class);
    target.like_num = Utils.findRequiredViewAsType(source, R.id.like_num, "field 'like_num'", TextView.class);
    target.guanzhu_num = Utils.findRequiredViewAsType(source, R.id.guanzhu_num, "field 'guanzhu_num'", TextView.class);
    target.fans_num = Utils.findRequiredViewAsType(source, R.id.fans_num, "field 'fans_num'", TextView.class);
    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.info = Utils.findRequiredViewAsType(source, R.id.info, "field 'info'", TextView.class);
    target.info2 = Utils.findRequiredViewAsType(source, R.id.info2, "field 'info2'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_guanzhu, "field 'tv_guanzhu' and method 'OnClick'");
    target.tv_guanzhu = Utils.castView(view, R.id.tv_guanzhu, "field 'tv_guanzhu'", TextView.class);
    view7f090581 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.tv_zp = Utils.findRequiredViewAsType(source, R.id.tv_zp, "field 'tv_zp'", TextView.class);
    target.tv_dt = Utils.findRequiredViewAsType(source, R.id.tv_dt, "field 'tv_dt'", TextView.class);
    view = Utils.findRequiredView(source, R.id.sixin, "field 'sixin' and method 'OnClick'");
    target.sixin = Utils.castView(view, R.id.sixin, "field 'sixin'", TextView.class);
    view7f0904d9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.guanzhu = Utils.findRequiredViewAsType(source, R.id.guanzhu, "field 'guanzhu'", LinearLayout.class);
    target.re_top = Utils.findRequiredViewAsType(source, R.id.re_top, "field 're_top'", RelativeLayout.class);
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
    target.im_zp = Utils.findRequiredViewAsType(source, R.id.im_zp, "field 'im_zp'", ImageView.class);
    target.im_dt = Utils.findRequiredViewAsType(source, R.id.im_dt, "field 'im_dt'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InfoDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.my_recy = null;
    target.zp_num = null;
    target.like_num = null;
    target.guanzhu_num = null;
    target.fans_num = null;
    target.hand = null;
    target.name = null;
    target.info = null;
    target.info2 = null;
    target.tv_guanzhu = null;
    target.tv_zp = null;
    target.tv_dt = null;
    target.sixin = null;
    target.guanzhu = null;
    target.re_top = null;
    target.re_zp = null;
    target.re_dt = null;
    target.im_zp = null;
    target.im_dt = null;

    view7f090581.setOnClickListener(null);
    view7f090581 = null;
    view7f0904d9.setOnClickListener(null);
    view7f0904d9 = null;
    view7f090472.setOnClickListener(null);
    view7f090472 = null;
    view7f09045a.setOnClickListener(null);
    view7f09045a = null;
  }
}
