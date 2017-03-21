package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_pict;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/22.
 */
public class class_info_ImageView_TagsEntity implements Serializable {
    private int pictId;
    private ArrayList<db_entity_pict> pictList;
    private int countPictOrder;
    private int countDateOrder;
    private int Date;


    public void setPictId(int id){
        this.pictId=id;
    }

    public void setCountPictOrder(int count){
        this.countPictOrder=count;
    }

    public void setCountDateOrder(int count){
        this.countDateOrder=count;
    }

    public void setPictList(ArrayList<db_entity_pict> pictList){
        this.pictList=pictList;
    }

    public void setDate(int date){
        Date=date;
    }


    public int getPictId(){
        return pictId;
    }
    public int getCountPictOrder() {
        return countPictOrder;
    }
    public int getCountDateOrder() {
        return countDateOrder;
    }
    public ArrayList<db_entity_pict> getPictList(){
        return pictList;
    }
    public int getDate(){
        return Date;
    }
}
