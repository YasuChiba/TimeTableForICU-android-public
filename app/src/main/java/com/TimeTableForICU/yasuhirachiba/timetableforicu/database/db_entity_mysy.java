package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_entity_mysy {
    private int id;
    private String Jtitle;
    private String Etitle;

    private String classroom;
    private String teacher;
    private int msy_id;
    private String schedule_String;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setTitle(String value){
        this.Jtitle=value;
    }
    public String getTitle(){
        return Jtitle;
    }

   /* public void setEtitle(String value){
        this.Etitle=value;
    }
    public String getEtitle(){
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



    public void setSchedule_string(String val){
        this.schedule_String=val;
    }

    public String getSchedule_String(){
        return schedule_String;
    }
}
