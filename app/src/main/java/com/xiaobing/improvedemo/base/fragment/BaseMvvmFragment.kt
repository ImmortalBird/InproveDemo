package com.xiaobing.improvedemo.base.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.xiaobing.improvedemo.main.IDApplication
import java.lang.reflect.ParameterizedType

open class BaseMvvmFragment<Binding : ViewBinding,VM : ViewModel> : BaseFragment<Binding>() {
    @Suppress("UNCHECKED_CAST")
    protected val factory: ViewModelProvider.AndroidViewModelFactory
            by lazy { ViewModelProvider.AndroidViewModelFactory.getInstance(IDApplication.instance) }

    @Suppress("UNCHECKED_CAST")
    var mViewModel: VM? = null
        get() {
            if (field == null) {
                val bindingClass =
                    (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
                field = ViewModelProvider(this, factory)[bindingClass]
            }

            return field
        }

}