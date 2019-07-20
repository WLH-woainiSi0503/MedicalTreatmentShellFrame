package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/18 9:52
 * @Description：描述信息
 */
public class Cjyp1Presenter extends WDPresenter<IAppRequest> {
    public Cjyp1Presenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.cjyp1how((int) args[0],(int) args[1],(int) args[2]);
    }
}
