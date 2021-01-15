// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DesignerMangerActivity_ViewBinding implements Unbinder {
  private DesignerMangerActivity target;

  @UiThread
  public DesignerMangerActivity_ViewBinding(DesignerMangerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DesignerMangerActivity_ViewBinding(DesignerMangerActivity target, View source) {
    this.target = target;

    target.hotRecycler = Utils.findRequiredViewAsType(source, R.id.recy_black, "field 'hotRecycler'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DesignerMangerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hotRecycler = null;
  }
}
