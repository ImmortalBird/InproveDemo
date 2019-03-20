package com.xiaobing.improvedemo.span;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主要作用是
 * 展示各种span的显示效果
 * todo 未完成
 */
public class SpanActivity extends BaseActivity {
    @BindView(R.id.et_content)
    TextInputEditText etContent;
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
        switch (view.getId()) {
            case R.id.bt_span:
                // 文字颜色
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF4040"));
                stringBuilder.setSpan(foregroundColorSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                etContent.append(stringBuilder);
                etContent.append("\n");
                break;
            case R.id.bt_span1:

                stringBuilder = new SpannableStringBuilder(content);
                //背景颜色
                BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.parseColor("#FF4040"));

                stringBuilder.setSpan(backgroundColorSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                etContent.append(stringBuilder);
                etContent.append("\n");
                break;
            case R.id.bt_span2:
                stringBuilder=new SpannableStringBuilder(content);
                // 可点击

                ClickableSpan clickableSpan=new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(SpanActivity.this,"可点击", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        //去掉可点击文字的下划线
                        ds.setUnderlineText(false);
                    }
                };

                //文本可点击，有点击事件
                stringBuilder.setSpan(clickableSpan,0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                // 设置此方法后，点击事件才能生效
                etContent.setMovementMethod(LinkMovementMethod.getInstance());
                etContent.append(stringBuilder);
                etContent.append("\n");
                break;
            case R.id.bt_span3:
                stringBuilder=new SpannableStringBuilder(content);
                // 模糊效果

                MaskFilter filter=new BlurMaskFilter(4.0f, BlurMaskFilter.Blur.OUTER);
                MaskFilterSpan maskFilterSpan=new MaskFilterSpan(filter);

                stringBuilder.setSpan(maskFilterSpan,0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                etContent.append(stringBuilder);
                etContent.append("\n");
                break;
            case R.id.bt_span4:
                stringBuilder=new SpannableStringBuilder(content);
                // 删除线
                stringBuilder.setSpan(new StrikethroughSpan(),0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                etContent.setMovementMethod(LinkMovementMethod.getInstance());
                etContent.append(stringBuilder);
                etContent.append("\n");
                break;
            case R.id.bt_span5:
                // 下划线
                break;
            case R.id.bt_span6:
                // 文字字号绝对大小
                break;
            case R.id.bt_span7:
                //
                break;
            case R.id.bt_span8:
                break;
            case R.id.bt_span9:
                break;
            case R.id.bt_span10:
                break;
            case R.id.bt_span11:
                break;
            case R.id.bt_span12:
                break;
            case R.id.bt_span13:
                break;
            case R.id.bt_span14:
                break;
            case R.id.bt_span15:
                break;
            case R.id.bt_span16:
                break;
        }
    }
}
