// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.map;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LocateRecyclerAdapter$LocateViewHolder_ViewBinding implements Unbinder {
  private LocateRecyclerAdapter.LocateViewHolder target;

  @UiThread
  public LocateRecyclerAdapter$LocateViewHolder_ViewBinding(
      LocateRecyclerAdapter.LocateViewHolder target, View source) {
    this.target = target;

    target.mTextView = Utils.findRequiredViewAsType(source, R.id.locate_info_adress, "field 'mTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LocateRecyclerAdapter.LocateViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTextView = null;
  }
}
