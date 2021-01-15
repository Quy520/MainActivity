// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Fabu.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.viegroup.MyViewGroup;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChoseTagActivity_ViewBinding implements Unbinder {
  private ChoseTagActivity target;

  @UiThread
  public ChoseTagActivity_ViewBinding(ChoseTagActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChoseTagActivity_ViewBinding(ChoseTagActivity target, View source) {
    this.target = target;

    target.myViewGroup = Utils.findRequiredViewAsType(source, R.id.myViewGroup, "field 'myViewGroup'", MyViewGroup.class);
    target.flowLayout = Utils.findRequiredViewAsType(source, R.id.flowLayout, "field 'flowLayout'", MultiLineChooseLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChoseTagActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myViewGroup = null;
    target.flowLayout = null;
  }
}
