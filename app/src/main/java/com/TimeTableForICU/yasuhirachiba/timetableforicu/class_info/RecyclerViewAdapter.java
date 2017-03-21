package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.ExpandableHeightGridView;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_MemoPictTable;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_memo;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_pict;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_manipulatorForClassInfo;

import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private  ArrayList<db_entity_MemoPictTable> mMemoPictTableList;
    private Context mContext;

    private ItemClickListener LongclickListener;
    private ItemClickListener clickListenerForTv;
    private ItemClickListener clickListenerForBtn;

    private ItemClickListener startCamerListner;

    private itemClickListenerForGallery clickLForGallery;




    private int recyclerviewPosition=0;
    private int msy_id;

    db_manipulatorForClassInfo DBmcl;


    public RecyclerViewAdapter(Context context, ArrayList<db_entity_MemoPictTable> dataList, db_manipulatorForClassInfo DBmcl,int msy_id) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mMemoPictTableList = dataList;
        this.DBmcl=DBmcl;
        this.msy_id=msy_id;
    }

    public void setClickListenerForTV(ItemClickListener itemClickListener) {
        this.clickListenerForTv = itemClickListener;
    }
    public void setOnLongClickListner(ItemClickListener itemClickListener) {
        this.LongclickListener = itemClickListener;
    }
    public void setStartCamera(ItemClickListener itemClickListener){
        this.startCamerListner=itemClickListener;
    }
    public void setClickLForGallery(itemClickListenerForGallery itemClickListenerForGallery){
        this.clickLForGallery=itemClickListenerForGallery;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.class_info_memo_cardview, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        final int date = mMemoPictTableList.get(position).getDate();
        recyclerviewPosition=position;

        ArrayList<db_entity_memo> memoList=mMemoPictTableList.get(position).getMemoList();

        for(db_entity_memo memotmp:memoList){
            TextView tv=new TextView(mContext);
            tv.setClickable(true);
            tv.setTag(memotmp.getId());
            tv.setMinLines(3);
            tv.setHint(R.string.recyclerViewAdapter_MemoPressLongTodelete);
            tv.setBackgroundResource(R.drawable.border);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListenerForTv.onClick(v, 1);
                }
            });
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    LongclickListener.onClick(v,1);
                    return false;
                }
            });

            tv.setText(memotmp.getMemo());
            holder.memo_ll.addView(tv);
        }






        //------------------------------------grid view------------------------------------------------------------



        final ArrayList<db_entity_pict> pictList=mMemoPictTableList.get(position).getPictList();
        ArrayList<db_entity_pict> picttmplist=new ArrayList<>();

        int i=0;
        holder.btnForShowMorePict.setVisibility(View.GONE);
        if(pictList.size()>5) {
            for (db_entity_pict picttmp : pictList) {
                if (i < 5) {
                   picttmplist.add(picttmp);
                    i++;
                } else {
                    holder.btnForShowMorePict.setVisibility(View.VISIBLE);
                    break;
                }

            }
        }
        else {
            for(db_entity_pict picttmp:pictList){
                picttmplist.add(picttmp);

            }
        }
        db_entity_pict picttmp =new db_entity_pict();
        picttmp.setUri("end");
        picttmplist.add(picttmp);


        final gridViewAdapter mgridViewAdapter=new gridViewAdapter(mContext,R.layout.class_info_gridview_item,picttmplist,date,position);

        mgridViewAdapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                class_info_ImageView_TagsEntity tagEntity=(class_info_ImageView_TagsEntity)view.getTag();

                if(position==-1){
                    startCamerListner.onClick(view,1);

                }
                else {

                    ArrayList<Image> images = new ArrayList<>();
                    ArrayList<db_entity_pict> entity_pictList=mMemoPictTableList.get(tagEntity.getCountDateOrder()).getPictList();

                    for(db_entity_pict entity_tmp:entity_pictList){
                        Image image=new Image();
                        image.setLarge(entity_tmp.getUri());
                        image.setName("");
                        image.setTimestamp("");

                        images.add(image);

                    }
                    Log.d("gridview R tagcheck",String.valueOf(position));


                    Bundle bundle = new Bundle();
                    bundle.putSerializable("images", images);
                    bundle.putInt("position",position);
                    try {
                        FragmentManager fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
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
                try{
                    mContext.getContentResolver().delete(Uri.parse(entity_pict.getUri()),null,null);
                    DBmcl.db_cl_deletPictRecordById(msy_id,tagtmp.getPictId());

                }
                catch (Exception e1){
                    DBmcl.db_cl_deletPictRecordById(msy_id,tagtmp.getPictId());

                }






            }
        });



        holder.pict_gridView.setAdapter(mgridViewAdapter);






        holder.tvForDate.setText(changeDateFormat(String.valueOf(date)));

        holder.btnForShowMorePict.setTag(position);


        holder.btnForShowMorePict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickLForGallery.onClick(v,pictList,date,msy_id);

            }
        });










    }


    @Override
    public int getItemCount() {
        return mMemoPictTableList.size();
    }

    public void remove(int position) {
        mMemoPictTableList.remove(position);
        notifyItemRemoved(position);
    }










    static class ViewHolder extends RecyclerView.ViewHolder {
        BootstrapButton btnForShowMorePict;
        TextView memo;
        TextView tvForDate;
        CardView cardView;
        RecyclerView    recyclerView;
        LinearLayout memo_ll;
        ExpandableHeightGridView pict_gridView;

        public ViewHolder(View v) {
            super(v);
            btnForShowMorePict=(BootstrapButton)v.findViewById(R.id.cl_info_memoRow_showMorePict_btn);
            cardView = (CardView) v.findViewById(R.id.cl_info_memo_cardView);
            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
            memo_ll=(LinearLayout)v.findViewById(R.id.cl_info_memo_ll);
            pict_gridView=(ExpandableHeightGridView) v.findViewById(R.id.cl_info_pict_gridView);
            tvForDate=(TextView)v.findViewById(R.id.cl_info_memoRow_tvForDate);

        }
    }



    public String changeDateFormat(String date){
        String result="";

        try{
            if(date.charAt(4)=='0'){
                result=result+date.charAt(5)+"/";
            }
            else{
                result=result+date.charAt(4)+date.charAt(5)+"/";
            }
            if(date.charAt(6)=='0'){
                result=result+date.charAt(7);
            }
            else {
                result =result+date.charAt(6)+date.charAt(7);
            }
            return result;
        }catch (StringIndexOutOfBoundsException e) {
            return "2000";
        }
    }






}


