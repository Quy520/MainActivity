// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.adapter;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewsListRecycleAdapter$ViewHolderOne_ViewBinding implements Unbinder {
  private NewsListRecycleAdapter.ViewHolderOne target;

  @UiThread
  public NewsListRecycleAdapter$ViewHolderOne_ViewBinding(
      NewsListRecycleAdapter.ViewHolderOne target, View source) {
    this.target = target;

    target.banner2 = Utils.findRequiredViewAsType(source, R.id.banner2, "field 'banner2'", Banner.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NewsListRecycleAdapter.ViewHolderOne target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.banner2 = null;
  }
}
