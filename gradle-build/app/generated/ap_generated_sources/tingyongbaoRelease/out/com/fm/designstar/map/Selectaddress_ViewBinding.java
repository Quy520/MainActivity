// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.map;

import android.view.View;
import android.widget.EditText;
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
import java.lang.IllegalStateException;
import java.lang.Override;

public class Selectaddress_ViewBinding implements Unbinder {
  private Selectaddress target;

  private View view7f0902a2;

  private View view7f0902a5;

  @UiThread
  public Selectaddress_ViewBinding(Selectaddress target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Selectaddress_ViewBinding(final Selectaddress target, View source) {
    this.target = target;

    View view;
    target.mLocateRecycler = Utils.findRequiredViewAsType(source, R.id.locate_recycler, "field 'mLocateRecycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.locate_cancel, "field 'mLocateCancel' and method 'onClick'");
    target.mLocateCancel = Utils.castView(view, R.id.locate_cancel, "field 'mLocateCancel'", TextView.class);
    view7f0902a2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.locate_refresh, "field 'mLocateRefresh' and method 'onClick'");
    target.mLocateRefresh = Utils.castView(view, R.id.locate_refresh, "field 'mLocateRefresh'", TextView.class);
    view7f0902a5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.top_title = Utils.findRequiredViewAsType(source, R.id.top_title, "field 'top_title'", LinearLayout.class);
    target.titleLayout = Utils.findRequiredViewAsType(source, R.id.titleLayout, "field 'titleLayout'", RelativeLayout.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.searchEt = Utils.findRequiredViewAsType(source, R.id.searchEt, "field 'searchEt'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Selectaddress target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mLocateRecycler = null;
    target.mLocateCancel = null;
    target.mLocateRefresh = null;
    target.top_title = null;
    target.titleLayout = null;
    target.back = null;
    target.searchEt = null;

    view7f0902a2.setOnClickListener(null);
    view7f0902a2 = null;
    view7f0902a5.setOnClickListener(null);
    view7f0902a5 = null;
  }
}
