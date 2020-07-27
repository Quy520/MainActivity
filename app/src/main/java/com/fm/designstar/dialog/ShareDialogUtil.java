package com.fm.designstar.dialog;

import android.content.ClipboardManager;
import android.content.Context;

import android.os.StrictMode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fm.designstar.R;
import com.fm.designstar.utils.ToastUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/29 10:46
 * @update : 2018/9/29
 */
public class ShareDialogUtil {
    private BottomSheetDialog bottomSheet;
    private Context context;
    private int sex=1;
    private CheckBox man;
    private CheckBox woamn;
    private View.OnClickListener clickListener;
    public ShareDialogUtil(Context context) {
        this.context = context;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public interface ShareCallBack {
        void success();
    }
    public void setOnClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public void showDialog() {
        bottomSheet = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sex, null);
        bottomSheet.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        man=view.findViewById(R.id.man);
        woamn=view.findViewById(R.id.woman);
        man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ToastUtil.showToast("manb"+b);
                if (b){
                    sex=1;
                    woamn.setChecked(false);
                }else {
                    ToastUtil.showToast("b"+b);
                }

            }
        });
        woamn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ToastUtil.showToast("woman"+b);
                if (b){
                    sex=2;
                    man.setChecked(false);
                }
            }
        });

        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onClick(view);
                }
                bottomSheet.cancel();
            }
        });
        view.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onClick(view);
                }
                bottomSheet.cancel();

            }
        });

        bottomSheet.setContentView(view);
        ((View) view.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        bottomSheet.show();

    }

    public int getSex(){
        return sex;
    }




}
