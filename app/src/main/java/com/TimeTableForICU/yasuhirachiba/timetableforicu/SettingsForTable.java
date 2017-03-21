package com.TimeTableForICU.yasuhirachiba.timetableforicu;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info.delete_methods;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulator;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulatorForClassInfo;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.openHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SettingsForTable extends AppCompatActivity{
    String idList[]={"11","12","13","14","15","16","21","22","23","24","25","26","31","32","33","34","35","36","41","42","43","44","45","46","51","52","53","54","55","56","61","62","63","64","65","66","71","72","73","74","75","76"};


    table_info_entity tableInfoEntity=new table_info_entity();

    openHelper helper;
    SQLiteDatabase db;
    db_manipulator DBm;

    ArrayAdapter<String> adapter;


    table_settings_entity tableSettingsEntity;

    private Switch switchSaturday;
    private Switch switchperiod6;
    private Switch switchperiod7;
    private Switch switchperiod8;
    private Switch switchSyllabusLang;
    private Switch switchSettingsLang;
    private Switch switchSlideText;
    private EditText etSettingsFontSize;

    private BootstrapButton btn_ok;


    SharedPreferences prefs;
    Gson gson;

    ArrayList<table_info_entity> tableSettingsEntityArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_for_table);

        ActionBar actionBar = getSupportActionBar();       //戻るボタン
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        int color = R.color.forTitlebar;
        Drawable backgroundDrawable = getApplicationContext().getResources().getDrawable(color);
        actionBar.setBackgroundDrawable(backgroundDrawable);



        helper=new openHelper(this);
        db=helper.getReadableDatabase();


        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        gson = new Gson();


        String tableSettings = prefs.getString(VariablesDef.CURRENT_TABLE_SETTINGS, "");
        tableSettingsEntity= gson.fromJson(tableSettings, table_settings_entity.class);
        LanguageSetting.setting(SettingsForTable.this,tableSettingsEntity.getSettingsJapanese());



        initialSettings();





    }








    public class ClickL implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){


                case(R.id.settings_bt_ok):
                    tableSettingsEntity.setFontsize(Integer.valueOf(etSettingsFontSize.getText().toString()));
                    String jsonInstanceString3=gson.toJson(tableSettingsEntity);
                    prefs.edit().putString(VariablesDef.CURRENT_TABLE_SETTINGS,jsonInstanceString3).apply();
                    db.close();
                    finish();

                    break;


            }
        }
    }



    private class CheckedListner implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String jsonInstanceString3;
            switch (buttonView.getId()){

                case(R.id.switch_saturday):
                    tableSettingsEntity.setSaturday(isChecked);

                    break;
                case(R.id.switch_6period):
                    tableSettingsEntity.setPeriod6(isChecked);


                    break;
                case(R.id.switch_7period):
                    tableSettingsEntity.setPeriod7(isChecked);


                    break;
                case(R.id.switch_8period):
                    tableSettingsEntity.setPeriod8(isChecked);

                    break;
                case(R.id.switch_syllabusLanguage):
                    tableSettingsEntity.setSyllabusJapanese(isChecked);

                    break;
                case (R.id.switch_settingsLanguage):
                    tableSettingsEntity.setSettingsJapanese(isChecked);
                    LanguageSetting.setting(SettingsForTable.this,tableSettingsEntity.getSettingsJapanese());
                    setContentView(R.layout.activity_settings_for_table);
                    initialSettings();


                    break;
                case(R.id.switch_slideText):
                    tableSettingsEntity.setTextslide(isChecked);
                    break;






            }
        }
    }







    public void getSettings(){


       // 保存されているjson文字列を取得
        String tableSetting = prefs.getString("TableInfoArrayList", "");
// json文字列を 「UserSettingクラス」のインスタンスに変換
        final ArrayList<table_info_entity> tableInfoEntityArrayList = gson.fromJson(tableSetting, new TypeToken<ArrayList<table_info_entity>>() {}.getType());

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(table_info_entity tmp:tableInfoEntityArrayList){
            adapter.add(tmp.getTableName()+"        "+tmp.getSyllabusName());

        }

        CustomListView listView = (CustomListView) findViewById(R.id.settingsForTable_listview);
        // アダプターを設定します
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view, final int pos, long id) {

                // 選択アイテムを取得
                ListView listView = (ListView)parent;
                final String item = (String)listView.getItemAtPosition(pos);


                new AlertDialog.Builder(SettingsForTable.this)
                        .setTitle("title")
                        .setMessage(R.string.settings_for_table_setDefaultTable)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                table_info_entity tableEntityTmp=tableInfoEntityArrayList.get(pos);

                                String jsonInstanceString2=gson.toJson(tableEntityTmp);
                                prefs.edit().putString(VariablesDef.CURRENT_TABLE_INFO,jsonInstanceString2).apply();

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int size= tableInfoEntityArrayList.size();
                final int pos=position;
                if(size==1){

                    //すでにテーブルが一個しか存在しない時は削除できない旨を伝える
                    final RelativeLayout layout = (RelativeLayout) findViewById(R.id.settingsForTable_root_rl);
                    Snackbar.make(layout, R.string.settings_for_table_tableCannnotDelete, Snackbar.LENGTH_LONG)
                            .show();


                }
                else {
                    new AlertDialog.Builder(SettingsForTable.this)
                            .setTitle("title")
                            .setMessage(R.string.settings_for_table_tableDeleteOrNot)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {



                                    db_manipulator DBmTmp=new db_manipulator(db,tableInfoEntityArrayList.get(pos).getTableName(),tableInfoEntityArrayList.get(pos).getSyllabusName());
                                    db_manipulatorForClassInfo DBmcl=new db_manipulatorForClassInfo(db);
                                    ArrayList<Integer> msy_idList=new ArrayList<Integer>();
                                    for(String tmpId:idList){                                    //msy_idをtableから抽出
                                        db_entity entity=DBmTmp.db_table1_showbyId(tmpId);
                                        if(entity.getSy_id()!=-1){
                                            int i=0;
                                            for(i=0;i<msy_idList.size();i++){
                                                if(entity.getSy_id()==msy_idList.get(i)){
                                                    break;
                                                }
                                            }
                                            if(i==msy_idList.size()){
                                                msy_idList.add(entity.getSy_id());
                                            }
                                        }

                                    }
                                    ArrayList<Integer> cl_infoList=new ArrayList<Integer>();
                                    for(String tmpId:idList){                                    //classinfoIdをtableから抽出
                                        db_entity entity=DBmTmp.db_table1_showbyId(tmpId);
                                        if(entity.getClass_info_id()!=0&&entity.getClass_info_id()!=-1){
                                            int i=0;
                                            for(i=0;i<cl_infoList.size();i++){
                                                if(entity.getClass_info_id()==cl_infoList.get(i)){
                                                    break;
                                                }
                                            }
                                            if(i==cl_infoList.size()){
                                                cl_infoList.add(entity.getClass_info_id());
                                            }
                                        }

                                    }


                                    for(int j=0;j<msy_idList.size();j++){
                                        delete_methods.delete_Class_byMsyIdClassInfoId(msy_idList.get(j),cl_infoList.get(j),DBmTmp,DBmcl,getApplicationContext());
                                    }


                                    DBmTmp.db_dropTable();
                                    DBmTmp=null;



                                    String tableSetting = prefs.getString(VariablesDef.CURRENT_TABLE_INFO, "");
                                    table_info_entity tableInfoEntity= gson.fromJson(tableSetting, table_info_entity.class);


                                    if(tableInfoEntity.getTableName().equals(tableInfoEntityArrayList.get(pos).getTableName())){
                                        String jsonInstanceString2;
                                        if(size-1==pos){
                                            jsonInstanceString2=gson.toJson(tableInfoEntityArrayList.get(pos-1));
                                        }
                                        else{
                                            jsonInstanceString2=gson.toJson(tableInfoEntityArrayList.get(pos+1));
                                        }
                                        prefs.edit().putString(VariablesDef.CURRENT_TABLE_INFO,jsonInstanceString2).apply();

                                    }


                                    adapter.remove(tableInfoEntityArrayList.get(pos).getTableName()+"        "+tableInfoEntityArrayList.get(pos).getSyllabusName());
                                    adapter.notifyDataSetChanged();




                                    tableInfoEntityArrayList.remove(pos);
                                    String jsonInstanceString = gson.toJson(tableInfoEntityArrayList);
                                    prefs.edit().putString(VariablesDef.TableInfo_ArrayList,jsonInstanceString).apply();






                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();




                }



                return true;
            }
        });

    }












    public void initialSettings(){

        getSettings();

        BootstrapButton btn=(BootstrapButton)findViewById(R.id.settingsForTable_add_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater)SettingsForTable.this.getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.dialog_for_create_new_table,
                        (ViewGroup)findViewById(R.id.dialogForNewTable_ll));


                EditText name_et = (EditText)layout.findViewById(R.id.dialogForNewTable_et_name);
                name_et.setHint("Only alphabets and numbers");

                Spinner spinner_term=(Spinner)layout.findViewById(R.id.dialogForNewTable_spinner_term);
                Spinner spinner_year=(Spinner)layout.findViewById(R.id.dialogForNewTable_spinner_year);
                spinner_term.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Spinner spinner = (Spinner) parent;
                        String term = (String) spinner.getSelectedItem();
                        tableInfoEntity.setTerm(term);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Spinner spinner = (Spinner) parent;
                        String term = (String) spinner.getSelectedItem();
                        tableInfoEntity.setTerm(term);
                    }
                });

                spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Spinner spinner = (Spinner) parent;
                        String year= (String) spinner.getSelectedItem();
                        tableInfoEntity.setYear(Integer.valueOf(year));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Spinner spinner = (Spinner) parent;
                        String year= (String) spinner.getSelectedItem();
                        tableInfoEntity.setYear(Integer.valueOf(year));

                    }
                });


                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsForTable.this);
                builder.setTitle("New Table");
                builder.setView(layout);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // OK ボタンクリック処理
                        EditText name_et = (EditText)layout.findViewById(R.id.dialogForNewTable_et_name);
                        tableInfoEntity.setTableName(name_et.getText().toString());




                        DBm=new db_manipulator(db,null,null);

                        int checkExsistence=DBm.db_table_CheckRecordExistence(tableInfoEntity.getTableName());

                        if(checkExsistence==0){
                            DBm.db_createTable(tableInfoEntity.getTableName());
                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            Gson gson = new Gson();

                            String tableSetting = prefs.getString(VariablesDef.TableInfo_ArrayList, "");
                            ArrayList<table_info_entity> TableInfoArrayList = gson.fromJson(tableSetting, new TypeToken<ArrayList<table_info_entity>>() {}.getType());


                            TableInfoArrayList.add(tableInfoEntity);
                            String jsonInstanceString = gson.toJson(TableInfoArrayList);// objectをjson文字列へ変換

                            prefs.edit().putString(VariablesDef.TableInfo_ArrayList,jsonInstanceString).apply();// 変換後の文字列をputStringで保存



                            getSettings();

                        }
                        else {
                            final RelativeLayout layout = (RelativeLayout) findViewById(R.id.settingsForTable_root_rl);
                            Snackbar.make(layout, R.string.settings_for_table_tableExists, Snackbar.LENGTH_LONG)
                                    .show();
                        }




                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                builder.create().show();


            }
        });



        switchSaturday=(Switch)findViewById(R.id.switch_saturday);
        switchSaturday.setChecked(tableSettingsEntity.getSaturday());
        switchSaturday.setOnCheckedChangeListener(new CheckedListner());

        switchperiod6=(Switch)findViewById(R.id.switch_6period);
        switchperiod6.setChecked(tableSettingsEntity.getPeriod6());
        switchperiod6.setOnCheckedChangeListener(new CheckedListner());

        switchperiod7=(Switch)findViewById(R.id.switch_7period);
        switchperiod7.setChecked(tableSettingsEntity.getPeriod7());
        switchperiod7.setOnCheckedChangeListener(new CheckedListner());

        switchperiod8=(Switch)findViewById(R.id.switch_8period);
        switchperiod8.setChecked(tableSettingsEntity.getPeriod8());
        switchperiod8.setOnCheckedChangeListener(new CheckedListner());

        switchSyllabusLang=(Switch)findViewById(R.id.switch_syllabusLanguage);
        switchSyllabusLang.setChecked(tableSettingsEntity.getSyllabusJapanese());
        switchSyllabusLang.setOnCheckedChangeListener(new CheckedListner());

        switchSettingsLang=(Switch)findViewById(R.id.switch_settingsLanguage);
        switchSettingsLang.setChecked(tableSettingsEntity.getSettingsJapanese());
        switchSettingsLang.setOnCheckedChangeListener(new CheckedListner());

        switchSlideText=(Switch)findViewById(R.id.switch_slideText);
        switchSlideText.setChecked(tableSettingsEntity.getTexslide());
        switchSlideText.setOnCheckedChangeListener(new CheckedListner());

        etSettingsFontSize=(EditText)findViewById(R.id.et_settingsFontSize);
        etSettingsFontSize.setInputType( InputType.TYPE_CLASS_NUMBER);
        etSettingsFontSize.setText(String.valueOf(tableSettingsEntity.getFontsize()));


        btn_ok=(BootstrapButton)findViewById(R.id.settings_bt_ok);
        btn_ok.setOnClickListener(new ClickL());





    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {
         //   Intent it=new Intent();
           // setResult(RESULT_CANCELED,it);
            db.close();
            finish();
        }

        return super.onOptionsItemSelected(item);


    }



}
