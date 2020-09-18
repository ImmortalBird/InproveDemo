package com.xiaobing.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {


    TextView mSuspensionBar;
    int mSuspensionHeight;
    int mCurrentPosition = 0;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSuspensionBar = findViewById(R.id.tvHead);
        RecyclerView rv = findViewById(R.id.rv);


        linearLayoutManager = (LinearLayoutManager) rv.getLayoutManager();
        rv.setAdapter(new MyAdapter());
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mSuspensionBar.getHeight();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    if (view.getTop() <= mSuspensionHeight) {
                        mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
                    } else {
                        mSuspensionBar.setY(0);
                    }
                }
                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();

                    mSuspensionBar.setY(0);
                    updateSuspensionBar(((TextView)linearLayoutManager.findViewByPosition(mCurrentPosition).findViewById(R.id.tvHead)).getText());
                }
            }
        });
    }

    private void updateSuspensionBar(CharSequence position) {
        mSuspensionBar.setText(position);
    }

}
