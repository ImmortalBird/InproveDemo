package com.example.vaptest

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tencent.qgame.animplayer.AnimConfig
import com.tencent.qgame.animplayer.AnimView
import com.tencent.qgame.animplayer.inter.IAnimListener
import com.tencent.qgame.animplayer.util.ScaleType
import com.tencent.qgame.playerproj.player.FileUtil
import java.io.File

class MainActivity : AppCompatActivity() {

    private val dir by lazy {
        // 存放在sdcard应用缓存文件中
        getExternalFilesDir(null)?.absolutePath ?: Environment.getExternalStorageDirectory().path
    }
    private val uiHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val files = Array(1) {
            "demo2.mp4"
        }
        FileUtil.copyAssetsToStorage(this, dir, files) {
            uiHandler.post {
                init()
            }
        }
//        init()
        findViewById<TextView>(R.id.textView).setOnClickListener {
            init()
        }
    }

    private fun init() {
        // 获取视频播放 AnimView
        var animView = findViewById<AnimView>(R.id.player)
        // 可选: 设置视频对齐方式（默认FIT_XY，支持自定义）
        animView.setScaleType(ScaleType.FIT_XY)
        val file = File("$dir${File.separator}demo2.mp4")
        with(animView) {
            setAnimListener(object : IAnimListener {
                override fun onFailed(errorType: Int, errorMsg: String?) {
                    Log.e("MainActivity", "onFailed $errorType  $errorMsg")
                }

                override fun onVideoComplete() {
                    Log.e("MainActivity", "onVideoComplete")
                }

                override fun onVideoDestroy() {
                    Log.e("MainActivity", "onVideoDestroy")
                }

                override fun onVideoRender(frameIndex: Int, config: AnimConfig?) {
                    Log.e("MainActivity", "onVideoRender   $frameIndex  ")
                }

                override fun onVideoStart() {
                    Log.e("MainActivity", "onVideoStart")
                }
            })
            // 开始播放动画文件
            startPlay(file)
        }
    }
}