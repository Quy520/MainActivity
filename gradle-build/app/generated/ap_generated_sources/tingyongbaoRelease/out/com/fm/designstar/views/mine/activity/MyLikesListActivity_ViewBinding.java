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

public class MyLikesListActivity_ViewBinding implements Unbinder {
  private MyLikesListActivity target;

  @UiThread
  public MyLikesListActivity_ViewBinding(MyLikesListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyLikesListActivity_ViewBinding(MyLikesListActivity target, View source) {
    this.target = target;

    target.recy_black = Utils.findRequiredViewAsType(source, R.id.recy_fans, "field 'recy_black'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyLikesListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recy_black = null;
  }
}
