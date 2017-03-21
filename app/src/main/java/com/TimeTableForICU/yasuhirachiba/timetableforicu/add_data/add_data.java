package com.TimeTableForICU.yasuhirachiba.timetableforicu.add_data;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.ExpandableHeightGridView;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.LanguageSetting;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.VariablesDef;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info.ItemClickListener;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_sy;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulator;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulatorForClassInfo;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.openHelper;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.table_info_entity;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.table_settings_entity;
import com.google.gson.Gson;

import java.util.ArrayList;

import me.priyesh.chroma.ChromaDialog;
import me.priyesh.chroma.ColorMode;
import me.priyesh.chroma.ColorSelectListener;

public class add_data extends AppCompatActivity {

    openHelper helper;
    SQLiteDatabase db;
    db_manipulator DBm;
    db_manipulatorForClassInfo DBmcl;


    EditText et_add_data_cstitle;
    EditText et_add_data_classroom;
    EditText et_add_data_teacher;
    Spinner sp_period;
    Spinner sp_week;
    BootstrapButton btn_schedule;
    BootstrapButton btn_more;
    BootstrapButton btn_ok;

    Button btnforColorChoice;
    Button btnforColorChoice_color;

    /*    ArrayList<BootstrapButton> schedule_list_button=new ArrayList<BootstrapButton>();
        ArrayList<TextView> schedule_list_tvForMargin=new ArrayList<TextView>();
        ArrayList<LinearLayout>ll_more_list=new ArrayList<LinearLayout>();
        ArrayList<Integer> schedule_list=new ArrayList<Integer>();
        */
    ArrayList<add_data_schedules> schedulesArrayList=new ArrayList<add_data_schedules>();
    SharedPreferences prefs;
    Gson gson;
    table_info_entity tableInfoEntity;
    table_settings_entity tableSettingsEntity;

  //  LinearLayout ll;
    LinearLayout ll_more;
    LinearLayout ll_more_main;


    ExpandableHeightGridView gridView;
    add_data_gridViewAdapter gridViewAdapter;

    View layout;

    ArrayList<db_entity_sy> entity=new ArrayList<db_entity_sy>();
    ArrayList<String> cstitle=new ArrayList<String>();
    int sy_id=-1;
    int scnum=0;
    int count=0;
    String id;
   /* String schedule_string="";*/

    String bgColor="";
    String colorname="#ffffff";

    DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
    float width=dm.widthPixels;
    float height=dm.heightPixels;

    boolean scroll=false;
    boolean isMoreClicked =false;

