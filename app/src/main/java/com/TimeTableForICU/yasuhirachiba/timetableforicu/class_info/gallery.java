package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.ExpandableHeightGridView;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_pict;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulatorForClassInfo;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.openHelper;

import java.util.ArrayList;

public class gallery extends AppCompatActivity {

    openHelper helper;
    SQLiteDatabase db;

    db_manipulatorForClassInfo DBmcl;


    private int msy_id;

    private ArrayList<db_entity_pict> pictList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);
        ActionBar actionBar = getSupportActionBar();       //戻るボタン
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int color = R.color.forTitlebar;
        Drawable backgroundDrawable = getApplicationContext().getResources().getDrawable(color);
        actionBar.setBackgroundDrawable(backgroundDrawable);



        helper=new openHelper(getApplicationContext());
        db=helper.getWritableDatabase();
        DBmcl=new db_manipulatorForClassInfo(db);



        pictList= (ArrayList<db_entity_pict>) getIntent().getSerializableExtra("pictList");
        int date= getIntent().getIntExtra("date", -1);
        msy_id= getIntent().getIntExtra("msy_id", -1);



        final gridViewAdapter mgridViewAdapter=new gridViewAdapter(getApplicationContext(),R.layout.class_info_gridview_item,pictList,date,-1);
        mgridViewAdapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                class_info_ImageView_TagsEntity tagEntity=(class_info_ImageView_TagsEntity)view.getTag();

                if(position==-1){
                }
                else {

                    ArrayList<Image> images = new ArrayList<>();

                    for(db_entity_pict entity_tmp:pictList){
                        Image image=new Image();
                        image.setLarge(entity_tmp.getUri());
                        image.setName("test");
                        image.setTimestamp("test");

                        images.add(image);

                    }
                    Log.d("gridview R tagcheck",String.valueOf(position));


                    Bundle bundle = new Bundle();
                    bundle.putSerializable("images", images);
                    bundle.putInt("position",position);
                    try {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction ft=fragmentManager.beginTransaction();
                        SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                        newFragment.setArguments(bundle);
                        newFragment.show(ft, "slideshow");


                    } catch (ClassCastException e) {
                        Log.e("test", "Can't get fragment manager");
                    }
                }

            }
        });
        mgridViewAdapter.setLongClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                class_info_ImageView_TagsEntity tagtmp=(class_info_ImageView_TagsEntity)view.getTag();
                db_entity_pict entity_pict=DBmcl.db_cl_PictTable_showBy_id(msy_id,tagtmp.getPictId());

                getApplicationContext().getContentResolver().delete(Uri.parse(entity_pict.getUri()), null, null);
                DBmcl.db_cl_deletPictRecordById(msy_id,tagtmp.getPictId());



            }
        });

        ExpandableHeightGridView gridView =(ExpandableHeightGridView)findViewById(R.id.cl_info_gallery_gridView);
        gridView.setAdapter(mgridViewAdapter);






    }

    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
