// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchActivity_ViewBinding implements Unbinder {
  private SearchActivity target;

  @UiThread
  public SearchActivity_ViewBinding(SearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchActivity_ViewBinding(SearchActivity target, View source) {
    this.target = target;

    target.titleLayout = Utils.findRequiredViewAsType(source, R.id.titleLayout, "field 'titleLayout'", RelativeLayout.class);
    target.topLayout = Utils.findRequiredViewAsType(source, R.id.topLayout, "field 'topLayout'", LinearLayout.class);
    target.cancle = Utils.findRequiredViewAsType(source, R.id.cancle, "field 'cancle'", TextView.class);
    target.searchEt = Utils.findRequiredViewAsType(source, R.id.searchEt, "field 'searchEt'", EditText.class);
    target.clear = Utils.findRequiredViewAsType(source, R.id.clear, "field 'clear'", ImageView.class);
    target.recy_designer = Utils.findRequiredViewAsType(source, R.id.recy_designer, "field 'recy_designer'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleLayout = null;
    target.topLayout = null;
    target.cancle = null;
    target.searchEt = null;
    target.clear = null;
    target.recy_designer = null;
  }
}
