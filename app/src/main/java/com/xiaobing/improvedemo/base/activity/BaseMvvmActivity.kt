package com.xiaobing.improvedemo.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.xiaobing.improvedemo.main.IDApplication
import java.lang.reflect.ParameterizedType

open class BaseMvvmActivity<VM : ViewModel, Binding : ViewBinding>: BaseActivity(){
    @Suppress("UNCHECKED_CAST")
    protected val factory: ViewModelProvider.AndroidViewModelFactory
            by lazy { ViewModelProvider.AndroidViewModelFactory.getInstance(IDApplication.instance) }

    @Suppress("UNCHECKED_CAST")
    var mViewModel: VM? = null
        get() {
            if (field == null) {
                val bindingClass =
                    (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
                field = ViewModelProvider(this, factory)[bindingClass]
            }

            return field
        }
    /**
     * 默认的ViewBinding
     */
    @Suppress("UNCHECKED_CAST")
    val mBinding: Binding by lazy {
        val bindingClass =
            (this.javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments.getOrNull(1) as Class<Binding>
        bindingClass.let {
            val inflate = it.getMethod(
                "inflate",
                LayoutInflater::class.java
            )
            inflate.invoke(null, layoutInflater) as Binding
        }
    }

    override fun onCreate() {
        setContentView(mBinding.root)
    }

    override fun initView() {
    }

    override fun setLayoutId(): Int {
        return 0;
    }
}