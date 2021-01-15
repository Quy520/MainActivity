// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.adapter;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeAdapter$LikeViewHolderthree_ViewBinding implements Unbinder {
  private HomeAdapter.LikeViewHolderthree target;

  @UiThread
  public HomeAdapter$LikeViewHolderthree_ViewBinding(HomeAdapter.LikeViewHolderthree target,
      View source) {
    this.target = target;

    target.hotRecycler = Utils.findRequiredViewAsType(source, R.id.recy_home, "field 'hotRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeAdapter.LikeViewHolderthree target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hotRecycler = null;
  }
}
