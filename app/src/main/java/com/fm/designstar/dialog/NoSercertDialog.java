package com.fm.designstar.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.utils.TextViewUtil;

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
public class NoSercertDialog extends AlertDialog {
    private Context mContext;
    private OnClickListener onClickListener;

    public NoSercertDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    protected NoSercertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected NoSercertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_nosercet);

        TextView ok = findViewById(R.id.ok);
        TextView dissagress = findViewById(R.id.dissagress);




        dissagress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.up();
                }

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.close();
                }
                cancel();
            }
        });
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
    }

    public interface OnClickListener {
        void up();

        void close();
    }



    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
