package com.TimeTableForICU.yasuhirachiba.timetableforicu.add_data;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class add_data_schedules {

    int number;
    int schedule;
    LinearLayout ll;
    LinearLayout ll_more;
    LinearLayout ll_rowOfDateButton;
    BootstrapButton btn;
    TextView tvMargin;

    String btnSchedule;



    public add_data_schedules(int number,int schedule,LinearLayout ll,LinearLayout ll_more,LinearLayout ll_rowOfDateButton,BootstrapButton btn,TextView tvMargin){
        this.schedule=schedule;
        this.ll=ll;
        this.ll_more=ll_more;
        this.btn=btn;
        this.tvMargin=tvMargin;
        this.ll_rowOfDateButton=ll_rowOfDateButton;
    }


    public add_data_schedules(){

    }

    public void setNumber(int number){
        this.number=number;
    }
    public int getNumber(){
        return number;
    }
    public void setSchedule(int schedule){
        this.schedule=schedule;
    }

    public int getSchedule(){
        return schedule;
    }
    public void setLL(LinearLayout ll){
        this.ll=ll;
    }
    public LinearLayout getLL(){
        return ll;
    }
    public void setLL_more(LinearLayout ll_more){
        this.ll_more=ll_more;
    }
    public LinearLayout getLL_more(){
        return ll_more;
    }

    public void setBtn(BootstrapButton btn){
        this.btn=btn;
    }
    public BootstrapButton getBtn(){
        return btn;
    }

    public void setTvMargin(TextView tvMargin){
        this.tvMargin=tvMargin;
    }

    public TextView getTvMargin(){
        return tvMargin;
    }

    public void setLl_rowOfDateButton(LinearLayout ll_rowOfDateButton){
        this.ll_rowOfDateButton=ll_rowOfDateButton;
    }

    public LinearLayout getll_rowOfDateButton(){
        return ll_rowOfDateButton;
    }

    public void setBtnSchedule(String schedule){
        this.btnSchedule=schedule;
    }
    public String getBtnSchedule(){
        return btnSchedule;
    }
}
