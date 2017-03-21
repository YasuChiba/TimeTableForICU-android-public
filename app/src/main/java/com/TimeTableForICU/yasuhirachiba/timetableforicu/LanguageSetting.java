package com.TimeTableForICU.yasuhirachiba.timetableforicu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by YasuhiraChiba on 16/09/01.
 */
public class LanguageSetting {

    public static void setting(Context context,boolean Language) {

        Locale locale;      // アプリで使用されているロケール情報を取得
        if(Language==true){
            locale = Locale.JAPAN;
        }
        else {
            locale = Locale.US;
        }

        Locale.setDefault(locale);            // 新しいロケールを設定
        Configuration config = new Configuration();
        config.locale = locale;             // Resourcesに対するロケールを設定
        Resources resources = context.getResources();
        resources.updateConfiguration(config, null);  // Resourcesに対する新しいロケールを反映

    }
}
