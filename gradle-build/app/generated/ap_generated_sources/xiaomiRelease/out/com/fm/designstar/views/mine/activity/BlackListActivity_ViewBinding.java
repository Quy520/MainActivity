// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BlackListActivity_ViewBinding implements Unbinder {
  private BlackListActivity target;

  @UiThread
  public BlackListActivity_ViewBinding(BlackListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BlackListActivity_ViewBinding(BlackListActivity target, View source) {
    this.target = target;

    target.recy_black = Utils.findRequiredViewAsType(source, R.id.recy_black, "field 'recy_black'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BlackListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recy_black = null;
  }
}
