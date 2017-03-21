package com.TimeTableForICU.yasuhirachiba.timetableforicu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.add_data.add_data;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info.class_info;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.DB_updateClass;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulator;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.openHelper;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.user_policy.User_policy;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    openHelper helper;
    SQLiteDatabase db;
    db_manipulator DBm;

    final int REQUEST_CODE_FOR_SETTINGS_TABLE=20;

    int btnList[]={R.id.button11,R.id.button12,R.id.button13,R.id.button14,R.id.button15,R.id.button16,R.id.button21,R.id.button22,R.id.button23,R.id.button24,R.id.button25,R.id.button26,R.id.button31,R.id.button32,R.id.button33,R.id.button34,R.id.button35,R.id.button36,R.id.button41,R.id.button42,R.id.button43,R.id.button44,R.id.button45,R.id.button46,R.id.button51,R.id.button52,R.id.button53,R.id.button54,R.id.button55,R.id.button56,R.id.button61,R.id.button62,R.id.button63,R.id.button64,R.id.button65,R.id.button66,R.id.button71,R.id.button72,R.id.button73,R.id.button74,R.id.button75,R.id.button76,R.id.button81,R.id.button82,R.id.button83,R.id.button84,R.id.button85,R.id.button86};
    String idList[]={"11","12","13","14","15","16","21","22","23","24","25","26","31","32","33","34","35","36","41","42","43","44","45","46","51","52","53","54","55","56","61","62","63","64","65","66","71","72","73","74","75","76","81","82","83","84","85","86"};
    int L4MarginList[]={R.id.L4_margin1,R.id.L4_margin2,R.id.L4_margin3,R.id.L4_margin4,R.id.L4_margin5,R.id.L4_margin6};
    int weekList[]={R.id.week,R.id.week1,R.id.week2,R.id.week3,R.id.week4,R.id.week5,R.id.week6};
    int periodLiist[]={R.id.period1,R.id.period2,R.id.period3,R.id.period4,R.id.period5,R.id.period6,R.id.period7,R.id.period8};
    int frameLayoutList[]={R.id.frameLayout1,R.id.frameLayout2,R.id.frameLayout3,R.id.frameLayout5,R.id.frameLayout6,R.id.frameLayout7,R.id.frameLayout8,R.id.frameLayout9};
    int timeTvList[]={R.id.time_tv1,R.id.time_tv2,R.id.time_tv3,R.id.time_tv4,R.id.time_tv5,R.id.time_tv6,R.id.time_tv9,R.id.time_tv10,R.id.time_tv11,R.id.time_tv12,R.id.time_tv13,R.id.time_tv14,R.id.time_tv15,R.id.time_tv16,R.id.time_tv17,R.id.time_tv18};
    int period6[]={R.id.frameLayout7,R.id.button61,R.id.button62,R.id.button63,R.id.button64,R.id.button65,R.id.button66};
    int period7[]={R.id.frameLayout8,R.id.button71,R.id.button72,R.id.button73,R.id.button74,R.id.button75,R.id.button76};
    int period8[]={R.id.frameLayout9,R.id.button81,R.id.button82,R.id.button83,R.id.button84,R.id.button85,R.id.button86};



    Button btn[]=new Button[btnList.length];
    int sizeofview_height;

    ArrayList<Integer> sizeofView=new ArrayList<Integer>();
    table_info_entity tableInfoEntity =new table_info_entity();
    SharedPreferences prefer;

    TextView tvForBottom;

    boolean TableCreated=false;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        int colorId = getResources().getColor(R.color.forTitlebar);
        toolbar.setBackgroundColor(colorId);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        // ドロワー開くボタン(三本線)を非表示
        toggle.setDrawerIndicatorEnabled(true);


