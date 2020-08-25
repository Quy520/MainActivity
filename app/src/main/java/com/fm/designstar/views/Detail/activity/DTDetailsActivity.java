package com.fm.designstar.views.Detail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fm.designstar.R;
import com.fm.designstar.app.App;
import com.fm.designstar.base.BaseActivity;
import com.fm.designstar.events.GetCommetsEvent;
import com.fm.designstar.events.GetLikesEvent;
import com.fm.designstar.events.GetTagsEvent;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.response.CommentsResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.photo.ShowPictureActivity;
import com.fm.designstar.utils.AndroidBug5497Workaround;
import com.fm.designstar.utils.StringUtil;
import com.fm.designstar.utils.TimeUtil;
import com.fm.designstar.utils.ToastUtil;
import com.fm.designstar.utils.Tool;
import com.fm.designstar.utils.Util;
import com.fm.designstar.utils.image.RequestOptionsUtil;
import com.fm.designstar.views.Detail.adapter.ReplyAdapter;
import com.fm.designstar.views.Detail.contract.DelCommentContract;
import com.fm.designstar.views.Detail.contract.GetCommentContract;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.contract.SendCommentContract;
import com.fm.designstar.views.Detail.presenter.DelCommentPresenter;
import com.fm.designstar.views.Detail.presenter.GetCommentPresenter;
import com.fm.designstar.views.Detail.presenter.LikePresenter;
import com.fm.designstar.views.Detail.presenter.SendCommentPresenter;
import com.fm.designstar.views.login.activitys.LoginActivity;
import com.fm.designstar.views.main.adapter.ReviewImageAdapter;
import com.fm.designstar.views.main.adapter.StffReviewGroupAdapter;
import com.fm.designstar.views.mine.activity.InfoDetailActivity;
import com.fm.designstar.widget.CircleImageView;
import com.fm.designstar.widget.CostomGrideView;
import com.fm.designstar.widget.viegroup.MyViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DTDetailsActivity extends BaseActivity<GetCommentPresenter> implements GetCommentContract.View, SendCommentContract.View, DelCommentContract.View , LikeContract.View {
private HomeFindBean findBean;
private int Width;
    private List<String> urlList=new ArrayList<>();
    private List<String> tagList=new ArrayList<>();
    @BindView(R.id.hand)
    CircleImageView hand;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.likenum)
    TextView likenum;

    @BindView(R.id.buttonLay)
    RelativeLayout buttonLay;
    @BindView(R.id.inputLay)
    RelativeLayout inputLay;
    @BindView(R.id.editText)
    EditText editText;

    @BindView(R.id.imgLay)
    FrameLayout imgLay;
    @BindView(R.id.ly_two_img)
    LinearLayout ly_two_img;

    @BindView(R.id.oneImg)
    ImageView oneImg; 
    @BindView(R.id.im)
    ImageView im;

    @BindView(R.id.oneImg2)
    ImageView oneImg2;
    @BindView(R.id.oneImg3)
    ImageView oneImg3;
    @BindView(R.id.gw)
    CostomGrideView gw;
    @BindView(R.id.gw2)
    CostomGrideView gw2;

    @BindView(R.id.myViewGroup)
    MyViewGroup myViewGroup;

    @BindView(R.id.commentRecycler)
    RecyclerView commentRecycler;
    @BindView(R.id.no_reply)
    LinearLayout noReply;

    @BindView(R.id.check_like)
    CheckBox check_like;
    private boolean isselect;


    private ReviewImageAdapter reviewAdapter;
    private RequestOptions rOptions;
    private ReplyAdapter commentAdapter;
