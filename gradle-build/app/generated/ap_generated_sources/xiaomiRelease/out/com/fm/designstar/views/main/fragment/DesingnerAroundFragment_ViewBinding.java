// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DesingnerAroundFragment_ViewBinding implements Unbinder {
  private DesingnerAroundFragment target;

  @UiThread
  public DesingnerAroundFragment_ViewBinding(DesingnerAroundFragment target, View source) {
    this.target = target;

    target.designer_recy = Utils.findRequiredViewAsType(source, R.id.designer_recy, "field 'designer_recy'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DesingnerAroundFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.designer_recy = null;
  }
}
