// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FeedbackActivity_ViewBinding implements Unbinder {
  private FeedbackActivity target;

  private View view7f0902d9;

  @UiThread
  public FeedbackActivity_ViewBinding(FeedbackActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FeedbackActivity_ViewBinding(final FeedbackActivity target, View source) {
    this.target = target;

    View view;
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", ScrollView.class);
    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.next, "field 'next' and method 'OnClick'");
    target.next = Utils.castView(view, R.id.next, "field 'next'", TextView.class);
    view7f0902d9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FeedbackActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.scrollView = null;
    target.editText = null;
    target.next = null;

    view7f0902d9.setOnClickListener(null);
    view7f0902d9 = null;
  }
}