private int pagenum=0;
private SendCommentPresenter sendCommentPresenter;
private DelCommentPresenter delCommentPresenter;
private LikePresenter likePresenter;
private int like=0;
private String id;
    @Override
    public int getLayoutId() {
        return R.layout.activity_d_t_details;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
        sendCommentPresenter=new SendCommentPresenter();
        sendCommentPresenter.init(this);
        delCommentPresenter=new DelCommentPresenter();
        delCommentPresenter.init(this);
        likePresenter=new LikePresenter();
        likePresenter.init(this);
    }

    @Override
    public void loadData() {
        AndroidBug5497Workaround.assistActivity(this);

        mTitle.setTitle("详情");
        findBean= (HomeFindBean) getIntent().getSerializableExtra("info");
        Width=(int) (210 * getResources().getDisplayMetrics().density + 0.5f);
        if (findBean==null){
            return;
        }
        mPresenter.GetComment(pagenum,10,findBean.getMomentId()+"");
        rOptions = RequestOptionsUtil.getRoundedOptionsErr(mContext);
        urlList=new ArrayList<>();
        tagList=new ArrayList<>();
        if (!StringUtil.isBlank(findBean.getHeadUri())){
            Glide.with(mContext).load(findBean.getHeadUri()).error(R.mipmap.defu_hand).into(hand);
        }

        name.setText(findBean.getNickName());
        hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=  new Intent(mContext, InfoDetailActivity.class);
                intent.putExtra("UUID",findBean.getUserId()+"");
                mContext.startActivity(intent);
            }
        });
        if (StringUtil.isBlank(findBean.getAddress())){
            im.setVisibility(View.GONE);
            address.setVisibility(View.GONE);
        }else {
            im.setVisibility(View.VISIBLE);
            address.setVisibility(View.VISIBLE);
            address.setText(findBean.getAddress());
        }
        if (findBean.getIsLike()==0){
            check_like.setChecked(false);
        }else {
            check_like.setChecked(true);
        }
        likenum.setText(findBean.getLikes()+"");
        content.setText(findBean.getContent());
        time.setText(TimeUtil.getfriendlyTime(findBean.getCreateTimeStamp()));



        if (findBean.getMomentType()==2){//作品


        }else {//随手拍



        }


        for (int i=0;i<findBean.getTagsList().size();i++){
            tagList.add(findBean.getTagsList().get(i).getTagName());
        }
        if (findBean.getMediaType()==2){//视频
            oneImg.getLayoutParams().height = (int)((Width*(findBean.getMultimediaList().get(0).getHeight()))/findBean.getMultimediaList().get(0).getWidth());
            imgLay.setVisibility(View.VISIBLE);
            oneImg.setVisibility(View.VISIBLE);
            gw.setVisibility(View.GONE);
            Glide.with(mContext).load(findBean.getMultimediaList().get(0).getPreUrl()).apply(rOptions).into(oneImg);
        }else if(findBean.getMediaType()==0) {
            imgLay.setVisibility(View.GONE);

        }else {//图片
            if (findBean.getMultimediaList().size()>0){
                for (int i=0;i<findBean.getMultimediaList().size();i++){
                    urlList.add(findBean.getMultimediaList().get(i).getMultimediaUrl());
                }

                switch (findBean.getMultimediaList().size()) {

                    case 0:
                        imgLay.setVisibility(View.GONE);
                        break;
                    case 1:
                        oneImg.getLayoutParams().height = (int)((Width*(findBean.getMultimediaList().get(0).getHeight()))/findBean.getMultimediaList().get(0).getWidth());
                        imgLay.setVisibility(View.VISIBLE);
                        oneImg.setVisibility(View.VISIBLE);
                        gw.setVisibility(View.GONE);
                        Glide.with(mContext).load(findBean.getMultimediaList().get(0).getMultimediaUrl()).apply(rOptions).into(oneImg);

                        break;
                    case 2:
                        imgLay.setVisibility(View.VISIBLE);
                        oneImg.setVisibility(View.GONE);
                        ly_two_img.setVisibility(View.VISIBLE);
                        gw.setVisibility(View.GONE);
                        oneImg2.getLayoutParams().height = (int)((Width*(findBean.getMultimediaList().get(0).getHeight()))/findBean.getMultimediaList().get(0).getWidth());
                        oneImg3.getLayoutParams().height = (int)((Width*(findBean.getMultimediaList().get(1).getHeight()))/findBean.getMultimediaList().get(1).getWidth());

                        Glide.with(mContext).load(findBean.getMultimediaList().get(0).getMultimediaUrl()).apply(rOptions).into(oneImg2);
                        Glide.with(mContext).load(findBean.getMultimediaList().get(1).getMultimediaUrl()).apply(rOptions).into(oneImg3);
                        break;
                    case 4:
                        imgLay.setVisibility(View.VISIBLE);
                        oneImg.setVisibility(View.GONE);
                        gw.setVisibility(View.GONE);
                        gw2.setVisibility(View.VISIBLE);
                        if (findBean.getMultimediaList() == null) {
                            return;
                        }else {

                        }
                        reviewAdapter = new ReviewImageAdapter(mContext, urlList);
                        gw2.setAdapter(reviewAdapter);
                        reviewAdapter.notifyDataSetChanged(urlList);
                        break;
                    default:
                        imgLay.setVisibility(View.VISIBLE);
                        oneImg.setVisibility(View.GONE);
                        gw.setVisibility(View.VISIBLE);
                        gw2.setVisibility(View.VISIBLE);

                        reviewAdapter = new ReviewImageAdapter(mContext, urlList);
                        gw.setAdapter(reviewAdapter);
                        reviewAdapter.notifyDataSetChanged(urlList);
                        break;
                }
            }

        }


        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowPictureActivity.startAction(mContext,gw, findBean.getMultimediaList(), "", "", 0, position);

            }
        });
        gw2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ShowPictureActivity.startAction(mContext, gw2, findBean.getMultimediaList(), "", "", 0, position);

            }
        });
        oneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowPictureActivity.startAction(mContext, oneImg, findBean.getMultimediaList(), "", "", 0, 0);
            }
        });
        oneImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPictureActivity.startAction(mContext, oneImg2, findBean.getMultimediaList(), "", "", 0, 0);
            }
        });
        oneImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowPictureActivity.startAction(mContext, oneImg3, findBean.getMultimediaList(), "", "", 0, 1);
            }
        });


        StffReviewGroupAdapter reviewGroupAdapter = new StffReviewGroupAdapter(mContext);
        if (urlList.size()>0){
            reviewGroupAdapter.addData(tagList);
        }
        reviewGroupAdapter.setMAX_SHOW(6);

        myViewGroup.setAdapter(reviewGroupAdapter);



        commentRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        commentRecycler.setNestedScrollingEnabled(false);
        commentAdapter = new ReplyAdapter();
        commentRecycler.setAdapter(commentAdapter);
        commentAdapter.setOnClickListener(new ReplyAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                if (Tool.isFastDoubleClick()) {
                    return;
                }
                if (!App.getConfig().getLoginStatus()) {
                    startActivity(LoginActivity.class);
                    return;
                }

            }
        });

        commentAdapter.setOnClickListener(new ReplyAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                id=findBean.getMomentId()+"";
                delCommentPresenter.DelComment(commentAdapter.getData().get(position).getCode()+"",findBean.getMomentId()+"");

            }
        });

        check_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isselect=b;
                if (b){
                    if (compoundButton.isPressed()){
                        like=1;
                        likePresenter.Like(1,findBean.getMomentId());
                    }

                }else {
                    like=0;
                    likePresenter.Like(1,findBean.getMomentId());

                }
            }
        });

    }
    @OnClick({R.id.go_comment,R.id.send,R.id.inputLay})
    public void OnClick(View view) {
        if (Tool.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.go_comment:

                buttonLay.setVisibility(View.GONE);
                inputLay.setVisibility(View.VISIBLE);

                showKeyboard(editText);
                break;

            case R.id.inputLay:
                buttonLay.setVisibility(View.VISIBLE);
                inputLay.setVisibility(View.GONE);
                closeKeyboard();
                break;
            case R.id.send:
                if (!App.getConfig().getLoginStatus()) {
                    startActivity(LoginActivity.class);
                    return;
                }
                if (StringUtil.isBlank(editText.getText().toString())) {
                    ToastUtil.showToast(R.string.reply_empty);
                    return;
                }
                id=findBean.getMomentId()+"";
                sendCommentPresenter.SendComment(editText.getText().toString(),1,findBean.getMomentId()+"");
                buttonLay.setVisibility(View.VISIBLE);
                inputLay.setVisibility(View.GONE);
                closeKeyboard();
                break;
        }
    }
    @Override
    public void GetCommentSuccess(CommentsResponse commentsResponse) {
        if (!StringUtil.isBlank(id)){
            EventBus.getDefault().removeStickyEvent(GetCommetsEvent.class);
            EventBus.getDefault().post(new GetCommetsEvent(id,commentsResponse.getTotal()));
        }
    if (pagenum==0){
        commentAdapter.clearData();
    }

        commentAdapter.addData(commentsResponse.getResult());
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

    }

    @Override
    public void SendCommentSuccess() {
        ToastUtil.showToast("评论成功");
        mPresenter.GetComment(pagenum,10,findBean.getMomentId()+"");


    }

    @Override
    public void DelCommentSuccess() {
        ToastUtil.showToast("评论删除成功");
        mPresenter.GetComment(pagenum,10,findBean.getMomentId()+"");

    }

    @Override
    public void LikeSuccess(LikeResponse likeResponse) {
            likenum.setText(likeResponse.getLikes()+"");


        Log.e("qsd","bbbb"+isselect+"value"+likeResponse.getLikes());
        EventBus.getDefault().removeStickyEvent(GetLikesEvent.class);
        EventBus.getDefault().post(new GetLikesEvent(isselect,likeResponse.getLikes()));

    }
}