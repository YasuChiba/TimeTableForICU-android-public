package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_entity_memo {


    private int Id=-1;
    private String Memo="";
    private int Date;

    public void setDate(int date){
        Date=date;
    }

    public int getDate(){
        return Date;
    }
    public void setId(int Id){
        this.Id=Id;
    }
    public int getId(){
        return Id;

    }
    public void setMemo(String Memo){
        this.Memo=Memo;
    }

    public String getMemo(){
        return Memo;
    }
}
