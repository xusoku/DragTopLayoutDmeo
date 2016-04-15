package com.example.xusoku.dragtopviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by xusoku on 2016/4/15.
 */
public class MyLinerlayout extends LinearLayout {
    public MyLinerlayout(Context context) {
        super(context);
    }

    public MyLinerlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinerlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int height = 0;
        int width = 0;
        measureChildren(widthMeasureSpec,0);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
                width = lp.width + child.getMeasuredWidth();
                height = lp.height + child.getMeasuredHeight();
            }
        }
        setMeasuredDimension(width, height);
    }
}