/*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = getLayoutInflater().inflate(R.layout.nav_header_main, navigationView,false);
        navigationView.addHeaderView(headerView);


        tvForBottom=(TextView)findViewById(R.id.bottom_tv);



        helper=new openHelper(this);
        db=helper.getReadableDatabase();

        prefer = getSharedPreferences("window_data", MODE_PRIVATE);




        Spinner spinnerForHeader=(Spinner)headerView.findViewById(R.id.nav_header_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




        spinnerForHeader.setAdapter(adapter);

        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

      // defaultSharedPreferences.edit().putBoolean("initialize",true).apply();
        if(defaultSharedPreferences.getBoolean("initialize",true)){



            // アラーとダイアログ を生成
            // カスタムビューを設定
            LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                    LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.dialog_for_create_new_table,
                    (ViewGroup)findViewById(R.id.dialogForNewTable_ll));

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


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.main_first_createTable);
            builder.setView(layout);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // OK ボタンクリック処理
                    EditText name_et = (EditText)layout.findViewById(R.id.dialogForNewTable_et_name);
                    tableInfoEntity.setTableName(name_et.getText().toString());


                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Gson gson = new Gson();
                    ArrayList<table_info_entity> tableInfoEntityArrayList =new ArrayList<>();
                    tableInfoEntityArrayList.add(tableInfoEntity);
                    String jsonInstanceString = gson.toJson(tableInfoEntityArrayList);// objectをjson文字列へ変換
                    String jsonInstanceString2=gson.toJson(tableInfoEntity);

                    prefs.edit().putString(VariablesDef.TableInfo_ArrayList,jsonInstanceString).apply();// 変換後の文字列をputStringで保存
                    prefs.edit().putString(VariablesDef.CURRENT_TABLE_INFO,jsonInstanceString2).apply();


                    table_settings_entity tableSettingsEntity=new table_settings_entity();
                    String jsonInstanceString3=gson.toJson(tableSettingsEntity);
                    prefs.edit().putString(VariablesDef.CURRENT_TABLE_SETTINGS,jsonInstanceString3).apply();

                    //DBm=new db_manipulator(db,null,null);
                    DBm=new db_manipulator(db, tableInfoEntity.getTableName(), tableInfoEntity.getSyllabusName());

                    DBm.db_createTable(tableInfoEntity.getTableName());

                    SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    defaultSharedPreferences.edit().putBoolean("TableCreated",true).apply();



                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative);  //初回起動時に画面サイズ取得
                    SharedPreferences.Editor editor = prefer.edit();
                    int height=rl.getHeight();
                    int width=rl.getWidth();
                    editor.putInt(VariablesDef.LAYOUT_HEIGHT,height);
                    editor.putInt(VariablesDef.LAYOUT_WIDTH,width);

                    TextView tvForTime=(TextView)findViewById(R.id.time_tv1);             //文字サイズ確定
                    float textsize= AjustTextSize.textSizeForString(tvForTime,"10:00");
                    float textsize1= AjustTextSize.textSizeForString(tvForTime,"1");
                    editor.putFloat(VariablesDef.TEXT_SIZE_FOR_TIME,textsize);
                    editor.putFloat(VariablesDef.TEXT_SIZE_FOR_PERIOD,textsize1);

                    editor.commit();

                    defaultSharedPreferences.edit().putBoolean("initialize",false).apply();
                    defaultSharedPreferences.edit().putBoolean("CreateTable",true).apply();







                    setTable(height);

                }
            });

            builder.setCancelable(false);

            builder.create().show();



            //初めての時の処理
        }else{
            //二回目以降の処理
        }




    }
    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(defaultSharedPreferences.getBoolean("CreateTable",false)){
            setTable(prefer.getInt(VariablesDef.LAYOUT_HEIGHT,0));


        }

        if(defaultSharedPreferences.getBoolean("syllabus2017update",true)){
            System.out.println("test init called ");
            if (DB_updateClass.insert2017syllabus(this)){
                defaultSharedPreferences.edit().putBoolean("syllabus2017update",false).apply();
            }
        }
    }
    @Override
    protected void onStop(){
        super.onStop();


    }




    public class testClickL implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent it_add=new Intent(getApplicationContext(),add_data.class);
            Intent it_info=new Intent(getApplicationContext(),class_info.class);
            db_entity entity;

            String vId= view.getTag().toString();
            entity=DBm.db_table1_showbyId(vId);

            if(entity.getInitialize()==0){
                it_add.putExtra("id",vId);
                startActivityForResult(it_add,1);

            }
            else {
                int msy_id=entity.getSy_id();
                int cs_info_id=entity.getClass_info_id();
                it_info.putExtra("cs_info_id",cs_info_id);
                it_info.putExtra("msy_id",msy_id);
                startActivityForResult(it_info,1);
            }



        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            Intent it=new Intent(getApplicationContext(),SettingsForTable.class);
            startActivityForResult(it,REQUEST_CODE_FOR_SETTINGS_TABLE);




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_settings) {
            Intent it=new Intent(getApplicationContext(),SettingsForTable.class);
            startActivityForResult(it,REQUEST_CODE_FOR_SETTINGS_TABLE);

        }
        else if(id == R.id.nav_userPolicy){
            Intent it = new Intent(getApplication(), User_policy.class);
            startActivityForResult(it,REQUEST_CODE_FOR_SETTINGS_TABLE);
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void onActivityResult(int requestCode,int resultCode,Intent it){

        if(resultCode==RESULT_OK){
            db_entity entity;
            String id=String.valueOf(requestCode);
            setTable(prefer.getInt(VariablesDef.LAYOUT_HEIGHT,0));




        }

        else{

        }


    }





    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

    }



    //タイトル、ろん４のデータ等々読み込んで時間割に表示するロジック------------------------------------------------------------

    public void setTable(int height){

        int colorForWeekAndPeriodId = getResources().getColor(R.color.forWeekAndPeriod);
        db_entity entity;


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String tableInfo = prefs.getString(VariablesDef.CURRENT_TABLE_INFO, "");
        table_info_entity tableInfoEntity= gson.fromJson(tableInfo, table_info_entity.class);

        String tableSettings = prefs.getString(VariablesDef.CURRENT_TABLE_SETTINGS, "");
        table_settings_entity tableSettingsEntity= gson.fromJson(tableSettings, table_settings_entity.class);

        String tablename=tableInfoEntity.getTableName();
        String syllabusname=tableInfoEntity.getSyllabusName();
        DBm=new db_manipulator(db,tablename,syllabusname);
        setTitle("   "+tablename);


        LanguageSetting.setting(MainActivity.this,tableSettingsEntity.getSettingsJapanese());

        if(tableSettingsEntity.getTexslide()==true){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.animationtest);
            animation.setInterpolator(new LinearInterpolator());
            tvForBottom.startAnimation(animation);
            tvForBottom.setText("意見、感想、バグ報告等はfacebookかレビュー欄まで");
        }
        else{
            tvForBottom.clearAnimation();
            tvForBottom.setText("");
        }






        int heighttmp = Math.round(height/100);
        int weekTimes=0;
        int L4MTimes=0;
        int btTimes=0;
        int timesnum=0;
        int bottomMargin=0;

        int GoneNum=0;
        if(tableSettingsEntity.getPeriod6()==false){ GoneNum+=1;}
        if(tableSettingsEntity.getPeriod7()==false){GoneNum+=1;}
        if(tableSettingsEntity.getPeriod8()==false){GoneNum+=1;}

        if(GoneNum==0){
            weekTimes=5;
            L4MTimes=6;
            btTimes=11;
            timesnum=8;

        }
        if(GoneNum==1){
            weekTimes=6;
            L4MTimes=6;
            btTimes=12;
            timesnum=7;
            bottomMargin=0;
        }
        if(GoneNum==2){
            weekTimes=5;
            L4MTimes=6;
            btTimes=14;
            timesnum=6;
            bottomMargin=1;
        }
        if(GoneNum==3){
            weekTimes=5;
            L4MTimes=6;
            btTimes=17;
            timesnum=5;
            bottomMargin=1;
        }



        int weekHeight=heighttmp*weekTimes;
        int L4MHeight=heighttmp*L4MTimes;
        int btHeight=heighttmp*btTimes;

       // int L4Change=0;

       int remeinder=height-weekHeight-L4MHeight-btHeight*timesnum;
       // weekHeight=weekHeight+remeinder;
        bottomMargin=bottomMargin+remeinder;


        TextView rightMargin=(TextView)findViewById(R.id.rightMargin_tv);
        rightMargin.setHeight(height-bottomMargin);
        rightMargin.setVisibility(View.GONE);


        LinearLayout mainLL=(LinearLayout)findViewById(R.id.main_ll);
        mainLL.setMinimumHeight(height-bottomMargin);

        LinearLayout bottomLL=(LinearLayout)findViewById(R.id.bottom_ll);
        bottomLL.setMinimumHeight(bottomMargin);








        for(int i=0;i<weekList.length;i++){

            TextView tv=(TextView)findViewById(weekList[i]) ;
            tv.setHeight(weekHeight);

            if(i==0||i==6){
                if(i==0){
                    tv.setBackgroundResource(R.drawable.border_for_week);
                }
                else {
                    tv.setBackgroundResource(R.drawable.border_for_week2);
                }
            }
            else {
                GradientDrawable bgShape=new GradientDrawable();
                //  bgShape.setColor(Color.parseColor("#ffffff"));
                bgShape.setColor(colorForWeekAndPeriodId);
                bgShape.setStroke(1,getResources().getColor(R.color.transparent));

                tv.setBackground(bgShape);
            }

        }
        for(int i=0;i<L4MarginList.length;i++){
            GradientDrawable bgShape2=new GradientDrawable();
            TextView tvM=(TextView)findViewById(L4MarginList[i]);
            bgShape2.setColor(Color.parseColor("#a4a3a3"));
            bgShape2.setStroke(1,getResources().getColor(R.color.forStroke));
            tvM.setBackground(bgShape2);
            tvM.setHeight(L4MHeight);
        }


        TextView tvForTime=(TextView)findViewById(R.id.time_tv1);
        float textsize= prefer.getFloat(VariablesDef.TEXT_SIZE_FOR_TIME,0);
        float textsize1= prefer.getFloat(VariablesDef.TEXT_SIZE_FOR_PERIOD,0);

        FrameLayout flForL4=(FrameLayout)findViewById(R.id.frameLayout4);
        GradientDrawable bgShape2=new GradientDrawable();
        bgShape2.setColor(Color.parseColor("#a4a3a3"));
        bgShape2.setStroke(1,getResources().getColor(R.color.forStroke));
        flForL4.setMinimumHeight(L4MHeight);
        flForL4.setBackground(bgShape2);



        for(int i=0;i<periodLiist.length;i++){
            TextView tv1=(TextView)findViewById(periodLiist[i]);
            tv1.setTextSize(textsize1);

        }
        for(int i=0;i<frameLayoutList.length;i++){
            FrameLayout frame=(FrameLayout)findViewById(frameLayoutList[i]);
            frame.setMinimumHeight(btHeight);
            GradientDrawable bgShape3=new GradientDrawable();
            // bgShape.setColor(Color.parseColor("#ffffff"));
            bgShape3.setColor(colorForWeekAndPeriodId);
            bgShape3.setStroke(1,getResources().getColor(R.color.forStroke));
            frame.setBackground(bgShape3);
        }
        for(int i=0;i<timeTvList.length;i++){
            TextView timeTv=(TextView)findViewById(timeTvList[i]);
            timeTv.setTextSize(textsize-2);
        }








        ArrayList<String> exceptList=new ArrayList<String>();
        int fontsize=tableSettingsEntity.getFontsize();
        for(int i=0;i<btn.length;i++){
            btn[i] = (Button) findViewById(btnList[i]);
            btn[i].setOnClickListener(new testClickL());
            btn[i].setVisibility(View.VISIBLE);
            btn[i].setHeight(btHeight);
            btn[i].setTag(idList[i]);
            btn[i].setAllCaps(false);
            btn[i].setTextSize(fontsize);
        }

        setVisiblity(tableSettingsEntity);


        for(int i=0;i<btnList.length;i++){
            boolean judge=false;
            for (String tmp:exceptList){
                if(idList[i].equals(tmp)){
                    judge=true;
                }
            }
            if(judge==false) {
                entity = DBm.db_table1_showbyId(idList[i]);

                if (entity.getInitialize() == 0) {
                    btn[i].setText("");
                } else {                           //tableに授業ある時に、タイトルなど設定する

                    //こっから連続コマの時にぶちぬくロジック
                    int a = 1;
                    while (true) {
                        int nextId = Integer.valueOf(idList[i]) + 10 * a;
                        if(nextId==41||nextId==42||nextId==43||nextId==44||nextId==45||nextId==46){
                            break;
                        }
                        if(nextId<87) {
                            db_entity entity_next = DBm.db_table1_showbyId(String.valueOf(nextId));
                            if (entity_next.getInitialize() != 0 && entity_next.getClass_info_id() == entity.getClass_info_id()) {
                                exceptList.add(String.valueOf(nextId));
                                btn[i].setHeight((a+1)*btHeight);
                                int tmp=i+6*a;
                                btn[tmp].setVisibility(View.GONE);
                                a++;

                            } else {
                                break;
                            }
                        }
                        else {
                            break;
                        }
                    }     //ここまで

                    if (entity.getL4() == 1) {     //L4の時の処理
                        int tmp = Integer.parseInt(idList[i]) % 10;
                        TextView tvMargin = (TextView) findViewById(L4MarginList[tmp-1]);  //下一桁とってきて、対応するマージンのtextviewを指定する
                        int L4tmp=(heighttmp*3);
                        if(a==1) {                 //ろんふぉーから連チャンのコマかどうか見る

                            btn[i].setHeight(btHeight+heighttmp*3);
                            tvMargin.setHeight(L4tmp);

                         /*    btn[i].setHeight(btHeight+L4Change);
                            tvMargin.setHeight(L4Change);*/
                        }
                        else{

                            btn[i].setHeight((a+1)*btHeight+heighttmp*2);
                            tvMargin.setHeight(L4tmp);

                           /* btn[i].setHeight((a+1)*btHeight+L4Change);
                            tvMargin.setHeight(L4Change);*/
                        }

                    }
                    //  btn[i].setBackgroundColor(Color.parseColor(colorname));
                    btn[i].setText(entity.getTitle() + "\n" + entity.getClassroom());



                }
                String colorname=entity.getColor();
                GradientDrawable bgShape=new GradientDrawable();
                bgShape.setColor(Color.parseColor(colorname));
                bgShape.setStroke(1,getResources().getColor(R.color.forStroke));
                btn[i].setBackground(bgShape);

            }






        }


    }


    public void setVisiblity(table_settings_entity tableSettingsEntity) {
        LinearLayout ll_saturday=(LinearLayout)findViewById(R.id.saturday_ll);

        if (tableSettingsEntity.getSaturday() == false) {
            ll_saturday.setVisibility(View.GONE);
        } else {
            ll_saturday.setVisibility(View.VISIBLE);
        }

        if (tableSettingsEntity.getPeriod6() == false) {
            FrameLayout fl = (FrameLayout) findViewById(period6[0]);
            fl.setVisibility(View.GONE);
            for (int i = 30; i < 36; i++) {
               // Button btn = (Button) findViewById(period6[i]);
                btn[i].setVisibility(View.GONE);
            }
        } else {
            FrameLayout fl = (FrameLayout) findViewById(period6[0]);
            fl.setVisibility(View.VISIBLE);
            for (int i = 30; i < 36; i++) {
               // Button btn = (Button) findViewById(period6[i]);
                btn[i].setVisibility(View.VISIBLE);
            }
        }
        if (tableSettingsEntity.getPeriod7() == false) {
            FrameLayout fl = (FrameLayout) findViewById(period7[0]);
            fl.setVisibility(View.GONE);
            for (int i = 36; i < 42; i++) {
               // Button btn = (Button) findViewById(period7[i]);
                btn[i].setVisibility(View.GONE);
            }
        } else {
            FrameLayout fl = (FrameLayout) findViewById(period7[0]);
            fl.setVisibility(View.VISIBLE);
            for (int i = 36; i < 42; i++) {
               // Button btn = (Button) findViewById(period7[i]);
                btn[i].setVisibility(View.VISIBLE);
            }
        }
        if (tableSettingsEntity.getPeriod8() == false) {
            FrameLayout fl = (FrameLayout) findViewById(period8[0]);
            fl.setVisibility(View.GONE);
            for (int i = 42; i < 48; i++) {
              //  Button btn = (Button) findViewById(period8[i]);
                btn[i].setVisibility(View.GONE);
            }
        } else {
            FrameLayout fl = (FrameLayout) findViewById(period8[0]);
            fl.setVisibility(View.VISIBLE);
            for (int i = 42; i <48; i++) {
              //  Button btn = (Button) findViewById(period8[i]);
                btn[i].setVisibility(View.VISIBLE);
            }
        }
    }



}



