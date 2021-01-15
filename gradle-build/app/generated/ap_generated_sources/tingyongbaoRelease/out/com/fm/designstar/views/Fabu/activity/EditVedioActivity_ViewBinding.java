// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Fabu.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditVedioActivity_ViewBinding implements Unbinder {
  private EditVedioActivity target;

  @UiThread
  public EditVedioActivity_ViewBinding(EditVedioActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditVedioActivity_ViewBinding(EditVedioActivity target, View source) {
    this.target = target;

    target.videoView = Utils.findRequiredViewAsType(source, R.id.vv_player, "field 'videoView'", VideoView.class);
    target.sbVideo = Utils.findRequiredViewAsType(source, R.id.sb_select, "field 'sbVideo'", SeekBar.class);
    target.ivHead = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'ivHead'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditVedioActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.videoView = null;
    target.sbVideo = null;
    target.ivHead = null;
  }
}
