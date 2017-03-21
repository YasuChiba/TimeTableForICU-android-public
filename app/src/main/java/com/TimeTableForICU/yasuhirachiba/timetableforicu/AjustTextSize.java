package com.TimeTableForICU.yasuhirachiba.timetableforicu;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by YasuhiraChiba on 16/08/31.
 */
public class AjustTextSize {




    public static float textSizeForString(TextView myTextView, String s) {
        int MIN_SIZE=1;
        float originalTextSize = 20;
        myTextView.setTextSize(originalTextSize);   // 20をセットしたのに
        float textSize = myTextView.getTextSize();  // これが 30になる
        double ratio = (double) (textSize/originalTextSize);

        float tvWidth = myTextView.getWidth();
        float textWidth;
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        while (true) {
            textPaint.setTextSize(textSize);
            textWidth = textPaint.measureText(s);
            if (textWidth < tvWidth || textSize == MIN_SIZE) {
                break;
            }
            textSize -= 1;
        }

        return (float) (textSize / ratio);
    }

}
