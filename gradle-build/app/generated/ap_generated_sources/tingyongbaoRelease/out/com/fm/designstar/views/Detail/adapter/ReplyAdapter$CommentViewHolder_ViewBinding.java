// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Detail.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReplyAdapter$CommentViewHolder_ViewBinding implements Unbinder {
  private ReplyAdapter.CommentViewHolder target;

  @UiThread
  public ReplyAdapter$CommentViewHolder_ViewBinding(ReplyAdapter.CommentViewHolder target,
      View source) {
    this.target = target;

    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.date = Utils.findRequiredViewAsType(source, R.id.date, "field 'date'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", TextView.class);
    target.delete = Utils.findRequiredViewAsType(source, R.id.delete, "field 'delete'", TextView.class);
    target.constraintLayout = Utils.findRequiredViewAsType(source, R.id.cons, "field 'constraintLayout'", ConstraintLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReplyAdapter.CommentViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.name = null;
    target.date = null;
    target.content = null;
    target.delete = null;
    target.constraintLayout = null;
  }
}
