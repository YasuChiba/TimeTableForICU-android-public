package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

import java.util.Comparator;

/**
 * Created by YasuhiraChiba on 16/08/27.
 */
public class MemoPictTableComparator implements Comparator<db_entity_MemoPictTable>{
    @Override
    public int compare(db_entity_MemoPictTable lhs, db_entity_MemoPictTable rhs) {

        return lhs.getDate() < rhs.getDate() ? -1 : 1;

    }
}
