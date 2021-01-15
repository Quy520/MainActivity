// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Fabu.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviewVedioAdapter$ReviewPhotoViewHolder_ViewBinding implements Unbinder {
  private ReviewVedioAdapter.ReviewPhotoViewHolder target;

  @UiThread
  public ReviewVedioAdapter$ReviewPhotoViewHolder_ViewBinding(
      ReviewVedioAdapter.ReviewPhotoViewHolder target, View source) {
    this.target = target;

    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.close = Utils.findRequiredViewAsType(source, R.id.close, "field 'close'", ImageView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.biaji = Utils.findRequiredViewAsType(source, R.id.biaji, "field 'biaji'", TextView.class);
    target.re_image = Utils.findRequiredViewAsType(source, R.id.re_image, "field 're_image'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReviewVedioAdapter.ReviewPhotoViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
    target.close = null;
    target.time = null;
    target.biaji = null;
    target.re_image = null;
  }
}
