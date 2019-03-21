package com.xiaobing.improvedemo.span;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主要作用是
 * 展示各种span的显示效果
 * 基础部分已完成
 * <p>
 * TODO: 2019/3/21 需要添加更多的进阶用法
 */
public class SpanActivity extends BaseActivity {
    @BindView(R.id.et_content)
    TextView etContent;
    //    @BindView(R.id.til)
//    TextInputLayout til;
    @BindView(R.id.bt_span)
    TextView btSpan;
    @BindView(R.id.bt_span1)
    TextView btSpan1;
    @BindView(R.id.bt_span2)
    TextView btSpan2;
    @BindView(R.id.bt_span3)
    TextView btSpan3;
    @BindView(R.id.bt_span4)
    TextView btSpan4;
    @BindView(R.id.bt_span5)
    TextView btSpan5;
    @BindView(R.id.bt_span6)
    TextView btSpan6;
    @BindView(R.id.bt_span7)
    TextView btSpan7;
    @BindView(R.id.bt_span8)
    TextView btSpan8;
    @BindView(R.id.bt_span9)
    TextView btSpan9;
    @BindView(R.id.bt_span10)
    TextView btSpan10;
    @BindView(R.id.bt_span11)
    TextView btSpan11;
    @BindView(R.id.bt_span12)
    TextView btSpan12;
    @BindView(R.id.bt_span13)
    TextView btSpan13;
    @BindView(R.id.bt_span14)
    TextView btSpan14;
    @BindView(R.id.bt_span15)
    TextView btSpan15;
    @BindView(R.id.bt_span16)
    TextView btSpan16;
    String content = "预祝党的十九大完美谢慕";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_span_main);

        super.onCreate(savedInstanceState);
        setTitle(R.string.ID_span_title);
    }

    @OnClick({R.id.bt_span, R.id.bt_span1, R.id.bt_span2, R.id.bt_span3, R.id.bt_span4, R.id.bt_span5, R.id.bt_span6, R.id.bt_span7, R.id.bt_span8, R.id.bt_span9, R.id.bt_span10, R.id.bt_span11, R.id.bt_span12, R.id.bt_span13, R.id.bt_span14, R.id.bt_span15, R.id.bt_span16})
    public void onViewClicked(View view) {
        SpannableStringBuilder sb = new SpannableStringBuilder(content);
        switch (view.getId()) {
            case R.id.bt_span:
                // 文字颜色
                sb.append("文字颜色 ForegroundColorSpan");
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF4040"));
                sb.setSpan(foregroundColorSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            case R.id.bt_span1:
                //背景颜色
                sb.append("背景颜色 BackgroundColorSpan");
                BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#FF4040"));
                sb.setSpan(backgroundColorSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            case R.id.bt_span2:
                // 可点击
                sb.append("可点击 ClickableSpan");
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(SpanActivity.this, "可点击", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        //去掉可点击文字的下划线
                        ds.bgColor = getResources().getColor(R.color.colorAccent);
                        ds.setUnderlineText(false);
                    }
                };

                //文本可点击，有点击事件
                sb.setSpan(clickableSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                // 设置此方法后，点击事件才能生效
                etContent.setMovementMethod(LinkMovementMethod.getInstance());

                break;
            case R.id.bt_span3:
                // 模糊效果
                sb.append("模糊效果 MaskFilterSpan ");
                MaskFilter filter = new BlurMaskFilter(25.0f, BlurMaskFilter.Blur.NORMAL);
                MaskFilterSpan maskFilterSpan = new MaskFilterSpan(filter);
                sb.setSpan(maskFilterSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            case R.id.bt_span4:
                // 删除线
                sb.append("删除线 StrikethroughSpan ");
                sb.setSpan(new StrikethroughSpan(), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            case R.id.bt_span5:
                // 下划线
                sb.append("下划线  UnderlineSpan");
                UnderlineSpan underlineSpan = new UnderlineSpan();
                sb.setSpan(underlineSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            case R.id.bt_span6:
                // 文字字号绝对大小
                sb.append("绝对大小  AbsoluteSizeSpan");
                AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(30);
                sb.setSpan(absoluteSizeSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            case R.id.bt_span7:
                // 设置图片 基于底部
                sb.append("设置图片 基于底部   DynamicDrawableSpan");
                DynamicDrawableSpan dds = new DynamicDrawableSpan() {
                    @Override
                    public Drawable getDrawable() {
                        Drawable drawable = getResources().getDrawable(R.mipmap.tutu);
                        drawable.setBounds(0, 0, 50, 50);
                        return drawable;
                    }
                };
                sb.setSpan(dds, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


                break;
            case R.id.bt_span8:
//                图片
                sb.append("--图片  ImageSpan");
                Drawable drawable = getResources().getDrawable(R.mipmap.tutu);
                // 刚才没加这句话，图片不显示
                drawable.setBounds(0, 0, 100, 100);
                ImageSpan imageSpan = new ImageSpan(drawable);
                sb.setSpan(imageSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


                break;
            case R.id.bt_span9:
//                文字字号相对大小
                sb.append(" --- 文字字号相对大小 RelativeSizeSpan");
                RelativeSizeSpan rss = new RelativeSizeSpan(1.6f);
                sb.setSpan(rss, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.bt_span10:
//                文字横向缩放
                sb.append(" --- 文字横向缩放  ScaleXSpan");
                ScaleXSpan scaleXSpan = new ScaleXSpan(1.2F);
                sb.setSpan(scaleXSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.bt_span11:
//                文字粗体斜体等效果
                sb.append(" --- 文字粗体、斜体 效果  StyleSpan");
                StyleSpan ss1 = new StyleSpan(Typeface.BOLD_ITALIC);
                StyleSpan ss2 = new StyleSpan(Typeface.ITALIC);
                StyleSpan ss3 = new StyleSpan(Typeface.BOLD);
                StyleSpan ss4 = new StyleSpan(Typeface.NORMAL);

                sb.setSpan(ss1, 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                sb.setSpan(ss2, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                sb.setSpan(ss3, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                sb.setSpan(ss4, 8, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                etContent.setHighlightColor(Color.parseColor("#36969696"));
                // 这种方式设置斜体在 使用 TextView.append(sb) 时无效，在 TextView.setText()时有效
                etContent.setText(sb);
                etContent.append("\n");
                break;
            case R.id.bt_span12:
//                上标
                sb.append("--- 上标  SuperscriptSpan");

                SuperscriptSpan ss = new SuperscriptSpan();
                sb.setSpan(ss, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.bt_span13:
//                字体、大小、样式和颜色
                sb.append(" --- 字体、大小、样式、颜色 -- TextAppearanceSpan");
                TextAppearanceSpan tas = new TextAppearanceSpan(this, android.R.style.TextAppearance_Holo_Large);
                sb.setSpan(tas, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.bt_span14:
//                字体
                sb.append(" --- 字体 TypefaceSpan ");
//                Typeface typeface = new Typeface();
                TypefaceSpan ts = new TypefaceSpan("serif");
                sb.setSpan(ts, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            case R.id.bt_span15:
//                超链接
                sb.append(" --- 超链接  UrlSpan");
                URLSpan us = new URLSpan("http://www.baidu.com");
                sb.setSpan(us, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                etContent.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            case R.id.bt_span16:
//                下标
                sb.append(" --- 下标  ");
                SubscriptSpan sSpan = new SubscriptSpan();
                sb.setSpan(sSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                break;
            default:
                return;
        }
        etContent.append(sb);
        etContent.append("\n");
        etContent.append("\n");
    }
}
