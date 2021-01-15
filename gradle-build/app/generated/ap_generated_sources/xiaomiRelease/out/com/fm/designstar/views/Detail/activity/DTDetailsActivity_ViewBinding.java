// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.Detail.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.CostomGrideView;
import com.fm.designstar.widget.viegroup.MyViewGroup;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DTDetailsActivity_ViewBinding implements Unbinder {
  private DTDetailsActivity target;

  private View view7f090228;

  private View view7f0901e0;

  private View view7f0904cd;

  private View view7f0902b9;

  @UiThread
  public DTDetailsActivity_ViewBinding(DTDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DTDetailsActivity_ViewBinding(final DTDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.hand = Utils.findRequiredViewAsType(source, R.id.hand, "field 'hand'", CircleImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", TextView.class);
    target.content = Utils.findRequiredViewAsType(source, R.id.content, "field 'content'", TextView.class);
    target.more = Utils.findRequiredViewAsType(source, R.id.more, "field 'more'", ImageView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.likenum = Utils.findRequiredViewAsType(source, R.id.likenum, "field 'likenum'", TextView.class);
    target.message_num = Utils.findRequiredViewAsType(source, R.id.message_num, "field 'message_num'", TextView.class);
    target.buttonLay = Utils.findRequiredViewAsType(source, R.id.buttonLay, "field 'buttonLay'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.inputLay, "field 'inputLay' and method 'OnClick'");
    target.inputLay = Utils.castView(view, R.id.inputLay, "field 'inputLay'", RelativeLayout.class);
    view7f090228 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
    target.imgLay = Utils.findRequiredViewAsType(source, R.id.imgLay, "field 'imgLay'", FrameLayout.class);
    target.ly_two_img = Utils.findRequiredViewAsType(source, R.id.ly_two_img, "field 'ly_two_img'", LinearLayout.class);
    target.oneImg = Utils.findRequiredViewAsType(source, R.id.oneImg, "field 'oneImg'", ImageView.class);
    target.im = Utils.findRequiredViewAsType(source, R.id.im, "field 'im'", ImageView.class);
    target.oneImg2 = Utils.findRequiredViewAsType(source, R.id.oneImg2, "field 'oneImg2'", ImageView.class);
    target.oneImg3 = Utils.findRequiredViewAsType(source, R.id.oneImg3, "field 'oneImg3'", ImageView.class);
    target.gw = Utils.findRequiredViewAsType(source, R.id.gw, "field 'gw'", CostomGrideView.class);
    target.gw2 = Utils.findRequiredViewAsType(source, R.id.gw2, "field 'gw2'", CostomGrideView.class);
    target.myViewGroup = Utils.findRequiredViewAsType(source, R.id.myViewGroup, "field 'myViewGroup'", MyViewGroup.class);
    target.commentRecycler = Utils.findRequiredViewAsType(source, R.id.commentRecycler, "field 'commentRecycler'", RecyclerView.class);
    target.noReply = Utils.findRequiredViewAsType(source, R.id.no_reply, "field 'noReply'", LinearLayout.class);
    target.check_like = Utils.findRequiredViewAsType(source, R.id.check_like, "field 'check_like'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.go_comment, "method 'OnClick'");
    view7f0901e0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.send, "method 'OnClick'");
    view7f0904cd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.message, "method 'OnClick'");
    view7f0902b9 = view;
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
    DTDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.hand = null;
    target.name = null;
    target.address = null;
    target.content = null;
    target.more = null;
    target.time = null;
    target.likenum = null;
    target.message_num = null;
    target.buttonLay = null;
    target.inputLay = null;
    target.editText = null;
    target.imgLay = null;
    target.ly_two_img = null;
    target.oneImg = null;
    target.im = null;
    target.oneImg2 = null;
    target.oneImg3 = null;
    target.gw = null;
    target.gw2 = null;
    target.myViewGroup = null;
    target.commentRecycler = null;
    target.noReply = null;
    target.check_like = null;

    view7f090228.setOnClickListener(null);
    view7f090228 = null;
    view7f0901e0.setOnClickListener(null);
    view7f0901e0 = null;
    view7f0904cd.setOnClickListener(null);
    view7f0904cd = null;
    view7f0902b9.setOnClickListener(null);
    view7f0902b9 = null;
  }
}
