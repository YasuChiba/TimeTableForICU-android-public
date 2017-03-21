package com.TimeTableForICU.yasuhirachiba.timetableforicu.modify_data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;
import com.TimeTableForICU.yasuhirachiba.timetableforicu.database.db_entity_forAddDataModifyData_ListView;

import java.util.List;

/**
 * Created by YasuhiraChiba on 16/09/02.
 */
public class advancedSettings_listviewAdapter extends ArrayAdapter<db_entity_forAddDataModifyData_ListView> {


    Context context;
    LayoutInflater layoutInflater = null;




    public advancedSettings_listviewAdapter(Context context, int resource, List<db_entity_forAddDataModifyData_ListView> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }






    @Override
    public int getCount() {
        return 0;
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.add_data_more_settings_row,parent,false);
        }


        ((BootstrapButton)convertView.findViewById(R.id.more_button)).setText(getItem(position).getSchedule_String());

        ((TextView)convertView.findViewById(R.id.more_et1)).setText(getItem(position).getJtitle());

        ((TextView)convertView.findViewById(R.id.more_et2)).setText(getItem(position).getClassroom());


        return convertView;
*/

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.add_data_more_settings_row,null);
            holder.btn=(BootstrapButton)convertView.findViewById(R.id.more_button);
            holder.etForClass =(EditText) convertView.findViewById(R.id.more_et2);
            holder.etForTitle =(EditText) convertView.findViewById(R.id.more_et1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Fill EditText with the value you have in data source
        holder.btn.setText(getItem(position).getFormattedSchedule());
        holder.etForTitle.setText(getItem(position).getTitle());
        holder.etForClass.setText(getItem(position).getClassroom());

        holder.btn.setTag(position);
        holder.etForClass.setTag(position);
        holder.etForTitle.setTag(position);

       holder.etForTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (!hasFocus){
                   final int position = (int)v.getTag();
                   final EditText et = (EditText) v;
                   getItem(position).setTitle(et.getText().toString());
               }
           }
       });
        holder.etForClass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    final int position = (int)v.getTag();
                    final EditText et = (EditText) v;
                    getItem(position).setClassroom(et.getText().toString());
                }
            }
        });




        return convertView;



    }


    private class ViewHolder{

        BootstrapButton btn;
        EditText etForTitle;
        EditText etForClass;

    }


}
