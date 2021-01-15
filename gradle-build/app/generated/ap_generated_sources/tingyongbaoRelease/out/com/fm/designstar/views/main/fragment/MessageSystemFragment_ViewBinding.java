// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.fragment;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageSystemFragment_ViewBinding implements Unbinder {
  private MessageSystemFragment target;

  private View view7f0900d2;

  private View view7f0900d4;

  private View view7f0900d5;

  private View view7f0900d3;

  @UiThread
  public MessageSystemFragment_ViewBinding(final MessageSystemFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.all_message, "field 'all_message' and method 'OnClick'");
    target.all_message = Utils.castView(view, R.id.all_message, "field 'all_message'", TextView.class);
    view7f0900d2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.all_pl, "field 'all_pl' and method 'OnClick'");
    target.all_pl = Utils.castView(view, R.id.all_pl, "field 'all_pl'", TextView.class);
    view7f0900d4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.all_zan, "field 'all_zan' and method 'OnClick'");
    target.all_zan = Utils.castView(view, R.id.all_zan, "field 'all_zan'", TextView.class);
    view7f0900d5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.all_notice, "field 'all_notice' and method 'OnClick'");
    target.all_notice = Utils.castView(view, R.id.all_notice, "field 'all_notice'", TextView.class);
    view7f0900d3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.message_recy = Utils.findRequiredViewAsType(source, R.id.message_recy, "field 'message_recy'", XRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageSystemFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.all_message = null;
    target.all_pl = null;
    target.all_zan = null;
    target.all_notice = null;
    target.message_recy = null;

    view7f0900d2.setOnClickListener(null);
    view7f0900d2 = null;
    view7f0900d4.setOnClickListener(null);
    view7f0900d4 = null;
    view7f0900d5.setOnClickListener(null);
    view7f0900d5 = null;
    view7f0900d3.setOnClickListener(null);
    view7f0900d3 = null;
  }
}
