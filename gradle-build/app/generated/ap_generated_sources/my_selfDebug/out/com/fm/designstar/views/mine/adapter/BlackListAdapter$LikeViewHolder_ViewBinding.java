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

public class BlackListAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private BlackListAdapter.LikeViewHolder target;

  @UiThread
  public BlackListAdapter$LikeViewHolder_ViewBinding(BlackListAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BlackListAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.name = null;
  }
}
