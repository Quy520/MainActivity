// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Fabu.activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChoseScrollTagActivity_ViewBinding implements Unbinder {
  private ChoseScrollTagActivity target;

  @UiThread
  public ChoseScrollTagActivity_ViewBinding(ChoseScrollTagActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChoseScrollTagActivity_ViewBinding(ChoseScrollTagActivity target, View source) {
    this.target = target;

    target.recLeft = Utils.findRequiredViewAsType(source, R.id.rec_left, "field 'recLeft'", RecyclerView.class);
    target.recRight = Utils.findRequiredViewAsType(source, R.id.rec_right, "field 'recRight'", RecyclerView.class);
    target.rightTitle = Utils.findRequiredViewAsType(source, R.id.right_title, "field 'rightTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChoseScrollTagActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recLeft = null;
    target.recRight = null;
    target.rightTitle = null;
  }
}
