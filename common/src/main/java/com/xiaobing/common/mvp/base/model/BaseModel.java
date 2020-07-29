package com.xiaobing.common.mvp.base.model;

import com.xiaobing.common.mvp.base.presenter.BasePresenter;

public abstract class BaseModel<P extends BasePresenter, CONTRACT> {
    protected P presenter;

    public BaseModel(P presenter) {
        this.presenter = presenter;
    }

    public abstract CONTRACT getContract();
}
