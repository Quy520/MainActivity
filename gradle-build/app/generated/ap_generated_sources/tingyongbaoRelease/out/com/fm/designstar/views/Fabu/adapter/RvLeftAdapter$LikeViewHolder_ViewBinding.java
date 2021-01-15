// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Fabu.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RvLeftAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private RvLeftAdapter.LikeViewHolder target;

  @UiThread
  public RvLeftAdapter$LikeViewHolder_ViewBinding(RvLeftAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RvLeftAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
  }
}
