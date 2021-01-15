// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShDesignerActivity_ViewBinding implements Unbinder {
  private ShDesignerActivity target;

  @UiThread
  public ShDesignerActivity_ViewBinding(ShDesignerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShDesignerActivity_ViewBinding(ShDesignerActivity target, View source) {
    this.target = target;

    target.recy_mansger = Utils.findRequiredViewAsType(source, R.id.recy_mansger, "field 'recy_mansger'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShDesignerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recy_mansger = null;
  }
}
