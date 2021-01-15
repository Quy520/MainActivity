package com.fm.designstar.views.Fabu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;



import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.model.bean.TagsBean;
import com.fm.designstar.model.bean.TagsInfoVoBean;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.views.Fabu.adapter.RvLeftAdapter;
import com.fm.designstar.views.Fabu.adapter.RvRightAdapter;
import com.fm.designstar.views.Fabu.contract.GetTagContract;
import com.fm.designstar.views.Fabu.presenter.GetTagPresenter;
import com.fm.designstar.widget.recycler.BaseRecyclerAdapter;
import com.fm.designstar.widget.viegroup.MyViewGroup;
import com.google.gson.Gson;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChoseTagActivity extends BaseActivity<GetTagPresenter> /* implements GetTagContract.View */{


    @BindView(R.id.myViewGroup)
    MyViewGroup myViewGroup;
    @BindView(R.id.flowLayout)
    MultiLineChooseLayout flowLayout;



    private LinearLayoutManager mManagerLeft;
    private LinearLayoutManager mManagerRight;
    private boolean mIsFromClick;
    private JSONObject object;
    private JSONObject object2;
    private JSONArray jsonArray;//JSONObject对象，处理一个一个集合或者数组
    private JSONArray jsonArray2;//JSONObject对象，处理一个一个集合或者数组
    private TagsInfoVoBean tagsInfoVoBean;
    private List<TagsInfoVoBean> list=new ArrayList<>(); //保存数据的集合
    private List<TagsInfoVoBean> list2=new ArrayList<>(); //保存数据的集合
    private List<String> list3=new ArrayList<>(); //保存数据的集合
    private List<Integer> list4=new ArrayList<>(); //保存数据的集合
    private List<TagsInfoVoBean> resule=new ArrayList<TagsInfoVoBean>(); //保存数据的集合
    /**
     * 是否来自点击
     */
  //  String json="{\"code\":200,\"msg\":\"操作成功\",\"data\":[{\"tagType\":\"360°螺旋挂树\",\"tagsInfoVo\":[{\"id\":5,\"tagName\":\"阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮\"}]},{\"tagType\":\"test\",\"tagsInfoVo\":[{\"id\":1,\"tagName\":\"test\"},{\"id\":2,\"tagName\":\"AAA\"}]},{\"tagType\":\"商业空间设计\",\"tagsInfoVo\":[{\"id\":12,\"tagName\":\"商场\"},{\"id\":13,\"tagName\":\"便利店\"},{\"id\":14,\"tagName\":\"餐饮店\"},{\"id\":15,\"tagName\":\"咖啡厅\"},{\"id\":16,\"tagName\":\"奶茶店\"},{\"id\":17,\"tagName\":\"美术馆\"},{\"id\":18,\"tagName\":\"展览馆\"},{\"id\":19,\"tagName\":\"博物馆\"},{\"id\":20,\"tagName\":\"ktv\"},{\"id\":21,\"tagName\":\"游艺厅\"},{\"id\":22,\"tagName\":\"办公室\"},{\"id\":23,\"tagName\":\"会议室\"}]},{\"tagType\":\"室内设计风格\",\"tagsInfoVo\":[{\"id\":24,\"tagName\":\"北欧风格\"},{\"id\":25,\"tagName\":\"日式风格\"},{\"id\":26,\"tagName\":\"美式风格\"},{\"id\":27,\"tagName\":\"新中式风格\"},{\"id\":28,\"tagName\":\"中式风格\"},{\"id\":29,\"tagName\":\"复古风格\"},{\"id\":30,\"tagName\":\"现代风格\"},{\"id\":31,\"tagName\":\"轻奢风格\"},{\"id\":32,\"tagName\":\"极简风格\"},{\"id\":33,\"tagName\":\"孟菲斯风格\"},{\"id\":34,\"tagName\":\"Artdeco风格\"},{\"id\":35,\"tagName\":\"法式风格\"},{\"id\":36,\"tagName\":\"工业风风格\"}]},{\"tagType\":\"家居室内空间\",\"tagsInfoVo\":[{\"id\":37,\"tagName\":\"露台花园\"},{\"id\":38,\"tagName\":\"客厅\"},{\"id\":39,\"tagName\":\"卧室\"},{\"id\":40,\"tagName\":\"儿童房\"},{\"id\":41,\"tagName\":\"书房\"},{\"id\":42,\"tagName\":\"厨房\"},{\"id\":43,\"tagName\":\"走廊\"},{\"id\":44,\"tagName\":\"开放式厨房\"},{\"id\":45,\"tagName\":\"餐厅\"},{\"id\":46,\"tagName\":\"卫生间\"},{\"id\":47,\"tagName\":\"楼梯\"},{\"id\":48,\"tagName\":\"西厨\"},{\"id\":49,\"tagName\":\"飘窗\"},{\"id\":50,\"tagName\":\"玄关\"},{\"id\":51,\"tagName\":\"阁楼\"},{\"id\":52,\"tagName\":\"洗衣区\"},{\"id\":53,\"tagName\":\"储物间\"},{\"id\":54,\"tagName\":\"咖啡角\"},{\"id\":55,\"tagName\":\"阅读角\"},{\"id\":56,\"tagName\":\"植物角\"},{\"id\":57,\"tagName\":\"儿童游戏区\"}]},{\"tagType\":\"局部\",\"tagsInfoVo\":[{\"id\":117,\"tagName\":\"长虹玻璃\"},{\"id\":118,\"tagName\":\"谷仓门\"},{\"id\":119,\"tagName\":\"自流平\"},{\"id\":120,\"tagName\":\"人字拼地板\"},{\"id\":121,\"tagName\":\"黑框玻璃门\"},{\"id\":122,\"tagName\":\"百叶窗\"},{\"id\":123,\"tagName\":\"黑板墙\"},{\"id\":124,\"tagName\":\"大理石\"},{\"id\":125,\"tagName\":\"彩色墙\"},{\"id\":126,\"tagName\":\"镜子\"},{\"id\":127,\"tagName\":\"职住一体\"},{\"id\":128,\"tagName\":\"干湿分离\"},{\"id\":129,\"tagName\":\"玻璃\"},{\"id\":130,\"tagName\":\"岛台\"},{\"id\":131,\"tagName\":\"壁炉\"},{\"id\":132,\"tagName\":\"水龙头\"},{\"id\":133,\"tagName\":\"花洒\"},{\"id\":134,\"tagName\":\"水槽\"},{\"id\":135,\"tagName\":\"台盆\"},{\"id\":136,\"tagName\":\"壁龛\"}]},{\"tagType\":\"建筑风格\",\"tagsInfoVo\":[{\"id\":137,\"tagName\":\"中式建筑风格\"},{\"id\":138,\"tagName\":\"日式建筑风格\"},{\"id\":139,\"tagName\":\"新加坡建筑风格\"},{\"id\":140,\"tagName\":\"法式建筑风格\"},{\"id\":141,\"tagName\":\"美式建筑风格\"},{\"id\":142,\"tagName\":\"欧式建筑风格\"},{\"id\":143,\"tagName\":\"北美建筑风格\"},{\"id\":144,\"tagName\":\"地中海建筑风格\"},{\"id\":145,\"tagName\":\"澳洲建筑风格\"},{\"id\":146,\"tagName\":\"非洲建筑风格\"},{\"id\":147,\"tagName\":\"古典主义建筑风格\"},{\"id\":148,\"tagName\":\"新古典主义建筑风格\"},{\"id\":149,\"tagName\":\"哥特式建筑风格\"},{\"id\":150,\"tagName\":\"巴洛克建筑风格\"},{\"id\":151,\"tagName\":\"洛可可建筑风格\"},{\"id\":152,\"tagName\":\"园林建筑风格\"},{\"id\":153,\"tagName\":\"概念式建筑风格\"}]},{\"tagType\":\"我最牛\",\"tagsInfoVo\":[{\"id\":3,\"tagName\":\"牛掰哄哄\"},{\"id\":4,\"tagName\":\"掉渣天\"}]},{\"tagType\":\"户型\",\"tagsInfoVo\":[{\"id\":6,\"tagName\":\"一居室\"},{\"id\":7,\"tagName\":\"二居室\"},{\"id\":8,\"tagName\":\"三居室\"},{\"id\":9,\"tagName\":\"四居室\"},{\"id\":10,\"tagName\":\"别墅\"},{\"id\":11,\"tagName\":\"复式\"}]},{\"tagType\":\"硬装\",\"tagsInfoVo\":[{\"id\":58,\"tagName\":\"电视墙\"},{\"id\":59,\"tagName\":\"背景墙\"},{\"id\":60,\"tagName\":\"吧台\"},{\"id\":61,\"tagName\":\"射灯筒灯\"},{\"id\":62,\"tagName\":\"地砖\"},{\"id\":63,\"tagName\":\"地板\"},{\"id\":64,\"tagName\":\"门\"},{\"id\":65,\"tagName\":\"飘窗\"},{\"id\":66,\"tagName\":\"地漏\"},{\"id\":67,\"tagName\":\"美缝\"},{\"id\":68,\"tagName\":\"壁纸\"},{\"id\":69,\"tagName\":\"橱柜\"},{\"id\":70,\"tagName\":\"墙漆\"},{\"id\":71,\"tagName\":\"隔断\"},{\"id\":72,\"tagName\":\"吊顶\"},{\"id\":73,\"tagName\":\"踢脚线\"},{\"id\":74,\"tagName\":\"卡座\"},{\"id\":75,\"tagName\":\"LOFT\"},{\"id\":76,\"tagName\":\"地暖\"},{\"id\":77,\"tagName\":\"插座\"},{\"id\":78,\"tagName\":\"新风系统\"},{\"id\":79,\"tagName\":\"浴缸\"},{\"id\":80,\"tagName\":\"门槛石\"},{\"id\":81,\"tagName\":\"马桶\"},{\"id\":82,\"tagName\":\"榻榻米\"},{\"id\":83,\"tagName\":\"鞋柜\"},{\"id\":84,\"tagName\":\"地面\"},{\"id\":85,\"tagName\":\"厨卫工程\"},{\"id\":86,\"tagName\":\"水电工程\"}]},{\"tagType\":\"软装\",\"tagsInfoVo\":[{\"id\":87,\"tagName\":\"沙发\"},{\"id\":88,\"tagName\":\"床\"},{\"id\":89,\"tagName\":\"桌子\"},{\"id\":90,\"tagName\":\"椅子\"},{\"id\":91,\"tagName\":\"茶几\"},{\"id\":92,\"tagName\":\"书柜\"},{\"id\":93,\"tagName\":\"屏风\"},{\"id\":94,\"tagName\":\"梳妆台\"},{\"id\":95,\"tagName\":\"圆桌\"},{\"id\":96,\"tagName\":\"酒柜\"},{\"id\":97,\"tagName\":\"灯具知识\"},{\"id\":98,\"tagName\":\"吊灯\"},{\"id\":99,\"tagName\":\"落地灯\"},{\"id\":100,\"tagName\":\"台灯\"},{\"id\":101,\"tagName\":\"壁灯\"},{\"id\":102,\"tagName\":\"前灯\"},{\"id\":103,\"tagName\":\"感应灯\"},{\"id\":104,\"tagName\":\"窗帘\"},{\"id\":105,\"tagName\":\"照片墙\"},{\"id\":106,\"tagName\":\"装饰画\"},{\"id\":107,\"tagName\":\"绿植\"},{\"id\":108,\"tagName\":\"地毯\"},{\"id\":109,\"tagName\":\"家饰家纺\"},{\"id\":110,\"tagName\":\"家居店\"},{\"id\":111,\"tagName\":\"免打孔\"},{\"id\":112,\"tagName\":\"雕塑\"},{\"id\":113,\"tagName\":\"智能家居\"},{\"id\":114,\"tagName\":\"置物架\"},{\"id\":115,\"tagName\":\"家具定制\"},{\"id\":116,\"tagName\":\"鞋柜\"}]}],\"ok\":true}";

    ArrayList<String> allItemSelectedTextWithListArray=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_chose_tag;
    }

    @Override
    public void initPresenter() {
      //  mPresenter.init(this);

    }

    @Override
    public void loadData() {
        mTitle.setTitle(R.string.bq);
        mPresenter.GetTag(2);

    mTitle.setRightTitle("确认", new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        for (int i=0;i<allItemSelectedTextWithListArray.size();i++){
            for (int j=0;j<list3.size();j++){
                if (allItemSelectedTextWithListArray.get(i).equals(list3.get(j))){
                    resule.add(list.get(j));
                }
            }

        }

        Intent intent = new Intent();
       intent.putExtra("resule", (Serializable) resule);
        setResult(RESULT_OK, intent);
        finish();
        Log.e("qsd","=="+resule.size()+"=="+new Gson().toJson(resule));

    }
});


        flowLayout.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
            @Override
            public void onItemClick(int position, String text) {
                if (allItemSelectedTextWithListArray.size()>4){
                    ToastUtil.showToast("标签最多旋转五个");
                    flowLayout.cancelSelectedIndex(position);
                    return;
                }

              allItemSelectedTextWithListArray = flowLayout.getAllItemSelectedTextWithListArray();




            }
        });

    }





/*

    @Override
    public void GetTagSuccess(TagInfoResponse infoResponse) {
        list3=new ArrayList<>();
        list4=new ArrayList<>();
      for (int i=0;i<infoResponse.getList().size();i++){
          TagsBean tagsBean = infoResponse.getList().get(i);
          list2 = tagsBean.getTagInfoVoList();
          list.addAll(tagsBean.getTagInfoVoList());
          for (int j=0;j<list2.size();j++){
              list3.add(list2.get(j).getTagName());
              list4.add(list2.get(j).getId());
          }
      }



        flowLayout.setList(list3);



    }
*/

 /*   @Override
    public void setTagSucess() {

    }

    @Override
    public void showLoading(String content, int code) {
        App.loadingDefault(mActivity);

    }

    @Override
    public void stopLoading(int code) {
        App.hideLoading();

    }

    @Override
    public void showErrorMsg(String msg, int code) {
        App.hideLoading();
        ToastUtil.showToast(msg);

    }*/
}