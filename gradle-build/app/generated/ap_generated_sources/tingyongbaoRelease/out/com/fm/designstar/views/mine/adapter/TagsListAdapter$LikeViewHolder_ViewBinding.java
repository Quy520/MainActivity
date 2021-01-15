// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.adapter;

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

public class TagsListAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private TagsListAdapter.LikeViewHolder target;

  @UiThread
  public TagsListAdapter$LikeViewHolder_ViewBinding(TagsListAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.tags = Utils.findRequiredViewAsType(source, R.id.tags, "field 'tags'", TextView.class);
    target.chosed = Utils.findRequiredViewAsType(source, R.id.chosed, "field 'chosed'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TagsListAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tags = null;
    target.chosed = null;
  }
}
