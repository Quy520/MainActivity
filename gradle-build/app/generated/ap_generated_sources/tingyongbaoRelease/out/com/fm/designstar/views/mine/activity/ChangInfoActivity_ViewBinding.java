// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.mine.activity;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangInfoActivity_ViewBinding implements Unbinder {
  private ChangInfoActivity target;

  @UiThread
  public ChangInfoActivity_ViewBinding(ChangInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangInfoActivity_ViewBinding(ChangInfoActivity target, View source) {
    this.target = target;

    target.editText = Utils.findRequiredViewAsType(source, R.id.info, "field 'editText'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editText = null;
  }
}
