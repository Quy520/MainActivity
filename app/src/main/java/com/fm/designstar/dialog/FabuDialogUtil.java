package com.fm.designstar.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.fm.designstar.R;
import com.fm.designstar.utils.GlideLoader;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.Fabu.FabuActivity;
import com.fm.designstar.widget.imagePicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import static com.fm.designstar.views.Fabu.FabuActivity.SELECT_CODE;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/29 10:46
 * @update : 2018/9/29
 */
public class FabuDialogUtil {
    private BottomSheetDialog bottomSheet;
    private Context context;
    private Activity activity;
    private LinearLayout suishoupai;
    private LinearLayout zp;
    public FabuDialogUtil(Context context,Activity activity) {
        this.activity=activity;
        this.context = context;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }


    public void showDialog() {
        bottomSheet = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fabu, null);
        bottomSheet.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        suishoupai=view.findViewById(R.id.suishoupai);
        zp=view.findViewById(R.id.zp);
        suishoupai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,FabuActivity.class);
                intent.putExtra("type",1);
                context.startActivity(intent);
                bottomSheet.cancel();

            }
        });

        zp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,FabuActivity.class);
                intent.putExtra("type",2);
                context.startActivity(intent);
                bottomSheet.cancel();


            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheet.cancel();
            }
        });


        bottomSheet.setContentView(view);
        ((View) view.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        bottomSheet.show();

    }






}
