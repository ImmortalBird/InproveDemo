package com.xiaobing.common.mvp.base.view;


import com.xiaobing.common.mvp.base.presenter.BasePresenter;

public interface BaseView<P extends BasePresenter, CONTRACT> {


    P getPresenter();

    CONTRACT getContract();

}
