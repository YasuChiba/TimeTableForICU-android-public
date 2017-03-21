package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_manipulator {
    private static String TABLE_NAME ="table1";
    private static final String SYCOLUMN_ID="id";
    private static final String SYCOLUMN_JTITLE="Jtitle";
    private static final String SYCOLUMN_ETITLE="Etitle";

    private static final String SYCOLUMN_CLASSROOM="classroom";
    private static final String SYCOLUMN_TEACHER="teacher";
    private static final String SYCOLUMN_INITIALIZE="initialize";
    private static final String SYCOLUMN_SY_ID="sy_id";
    private static final String SYCOLUMN_CLASS_INFO_ID="class_info_id";
    private static final String SYCOLUMN_L4="L4";
    private static final String SYCOLUMN_COLOR="color";

    private static String SYLLABUS_NAME;


    private static final String CLTABLE_NAME="class_info";
    private static final String CLCOLUMN_ID="_id";
    private static final String CLCOLUMN_SY_ID="sy_id";

    private static final String[] CLCOLUMNS={CLCOLUMN_ID,CLCOLUMN_SY_ID};
    SQLiteDatabase db;


    public db_manipulator(SQLiteDatabase db,String tableName,String syllabusName){
        this.db=db;
        SYLLABUS_NAME=syllabusName;
        TABLE_NAME="x"+tableName;
    }

    /*public db_manipulator(SQLiteDatabase db){
        this.db=db;

        SYLLABUS_NAME="syllabus";
        TABLE_NAME="table1";
    }*/



    public void db_createTable(String tableName){
        tableName="x"+tableName;

        String query_table = "create table "+tableName+" ('_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,'id' TEXT,'Jtitle' TEXT,'Etitle' TEXT,'classroom' TEXT, 'teacher' TEXT,'initialize' INTEGER, 'L4' INTEGER,'sy_id' INTEGER, 'class_info_id' INTEGER,'color' TEXT,'textColor' TEXT )";
        db.execSQL(query_table);


        String query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('11','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('12','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('13','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('14','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('15','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('16','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('21','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('22','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('23','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('24','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('25','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('26','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('31','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('32','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('33','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('34','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('35','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('36','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('41','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('42','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('43','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('44','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('45','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('46','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('51','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('52','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('53','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('54','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('55','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('56','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('61','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('62','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('63','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('64','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('65','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('66','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('71','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('72','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('73','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('74','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('75','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('76','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('81','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('82','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('83','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('84','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('85','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
                query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('86','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);

        query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('91','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
        query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('92','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
        query= "insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('93','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
        query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('94','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
        query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('95','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);
        query="insert into "+tableName+"('id','Jtitle','Etitle','classroom','teacher','initialize','L4','sy_id','color','textColor') values('96','sample','sample','sample','sample','0','0','-1','#ffffff','#000000');";
        db.execSQL(query);


    }

    public int db_table_CheckRecordExistence(String name){

        name="x"+name;

        String query = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='"+name+"';";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String result = c.getString(0);
        c.close();

        return Integer.valueOf(result);
    }



    public void db_table1_update(String id,String Jtitle,String classroom,String teacher,int initialize,int sy_id,int class_info_id,int L4,String color){
        ContentValues values=new ContentValues();
        String[] selection ={id};

        values.put(SYCOLUMN_JTITLE,Jtitle);
     //   values.put(SYCOLUMN_ETITLE,Etitle);
        values.put(SYCOLUMN_CLASSROOM,classroom);
        values.put(SYCOLUMN_TEACHER,teacher);
        values.put(SYCOLUMN_INITIALIZE,initialize);
        values.put(SYCOLUMN_SY_ID,sy_id);
        values.put(SYCOLUMN_CLASS_INFO_ID,class_info_id);
        values.put(SYCOLUMN_L4,L4);
        values.put(SYCOLUMN_COLOR,color);
        db.update(TABLE_NAME,values,"id=?",selection);


    }

    public db_entity db_table1_showbyId(String id){
        String[] selection ={id};
        String rawq="SELECT * FROM "+TABLE_NAME+" WHERE id= ?;";
        Cursor c = db.rawQuery(rawq,selection);

        c.moveToFirst();
        db_entity entity=new db_entity();
        entity.setId(c.getString(1));
        entity.setTitle(c.getString(2));
     //   entity.setETitle(c.getString(3));

        entity.setClassroom(c.getString(4));
        entity.setTeacher(c.getString(5));
        entity.setInitialize(c.getInt(6));
        entity.setL4(c.getInt(7));
        entity.setSy_id(c.getInt(8));
        entity.setClass_info_id((c.getInt(9)));
        entity.setColor(c.getString(10));

        c.close();

        return entity;
    }



    public ArrayList<db_entity_forAddDataModifyData_ListView> db_table_showByMsy_Id(int msy_id){
        String[] selection ={String.valueOf(msy_id)};
        String rawq="SELECT * FROM "+TABLE_NAME+" WHERE sy_id= ?;";
        Cursor c = db.rawQuery(rawq,selection);

        int data_num=c.getCount();
        c.moveToFirst();

        ArrayList<db_entity_forAddDataModifyData_ListView> entityList=new ArrayList<>();
        if(data_num!=0) {
            do {
                db_entity_forAddDataModifyData_ListView entity_tmp=new db_entity_forAddDataModifyData_ListView();
                entity_tmp.setTitle(c.getString(2));
                entity_tmp.setClassroom(c.getString(4));
                entity_tmp.setSchedule(c.getString(1));
                entity_tmp.setColorname(c.getString(10));


                entityList.add(entity_tmp);
            }
            while (c.moveToNext());

        }

        c.close();



        return entityList;

    }


    public void db_table1_initialize(String id){
        ContentValues values=new ContentValues();
        String[] selection ={id};

        values.put(SYCOLUMN_JTITLE,"");
    //    values.put(SYCOLUMN_ETITLE,"");
        values.put(SYCOLUMN_CLASSROOM,"");
        values.put(SYCOLUMN_TEACHER,"");
        values.put(SYCOLUMN_INITIALIZE,0);
        values.put(SYCOLUMN_SY_ID,-1);
        values.put(SYCOLUMN_CLASS_INFO_ID,-1);
        values.put(SYCOLUMN_L4,0);
        values.put(SYCOLUMN_COLOR,"#ffffff");

        db.update(TABLE_NAME,values,"id=?",selection);


    }

    public void db_dropTable(){

        String sql = "drop table "+TABLE_NAME+";";
        db.execSQL(sql);

    }

    //---------------------------------------------------------------------------------------





    //--------------------------------------------------------------------------------------------------



    public ArrayList<db_entity_sy> sy_showByPeriod(String id){

        //if(id=="81"){id="41";} if(id=="82"){id="42";} if(id=="83"){id="43";} if(id=="84"){id="44";} if(id=="85"){id="45";} if(id=="86"){id="46";}
        String[] selection ={id};

        String sql = "select * from "+SYLLABUS_NAME+" where "+id+"=1;";


        Cursor c = db.rawQuery(sql,null);
        int data_num=c.getCount();

        boolean a=c.moveToFirst();
        ArrayList<db_entity_sy> entity = new ArrayList<db_entity_sy>();


        if(data_num!=0) {
            int i = 0;
            do {
                db_entity_sy entity_tmp = new db_entity_sy();
                entity_tmp.setId(c.getInt(0));
                entity_tmp.setJtitle(c.getString(6));
                entity_tmp.setEtitle(c.getString(5));
                entity_tmp.setClassroom(c.getString(8));
                entity_tmp.setTeacher(c.getString(9));
                entity_tmp.setSchedule_string(c.getString(11));
                entity.add(entity_tmp);
                i++;
            }
            while (c.moveToNext());
        }
        else{
            db_entity_sy entity_tmp = new db_entity_sy();
            entity_tmp.setJtitle("該当なし");
            entity_tmp.setEtitle("not found");
            entity.add(entity_tmp);
        }

        c.close();

        return entity;
    }
    //2つのidを検索してどっちもとってきたいとき（主にろんふぉー）
    public ArrayList<db_entity_sy> sy_showByPeriod(String id1,String id2){

        //if(id=="81"){id="41";} if(id=="82"){id="42";} if(id=="83"){id="43";} if(id=="84"){id="44";} if(id=="85"){id="45";} if(id=="86"){id="46";}
        String[] selection1 ={id1};
        String[] selection2={id2};
        String sql1 = "select * from "+SYLLABUS_NAME+" where "+id1+"=1;";
        String sql2 = "select * from "+SYLLABUS_NAME+" where "+id2+"=1;";

        Cursor c = db.rawQuery(sql1,null);
        int data_num=c.getCount();

        boolean a=c.moveToFirst();
        ArrayList<db_entity_sy> entity = new ArrayList<db_entity_sy>();


        if(data_num!=0) {
            int i = 0;
            do {
                db_entity_sy entity_tmp = new db_entity_sy();
                entity_tmp.setId(c.getInt(0));
                entity_tmp.setJtitle(c.getString(6));
                entity_tmp.setEtitle(c.getString(5));
                entity_tmp.setClassroom(c.getString(8));
                entity_tmp.setTeacher(c.getString(9));
                entity_tmp.setSchedule_string(c.getString(11));
                entity.add(entity_tmp);
                i++;
            }
            while (c.moveToNext());
        }



        c = db.rawQuery(sql2,null);
        data_num=c.getCount();
        c.moveToFirst();
        if(data_num!=0) {
            int i = 0;
            do {
                db_entity_sy entity_tmp = new db_entity_sy();
                entity_tmp.setId(c.getInt(0));
                entity_tmp.setJtitle(c.getString(6));
                entity_tmp.setEtitle(c.getString(5));
                entity_tmp.setClassroom(c.getString(8));
                entity_tmp.setTeacher(c.getString(9));
                entity_tmp.setSchedule_string(c.getString(11));
                entity.add(entity_tmp);
                i++;
            }
            while (c.moveToNext());
        }
        else{
            db_entity_sy entity_tmp = new db_entity_sy();
            entity_tmp.setJtitle("該当なし");
            entity_tmp.setEtitle("not found");
            entity.add(entity_tmp);
        }


        c.close();
        return entity;
    }




    //--------------------------------------------------------------------------


    private final static String MYSY_JTITLE="Jtitle";
    private final static String MYSY_ETITLE="Etitle";
    private final static String MYSY_ROOM="room";
    private final static String MYSY_INSTRUCTOR="instructor";
    private final static String MYSY_SCHEDULE_STRING="schedule_string";

    private final static String MYSY_TABLE_NAME="my_syllabus";


    public db_entity_mysy my_sy_showById(String id){

        String[] selection ={id};

        Cursor c = db.rawQuery("SELECT * FROM my_syllabus WHERE id= ?;",selection);


        int data_num=c.getCount();

        boolean a=c.moveToFirst();
        db_entity_mysy entity=new db_entity_mysy();

        if(data_num!=0) {


            entity.setId(c.getInt(0));
            entity.setTitle(c.getString(1));
        //    entity.setEtitle(c.getString(2));

            entity.setClassroom(c.getString(4));
            entity.setTeacher(c.getString(5));
            entity.setSchedule_string(c.getString(6));




        }
        else{

            entity.setTitle("not found");
           // entity.setEtitle("not found");

        }
        c.close();


        return entity;
    }


    public int my_sy_insertMySyllabus(String Jtitle,String room,String instructor,String schedule_string,ArrayList<Integer> schedule_int_list){
        int msy_id=-1;


        ContentValues values = new ContentValues();

        values.put(MYSY_JTITLE, Jtitle);
      //  values.put(MYSY_ETITLE,Etitle);
        values.put(MYSY_ROOM,room);
        values.put(MYSY_INSTRUCTOR,instructor);
        values.put(MYSY_SCHEDULE_STRING,schedule_string);

        for(int i=0;i<schedule_int_list.size();i++){
            String tmp="s"+schedule_int_list.get(i);
            values.put(tmp,1);
        }



        msy_id= (int) db.insert(MYSY_TABLE_NAME, null, values);


        return msy_id;
    }



    public void my_sy_updateMySyllabus(int msy_id,String Jtitle,String room,String instructor,String schedule_string,ArrayList<Integer> schedule_int_list){

        ContentValues values = new ContentValues();
        String[] where={String.valueOf(msy_id)};

        values.put(MYSY_JTITLE, Jtitle);
    //    values.put(MYSY_ETITLE,Etitle);
        values.put(MYSY_ROOM,room);
        values.put(MYSY_INSTRUCTOR,instructor);
        values.put(MYSY_SCHEDULE_STRING,schedule_string);


        for(int i=0;i<schedule_int_list.size();i++){
            String tmp="s"+schedule_int_list.get(i);
            values.put(tmp,1);
        }
        db.update(MYSY_TABLE_NAME, values, "id=?",where);


    }


    public void my_sy_deletebyId(int msy_id){
        String[] selection={String.valueOf(msy_id)};


        db.delete(MYSY_TABLE_NAME,"id=?",selection);


    }




}
