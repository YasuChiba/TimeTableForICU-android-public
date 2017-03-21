package com.TimeTableForICU.yasuhirachiba.timetableforicu.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class db_manipulatorForClassInfo {

    private static final String CLTABLE_NAME="class_info";
    private static final String CLCOLUMN_ID="_id";
    private static final String CLCOLUMN_SY_ID="sy_id";

    private static final String[] CLCOLUMNS={CLCOLUMN_ID,CLCOLUMN_SY_ID};
    SQLiteDatabase db;

    public db_manipulatorForClassInfo(SQLiteDatabase db){
        this.db=db;
    }


    public void db_cl_update(int id,int sy_id){
        ContentValues values=new ContentValues();
        String[] selection={String.valueOf(id)};

        values.put(CLCOLUMN_SY_ID,sy_id);

        db.update(CLTABLE_NAME,values,"_id=?",selection);


    }


    public int db_cl_insert(int sy_id){

        //  String query="insert into "+TABLE_NAME+"('"+COLUMN_SY_ID+"') values('"+sy_id+"');";

        ContentValues values = new ContentValues();
        values.put("sy_id", sy_id);


        int id= (int) db.insert(CLTABLE_NAME, null, values);



        return id;
    }

    public int db_cl_showbyId(int id){

        String[] selection ={String.valueOf(id)};

        String query="SELECT * FROM "+CLTABLE_NAME+" WHERE _id=?;";

        Cursor c = db.rawQuery(query,selection);

        c.moveToFirst();

        int sy_id=c.getInt(1);

        c.close();

        return sy_id;
    }

    public void db_cl_deletebyId(int id){
        String[] selection={String.valueOf(id)};

        db.delete(CLTABLE_NAME,"_id=?",selection);

    }







































    public void db_cl_createDataTable(int msy_id){

        String name="DT"+msy_id;



        String query_table = "CREATE TABLE "+name+ " (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Date INTEGER ) ";
        db.execSQL(query_table);

    }



    public void db_cl_createPictTable(int msy_id){

        String name="PT"+msy_id;    //msy_idに関連付けた名前にする

        String query_table = "CREATE TABLE "+name+ " ( _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Date INTEGER,Uri TEXT,Tag1 TEXT,Tag2 TEXT,Tag3 TEXT,Tag4 TEXT,Tag5 TEXT ) ";

        db.execSQL(query_table);

    }

    public void db_cl_createMemoTable(int msy_id){

        String name="MT"+msy_id;      //msy_idに関連付けた名前にする

        String query_table = "CREATE TABLE "+name+ " (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Date INTEGER,Memo TEXT ) ";


        db.execSQL(query_table);
    }

    public void db_cl_dropPictTable(int msy_id){

        String name="PT"+msy_id;

        String sql = "drop table "+name+";";
        db.execSQL(sql);
    }

    public void db_cl_dropMemoTable(int msy_id){

        String name="MT"+msy_id;

        String sql = "drop table "+name+";";
        db.execSQL(sql);
    }

    public void db_cl_dropDataTable(int msy_id){
        String name="DT"+msy_id;

        String sql = "drop table "+name+";";
        db.execSQL(sql);
    }


    public void db_cl_deletPictRecordById(int msy_id,int id){

        String name="PT"+msy_id;
        String[] selection ={String.valueOf(id)};

        db.delete(name, "_id = ?", selection);
    }


    public void db_cl_deletMemoRecordById(int msy_id,int id){
        String name="MT"+msy_id;
        String[] selection={String.valueOf(id)};

        db.delete(name,"_id=?",selection);

    }



    public void db_cl_deletPictRecordByDate(int msy_id,int date){

        String name="PT"+msy_id;
        String[] selection ={String.valueOf(date)};

        db.delete(name, "Date= ?", selection);
    }


    public void db_cl_deletMemoRecordByDate(int msy_id,int date){
        String name="MT"+msy_id;
        String[] selection={String.valueOf(date)};

        db.delete(name,"Date=?",selection);

    }

    public void db_cl_deleteDataRecordByDate(int msy_id,int date){

        String name="DT"+msy_id;

        String[] selection={String.valueOf(date)};

        db.delete(name,"Date=?",selection);
    }







    public int db_cl_insertPictTable(int msy_id,int Date,String Uri,String tag1,String tag2,String tag3,String tag4,String tag5){
        ContentValues values=new ContentValues();

        String tableName="PT"+msy_id;

        values.put("Date",Date);
        values.put("Uri",Uri);
        values.put("Tag1",tag1);
        values.put("Tag2",tag2);
        values.put("Tag3",tag3);
        values.put("Tag4",tag4);
        values.put("Tag5",tag5);

        int id= (int) db.insert(tableName, null, values);


        return id;


    }
    public int db_cl_insertMemoTable(int msy_id,int Date,String Memo){
        String tableName="MT"+msy_id;

        ContentValues values=new ContentValues();

        values.put("Date",Date);
        values.put("Memo",Memo);

        int id= (int) db.insert(tableName, null, values);

        return id;

    }

    public int db_cl_insertDataTable(int msy_id,int Date){
        String tableName="DT"+msy_id;

        ContentValues values=new ContentValues();

        values.put("Date",Date);

        int id= (int) db.insert(tableName, null, values);

        return id;

    }















    public void db_cl_updatePictTable(int msy_id,int Date,int id,String Uri,String tag1,String tag2,String tag3,String tag4,String tag5){

        String[] where={String.valueOf(id)};
        String tableName="PT"+msy_id;

        ContentValues values=new ContentValues();



        values.put("Date",Date);
        values.put("Uri",Uri);
        values.put("Tag1",tag1);
        values.put("Tag2",tag2);
        values.put("Tag3",tag3);
        values.put("Tag4",tag4);
        values.put("Tag5",tag5);

        db.update(tableName, values, "_id=?",where);

    }


    public void db_cl_updateMemoTable(int msy_id,int id,int Date,String Memo){
        String tableName="MT"+msy_id;
        String[] where={String.valueOf(id)};

        ContentValues values=new ContentValues();

        values.put("Date",Date);
        values.put("Memo",Memo);

        db.update(tableName, values, "_id=?",where);


    }


    public db_entity_memo db_cl_getMemoById(int msy_id,int id){
        String MemoTableName="MT"+msy_id;
        String[] selection ={String.valueOf(id)};

        Cursor cMemo = db.rawQuery("SELECT * FROM "+MemoTableName+" WHERE _id= ?;",selection);
        int data_num_memo=cMemo.getCount();
        cMemo.moveToFirst();


        db_entity_memo entity_memo=new db_entity_memo();
        entity_memo.setId(cMemo.getInt(0));
        entity_memo.setDate(cMemo.getInt(1));
        entity_memo.setMemo(cMemo.getString(2));


        cMemo.close();

        return entity_memo;

    }








    public db_entity_pict db_cl_PictTable_showBy_id(int msy_id,int id){

        db_entity_pict entity_pict=new db_entity_pict();
        String PictTableName="PT"+msy_id;
        String[] selection ={String.valueOf(id)};

        Cursor cPict = db.rawQuery("SELECT * FROM "+PictTableName+" WHERE _id= ?;",selection);
        int data_num_pict=cPict.getCount();
        cPict.moveToFirst();

        entity_pict.setId(cPict.getInt(0));
        entity_pict.setUri(cPict.getString(2));
        entity_pict.setTag1(cPict.getString(3));
        entity_pict.setTag2(cPict.getString(4));
        entity_pict.setTag3(cPict.getString(5));
        entity_pict.setTag4(cPict.getString(6));
        entity_pict.setTag5(cPict.getString(7));

        cPict.close();

        return entity_pict;
    }




    public ArrayList<db_entity_memo> db_cl_MemoTable_showBy_Date(int msy_id,int date){

        String MemoTableName="MT"+msy_id;
        String[] selection ={String.valueOf(date)};

        Cursor cMemo = db.rawQuery("SELECT * FROM "+MemoTableName+" WHERE Date= ?;",selection);
        int data_num_memo=cMemo.getCount();
        cMemo.moveToFirst();
        ArrayList<db_entity_memo> entity_memoList=new ArrayList<db_entity_memo>();
        if(data_num_memo!=0) {
            do {
                db_entity_memo entity_memo_tmp=new db_entity_memo();
                entity_memo_tmp.setId(cMemo.getInt(0));
                entity_memo_tmp.setMemo(cMemo.getString(2));

                entity_memoList.add(entity_memo_tmp);
            }
            while (cMemo.moveToNext());

        }

        cMemo.close();

        return entity_memoList;
    }








    public ArrayList<db_entity_pict> db_cl_PictTable_showBy_Date(int msy_id,int date){

        String PictTableName="PT"+msy_id;
        String[] selection ={String.valueOf(date)};

        Cursor cPict = db.rawQuery("SELECT * FROM "+PictTableName+" WHERE Date= ?;",selection);
        int data_num_pict=cPict.getCount();
        cPict.moveToFirst();

        ArrayList<db_entity_pict> entity_pictList=new ArrayList<db_entity_pict>();
        if(data_num_pict!=0) {
            do {
                db_entity_pict entity_pict_tmp=new db_entity_pict();
                entity_pict_tmp.setId(cPict.getInt(0));
                entity_pict_tmp.setUri(cPict.getString(2));
                entity_pict_tmp.setTag1(cPict.getString(3));
                entity_pict_tmp.setTag2(cPict.getString(4));
                entity_pict_tmp.setTag3(cPict.getString(5));
                entity_pict_tmp.setTag4(cPict.getString(6));
                entity_pict_tmp.setTag5(cPict.getString(7));


                entity_pictList.add(entity_pict_tmp);
            }
            while (cPict.moveToNext());

        }

        cPict.close();

        return entity_pictList;



    }




    public ArrayList<db_entity_MemoPictTable> db_cl_MemoPictTable_showByMySyllabusId(int msy_id){       //日付ごとにまとめてオブジェクトに突っ込む。そのリストを返す
        ArrayList<db_entity_MemoPictTable> entity = new ArrayList<db_entity_MemoPictTable>();

        String MemoTableName="MT"+msy_id;
        String PictTableName="PT"+msy_id;
        String DataTableName="DT"+msy_id;

        String sql = "select * from "+DataTableName+";";
        Cursor c_data=db.rawQuery(sql,null);


        int data_num=c_data.getCount();
        c_data.moveToFirst();


        if(data_num!=0){
            do{
                db_entity_MemoPictTable entity_memoPictTable_tmp=new db_entity_MemoPictTable();

                int date_tmp=c_data.getInt(1);
                String[] selection ={String.valueOf(date_tmp)};

                Cursor cMemo = db.rawQuery("SELECT * FROM "+MemoTableName+" WHERE Date= ?;",selection);
                int data_num_memo=cMemo.getCount();
                cMemo.moveToFirst();
                ArrayList<db_entity_memo> entity_memoList=new ArrayList<db_entity_memo>();
                if(data_num_memo!=0) {
                    do {
                        db_entity_memo entity_memo_tmp=new db_entity_memo();
                        entity_memo_tmp.setId(cMemo.getInt(0));
                        entity_memo_tmp.setMemo(cMemo.getString(2));

                        entity_memoList.add(entity_memo_tmp);
                    }
                    while (cMemo.moveToNext());

                }

                Cursor cPict = db.rawQuery("SELECT * FROM "+PictTableName+" WHERE Date= ?;",selection);
                int data_num_pict=cPict.getCount();
                cPict.moveToFirst();
                ArrayList<db_entity_pict> entity_pictList=new ArrayList<db_entity_pict>();
                if(data_num_pict!=0) {
                    do {
                        db_entity_pict entity_pict_tmp=new db_entity_pict();
                        entity_pict_tmp.setId(cPict.getInt(0));
                        entity_pict_tmp.setUri(cPict.getString(2));
                        entity_pict_tmp.setTag1(cPict.getString(3));
                        entity_pict_tmp.setTag2(cPict.getString(4));
                        entity_pict_tmp.setTag3(cPict.getString(5));
                        entity_pict_tmp.setTag4(cPict.getString(6));
                        entity_pict_tmp.setTag5(cPict.getString(7));


                        entity_pictList.add(entity_pict_tmp);
                    }
                    while (cPict.moveToNext());

                }

                cMemo.close();
                cPict.close();

                entity_memoPictTable_tmp.setMemoList(entity_memoList);
                entity_memoPictTable_tmp.setPictList(entity_pictList);
                entity_memoPictTable_tmp.setDate(date_tmp);

                entity.add(entity_memoPictTable_tmp);


            }
            while(c_data.moveToNext());




        }


        c_data.close();


        return entity;


    }



    public int db_cl_DataTableCheckRecordExistence(int msy_id,int Date){

        String DataTableName="DT"+msy_id;

        String[] selection ={String.valueOf(Date)};
        Cursor c = db.rawQuery("SELECT * FROM "+DataTableName+" WHERE Date= ?;",selection);

        int num=c.getCount();
        c.close();

        if(num>0){
            return 1;
        }
        else{
            return 0;
        }

    }





}
