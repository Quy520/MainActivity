package com.fm.designstar.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;


import com.fm.designstar.R;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.StringRes;

/**
 * @author DELL
 */
public class ActionSheetDialog {
    private Context context;
    private Dialog dialog;
    //    private TextView txtTitle;
    private TextView txtCancel;
    private LinearLayout lLayoutContent;
    private ScrollView scrollviewContent;
    private boolean showTitle = false;
    private List<SheetItem> sheetItemList;
    private Display display;

    private FrameLayout layout;

    private OnClickListener clickListener;


    public ActionSheetDialog(Context context) {
        this.context = context;
        if (context == null) {
            return;
        }
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public void setOnClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ActionSheetDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_actionsheet, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        // 获取自定义Dialog布局中的控件
        scrollviewContent = (ScrollView) view.findViewById(R.id.sLayout_content);
        lLayoutContent = (LinearLayout) view
                .findViewById(R.id.lLayout_content);

//        txtTitle = (TextView) view.findViewById(R.id.txt_title);
        layout = view.findViewById(R.id.layout);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        txtCancel = (TextView) view.findViewById(R.id.txt_cancel);
        txtCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClick(v);
                }
                cancel();
            }
        });

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        return this;
    }

    private void cancel() {
        try {
            if (Util.ActivityIsClose(context)) {
                return;
            }
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialog = null;
        }
    }

    public void setCancelable(boolean cancelable) {
        if (cancelable) {
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        } else {
            layout.setOnClickListener(null);
        }
    }




    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public ActionSheetDialog addSheetItem(String strItem, SheetItemColor color,
                                          OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }

    /**
     * @param resItem  条目名称String资源 id
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public ActionSheetDialog addSheetItem(@StringRes int resItem, SheetItemColor color,
                                          OnSheetItemClickListener listener) {
        return addSheetItem(context.getString(resItem), color, listener);
    }

    /**
     * 设置条目布局
     */
    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }
        int size = sheetItemList.size();
        // TODO 高度控制，非最佳解决办法
        // 添加条目过多的时候控制高度
        if (size >= 7) {
            LayoutParams params = (LayoutParams) scrollviewContent
                    .getLayoutParams();
            params.height = display.getHeight() / 2;
            scrollviewContent.setLayoutParams(params);
        }
        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            SheetItemColor color = sheetItem.color;
            final OnSheetItemClickListener listener = sheetItem.itemClickListener;

            TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(14);
            textView.setGravity(Gravity.CENTER);
            // 字体颜色
            if (color == null) {
                textView.setTextColor(Color.parseColor(SheetItemColor.Black
                        .getName()));
            } else {
                textView.setTextColor(Color.parseColor(color.getName()));
            }

            // 高度
            textView.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT, Tool.dip2px(context, 63)));

            // 点击事件
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    try {
                        if (Util.ActivityIsClose(context)) {
                            return;
                        }
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        dialog = null;
                    }
                }
            });
            lLayoutContent.addView(textView);
        }
    }

    public void show() {
        setSheetItems();
        if (Util.ActivityIsClose(context)) {
            return;
        }
        if (dialog != null) {
            dialog.show();
        }
    }


    public interface OnSheetItemClickListener {
        void onClick(int which);
    }

    public class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        SheetItemColor color;

        public SheetItem(String name, SheetItemColor color,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }

    public enum SheetItemColor {
        Black("#3E4248"),
        Red("#F8627F"),
        Yellow("#FFC145");

        private String name;

        private SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