    int dateButtonCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_add_data);

        ActionBar actionBar = getSupportActionBar();       //戻るボタン
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int color = R.color.forTitlebar;
        Drawable backgroundDrawable = getApplicationContext().getResources().getDrawable(color);
        actionBar.setBackgroundDrawable(backgroundDrawable);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        gson = new Gson();

        String tableInfo = prefs.getString(VariablesDef.CURRENT_TABLE_INFO, "");// 保存されているjson文字列を取得
        tableInfoEntity= gson.fromJson(tableInfo, table_info_entity.class);// json文字列を 「UserSettingクラス」のインスタンスに変換
        String tableSetting = prefs.getString(VariablesDef.CURRENT_TABLE_SETTINGS, "");// 保存されているjson文字列を取得
        tableSettingsEntity= gson.fromJson(tableSetting, table_settings_entity.class);// json文字列を 「UserSettingクラス」のインスタンスに変換


        SharedPreferences prefer = getSharedPreferences("window_data", MODE_PRIVATE);
        CardView cv=(CardView)findViewById(R.id.add_data_cardView);
        cv.setMinimumHeight( prefer.getInt(VariablesDef.LAYOUT_HEIGHT,0));


        ll_more_main=(LinearLayout)findViewById(R.id.add_data_ll_more);
        ll_more_main.setVisibility(View.GONE);



        et_add_data_cstitle = (EditText)findViewById(R.id.add_data_cstitle);
        et_add_data_classroom=(EditText)findViewById(R.id.add_data_classroom);
        et_add_data_teacher=(EditText)findViewById(R.id.add_data_csteacher);

        sp_period=(Spinner)findViewById(R.id.spinner_period);
        sp_week=(Spinner)findViewById(R.id.spinner_week);


        btn_schedule=(BootstrapButton)findViewById(R.id.adddata_button_schedule_ok);
        btn_schedule.setOnClickListener(new add_dataClickL());

        btn_ok=(BootstrapButton) findViewById(R.id.adddata_button_ok);
        btn_ok.setText("OK");
        btn_ok.setOnClickListener(new add_dataClickL());

        btn_more=(BootstrapButton)findViewById(R.id.add_data_more);
        btn_more.setText(R.string.add_data_showMore);
        btn_more.setOnClickListener(new add_dataClickL());

        btnforColorChoice=(Button)findViewById(R.id.add_data_color_choice);
        btnforColorChoice_color=(Button)findViewById(R.id.add_data_color_choice_color);
        btnforColorChoice_color.setBackgroundColor(Color.parseColor("#ffffff"));
        btnforColorChoice.setOnClickListener(new add_dataClickL());





        helper=new openHelper(this);
        db=helper.getWritableDatabase();
        DBmcl=new db_manipulatorForClassInfo(db);









        ScrollView scrollView = (ScrollView)findViewById(R.id.add_data_scrollView);    //スクロールの有効・無効化
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (scroll == true) {                   //スクロールさせたい時
                    return false;
                } else if (scroll == false) {   //スクロールさせないとき
                    /** タッチイベントをHandlingしたよ！　てことだとおもいます。*/
                    /** falseを返すと、スクロールするようになります。*/
                    //return true;
                    return false;
                }
                return false;
            }
        });








    }

    @Override
    protected void onStart(){
        super.onStart();

        LanguageSetting.setting(add_data.this,tableSettingsEntity.getSettingsJapanese());

        gridView=(ExpandableHeightGridView)findViewById(R.id.add_data_gridview);
        gridViewAdapter=new add_data_gridViewAdapter(getApplicationContext(),R.layout.add_data_gridview_item,schedulesArrayList);
        gridView.setAdapter(gridViewAdapter);
        gridViewAdapter.setLongClickListener(new gridViewItemLongClick());






        String tablename=tableInfoEntity.getTableName();
        String syllabusname=tableInfoEntity.getSyllabusName();

        DBm=new db_manipulator(db,tablename,syllabusname);


        Intent i = getIntent();
        id = i.getStringExtra("id");

        if(tableSettingsEntity.getSyllabusJapanese()==true){
            cstitle.add("シラバスから選択");
        }else {
            cstitle.add("tap here to select from syllabus");
        }

        String sid;
        if(id.charAt(0)=='4'){                    //4限のとこタップしてもL4が選択肢にくるようにする
            sid="s"+id;
            String sid2="s"+"9"+id.charAt(1);
            entity=DBm.sy_showByPeriod(sid,sid2);
        }
        else {
            sid = "s" + id;
            entity = DBm.sy_showByPeriod(sid);
        }


        if(tableSettingsEntity.getSyllabusJapanese()==true){
            for (db_entity_sy db_ent : entity) {
                cstitle.add(db_ent.getJtitle());
            }
        }
        else {
            for (db_entity_sy db_ent : entity) {
                cstitle.add(db_ent.getEtitle());
            }
        }


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cstitle);
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setDropDownWidth((int)width);

        spinner.setOnItemSelectedListener(new spClickL());


    }


    public class add_dataClickL implements View.OnClickListener{

        String period,week;
        Intent it=new Intent();


        @Override
        public void onClick(View v){


            switch(v.getId()){

                case (R.id.add_data_more):                                 //さらに表示の処理
                    if(scroll==false){
                        scroll=true;
                        isMoreClicked=true;

                        for(add_data_schedules tmp:schedulesArrayList){
                            ((TextView)tmp.getLL_more().findViewById(R.id.more_et1)).setText(et_add_data_cstitle.getText());
                            ((TextView)tmp.getLL_more().findViewById(R.id.more_et2)).setText(et_add_data_classroom.getText());
                        }




                        ll_more_main.setVisibility(View.VISIBLE);
                        et_add_data_cstitle.setFocusable(false);
                        et_add_data_classroom.setFocusable(false);
                        et_add_data_cstitle.setEnabled(false);
                        et_add_data_classroom.setEnabled(false);

                        btn_more.setText(R.string.add_data_fold_moreSettings);

                    }
                    else {
                        new AlertDialog.Builder(add_data.this)
                                .setTitle("")
                                .setMessage(R.string.add_data_foldOrNot )
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        for(int h=0;h<schedulesArrayList.size();h++) {
                                            ((TextView) schedulesArrayList.get(h).ll_more.findViewById(R.id.more_et1)).setText(et_add_data_cstitle.getText());
                                            ((TextView) schedulesArrayList.get(h).ll_more.findViewById(R.id.more_et2)).setText(et_add_data_classroom.getText());
                                        }
                                        ll_more_main.setVisibility(View.GONE);
                                        et_add_data_cstitle.setFocusable(true);
                                        et_add_data_classroom.setFocusable(true);
                                        et_add_data_cstitle.setEnabled(true);
                                        et_add_data_classroom.setEnabled(true);
                                        et_add_data_cstitle.setFocusableInTouchMode(true);
                                        et_add_data_classroom.setFocusableInTouchMode(true);

                                        btn_more.setText(R.string.add_data_showMore);
                                        scroll=false;

                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();

                    }




                    break;

                case (R.id.adddata_button_schedule_ok):
                    String period=(String)sp_period.getSelectedItem();
                    String week=(String)sp_week.getSelectedItem();
                    int tmp=0;
             /*       if(week.equals("Mon")){tmp=1;}else if(week.equals("Tue")){tmp=2;} else if(week.equals("Wed")){tmp=3;}else if(week.equals("Thu")){tmp=4;} else if(week.equals("Fri")){tmp=5;} else if(week.equals("Sat")){tmp=6;}
                    schedule_string=schedule_string+" "+period+tmp;*/


                    int duplicate=duplicate_check(week,period,-1);
                    if(duplicate!=-1){
                        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.add_data_root_relative);
                        Snackbar.make(layout, R.string.add_data_class_alredyExists_forSnackBar, Snackbar.LENGTH_LONG)
                                .show();


                    }
                    else {
                        AddButton_and_AddView(week, period, -1);   //コマを足してった時の処理。新しいボタンを作って挿入してくメソッド
                    }


                    break;





                case(R.id.adddata_button_ok):

                    if(isMoreClicked==false){      //一回も更に表示が押されてないと、一番最初のコマのとこのタイトルが空白になってるのでこれで読み込み直す
                        for(add_data_schedules stmp:schedulesArrayList){
                            ((TextView)stmp.getLL_more().findViewById(R.id.more_et1)).setText(et_add_data_cstitle.getText());
                            ((TextView)stmp.getLL_more().findViewById(R.id.more_et2)).setText(et_add_data_classroom.getText());
                        }
                    }
                    String add_data_cstitle= et_add_data_cstitle.getText().toString();
                    String add_data_classroom= et_add_data_classroom.getText().toString();
                    String add_data_teacher= et_add_data_teacher.getText().toString();




//授業登録　tale1と、mysyllabusに登録
                    //      schedule_list=anyalise_schedule_string.anyalise_schedule_string_cut(schedule_string);
                    int L4=0;
                    if(schedulesArrayList.size()!=0){
                        btn_ok.setEnabled(false);

                        ArrayList<Integer> schedule_list=new ArrayList<Integer>();
                        for(add_data_schedules temporaly:schedulesArrayList){
                            schedule_list.add(temporaly.getSchedule());
                        }

                        String schedule_string=anyalise_schedule_string.anyalise_schedule_IntListToString(schedule_list);

                        int msy_id= DBm.my_sy_insertMySyllabus(add_data_cstitle,add_data_classroom,add_data_teacher,schedule_string,schedule_list);
                        int cs_info_id= DBmcl.db_cl_insert(msy_id);


                        DBmcl.db_cl_createPictTable(msy_id);
                        DBmcl.db_cl_createMemoTable(msy_id);
                        DBmcl.db_cl_createDataTable(msy_id);


                        for(int k=0;k<schedulesArrayList.size();k++){
                            int tmp1=schedule_list.get(k)/10;
                            if(tmp1==9){                                        //ろんふぉーの時、table1の４０番第に入れるための処理。剰余つかって判定。9が来たら４に変える　そしてtable1の要素のL4に１を代入する。
                                schedule_list.set(k,40+schedule_list.get(k)%10);
                                L4=1;
                            }

                            add_data_cstitle= ((TextView)schedulesArrayList.get(k).getLL_more().findViewById(R.id.more_et1)).getText().toString();    //table1に入れるのは、詳細設定の方から引っ張ってきたやつ
                            add_data_classroom= ((TextView)schedulesArrayList.get(k).getLL_more().findViewById(R.id.more_et2)).getText().toString();

                            DBm.db_table1_update(String.valueOf(schedule_list.get(k)),add_data_cstitle,add_data_classroom,add_data_teacher,1,msy_id,cs_info_id,L4,colorname);
                        }
                        setResult(RESULT_OK,it);
                        db.close();
                        finish();
                        break;


                    }

                    else {
                        // DBm.db_table1_add(id, add_data_cstitle, add_data_classroom, add_data_teacher, 1, sy_id,cs_info_id);
                        AlertDialog.Builder builder = new AlertDialog.Builder(add_data.this);
                        builder.setMessage(R.string.add_data_putSchedule);
                        builder.show();
                        break;
                    }
                    //ここまで


                case(R.id.add_data_color_choice): //色選択
                    new ChromaDialog.Builder()
                            .initialColor(Color.GREEN)
                            .colorMode(ColorMode.RGB) // There's also ARGB and HSV
                            .onColorSelected(new ColorSelectListener() {
                                @Override
                                public void onColorSelected(@ColorInt int i) {
                                    btnforColorChoice_color.setBackgroundColor(i);
                                    colorname=String.format("#%06X", 0xFFFFFF & i);   //文字列の形式に色の数値を治す

                                }
                            })
                            .create()
                            .show(getSupportFragmentManager(), "ChromaDialog");


                  /*  final ColorDialog dialog=ColorDialog.newInstance();
                    dialog.setOnButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            colorname=(String)view.getTag();
                            btnforColorChoice_color.setBackgroundColor(Color.parseColor(colorname));
                            TextView tv=(TextView)findViewById(R.id.add_tv_test2);
                            tv.setText(colorname);
                            dialog.dismiss();
                        }
                    });


                    dialog.show(getFragmentManager(),"dialog_fragment");
*/

                   /* inflater = LayoutInflater.from(add_data.this);
                    layout = inflater.inflate(R.layout.color_choice, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(add_data.this);
                    builder.setTitle("ダイアログタイトル");
                    builder.setView(layout);
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    Button bt=(Button)findViewById(R.id.color_1);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bgColor=(String)view.getTag();

                        }
                    });


                    AlertDialog dialog = builder.create();
                    dialog.show();
*/






            }
        }





    }

    public class spClickL implements android.widget.AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(final AdapterView<?> adapterView, View view, int i, long l) {
            final int i1=i;
            final long l1=l;  //エラーでるからfinalつける
            if(scroll==true){
                new AlertDialog.Builder(add_data.this)
                        .setTitle("")
                        .setMessage(R.string.add_data_data_goneOrNot )
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int h=0;h<schedulesArrayList.size();h++) {
                                    ((TextView) schedulesArrayList.get(h).ll_more.findViewById(R.id.more_et1)).setText(et_add_data_cstitle.getText());
                                    ((TextView) schedulesArrayList.get(h).ll_more.findViewById(R.id.more_et2)).setText(et_add_data_classroom.getText());
                                }

                                ll_more_main.setVisibility(View.GONE);
                                et_add_data_cstitle.setFocusable(true);
                                et_add_data_classroom.setFocusable(true);
                                et_add_data_cstitle.setEnabled(true);
                                et_add_data_classroom.setEnabled(true);
                                et_add_data_cstitle.setFocusableInTouchMode(true);
                                et_add_data_classroom.setFocusableInTouchMode(true);

                                btn_more.setText(R.string.add_data_showMore);
                                scroll=false;



                                spinnnerSelectedLogic(adapterView,i1,l1);

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
            else{
                spinnnerSelectedLogic(adapterView,i1,l1);

            }






        }



        public void spinnnerSelectedLogic(final AdapterView adapterView, int i, long l){

            String item = (String) adapterView.getItemAtPosition(i);
            final int num=(int)l;
            if(num==0){          //自分で授業を作るとき
                et_add_data_cstitle.setText("");
                et_add_data_classroom.setText("");
                et_add_data_teacher.setText("");
                sy_id=-1;
                /*sp_period.setVisibility(View.VISIBLE);
                sp_week.setVisibility(View.VISIBLE);
                btn_schedule.setVisibility(View.VISIBLE);*/
                ArrayList<String> schedule_initial=anyalise_schedule_string.anyalise_schedule_string_NumToInitial(id);
                AddButton_and_AddView(null,null,Integer.valueOf(id));

            }
            else {

                String schedule_string=entity.get(num-1).getSchedule_String();
           /*     sp_period.setVisibility(View.GONE);
                sp_week.setVisibility(View.GONE);
                btn_schedule.setVisibility(View.GONE);*/

                final ArrayList<Integer> schedule_list=anyalise_schedule_string.anyalise_schedule_string_cut(schedule_string);
                schedulesArrayList.clear();
                gridViewAdapter.notifyDataSetChanged();
                ll_more_main.removeAllViews();
                ArrayList<Integer> dCheck=new ArrayList<>();

                for(int tmp:schedule_list){
                    int duplicate=duplicate_check(null,null,tmp);
                    int check=0;
                    if(duplicate!=-1){
                        dCheck.add(duplicate);
                    }
                }
                if(dCheck.size()!=0){
                    String checkedSchedule="";
                    for(int tmpcheck:dCheck){
                        String tmpcheckschedule=anyalise_schedule_string.anyalise_schedule_IntToInitial(tmpcheck);
                        checkedSchedule=checkedSchedule+" "+tmpcheckschedule;
                    }
                    Resources res = getResources();
                    String resource1 = res.getString(R.string.add_data_class_alredyExists);
                //  String resource2 = res.getString(R.string.add_data_class_alredyExists_OverwriteOrNot);
                    new AlertDialog.Builder(add_data.this)
                            .setMessage(resource1+"\n\n    "+checkedSchedule+"\n\n          ")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(tableSettingsEntity.getSyllabusJapanese()==true){
                                        et_add_data_cstitle.setText(entity.get(num-1).getJtitle());
                                    }else {
                                        et_add_data_cstitle.setText(entity.get(num-1).getEtitle());
                                    }

                                    et_add_data_classroom.setText(entity.get(num-1).getClassroom());
                                    et_add_data_teacher.setText(entity.get(num-1).getTeacher());

                                    sy_id = entity.get(num-1).getSy_id();
                                    schedulesArrayList.clear();
                                    gridViewAdapter.notifyDataSetChanged();
                                    ll_more_main.removeAllViews();
                             //       ll.removeAllViews();
                                    for(int tmp:schedule_list){
                                        AddButton_and_AddView(null,null,tmp);
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();

                }
                else {
                    if(tableSettingsEntity.getSyllabusJapanese()==true){
                        et_add_data_cstitle.setText(entity.get(num-1).getJtitle());
                    }else {
                        et_add_data_cstitle.setText(entity.get(num-1).getEtitle());
                    }
                    et_add_data_classroom.setText(entity.get(num-1).getClassroom());
                    et_add_data_teacher.setText(entity.get(num-1).getTeacher());

                    sy_id = entity.get(num-1).getSy_id();
                    schedulesArrayList.clear();
                    ll_more_main.removeAllViews();
                    gridViewAdapter.notifyDataSetChanged();
                    for(int tmp:schedule_list){
                        AddButton_and_AddView(null,null,tmp);
                    }


                }


            }




        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }


    }




    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {
            Intent it=new Intent();
            setResult(RESULT_CANCELED,it);
            db.close();
            finish();
        }

        return super.onOptionsItemSelected(item);


    }


    private void AddButton_and_AddView(String week,String period,int schedule){             //スケジュールのボタンと、更に表示のとこの項目どっちも加えてく


        int widthForB=(int)width/5;
        int hwidhtForB= widthForB /2;
        int widthForT=(int)width/42;

        boolean exsits=false;                    //追加しようとしたスケジュールがすでにボタンに存在するかチェックして、すれば何もしない
        if((week==null)&&(period==null)){
            int tmpsc=schedule;
            if(tmpsc/10==9){
                tmpsc=tmpsc-50;
            }
            for(add_data_schedules tmpschedule:schedulesArrayList){
                int tmpsc2=tmpschedule.getSchedule();
                if(tmpsc2/10==9){
                    tmpsc2=tmpsc2-50;
                }
                if(tmpsc2==tmpsc){
                    exsits=true;
                }
            }




        }
        else{
            int tmps=anyalise_schedule_string.anyalise_schedule_WeekStringPeriodStringToInt(week,period);
            if(tmps/10==9){
                tmps=tmps-50;
            }
            for(add_data_schedules tmpschedule:schedulesArrayList){
                int tmpsc2=tmpschedule.getSchedule();
                if(tmpsc2/10==9){
                    tmpsc2=tmpsc2-50;
                }
                if(tmpsc2==tmps){
                    exsits=true;
                }
            }

        }






        if(exsits==false){
            add_data_schedules schedules_tmp=new add_data_schedules();


            LayoutInflater inflater=LayoutInflater.from(add_data.this);
            ll_more=(LinearLayout)inflater.inflate(R.layout.add_data_more_settings_row,null);
            ll_more_main=(LinearLayout)findViewById(R.id.add_data_ll_more);



            TextView tvForMargin=new TextView(getApplicationContext());  //ボタンのあいだに隙間を作るためのtextview
            tvForMargin.setText("  ");
            tvForMargin.setWidth(widthForT);


            ((TextView)ll_more.findViewById(R.id.more_et1)).setText(et_add_data_cstitle.getText());
            ((TextView)ll_more.findViewById(R.id.more_et1)).setHint("Title");
            ((TextView)ll_more.findViewById(R.id.more_et2)).setText(et_add_data_classroom.getText());
            ((TextView)ll_more.findViewById(R.id.more_et2)).setHint("Classroom");


            if((week==null)&&(period==null)){
                String s_schedule=anyalise_schedule_string.anyalise_schedule_IntToInitial(schedule);
                ((BootstrapButton)ll_more.findViewById(R.id.more_button)).setText(s_schedule);
                schedules_tmp.setBtnSchedule(s_schedule);
            }
            else{
                ((BootstrapButton)ll_more.findViewById(R.id.more_button)).setText(week+"/"+period);
                schedules_tmp.setBtnSchedule(week+"/"+period);

            }


            if(schedule==-1){
                schedule=0;
                schedule=anyalise_schedule_string.anyalise_schedule_WeekStringPeriodStringToInt(week,period);

            }

            ll_more_main.addView(ll_more);


            schedules_tmp.setNumber(count);
            count++;
            schedules_tmp.setSchedule(schedule);
         //   schedules_tmp.setLL(ll);
            schedules_tmp.setLL_more(ll_more);
         //   schedules_tmp.setBtn(button);
            schedules_tmp.setTvMargin(tvForMargin);


            schedulesArrayList.add(schedules_tmp);

            gridViewAdapter.notifyDataSetChanged();


        }

        else {

            final RelativeLayout layout = (RelativeLayout) findViewById(R.id.add_data_root_relative);
            Snackbar.make(layout, R.string.add_data_class_alredyExists_forSnackBar, Snackbar.LENGTH_LONG)
                    .show();


        }




    }


    public class gridViewItemLongClick implements ItemClickListener{

        @Override
        public void onClick(View view, int position) {

            ll_more_main.removeView(schedulesArrayList.get(position).getLL_more());

            schedulesArrayList.remove(position);

            gridViewAdapter.notifyDataSetChanged();


        }
    }








    private int duplicate_check(String week,String period,int schedule) {

        int returnDuplicate=-1;
        if ((week == null) && (period == null)) {

            int tmpschedule = schedule;
            if (schedule / 10 == 9) {
                tmpschedule = schedule - 50;            //入れようとした時間にすでに他の授業が入っていないかチェック 入ってたらスケジュールをreturn
            }
            db_entity tmpEntity = DBm.db_table1_showbyId(String.valueOf(tmpschedule));
            if (tmpEntity.getInitialize() == 1) {
                returnDuplicate = schedule;
            }


        } else {

            int tmps=anyalise_schedule_string.anyalise_schedule_WeekStringPeriodStringToInt(week,period);
            int tmpschedule = tmps;
            if (tmps / 10 == 9) {
                tmpschedule = tmps - 50;            //入れようとした時間にすでに他の授業が入っていないかチェック
            }
            db_entity tmpEntity = DBm.db_table1_showbyId(String.valueOf(tmpschedule));
            if (tmpEntity.getInitialize() == 1) {
                returnDuplicate = tmps;
            }


        }
        return returnDuplicate;
    }










}

