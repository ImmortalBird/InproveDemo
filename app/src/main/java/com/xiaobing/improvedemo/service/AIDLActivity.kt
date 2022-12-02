package com.xiaobing.improvedemo.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.joker.common.ITest
import com.xiaobing.improvedemo.base.activity.BaseViewBindingActivity
import com.xiaobing.improvedemo.databinding.ActivityAidlBinding

class AIDLActivity : BaseViewBindingActivity<ActivityAidlBinding>() {
    var proxy: ITest? = null
    override fun initView() {
        super.initView()
        mBinding.header.tvTitle.text = "AIDL"
        val conn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                Log.e(
                    "Binder",
                    "onServiceConnected--name = $name--service = $service"
                )
                proxy = ITest.Stub.asInterface(service)
                Log.e("Binder", "onServiceConnected-- proxy=$proxy")
            }

            override fun onServiceDisconnected(name: ComponentName) {
                Log.e("Binder", "onServiceDisconnected--$name")
            }

            override fun onBindingDied(name: ComponentName?) {
                super.onBindingDied(name)
                Log.e("Binder", "onBindingDied--$name")
            }

            override fun onNullBinding(name: ComponentName?) {
                super.onNullBinding(name)
                Log.e("Binder", "onNullBinding--$name")
            }
        }
        mBinding.bind.setOnClickListener {
            Log.e("Binder", "bindService--")
            bindService(Intent(this, RemoteService::class.java), conn, BIND_AUTO_CREATE)
        }
        mBinding.start.setOnClickListener {
            Log.e("Binder", "startService--")
            startService(Intent(this, RemoteService::class.java))
        }
        mBinding.add.setOnClickListener {
            try {
                Log.e("Binder", "proxy.add--$proxy")
                proxy?.add(1, 2)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        mBinding.send.setOnClickListener {
            try {
                Log.e("Binder", "proxy.send--$proxy")
                proxy?.send(1, 2)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        mBinding.stop.setOnClickListener {
            Log.e("Binder", "stopService--before")
            stopService(Intent(this, RemoteService::class.java))
            Log.e("Binder", "stopService--after")
        }
        mBinding.unBind.setOnClickListener {
            Log.e("Binder", "unbindService--before")
            unbindService(conn)
            Log.e("Binder", "unbindService--after")
        }
    }

}