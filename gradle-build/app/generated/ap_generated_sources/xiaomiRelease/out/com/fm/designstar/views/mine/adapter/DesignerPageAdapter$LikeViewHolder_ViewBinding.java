// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DesignerPageAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private DesignerPageAdapter.LikeViewHolder target;

  @UiThread
  public DesignerPageAdapter$LikeViewHolder_ViewBinding(DesignerPageAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", TextView.class);
    target.details = Utils.findRequiredViewAsType(source, R.id.details, "field 'details'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DesignerPageAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.name = null;
    target.content = null;
    target.details = null;
  }
}
