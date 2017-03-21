package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

import android.content.Context;
import android.net.Uri;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_MemoPictTable;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_memo;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_pict;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulator;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulatorForClassInfo;

import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/24.
 */
public class delete_methods {



    public static void delete_DataMemoPict_byDate(int msy_id, int date, db_manipulatorForClassInfo DBmcl,Context context){


        DBmcl.db_cl_deleteDataRecordByDate(msy_id,date);

        ArrayList<db_entity_memo> entity_memoList=DBmcl.db_cl_MemoTable_showBy_Date(msy_id,date);
        for(db_entity_memo memoTmp:entity_memoList){
            DBmcl.db_cl_deletMemoRecordById(msy_id,memoTmp.getId());
        }


        ArrayList<db_entity_pict> entity_pictList=DBmcl.db_cl_PictTable_showBy_Date(msy_id,date);
        for(db_entity_pict pictTmp:entity_pictList){
            try{
                context.getContentResolver().delete(Uri.parse(pictTmp.getUri()),null,null);
                DBmcl.db_cl_deletPictRecordById(msy_id,pictTmp.getId());
            }
            catch (Exception e1){
                DBmcl.db_cl_deletPictRecordById(msy_id,pictTmp.getId());

            }


        }

    }



    public static void delete_Class_byMsyIdClassInfoId(int msy_id, int class_info_id, db_manipulator DBm,db_manipulatorForClassInfo DBmcl,Context context){

        ArrayList<db_entity_MemoPictTable> entity_memoPictTableList=DBmcl.db_cl_MemoPictTable_showByMySyllabusId(msy_id);
        for(db_entity_MemoPictTable MemoPictentity:entity_memoPictTableList){
            delete_methods.delete_DataMemoPict_byDate(msy_id,MemoPictentity.getDate(),DBmcl,context);
        }

        DBmcl.db_cl_dropMemoTable(msy_id);
        DBmcl.db_cl_dropPictTable(msy_id);
        DBmcl.db_cl_dropDataTable(msy_id);


        DBmcl.db_cl_deletebyId(class_info_id);
        DBm.my_sy_deletebyId(msy_id);



    }
}
