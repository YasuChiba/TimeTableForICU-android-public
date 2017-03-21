package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_entity_sy {
    private int id;
    private String jTitle;
    private String eTitle;
    private String classroom;
    private String teacher;
    private int initialize;
    private int sy_id;
    private String schedule_String;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setJtitle(String value){
        this.jTitle=value;
    }
    public String getJtitle(){
        return jTitle;
    }

    public void setEtitle(String value){
        this.eTitle=value;
    }
    public String getEtitle(){
        return eTitle;
    }

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

    public void setSchedule_string(String val){
        this.schedule_String=val;
    }

    public String getSchedule_String(){
        return schedule_String;
    }

}
