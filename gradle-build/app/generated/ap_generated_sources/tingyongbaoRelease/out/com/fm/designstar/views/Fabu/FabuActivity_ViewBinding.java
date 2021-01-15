// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Fabu;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.viegroup.MyViewGroup;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FabuActivity_ViewBinding implements Unbinder {
  private FabuActivity target;

  private View view7f0901fd;

  private View view7f0905ee;

  private View view7f09017a;

  private View view7f090147;

  private View view7f0901b5;

  @UiThread
  public FabuActivity_ViewBinding(FabuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FabuActivity_ViewBinding(final FabuActivity target, View source) {
    this.target = target;

    View view;
    target.re_title = Utils.findRequiredViewAsType(source, R.id.re_title, "field 're_title'", RelativeLayout.class);
    target.photoRecycler = Utils.findRequiredViewAsType(source, R.id.photoRecycler, "field 'photoRecycler'", RecyclerView.class);
    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.huati, "field 'huati' and method 'OnClick'");
    target.huati = Utils.castView(view, R.id.huati, "field 'huati'", TextView.class);
    view7f0901fd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.zb, "field 'zb' and method 'OnClick'");
    target.zb = Utils.castView(view, R.id.zb, "field 'zb'", TextView.class);
    view7f0905ee = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.delete, "field 'delete' and method 'OnClick'");
    target.delete = Utils.castView(view, R.id.delete, "field 'delete'", ImageView.class);
    view7f09017a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.myViewGroup = Utils.findRequiredViewAsType(source, R.id.myViewGroup, "field 'myViewGroup'", MyViewGroup.class);
    view = Utils.findRequiredView(source, R.id.close, "method 'OnClick'");
    view7f090147 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fabu, "method 'OnClick'");
    view7f0901b5 = view;
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
    FabuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.re_title = null;
    target.photoRecycler = null;
    target.editText = null;
    target.huati = null;
    target.zb = null;
    target.delete = null;
    target.myViewGroup = null;

    view7f0901fd.setOnClickListener(null);
    view7f0901fd = null;
    view7f0905ee.setOnClickListener(null);
    view7f0905ee = null;
    view7f09017a.setOnClickListener(null);
    view7f09017a = null;
    view7f090147.setOnClickListener(null);
    view7f090147 = null;
    view7f0901b5.setOnClickListener(null);
    view7f0901b5 = null;
  }
}
