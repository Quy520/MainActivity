package com.fm.designstar.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.fm.designstar.R;
import com.fm.designstar.utils.GlideLoader;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.Fabu.FabuActivity;
import com.fm.designstar.widget.imagePicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import static com.fm.designstar.views.Fabu.FabuActivity.SELECT_CODE;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/29 10:46
 * @update : 2018/9/29
 */
public class ChoseDialogUtil {
    private BottomSheetDialog bottomSheet;
    private Context context;
    private Activity activity;
    private LinearLayout suishoupai;
    private LinearLayout zp;
    private ArrayList<String> data;
    private OnSuccess clickListener;

    public ChoseDialogUtil(Context context, Activity activity, ArrayList<String> s) {
        this.activity=activity;
        this.context = context;
        this.data=s;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
    public interface OnSuccess {
        void success();
        void success2();
    }
    public void setOnClickListener(OnSuccess clickListener) {
        this.clickListener = clickListener;
    }

    public void showDialog() {
        bottomSheet = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_chose, null);
        bottomSheet.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        suishoupai=view.findViewById(R.id.suishoupai);
        zp=view.findViewById(R.id.zp);
        suishoupai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener!=null){
                    clickListener.success2();
                }
                ImagePicker.getInstance()
                        .setTitle("标题")//设置标题
                        .showCamera(true)//设置是否显示拍照按钮
                        .showImage(true)//设置是否展示图片
                        .showVideo(false)//设置是否展示视频
                        .filterGif(true)//设置是否过滤gif图片
                        .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                        .setSingleType(true)//设置图片视频不能同时选择
                        .setImagePaths(null)//设置历史选择记录
                        .setImageLoader(new GlideLoader())//设置自定义图片加载器
                        .start(activity, SELECT_CODE,1);
                        bottomSheet.cancel();

            }
        });

        zp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (clickListener!=null){
                        clickListener.success();
                        bottomSheet.dismiss();
                    }


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
