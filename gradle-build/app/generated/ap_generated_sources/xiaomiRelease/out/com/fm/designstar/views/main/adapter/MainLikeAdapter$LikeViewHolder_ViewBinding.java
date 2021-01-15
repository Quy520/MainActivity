// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainLikeAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private MainLikeAdapter.LikeViewHolder target;

  @UiThread
  public MainLikeAdapter$LikeViewHolder_ViewBinding(MainLikeAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.foodImg, "field 'imageView'", ImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.views = Utils.findRequiredViewAsType(source, R.id.views, "field 'views'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainLikeAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.name = null;
    target.time = null;
    target.views = null;
  }
}
