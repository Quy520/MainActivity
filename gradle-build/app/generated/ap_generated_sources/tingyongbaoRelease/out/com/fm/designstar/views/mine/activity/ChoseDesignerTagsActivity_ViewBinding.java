// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChoseDesignerTagsActivity_ViewBinding implements Unbinder {
  private ChoseDesignerTagsActivity target;

  @UiThread
  public ChoseDesignerTagsActivity_ViewBinding(ChoseDesignerTagsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChoseDesignerTagsActivity_ViewBinding(ChoseDesignerTagsActivity target, View source) {
    this.target = target;

    target.recy_mansger = Utils.findRequiredViewAsType(source, R.id.recy_tagmansger, "field 'recy_mansger'", RecyclerView.class);
    target.addTags = Utils.findRequiredViewAsType(source, R.id.addTags, "field 'addTags'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChoseDesignerTagsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recy_mansger = null;
    target.addTags = null;
  }
}
