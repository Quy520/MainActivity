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
    private CheckBox sercet;
    private OnSuccess clickListener;
    public ShareDialogUtil(Context context) {
        this.context = context;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public interface OnSuccess {
        void success(int sex);
    }
    public void setOnClickListener(OnSuccess clickListener) {
        this.clickListener = clickListener;
    }
    public void showDialog() {
        bottomSheet = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sex, null);
        bottomSheet.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        man=view.findViewById(R.id.man);
        woamn=view.findViewById(R.id.woman);
        sercet=view.findViewById(R.id.sercet);
        if (sex==1){
            man.setChecked(true);
        }else {
            woamn.setChecked(true);

        }
        man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    clickListener.success(1);
                    sex=1;
                    woamn.setChecked(false);
                    sercet.setChecked(false);

                }else {
                }

            }
        });
        woamn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    clickListener.success(2);

                    sex=2;
                    man.setChecked(false);
                    sercet.setChecked(false);

                }
            }
        });

        sercet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    clickListener.success(0);

                    sex=0;
                    man.setChecked(false);
                    woamn.setChecked(false);

                }
            }
        });

        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {

                }
                bottomSheet.cancel();
            }
        });
        view.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {

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
public void setSex(int msex){
        this.sex=msex;
    }




}
