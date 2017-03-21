package com.TimeTableForICU.yasuhirachiba.timetableforicu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by YasuhiraChiba on 16/08/27.
 */

public class ViewPagerForSlideShow extends ViewPager {

    public ViewPagerForSlideShow(Context context) {
        super(context);
    }

    public ViewPagerForSlideShow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}