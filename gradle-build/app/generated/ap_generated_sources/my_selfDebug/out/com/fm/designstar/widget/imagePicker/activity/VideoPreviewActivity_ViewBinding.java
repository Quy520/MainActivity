// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.widget.imagePicker.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoPreviewActivity_ViewBinding implements Unbinder {
  private VideoPreviewActivity target;

  private View view7f09025a;

  @UiThread
  public VideoPreviewActivity_ViewBinding(VideoPreviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VideoPreviewActivity_ViewBinding(final VideoPreviewActivity target, View source) {
    this.target = target;

    View view;
    target.mFlVideo = Utils.findRequiredViewAsType(source, R.id.fl, "field 'mFlVideo'", FrameLayout.class);
    target.mVideoView = Utils.findRequiredViewAsType(source, R.id.videoView, "field 'mVideoView'", VideoView.class);
    target.mIvThumb = Utils.findRequiredViewAsType(source, R.id.iv_thumb, "field 'mIvThumb'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_play, "field 'mIvPlay' and method 'onClick'");
    target.mIvPlay = Utils.castView(view, R.id.iv_play, "field 'mIvPlay'", ImageView.class);
    view7f09025a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoPreviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mFlVideo = null;
    target.mVideoView = null;
    target.mIvThumb = null;
    target.mIvPlay = null;

    view7f09025a.setOnClickListener(null);
    view7f09025a = null;
  }
}
