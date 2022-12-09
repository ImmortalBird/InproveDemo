package com.xiaobing.improvedemo.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


@Suppress("OverrideDeprecatedMigration")
open class BaseFragment<Binding : ViewBinding> : Fragment() {
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


    val logTag = this.javaClass.simpleName
    override fun onAttach(context: Context) {
        Log.v(logTag, "onAttach   fragment before super")
        super.onAttach(context)
        Log.v(logTag, "onAttach  fragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(logTag, "onCreate   fragment before super")
        super.onCreate(savedInstanceState)
        Log.v(logTag, "onCreate  fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v(logTag, "onCreateView   fragment before super")
        return mBinding.root
//        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v(logTag, "onViewCreated   fragment before super")
        super.onViewCreated(view, savedInstanceState)
        Log.v(logTag, "onViewCreated  fragment")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.v(logTag, "onActivityCreated  fragment before super")
        super.onActivityCreated(savedInstanceState)
        Log.v(logTag, "onActivityCreated  fragment")
    }

    override fun onStart() {
        Log.v(logTag, "start  fragment before super")
        super.onStart()
        Log.v(logTag, "start  fragment")
    }

    override fun onResume() {
        Log.v(logTag, "onResume  fragment before super")
        super.onResume()
        Log.v(logTag, "onResume  fragment")
    }


    override fun onPause() {
        Log.v(logTag, "onPause  fragment before super")
        super.onPause()
        Log.v(logTag, "onPause  fragment")
    }

    override fun onStop() {
        Log.v(logTag, "onStop  fragment before super")
        super.onStop()
        Log.v(logTag, "onStop  fragment")
    }


    override fun onDestroyView() {
        Log.v(logTag, "onDestroyView  fragment before super")
        super.onDestroyView()
        Log.v(logTag, "onDestroyView  fragment")
    }

    override fun onDestroy() {
        Log.v(logTag, "onDestroy  fragment before super")
        super.onDestroy()
        Log.v(logTag, "onDestroy  fragment")
    }


    override fun onDetach() {
        Log.v(logTag, "onDetach  fragment before super")
        super.onDetach()
        Log.v(logTag, "onDetach  fragment")
    }

}