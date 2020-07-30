package com.fm.designstar.map;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.fm.designstar.R;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Selectaddress extends BaseActivity implements LocateRecyclerAdapter.OnLocationItemClick, AMapLocationListener, PoiSearch.OnPoiSearchListener {
    private String keyWord = "";// 要输入的poi搜索关键字
    private ProgressDialog progDialog = null;// 搜索时进度条
    public AMapLocationClient mlocationClient = null;
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    ArrayList<PoiItem> pois=new ArrayList<>();
    private String city="";
    @BindView(R.id.locate_recycler)
    RecyclerView mLocateRecycler;
    @BindView(R.id.locate_cancel)
    TextView mLocateCancel;
    @BindView(R.id.locate_refresh)
    TextView mLocateRefresh; @BindView(R.id.top_title)
    LinearLayout top_title;
    private List<LocationInfo> mList;
    private LocateRecyclerAdapter mAdapter;
    private int currentPage = 0;// 当前页面，从0开始计数
    double latitude ;//获取纬度
    double longitude;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        mTitle.setTitle("所在位置");
        initLocate();
        mList = new ArrayList<>();
        mAdapter = new LocateRecyclerAdapter(this, mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLocateRecycler.setLayoutManager(layoutManager);
        mLocateRecycler.setAdapter(mAdapter);
        mAdapter.setLocationItemClick(this);
        mAdapter.setLocationItemClick(new LocateRecyclerAdapter.OnLocationItemClick() {
            @Override
            public void OnLocationClick(RecyclerView parent, View view, int position, LocationInfo locationInfo) {
                ToastUtil.showToast("p"+position+"=="+locationInfo.getAddress());
            }
        });
    }

    private void initLocate() {
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        mLocationOption.setOnceLocationLatest(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
        showProgressDialog();

    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                 latitude = amapLocation.getLatitude();//获取纬度
                 longitude = amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                Log.d("qsdhaha", amapLocation.getAddress());
                LocationInfo locationInfo = new LocationInfo();
                locationInfo.setAddress(amapLocation.getAddress());
                locationInfo.setLatitude(latitude);
                locationInfo.setLonTitude(longitude);
                mList.clear();
                mList.add(locationInfo);
                mAdapter.notifyDataSetChanged();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间

                doSearchQuery();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onPoiSearched(PoiResult result, int i) {
        dissmissProgressDialog();// 隐藏对话框
        query = result.getQuery();
         pois = result.getPois();
         if (pois!=null&&pois.size()>0){
             city = pois.get(0).getCityName();
         }
        mList.clear();
        for (PoiItem poi : pois) {
             city = poi.getCityName();
            String snippet = poi.getSnippet();
            LocationInfo info = new LocationInfo();
            info.setAddress(snippet);
            LatLonPoint point = poi.getLatLonPoint();

            info.setLatitude(point.getLatitude());
            info.setLonTitude(point.getLongitude());
            mList.add(info);
            Log.d("haha", "poi" + snippet);

        }

        mAdapter.notifyDataSetChanged();
        mlocationClient.stopLocation();

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


    @Override
    public void OnLocationClick(RecyclerView parent, View view, int position, LocationInfo locationInfo) {

    }

    @OnClick({R.id.locate_cancel, R.id.locate_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
          /*  case R.id.locate_cancel:

                doSearchQuery();
                break;
            case R.id.locate_refresh:
                initLocate();
                Toast.makeText(this, "正在重定位", Toast.LENGTH_SHORT).show();
                break;*/
        }
    }
    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        showProgressDialog();// 显示进度框
        currentPage = 0;
        Log.d("hah",keyWord+"==="+city);
        query = new PoiSearch.Query(keyWord, "", city);
        query.setPageSize(20);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 10000));
        poiSearch.searchPOIAsyn();
    }

    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        if (StringUtil.isBlank(keyWord)){
            progDialog.setMessage("正在定位请稍后");
        }else {

            progDialog.setMessage("正在搜索:\n" + keyWord);
        }
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }
}
