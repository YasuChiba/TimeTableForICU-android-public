package com.TimeTableForICU.yasuhirachiba.timetableforicu.add_data;


import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info.ItemClickListener;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info.class_info_ImageView_TagsEntity;



import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/31.
 */
public class add_data_gridViewAdapter extends ArrayAdapter<add_data_schedules> {

    private int resourceId;
    private class_info_ImageView_TagsEntity tagEntity;
    private Context mContext;
    private ItemClickListener itemClickListener;
    private ItemClickListener itemLongClickListener;
    private int Date;
    private int CountDateOrder;
    private ArrayList<add_data_schedules> add_data_schedulesArrayList;



    public add_data_gridViewAdapter(Context context, int resource, ArrayList<add_data_schedules> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
       add_data_schedulesArrayList=objects;

    }


    public void setLongClickListener(ItemClickListener itemClickListener) {
        this.itemLongClickListener = itemClickListener;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceId, null);
        }



        final BootstrapButton button =new BootstrapButton(mContext);
        button.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
        button.setBootstrapSize(DefaultBootstrapSize.XL);
        button.setShowOutline(false);
        button.setTag(position);
        button.setText(add_data_schedulesArrayList.get(position).getBtnSchedule());
        button.setTextSize(13);


        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemLongClickListener.onClick(v,position);
                return false;
            }
        });




        return button;

    }


}


