package com.xiangmu.ui;
/**
 * 引导页一个
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xiangmu.MainActivity;
import com.xiangmu.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private List<View> mList = new ArrayList<>();
    private View view1, view2, view3;
    private TextView point1, point2, point3, guide_text;
    private Button guide_button1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();

    }

    private void initView() {
        point1 = (TextView) findViewById(R.id.point1);
        point2 = (TextView) findViewById(R.id.point2);
        point3 = (TextView) findViewById(R.id.point3);
        guide_text = (TextView) findViewById(R.id.guide_text);
        guide_text.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        view1 = View.inflate(this, R.layout.pager_item_one, null);
        view2 = View.inflate(this, R.layout.pager_item_two, null);
        view3 = View.inflate(this, R.layout.pager_item_three, null);
        mList.add(view1);
        mList.add(view2);
        mList.add(view3);

        guide_button1 = (Button) view3.findViewById(R.id.guide_button1);
        guide_button1.setOnClickListener(this);

        //设置viewpager适配器
        mViewPager.setAdapter(new GuideAdapter());

        //设置指示器的显示
        setPointImg(true, false, false);

        //设置viewpager滑动事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    setPointImg(true, false, false);
                    guide_text.setVisibility(View.VISIBLE);
                }
                if (position == 1) {
                    setPointImg(false, true, false);
                    guide_text.setVisibility(View.VISIBLE);
                }
                if (position == 2) {
                    setPointImg(false, false, true);
                    guide_text.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guide_text:
                zhidong();
                break;
            case R.id.guide_button1:
                zhidong();
                break;
        }
    }


    //判断自动登录
    private void zhidong() {
       startActivity(new Intent(this,MainActivity.class));
       finish();
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //显示那个就添加那个
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mList.get(position));
            return mList.get(position);
        }

        //不显示那个就删除那个
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mList.get(position));
//            super.destroyItem(container, position, object);
        }
    }


    private void setPointImg(boolean poin1, boolean poin2, boolean poin3) {
        if (poin1) {
            point1.setText("1");
        } else {
            point1.setText(" ");
        }
        if (poin2) {
            point2.setText("2");
        } else {
            point2.setText(" ");
        }
        if (poin3) {
            point3.setText("3");
        } else {
            point3.setText(" ");
        }


    }


}
