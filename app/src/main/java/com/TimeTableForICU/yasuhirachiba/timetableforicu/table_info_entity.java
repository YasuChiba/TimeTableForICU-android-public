package com.TimeTableForICU.yasuhirachiba.timetableforicu;

import java.io.Serializable;

/**
 * Created by YasuhiraChiba on 16/08/29.
 */
public class table_info_entity implements Serializable{

    private String name;
    private String term;
    private int year;
    private int tablenum;



    public void setTableName(String name){
        this.name=name;
    }

    public void setTerm(String term){
        this.term=term;
    }
    public void setYear(int year){
        this.year=year;
    }
    public void setTablenum(int tablenum){
        this.tablenum=tablenum;
    }





    public String getTableName(){
        return name;
    }
    /*public String getTerm(){
        return term;
    }
    public int getYear(){
        return year;
    }*/
    public int getTablenum(){
        return tablenum;
    }

    public String getSyllabusName(){
        return term+String.valueOf(year);
    }


}
