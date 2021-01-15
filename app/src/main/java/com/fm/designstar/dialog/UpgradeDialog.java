package com.fm.designstar.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.utils.StringUtil;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/11/2 10:44
 * @update : 2018/11/2
 */
public class UpgradeDialog extends AlertDialog {
    private Context mContext;
    private boolean noUpgrade;
    private String versionInfo;
    private OnClickListener onClickListener;

    public UpgradeDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    protected UpgradeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected UpgradeDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_upgrade);
        View close = findViewById(R.id.close);
        TextView ok = findViewById(R.id.ok);
        Objects.requireNonNull(close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.close();
                }
                cancel();
            }
        });
        if (noUpgrade) {
            close.setVisibility(View.VISIBLE);
        } else {
            close.setVisibility(View.GONE);
        }
        if (!StringUtil.isBlank(versionInfo)) {
            ((TextView) Objects.requireNonNull(findViewById(R.id.name))).setText(versionInfo.replace("\\r\\n", "\r\n"));
        } else {
            ((TextView) Objects.requireNonNull(findViewById(R.id.name))).setText(R.string.version);
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.up();
                }

            }
        });
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
    }

    public interface OnClickListener {
        void up();

        void close();
    }

    public void setNoUpgrade(boolean noUpgrade) {
        this.noUpgrade = noUpgrade;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
