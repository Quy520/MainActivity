// Generated code from Butter Knife. Do not modify!
package com.fm.designstar.views.login.activitys;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.fm.designstar.R;
import com.fm.designstar.widget.VerificationCodeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MsgCodeActivity_ViewBinding implements Unbinder {
  private MsgCodeActivity target;

  @UiThread
  public MsgCodeActivity_ViewBinding(MsgCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MsgCodeActivity_ViewBinding(MsgCodeActivity target, View source) {
    this.target = target;

    target.verificationCodeView = Utils.findRequiredViewAsType(source, R.id.verificationCodeView, "field 'verificationCodeView'", VerificationCodeView.class);
    target.tv_phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'tv_phone'", TextView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MsgCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.verificationCodeView = null;
    target.tv_phone = null;
    target.tv_time = null;
  }
}
