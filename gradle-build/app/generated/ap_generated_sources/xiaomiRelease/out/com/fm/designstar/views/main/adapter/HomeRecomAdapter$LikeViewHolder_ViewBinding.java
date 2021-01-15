// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.main.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cn.jzvdother.JzvdStd;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeRecomAdapter$LikeViewHolder_ViewBinding implements Unbinder {
  private HomeRecomAdapter.LikeViewHolder target;

  @UiThread
  public HomeRecomAdapter$LikeViewHolder_ViewBinding(HomeRecomAdapter.LikeViewHolder target,
      View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.foodImg, "field 'imageView'", ImageView.class);
    target.jzvdStd = Utils.findRequiredViewAsType(source, R.id.video_player, "field 'jzvdStd'", JzvdStd.class);
    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.views = Utils.findRequiredViewAsType(source, R.id.views, "field 'views'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.check_like = Utils.findRequiredViewAsType(source, R.id.check_like, "field 'check_like'", CheckBox.class);
    target.check_guanzhu = Utils.findRequiredViewAsType(source, R.id.check_guanzhu, "field 'check_guanzhu'", CheckBox.class);
    target.likenum = Utils.findRequiredViewAsType(source, R.id.likenum, "field 'likenum'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.tag_name = Utils.findRequiredViewAsType(source, R.id.tag_name, "field 'tag_name'", TextView.class);
    target.tv_type = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tv_type'", TextView.class);
    target.message_num = Utils.findRequiredViewAsType(source, R.id.message_num, "field 'message_num'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeRecomAdapter.LikeViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.jzvdStd = null;
    target.hand = null;
    target.name = null;
    target.views = null;
    target.title = null;
    target.check_like = null;
    target.check_guanzhu = null;
    target.likenum = null;
    target.time = null;
    target.tag_name = null;
    target.tv_type = null;
    target.message_num = null;
  }
}
