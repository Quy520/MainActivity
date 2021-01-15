package com.fm.designstar.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fm.designstar.R;
import com.fm.designstar.utils.StringUtil;
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
public class SercertDialog extends AlertDialog {
    private Context mContext;
    private OnClickListener onClickListener;

    public SercertDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    protected SercertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected SercertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sercet);

        TextView ok = findViewById(R.id.ok);
        TextView dissagress = findViewById(R.id.dissagress);
        TextView name3 = findViewById(R.id.name3);


        TextViewUtil.setPartialColors3(mContext,name3,name3.getText().toString(),R.color.transparent);

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
