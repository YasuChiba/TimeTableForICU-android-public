package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_entity_MemoPictTable implements Serializable {


    private int date=-1;
    private int MemoId=-1;
    private int PictId=-1;

    ArrayList<db_entity_pict> pictList=new ArrayList<db_entity_pict>();
    ArrayList<db_entity_memo> memoList=new ArrayList<db_entity_memo>();


    public void setMemoId(int MemoId){
        this.MemoId=MemoId;
    }
    public int getMemoId(){
        return MemoId;
    }

    public void setPictId(int PictId){
        this.PictId=PictId;
    }
    public int getPictId(){
        return PictId;
    }

    public void setDate(int date){
        this.date=date;
    }
    public int getDate(){
        return date;
    }

    public void setMemoList(ArrayList<db_entity_memo> memoList){
        this.memoList=memoList;
    }
    public ArrayList<db_entity_memo> getMemoList(){
        return memoList;
    }

    public void setPictList(ArrayList<db_entity_pict> pictList){
        this.pictList=pictList;
    }
    public ArrayList<db_entity_pict> getPictList(){
        return pictList;
    }







}
