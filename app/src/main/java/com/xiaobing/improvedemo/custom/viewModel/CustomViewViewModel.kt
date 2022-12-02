package com.xiaobing.improvedemo.custom.viewModel

import androidx.lifecycle.ViewModel

class CustomViewViewModel : ViewModel() {


    val list = mutableListOf<FragmentBean>()

}

/**
 *
 * @property type Int
 * @property desc String
 * @constructor
 */
data class FragmentBean(val type: Int, val desc: String)
