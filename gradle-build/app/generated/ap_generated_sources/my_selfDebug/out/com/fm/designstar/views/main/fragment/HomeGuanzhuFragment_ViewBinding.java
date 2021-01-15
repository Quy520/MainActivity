// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeGuanzhuFragment_ViewBinding implements Unbinder {
  private HomeGuanzhuFragment target;

  @UiThread
  public HomeGuanzhuFragment_ViewBinding(HomeGuanzhuFragment target, View source) {
    this.target = target;

    target.hotRecycler = Utils.findRequiredViewAsType(source, R.id.home_recy, "field 'hotRecycler'", XRecyclerView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.nodada, "field 'imageView'", ImageView.class);
    target.tv_noda = Utils.findRequiredViewAsType(source, R.id.tv_noda, "field 'tv_noda'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeGuanzhuFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hotRecycler = null;
    target.imageView = null;
    target.tv_noda = null;
  }
}
