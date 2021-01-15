// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebActivity_ViewBinding implements Unbinder {
  private WebActivity target;

  @UiThread
  public WebActivity_ViewBinding(WebActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebActivity_ViewBinding(WebActivity target, View source) {
    this.target = target;

    target.toolLay = Utils.findRequiredViewAsType(source, R.id.toolLay, "field 'toolLay'", RelativeLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.mWebView = Utils.findRequiredViewAsType(source, R.id.web_view, "field 'mWebView'", WebView.class);
    target.mTvTagContent = Utils.findRequiredViewAsType(source, R.id.tv_tag_content, "field 'mTvTagContent'", TextView.class);
    target.mDialogView = Utils.findRequiredViewAsType(source, R.id.dialog_view, "field 'mDialogView'", LinearLayout.class);
    target.mProgressBar = Utils.findRequiredViewAsType(source, R.id.progressbar, "field 'mProgressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WebActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolLay = null;
    target.toolbar = null;
    target.mWebView = null;
    target.mTvTagContent = null;
    target.mDialogView = null;
    target.mProgressBar = null;
  }
}
