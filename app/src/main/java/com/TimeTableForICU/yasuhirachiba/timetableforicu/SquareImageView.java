package com.TimeTableForICU.yasuhirachiba.timetableforicu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by YasuhiraChiba on 16/08/26.
 */
public class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 縦横サイズ一緒に
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}