package com.example.vaptest

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tencent.qgame.animplayer.AnimView
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
            "demo.mp4"
        }
        FileUtil.copyAssetsToStorage(this, dir, files) {
            uiHandler.post {
                init()
            }
        }
        init()
        findViewById<TextView>(R.id.textView).setOnClickListener {
            init()
        }
    }

    private fun init() {
        // 获取视频播放 AnimView
        var animView = findViewById<AnimView>(R.id.player)
        // 可选: 设置视频对齐方式（默认FIT_XY，支持自定义）
        animView.setScaleType(ScaleType.FIT_XY)
        val file = File("$dir${File.separator}demo.mp4")
        // 开始播放动画文件
        animView.startPlay(file)
    }
}