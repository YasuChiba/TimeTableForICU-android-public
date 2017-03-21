package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.VariablesDef;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.add_data.anyalise_schedule_string;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.MemoPictTableComparator;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_MemoPictTable;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_memo;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_mysy;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_pict;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulator;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulatorForClassInfo;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.openHelper;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.modify_data.modify_data;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.table_info_entity;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class class_info extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    RecyclerView mRecyclerView;
    boolean loading=true;

    openHelper helper;
    SQLiteDatabase db;
    db_manipulator DBm;
    db_manipulatorForClassInfo DBmcl;
    int msy_id;
    int cs_info_id;

    db_entity_mysy entity_mysy;
    db_entity_MemoPictTable entity_Pict;

    String schedule_string;
    String date="123";
    ArrayList<db_entity_MemoPictTable> entity_memoPictTableList;
    ArrayList<db_entity_MemoPictTable> memopictTmpList;

    private Uri m_uri;
    private static final int REQUEST_CHOOSER = 1000;
    private int CameraDate =-1;

    private static final int REQUEST_GALLERY_UPPER_KITKAT=51;
    private static final int REQUEST_GALLERY_UNDER_KITKAT=50;

    private static final int REQUEST_myPHOTO_GALLERY=52;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final com.getbase.floatingactionbutton.FloatingActionButton action_Photo = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_Photo);
        action_Photo.setOnClickListener(new FabClickL());

        final com.getbase.floatingactionbutton.FloatingActionButton action_Memo = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_Memo);
        action_Memo.setOnClickListener(new FabClickL());

        final com.getbase.floatingactionbutton.FloatingActionButton action_Gallery = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.action_Gallery);
        action_Gallery.setOnClickListener(new FabClickL());



        ActionBar actionBar = getSupportActionBar();       //戻るボタン
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        int color = R.color.forTitlebar;
        Drawable backgroundDrawable = getApplicationContext().getResources().getDrawable(color);
        actionBar.setBackgroundDrawable(backgroundDrawable);





        helper=new openHelper(this);
        db=helper.getWritableDatabase();

        DBmcl=new db_manipulatorForClassInfo(db);





    }


    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
// 保存されているjson文字列を取得
        String tableSetting = prefs.getString(VariablesDef.CURRENT_TABLE_INFO, "");
