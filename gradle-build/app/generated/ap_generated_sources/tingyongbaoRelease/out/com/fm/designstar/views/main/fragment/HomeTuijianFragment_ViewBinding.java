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

public class HomeTuijianFragment_ViewBinding implements Unbinder {
  private HomeTuijianFragment target;

  @UiThread
  public HomeTuijianFragment_ViewBinding(HomeTuijianFragment target, View source) {
    this.target = target;

    target.home_recy = Utils.findRequiredViewAsType(source, R.id.home_recy, "field 'home_recy'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeTuijianFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.home_recy = null;
  }
}
