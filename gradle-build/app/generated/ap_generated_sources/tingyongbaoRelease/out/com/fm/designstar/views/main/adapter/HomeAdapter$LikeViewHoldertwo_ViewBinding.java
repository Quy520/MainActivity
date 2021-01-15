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

public class HomeAdapter$LikeViewHoldertwo_ViewBinding implements Unbinder {
  private HomeAdapter.LikeViewHoldertwo target;

  @UiThread
  public HomeAdapter$LikeViewHoldertwo_ViewBinding(HomeAdapter.LikeViewHoldertwo target,
      View source) {
    this.target = target;

    target.likeRecycler = Utils.findRequiredViewAsType(source, R.id.recy_gusslike, "field 'likeRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeAdapter.LikeViewHoldertwo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.likeRecycler = null;
  }
}
