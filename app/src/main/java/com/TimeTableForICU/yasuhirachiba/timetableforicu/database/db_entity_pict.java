package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

import java.io.Serializable;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_entity_pict implements Serializable {

    private int Id=-1;
    private String Uri="";
    private String Tag1="";
    private String Tag2="";
    private String Tag3="";
    private String Tag4="";
    private String Tag5="";




    public void setId(int Id){
        this.Id=Id;
    }
    public int getId(){
        return Id;

    }
    public void setUri(String Uri){
        this.Uri=Uri;
    }

    public String getUri(){
        return Uri;
    }

    public void setTag1(String tag1){
        this.Tag1=tag1;
    }
    public String getTag1(){
        return Tag1;
    }

    public void setTag2(String tag2){
        this.Tag2=tag2;
    }
    public String getTag2(){
        return Tag2;
    }
    public void setTag3(String tag3){
        this.Tag3=tag3;
    }
    public String getTag3(){
        return Tag3;
    }
    public void setTag4(String tag4){
        this.Tag4=tag4;
    }
    public String getTag4(){
        return Tag4;
    }
    public void setTag5(String tag5){
        this.Tag5=tag5;
    }
    public String getTag5(){
        return Tag5;
    }




}
