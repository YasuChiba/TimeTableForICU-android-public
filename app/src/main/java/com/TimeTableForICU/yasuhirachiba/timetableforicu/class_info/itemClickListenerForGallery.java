package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

import android.view.View;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_pict;

import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/29.
 */

public interface itemClickListenerForGallery {
    void onClick(View view, ArrayList<db_entity_pict> entity_pictList,int date,int msy_id);
}
