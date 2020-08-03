package com.fm.designstar.views.Fabu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.Manifest;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.base.PermissionsListener;
import com.fm.designstar.utils.GlideLoader;
import com.fm.designstar.utils.SpaceItemDecoration;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.views.Fabu.adapter.ReviewPhotoAdapter;
import com.fm.designstar.widget.imagePicker.ImagePicker;

import java.util.List;

public class FabuActivity extends BaseActivity {

    @BindView(R.id.re_title)
    RelativeLayout re_title;
    @BindView(R.id.photoRecycler)
    RecyclerView photoRecycler;
    private ReviewPhotoAdapter photoAdapter;
    public static final int SELECT_CODE = 100;
    private int type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fabu;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {

        type=getIntent().getIntExtra("type",1);
        re_title.getLayoutParams().height = Tool.dip2px(mContext, 44) + Util.getStatusBarH(mContext);
        ((ViewGroup.MarginLayoutParams) re_title.getLayoutParams()).topMargin = Util.getStatusBarH(mContext);
        photoRecycler.setLayoutManager(new GridLayoutManager(mContext, 4));
        photoRecycler.addItemDecoration(new SpaceItemDecoration().setRight(Tool.dip2px(mContext, 10)).setBottom(Tool.dip2px(mContext, 10)));
        photoAdapter = new ReviewPhotoAdapter(mContext);
        photoRecycler.setAdapter(photoAdapter);

        photoAdapter.setListener(new ReviewPhotoAdapter.AddPhotoListener() {
            @Override
            public void addPhoto() {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, mListener);
            }
        });


    }
    private PermissionsListener mListener = new PermissionsListener() {
        @Override
        public void onGranted() {
          /*  Bundle bundle = new Bundle();
            bundle.putStringArrayList(PhotoActivity.SELECT_RESULT, photoAdapter.getData());
            startActivityForResult(PhotoActivity.class, bundle, SELECT_CODE);*/
          if (type==1){
              ImagePicker.getInstance()
                      .setTitle("标题")//设置标题
                      .showCamera(true)//设置是否显示拍照按钮
                      .showImage(true)//设置是否展示图片
                      .showVideo(true)//设置是否展示视频
                      .filterGif(true)//设置是否过滤gif图片
                      .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                      .setSingleType(true)//设置图片视频不能同时选择
                      .setImagePaths(photoAdapter.getData())//设置历史选择记录
                      .setImageLoader(new GlideLoader())//设置自定义图片加载器
                      .start(FabuActivity.this, SELECT_CODE,3);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode

          }else {
              ImagePicker.getInstance()
                      .setTitle("标题")//设置标题
                      .showCamera(true)//设置是否显示拍照按钮
                      .showImage(false)//设置是否展示图片
                      .showVideo(true)//设置是否展示视频
                      .filterGif(true)//设置是否过滤gif图片
                      .setMaxCount(9)//设置最大选择图片数目(默认为1，单选)
                      .setSingleType(true)//设置图片视频不能同时选择
                      .setImagePaths(photoAdapter.getData())//设置历史选择记录
                      .setImageLoader(new GlideLoader())//设置自定义图片加载器
                      .start(FabuActivity.this, SELECT_CODE,3);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode

          }
                      /* auth = FirebaseAuth.getInstance();
            if (auth.getCurrentUser() == null) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(PhotoActivity.SELECT_RESULT, photoAdapter.getData());
                startActivityForResult(PhotoActivity.class, bundle, SELECT_CODE);
            } else {
                authenticateUser();
            }
*/
        }


        @Override
        public void onDenied(List<String> deniedPermissions, boolean isNeverAsk) {

        }
    };
}