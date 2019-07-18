package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/18 9:28
 * 描述：
 */
public class UserRecordPresenter extends WDPresenter<IAppRequest> {

    public UserRecordPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.myuserrecord((String)args [0],(String) args[1]);
    }
}
