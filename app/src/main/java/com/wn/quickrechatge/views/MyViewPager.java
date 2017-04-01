package com.wn.quickrechatge.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ds on 2017/3/28.ds
 */
public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // 决定事件是否中断
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 不拦截事件, 让嵌套的子viewpager有机会响应触摸事件
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 重写ViewPager滑动事件, 改为什么都不做
        return true;

    }

}
