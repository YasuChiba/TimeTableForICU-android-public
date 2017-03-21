package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_entity {
    private String id;
    private String Jtitle;
    private String Etitle;
    private String classroom;
    private String teacher;
    private int initialize;
    private int sy_id;
    private int class_info_id;
    private int L4;
    private String color;

    public void setId(String value){
        this.id=value;
    }
    public String getId(){
        return id;
    }

    public void setTitle(String value){
        this.Jtitle=value;
    }
    public String getTitle(){
        return Jtitle;
    }

   /* public void setETitle(String value){
        this.Etitle=value;
    }
    public String getETitle(){
        return Etitle;
    }

*/
    public void setClassroom(String value){
        this.classroom=value;
    }
    public String getClassroom(){
        return classroom;
    }

    public void setTeacher(String value){
        this.teacher=value;
    }
    public String getTeacher(){
        return teacher;
    }

    public void setInitialize(int value){
        this.initialize=value;
    }
    public int getInitialize(){
        return initialize;
    }

    public void setSy_id(int val){
        this.sy_id=val;
    }
    public int getSy_id(){
        return sy_id;
    }
    public void setClass_info_id(int val){
        this.class_info_id=val;
    }
    public int getClass_info_id(){
        return class_info_id;
    }

    public void setL4(int L4){
        this.L4=L4;
    }
    public int getL4(){
        return L4;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return color;
    }

}

