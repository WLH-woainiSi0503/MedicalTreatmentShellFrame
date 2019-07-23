package com.wd.MyHome.childactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.dingtao.common.bean.MyUser.MyConsultBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.bean.wardBean.WardLieBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyCollectVideoAdapter;
import com.wd.MyHome.presenter.MyCollectBingPresenter;
import com.wd.MyHome.presenter.MyCollectConsultPresenter;
import com.wd.MyHome.presenter.MyCollectVideoPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyUserCollectActivity extends WDActivity {

    @BindView(R2.id.mycollecttop)
    TopView mycollecttop;
    @BindView(R2.id.textrecycler)
    RecyclerView textrecycler;
    @BindView(R2.id.mybt1)
    RadioButton myt1;
    @BindView(R2.id.mybt2)
    RadioButton mybt2;
    @BindView(R2.id.mybt3)
    RadioButton mybt3;
    @BindView(R2.id.mycollectradio)
    RadioGroup mycollectradio;
    @BindView(R2.id.collectgone)
    RelativeLayout collectgone;
    private String uid = null;
    private String sid = null;
    private MyCollectConsultPresenter myCollectConsultPresenter;
    private MyCollectBingPresenter myCollectBingPresenter;
    private MyCollectVideoPresenter myCollectVideoPresenter;
    private int zhuangid;//状态值
    private int page = 1;
    private MyCollectVideoAdapter myCollectVideoAdapter;//视频的适配器

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_collect;
    }

    @Override
    protected void initView() {
        //
        mycollecttop.setTitle("我的收藏");
        //创建p层
        myCollectConsultPresenter = new MyCollectConsultPresenter(new getconsult());
        myCollectBingPresenter = new MyCollectBingPresenter(new getbing());
        myCollectVideoPresenter = new MyCollectVideoPresenter(new getmyvideo());
        //创建适配器
        myCollectVideoAdapter = new MyCollectVideoAdapter(this);

        mycollectradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.mybt1) {
                    myCollectConsultPresenter.reqeust(uid, sid, page, 10);
                } else if (checkedId == R.id.mybt2) {
                    myCollectVideoPresenter.reqeust(uid, sid, page, 10);
                } else if (checkedId == R.id.mybt3) {
                    myCollectBingPresenter.reqeust(uid, sid, page, 10);
                }
            }
        });
    }

    //咨询
    class getconsult implements DataCall<List<MyConsultBean>> {
        @Override
        public void success(List<MyConsultBean> data, Object... args) {
            //创建 适配器//已经请求道接口，但是没有数据
            Log.i("aaa", "咨询: " + data);
            if (data == null) {
                collectgone.setVisibility(View.VISIBLE);
            }else{
                collectgone.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //病友圈
    class getbing implements DataCall<List<WardLieBean>> {
        @Override
        public void success(List<WardLieBean> data, Object... args) {
            //创建适配器
            Log.i("aaa", "病友圈: " + data);
            if (data == null) {
                collectgone.setVisibility(View.VISIBLE);
            }else{
                collectgone.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //我的视频
    class getmyvideo implements DataCall<List<VideoBean>> {
        @Override
        public void success(List<VideoBean> data, Object... args) {
            if (data == null) {
                collectgone.setVisibility(View.VISIBLE);
            }else{
                collectgone.setVisibility(View.GONE);
                //创建适配器
                Log.i("aaa", "视频: " + data);
                myCollectVideoAdapter.clear();
                myCollectVideoAdapter.add(data);
                //把UID，su穿进去
                myCollectVideoAdapter.adduid(uid);
                myCollectVideoAdapter.addsid(sid);
                textrecycler.setAdapter(myCollectVideoAdapter);
                textrecycler.setLayoutManager
                        (new LinearLayoutManager
                                (MyUserCollectActivity.this, LinearLayoutManager.VERTICAL, false));
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MyUserCollectActivity.this);
        myCollectConsultPresenter.reqeust(intt.get(0), intt.get(1), page, 10);
        uid = intt.get(0);
        sid = intt.get(1);
    }

    @Override
    protected void destoryData() {

    }

}