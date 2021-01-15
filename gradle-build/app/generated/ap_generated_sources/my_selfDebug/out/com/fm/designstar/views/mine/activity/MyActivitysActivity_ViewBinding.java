// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyActivitysActivity_ViewBinding implements Unbinder {
  private MyActivitysActivity target;

  @UiThread
  public MyActivitysActivity_ViewBinding(MyActivitysActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyActivitysActivity_ViewBinding(MyActivitysActivity target, View source) {
    this.target = target;

    target.recy_activits = Utils.findRequiredViewAsType(source, R.id.recy_activits, "field 'recy_activits'", XRecyclerView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.nodada, "field 'imageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyActivitysActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recy_activits = null;
    target.imageView = null;
  }
}
