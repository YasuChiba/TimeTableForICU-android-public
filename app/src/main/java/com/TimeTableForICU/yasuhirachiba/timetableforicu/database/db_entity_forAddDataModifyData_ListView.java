package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

/**
 * Created by YasuhiraChiba on 16/09/02.
 */
public class db_entity_forAddDataModifyData_ListView {
    private String title;
    private String classroom;
    private String FormattedSchedule="";
    private String schedule;

    private String colorname;


    public void setTitle(String title){
        this.title=title;
    }
    public void setClassroom(String classroom){
        this.classroom=classroom;
    }
    public void setFormattedSchedule(String formattedSchedule){
        this.FormattedSchedule=formattedSchedule;
    }
    public void setSchedule(String schedule){
        this.schedule=schedule;
    }
    public void setColorname(String colorname){
        this.colorname=colorname;
    }



    public String getTitle(){
        return title;
    }
    public String getClassroom(){
        return classroom;
    }
    public String getFormattedSchedule(){
        return FormattedSchedule;
    }
    public String getSchedule(){
        return schedule;
    }
    public String getColorname(){
        return colorname;
    }
}
