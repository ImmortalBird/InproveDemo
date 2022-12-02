package com.xiaobing.improvedemo.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

open class BaseViewBindingActivity<Binding : ViewBinding> : FragmentActivity() {
    /**
     * 默认的ViewBinding
     */
    @Suppress("UNCHECKED_CAST")
    val mBinding: Binding by lazy {
        val bindingClass =
            (this.javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments.getOrNull(0) as Class<*>
        bindingClass.let {
            val inflate = it.getMethod(
                "inflate",
                LayoutInflater::class.java
            )
            inflate.invoke(null, layoutInflater) as Binding
        }
    }

    protected open fun  initView(){}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView()
    }


}