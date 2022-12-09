package com.xiaobing.improvedemo.span

import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.MaskFilter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.Toast
import com.joker.annotation.MainEnter
import com.xiaobing.improvedemo.R
import com.xiaobing.improvedemo.base.activity.BaseViewBindingActivity
import com.xiaobing.improvedemo.databinding.ActivitySpanMainBinding

/**
 * 主要作用是
 * 展示各种span的显示效果
 * 基础部分已完成
 *
 *
 * TODO: 2019/3/21 需要添加更多的进阶用法
 */
@MainEnter(name = "富文本")
class SpanActivity : BaseViewBindingActivity<ActivitySpanMainBinding>(), View.OnClickListener {
    var content = "预祝党的十九大完美谢慕"
    override fun initView() {
        setTitle(R.string.ID_span_title)
        mBinding.btSpan.setOnClickListener(this)
        mBinding.btSpan1.setOnClickListener(this)
        mBinding.btSpan2.setOnClickListener(this)
        mBinding.btSpan3.setOnClickListener(this)
        mBinding.btSpan4.setOnClickListener(this)
        mBinding.btSpan5.setOnClickListener(this)
        mBinding.btSpan6.setOnClickListener(this)
        mBinding.btSpan7.setOnClickListener(this)
        mBinding.btSpan8.setOnClickListener(this)
        mBinding.btSpan9.setOnClickListener(this)
        mBinding.btSpan10.setOnClickListener(this)
        mBinding.btSpan11.setOnClickListener(this)
        mBinding.btSpan12.setOnClickListener(this)
        mBinding.btSpan13.setOnClickListener(this)
        mBinding.btSpan14.setOnClickListener(this)
        mBinding.btSpan15.setOnClickListener(this)
        mBinding.btSpan16.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val sb = SpannableStringBuilder(content)
        when (view.id) {
            R.id.bt_span -> {
                // 文字颜色
                sb.append("文字颜色 ForegroundColorSpan")
                val foregroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF4040"))
                sb.setSpan(foregroundColorSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span1 -> {
                //背景颜色
                sb.append("背景颜色 BackgroundColorSpan")
                val backgroundColorSpan = BackgroundColorSpan(Color.parseColor("#FF4040"))
                sb.setSpan(backgroundColorSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span2 -> {
                // 可点击
                sb.append("可点击 ClickableSpan")
              val clickableSpan: ClickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Toast.makeText(this@SpanActivity, "可点击", Toast.LENGTH_LONG).show()
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        //去掉可点击文字的下划线
                        @Suppress("DEPRECATION")
                        ds.bgColor = if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M )
                            resources.getColor(R.color.colorAccent,theme)
                        else resources.getColor(R.color.colorAccent)
                        ds.isUnderlineText = false
                    }
                }

                //文本可点击，有点击事件
                sb.setSpan(clickableSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                // 设置此方法后，点击事件才能生效
                mBinding.etContent.movementMethod = LinkMovementMethod.getInstance()
            }
            R.id.bt_span3 -> {
                // 模糊效果
                sb.append("模糊效果 MaskFilterSpan ")
                val filter: MaskFilter = BlurMaskFilter(25.0f, BlurMaskFilter.Blur.NORMAL)
                val maskFilterSpan = MaskFilterSpan(filter)
                sb.setSpan(maskFilterSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span4 -> {
                // 删除线
                sb.append("删除线 StrikethroughSpan ")
                sb.setSpan(StrikethroughSpan(), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span5 -> {
                // 下划线
                sb.append("下划线  UnderlineSpan")
                val underlineSpan = UnderlineSpan()
                sb.setSpan(underlineSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span6 -> {
                // 文字字号绝对大小
                sb.append("绝对大小  AbsoluteSizeSpan")
                val absoluteSizeSpan = AbsoluteSizeSpan(30)
                sb.setSpan(absoluteSizeSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span7 -> {
                // 设置图片 基于底部
                sb.append("设置图片 基于底部   DynamicDrawableSpan")
                val dds: DynamicDrawableSpan = object : DynamicDrawableSpan() {
                    override fun getDrawable(): Drawable {
                        @Suppress("DEPRECATION")
                        val drawable  = if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M )
                            resources.getDrawable(R.mipmap.tutu,theme)
                        else resources.getDrawable(R.mipmap.tutu)
                        drawable.setBounds(0, 0, 50, 50)
                        return drawable
                    }
                }
                sb.setSpan(dds, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span8 -> {
                //                图片
                sb.append("--图片  ImageSpan")
                @Suppress("DEPRECATION")
                val drawable = resources.getDrawable(R.mipmap.tutu)
                // 刚才没加这句话，图片不显示
                drawable.setBounds(0, 0, 100, 100)
                val imageSpan = ImageSpan(drawable)
                sb.setSpan(imageSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span9 -> {
                //                文字字号相对大小
                sb.append(" --- 文字字号相对大小 RelativeSizeSpan")
                val rss = RelativeSizeSpan(1.6f)
                sb.setSpan(rss, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span10 -> {
                //                文字横向缩放
                sb.append(" --- 文字横向缩放  ScaleXSpan")
                val scaleXSpan = ScaleXSpan(1.2f)
                sb.setSpan(scaleXSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span11 -> {
                //                文字粗体斜体等效果
                sb.append(" --- 文字粗体、斜体 效果  StyleSpan")
                val ss1 = StyleSpan(Typeface.BOLD_ITALIC)
                val ss2 = StyleSpan(Typeface.ITALIC)
                val ss3 = StyleSpan(Typeface.BOLD)
                val ss4 = StyleSpan(Typeface.NORMAL)
                sb.setSpan(ss1, 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                sb.setSpan(ss2, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                sb.setSpan(ss3, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                sb.setSpan(ss4, 8, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                mBinding.etContent.highlightColor = Color.parseColor("#36969696")
                // 这种方式设置斜体在 使用 TextView.append(sb) 时无效，在 TextView.setText()时有效
                mBinding.etContent.text = sb
                mBinding.etContent.append("\n")
            }
            R.id.bt_span12 -> {
                //                上标
                sb.append("--- 上标  SuperscriptSpan")
                val ss = SuperscriptSpan()
                sb.setSpan(ss, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span13 -> {
                //                字体、大小、样式和颜色
                sb.append(" --- 字体、大小、样式、颜色 -- TextAppearanceSpan")
                val tas = TextAppearanceSpan(this, android.R.style.TextAppearance_Holo_Large)
                sb.setSpan(tas, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span14 -> {
                //                字体
                sb.append(" --- 字体 TypefaceSpan ")
                //                Typeface typeface = new Typeface();
                val ts = TypefaceSpan("serif")
                sb.setSpan(ts, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            R.id.bt_span15 -> {
                //                超链接
                sb.append(" --- 超链接  UrlSpan")
                val us = URLSpan("http://www.baidu.com")
                sb.setSpan(us, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                mBinding.etContent.movementMethod = LinkMovementMethod.getInstance()
            }
            R.id.bt_span16 -> {
                //                下标
                sb.append(" --- 下标  ")
                val sSpan = SubscriptSpan()
                sb.setSpan(sSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            else -> return
        }
        mBinding.etContent.append(sb)
        mBinding.etContent.append("\n")
        mBinding.etContent.append("\n")
    }
}