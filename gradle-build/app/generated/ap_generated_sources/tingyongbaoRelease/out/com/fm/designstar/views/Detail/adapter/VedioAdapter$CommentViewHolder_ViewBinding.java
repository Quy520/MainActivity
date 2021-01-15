// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Detail.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.JzvdStdTikTok;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VedioAdapter$CommentViewHolder_ViewBinding implements Unbinder {
  private VedioAdapter.CommentViewHolder target;

  @UiThread
  public VedioAdapter$CommentViewHolder_ViewBinding(VedioAdapter.CommentViewHolder target,
      View source) {
    this.target = target;

    target.videoplayer = Utils.findRequiredViewAsType(source, R.id.videoplayer, "field 'videoplayer'", JzvdStdTikTok.class);
    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.img_play = Utils.findRequiredViewAsType(source, R.id.img_play, "field 'img_play'", ImageView.class);
    target.left = Utils.findRequiredViewAsType(source, R.id.left, "field 'left'", ImageView.class);
    target.vedio_share = Utils.findRequiredViewAsType(source, R.id.vedio_share, "field 'vedio_share'", ImageView.class);
    target.check_like = Utils.findRequiredViewAsType(source, R.id.check_guanzhu, "field 'check_like'", CheckBox.class);
    target.likenum = Utils.findRequiredViewAsType(source, R.id.likenum, "field 'likenum'", TextView.class);
    target.comenum = Utils.findRequiredViewAsType(source, R.id.comenum, "field 'comenum'", TextView.class);
    target.comment = Utils.findRequiredViewAsType(source, R.id.comment, "field 'comment'", TextView.class);
    target.go_comment = Utils.findRequiredViewAsType(source, R.id.go_comment, "field 'go_comment'", TextView.class);
    target.root_view = Utils.findRequiredViewAsType(source, R.id.root_view, "field 'root_view'", RelativeLayout.class);
    target.re_message = Utils.findRequiredViewAsType(source, R.id.re_message, "field 're_message'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VedioAdapter.CommentViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.videoplayer = null;
    target.hand = null;
    target.img_play = null;
    target.left = null;
    target.vedio_share = null;
    target.check_like = null;
    target.likenum = null;
    target.comenum = null;
    target.comment = null;
    target.go_comment = null;
    target.root_view = null;
    target.re_message = null;
  }
}
