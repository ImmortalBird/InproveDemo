package com.xiaobing.common.mvp.base.presenter;

import com.xiaobing.common.mvp.base.model.BaseModel;
import com.xiaobing.common.mvp.base.view.BaseView;

import java.lang.ref.SoftReference;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel, CONTRACT> {

    /**
     * model
     */
    protected M model;
    /**
     *
     */
    protected SoftReference<V> viewPack;


    /**
     * 为 presenter 绑定View
     *
     * @param view Activity 或者 Fragment
     */
    public void bindView(V view) {
        viewPack = new SoftReference<>(view);
    }

    public void unBindView() {
        if (viewPack != null) {
            viewPack.clear();
            viewPack = null;
            // 我认为此处主动调用 gc 并不是必要操作
//            System.gc();
        }
    }

    /**
     * 获取view
     *
     * @return view
     * @throws Exception 如果没有绑定过view {@link #bindView}，调用此方法会抛出该异常
     */
    public V getView() throws Exception {
        if (viewPack != null) {
            return viewPack.get();
        }
        throw new Exception("请先绑定view");
    }

    public abstract M getModel();

    /**
     * 获取子类对应的契约
     */
    public abstract CONTRACT getContract();
}
