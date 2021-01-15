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

public class RvRightAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private RvRightAdapter.LikeViewHolder target;

  @UiThread
  public RvRightAdapter$LikeViewHolder_ViewBinding(RvRightAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.mTvBiaoTi = Utils.findRequiredViewAsType(source, R.id.tv_liandongrightada_BiaoTi, "field 'mTvBiaoTi'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RvRightAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvBiaoTi = null;
  }
}
