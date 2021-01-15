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
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private MessageAdapter.LikeViewHolder target;

  @UiThread
  public MessageAdapter$LikeViewHolder_ViewBinding(MessageAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.foodImg, "field 'imageView'", ImageView.class);
    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.hand = null;
    target.name = null;
    target.content = null;
    target.time = null;
  }
}
