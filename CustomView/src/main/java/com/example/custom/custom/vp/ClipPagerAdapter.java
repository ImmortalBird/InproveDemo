package com.example.custom.custom.vp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.custom.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/29
 */
public class ClipPagerAdapter extends PagerAdapter {

    private Context mContext;

    public ClipPagerAdapter( Context mContext) {

        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView =  new ImageView(mContext);
        imageView.setImageResource(R.drawable.beauty);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
