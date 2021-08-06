package com.example.test2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val name = MutableLiveData<String>("")
}