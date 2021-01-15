// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FansListAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private FansListAdapter.LikeViewHolder target;

  @UiThread
  public FansListAdapter$LikeViewHolder_ViewBinding(FansListAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.tv_type = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tv_type'", TextView.class);
    target.check_guanzhu = Utils.findRequiredViewAsType(source, R.id.check_guanzhu, "field 'check_guanzhu'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FansListAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.name = null;
    target.tv_type = null;
    target.check_guanzhu = null;
  }
}