// json文字列を 「UserSettingクラス」のインスタンスに変換
        table_info_entity tableSettingsEntity= gson.fromJson(tableSetting, table_info_entity.class);

        String tablename=tableSettingsEntity.getTableName();
        String syllabusname=tableSettingsEntity.getSyllabusName();

        DBm=new db_manipulator(db,tablename,syllabusname);

        Intent i = getIntent();
        msy_id = i.getIntExtra("msy_id", -1);
        cs_info_id=i.getIntExtra("cs_info_id",-1);

        entity_mysy=DBm.my_sy_showById(String.valueOf(msy_id));
        schedule_string=entity_mysy.getSchedule_String();

        TextView tv=(TextView)findViewById(R.id.csInfo_toolbar);
        tv.setText(entity_mysy.getTitle());

        BootstrapButton btShow=(BootstrapButton)findViewById(R.id.cl_info_memo_ShowBt);
       // btShow.setOnClickListener(new OnClickL());
        btShow.setVisibility(View.INVISIBLE);

        BootstrapButton btAdd=(BootstrapButton)findViewById(R.id.cl_info_memo_AddBt);
        btAdd.setOnClickListener(new OnClickL());


        setLayouts();



    }




    public void setLayouts() {
        entity_memoPictTableList =DBmcl.db_cl_MemoPictTable_showByMySyllabusId(msy_id);

        Collections.sort(entity_memoPictTableList, new MemoPictTableComparator());





        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        final RecyclerViewAdapter mAdapter=new RecyclerViewAdapter(this,entity_memoPictTableList,DBmcl,msy_id);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListenerForTV(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                editMemo((int)view.getTag());

            }
        });
        mAdapter.setOnLongClickListner(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                deleteMemo((int)view.getTag());
            }
        });

        mAdapter.setStartCamera(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                class_info_ImageView_TagsEntity tag=(class_info_ImageView_TagsEntity)view.getTag();

                launchChooser(tag.getDate());
                //startCamera(tag.getDate());
            }
        });

        mAdapter.setClickLForGallery(new itemClickListenerForGallery() {
            @Override
            public void onClick(View view, ArrayList<db_entity_pict> entity_pictList, int date, int msy_id) {
                Intent intent = new Intent(class_info.this, gallery.class);

                intent.putExtra("pictList",entity_pictList);
                intent.putExtra("date",date);
                intent.putExtra("msy_id",msy_id);

                intent.setAction(Intent.ACTION_VIEW);
                startActivityForResult(intent,REQUEST_myPHOTO_GALLERY);

            }
        });












        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                // 横にスワイプされたら要素を消す
                final int swipedPosition = viewHolder.getAdapterPosition();
                final int date=entity_memoPictTableList.get(swipedPosition).getDate();


                new AlertDialog.Builder(class_info.this)
                        .setTitle("")
                        .setMessage(R.string.class_info_deleteCardviewOrNot)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete_methods.delete_DataMemoPict_byDate(msy_id,date,DBmcl,getApplicationContext());

                                RecyclerViewAdapter adapter = (RecyclerViewAdapter) mRecyclerView.getAdapter();
                                adapter.remove(swipedPosition);
                                setLayouts();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setLayouts();
                            }
                        })
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                setLayouts();
                            }
                        })
                        .show();



            }



        };
        (new ItemTouchHelper(callback)).attachToRecyclerView(mRecyclerView);




        

    }





    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.class_info, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i = getIntent();
        final int msy_id = i.getIntExtra("msy_id",-1);
        //noinspection SimplifiableIfStatement
        if (id == R.id.modify) {
            Intent it_add=new Intent(class_info.this,modify_data.class);
            it_add.putExtra("msy_id",msy_id);
            it_add.putExtra("cs_info_id",cs_info_id);
            startActivityForResult(it_add,1);
            return true;
        }
        if(id==R.id.delete){
            new AlertDialog.Builder(class_info.this)
                    .setTitle("")
                    .setMessage(R.string.class_info_deleteClassOrNot )
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ArrayList<Integer> schedule_list= anyalise_schedule_string.anyalise_schedule_string_cut(schedule_string);
                            for(int k=0;k<schedule_list.size();k++){
                                int tmp1=schedule_list.get(k)/10;
                                if(tmp1==9){                                        //ろんふぉーの時、table1の４０番第に入れるための処理。剰余つかって判定。9が来たら４に変える
                                    schedule_list.set(k,40+schedule_list.get(k)%10);
                                }
                                DBm.db_table1_initialize(String.valueOf(schedule_list.get(k)));
                            }

                            delete_methods.delete_Class_byMsyIdClassInfoId(msy_id,cs_info_id,DBm,DBmcl,getApplicationContext());


                            Intent it=new Intent();
                            setResult(RESULT_OK,it);
                            db.close();
                            finish();

                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();


        }


        if (id == android.R.id.home) {
            Intent it=new Intent();
            setResult(RESULT_OK,it);
            db.close();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }




    private class FabClickL implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case(R.id.action_Memo):
                    addMemo(-1);

                    break;
                case(R.id.action_Photo):
                    startCamera(-1);

                    break;
                case(R.id.action_Gallery):
                    startDefaultGallery(-1);

            }

        }
    }
    private class OnClickL implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.cl_info_memo_ShowBt:
                    setLayouts();
                    break;


                case R.id.cl_info_memo_AddBt:


                    final Calendar calendar = Calendar.getInstance();    //デフォルトのdatepickerがバグってるのでhttp://stackoverflow.com/questions/11444238/jelly-bean-datepickerdialog-is-there-a-way-to-cancel　こっからもらった
                    int year  = calendar.get(Calendar.YEAR);
                    final int month = calendar.get(Calendar.MONTH);
                    int day   = calendar.get(Calendar.DAY_OF_MONTH);


                    Bundle b = new Bundle();
                    b.putInt(DatePickerDialogFragment.YEAR, year);
                    b.putInt(DatePickerDialogFragment.MONTH, month);
                    b.putInt(DatePickerDialogFragment.DATE,day);
                    DialogFragment picker = new DatePickerDialogFragment();
                    picker.setArguments(b);
                    picker.show(class_info.this.getSupportFragmentManager(), "fragment_date_picker");



                    break;



            }


        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String strDate = format.format(calendar.getTime());

        int DateExistence=DBmcl.db_cl_DataTableCheckRecordExistence(msy_id,Integer.valueOf(strDate));
        if(DateExistence==0){
            DBmcl.db_cl_insertDataTable(msy_id,Integer.valueOf(strDate));
        }

        DBmcl.db_cl_insertMemoTable(msy_id,Integer.valueOf(strDate),"");
        setLayouts();


    }





    public void addMemo(int date){

        String d;
        if(date==-1){
            Date Date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            d=sdf.format(Date);
        }
        else {
            d=String.valueOf(date);
        }
        final String da=d;

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.class_info_memo_input_dialog,
                (ViewGroup)findViewById(R.id.cl_info_memo_input_dialog_layout));


       /* TextView tv = (TextView) layout.findViewById(R.id.cl_info_memo_input_dialog_tvForDate);
        tv.setText(date);*/

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Memo   "+d);
        builder.setView(layout);

        builder.setPositiveButton("ok",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                EditText et = (EditText)layout.findViewById(R.id.cl_info_memo_input_dialog_et);

                int DateExistence=DBmcl.db_cl_DataTableCheckRecordExistence(msy_id,Integer.valueOf(da));
                if(DateExistence==0){
                    DBmcl.db_cl_insertDataTable(msy_id,Integer.valueOf(da));
                }

                DBmcl.db_cl_insertMemoTable(msy_id,Integer.valueOf(da),et.getText().toString());
                setLayouts();

            }
        });

        builder.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
            }
        });


        builder.create().show();



    }


    public void editMemo(int id){


        final int Id=id;

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.class_info_memo_input_dialog,
                (ViewGroup)findViewById(R.id.cl_info_memo_input_dialog_layout));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Memo");

        EditText et = (EditText)layout.findViewById(R.id.cl_info_memo_input_dialog_et);
        db_entity_memo entity_memo=DBmcl.db_cl_getMemoById(msy_id,id);
        final int Date=entity_memo.getDate();
        et.setText(entity_memo.getMemo());
        builder.setView(layout);

        builder.setPositiveButton("ok",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                EditText et = (EditText)layout.findViewById(R.id.cl_info_memo_input_dialog_et);

                int DateExistence=DBmcl.db_cl_DataTableCheckRecordExistence(msy_id,Date);
                if(DateExistence==0){
                    DBmcl.db_cl_insertDataTable(msy_id,Integer.valueOf(Date));
                }
                DBmcl.db_cl_updateMemoTable(msy_id,Id,Date,et.getText().toString());
                setLayouts();

            }
        });

        builder.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
            }
        });


        builder.create().show();


    }
    public void deleteMemo(int id){

        DBmcl.db_cl_deletMemoRecordById(msy_id,id);
        setLayouts();

    }





    private void startCamera(int date) {
        CameraDate =date;    //-1の時は写真をとった時の日なのでそれを取得する
        if(CameraDate==-1){
            Date da = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String d=sdf.format(da);
            CameraDate=Integer.valueOf(d);
        }

        //カメラの起動Intentの用意
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        m_uri = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        startActivityForResult(intentCamera, REQUEST_CHOOSER);
    }

    private void startDefaultGallery(int date){
        CameraDate =date;
        if(CameraDate==-1){
            Date da = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String d=sdf.format(da);
            CameraDate=Integer.valueOf(d);
        }

        Intent intentGallery;

        if (Build.VERSION.SDK_INT < 19) {
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
            startActivityForResult(Intent.createChooser(intentGallery, "Pick a source"), REQUEST_GALLERY_UNDER_KITKAT);

           // startActivityForResult(intentGallery, REQUEST_CHOOSER);
        } else {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
            startActivityForResult(Intent.createChooser(intentGallery, "Pick a source"), REQUEST_GALLERY_UPPER_KITKAT);

        }



    }




    public void launchChooser(int date) {
        CameraDate =date;
        if(CameraDate==-1){
            Date da = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String d=sdf.format(da);
            CameraDate=Integer.valueOf(d);
        }
        // ギャラリーから選択
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.addCategory(Intent.CATEGORY_OPENABLE);

        // カメラで撮影
        String filename = System.currentTimeMillis() + ".jpg";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, filename);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        m_uri= getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent i2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i2.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        // ギャラリー選択のIntentでcreateChooser()
        Intent chooserIntent = Intent.createChooser(i, "Pick Image");
        // EXTRA_INITIAL_INTENTS にカメラ撮影のIntentを追加
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { i2 });

        startActivityForResult(chooserIntent, REQUEST_CHOOSER);
    }












    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) {
            // キャンセル時
            return ;
        }

        if(requestCode == REQUEST_CHOOSER||requestCode==REQUEST_GALLERY_UNDER_KITKAT||requestCode==REQUEST_GALLERY_UPPER_KITKAT) {


            Uri resultUri = (data != null ? data.getData() : m_uri);

            if(resultUri == null) {
                // 取得失敗
                return;
            }

            // ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );


            int DateExistence=DBmcl.db_cl_DataTableCheckRecordExistence(msy_id,Integer.valueOf(CameraDate));
            if(DateExistence==0){
                DBmcl.db_cl_insertDataTable(msy_id,Integer.valueOf(CameraDate));
            }

            DBmcl.db_cl_insertPictTable(msy_id,Integer.valueOf(CameraDate), String.valueOf(resultUri),"testTag1_4",null,null,null,null);


            setLayouts();
        }
        else {
            setLayouts();
        }

    }
}










