package com.TimeTableForICU.yasuhirachiba.timetableforicu.add_data;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class anyalise_schedule_string {
    public static ArrayList<Integer> anyalise_schedule_string_cut(String schedule_string){
        ArrayList<Integer> list=new ArrayList<Integer>();


        //(1)StringTokenizerオブジェクトの生成
        StringTokenizer result = new StringTokenizer(schedule_string, " ");

        if (schedule_string == "0") {

        }
        else {
            while (result.hasMoreTokens()) {
                list.add(Integer.valueOf(result.nextToken()));
            }
        }
        return list;
    }

    public static ArrayList<String> anyalise_schedule_string_NumToInitial(String schedule_string){

        ArrayList<String> list=new ArrayList<String>();

        StringTokenizer result = new StringTokenizer(schedule_string, " ");
        String tmp,week="";

        if ((schedule_string.equals("0"))||(schedule_string.equals(""))) {

        }
        else {
            while (result.hasMoreTokens()) {
                tmp=result.nextToken();

                if((tmp.charAt(1))=='1'){week="Mon";}
                else if((tmp.charAt(1))=='2'){week="Tue";}
                else if((tmp.charAt(1))=='3'){week="Wed";}
                else if((tmp.charAt(1))=='4'){week="Thu";}
                else if((tmp.charAt(1))=='5'){week="Fri";}
                else if((tmp.charAt(1))=='6'){week="Sat";}
                if(tmp.charAt(0)=='9'){
                    list.add("*"+"4" + "/" + week);
                }
                else {
                    list.add(week+"/"+tmp.charAt(0));
                }
            }
        }
        return list;

    }
    public static String anyalise_schedule_IntToInitial(int schedule){
        String result="";
        int tmp1;
        int tmp2;
        String week="";
        String period="";

        tmp2=schedule/10;
        tmp1=schedule%10;




        if(tmp1==1){week="Mon";}
        else if(tmp1==2){week="Tue";}
        else if(tmp1==3){week="Wed";}
        else if(tmp1==4){week="Thu";}
        else if(tmp1==5){week="Fri";}
        else if(tmp1==6){week="Sat";}
        if(tmp2==9){
            result=week+"/"+"*"+"4";
        }
        else {
            result=week+"/"+tmp2;
        }




        return result;
    }


    public static String anyalise_schedule_IntListToString(ArrayList<Integer> scheduleList){


        String schedule_string="";
        for(int tmp:scheduleList){

            schedule_string=schedule_string+tmp+" ";
        }

        return  schedule_string;

    }


    public static int anyalise_schedule_WeekStringPeriodStringToInt(String week,String period){

        int schedule=0;

        if(week.equals("Mon")){
            schedule=1;
        }
        if(week.equals("Tue")){
            schedule=2;
        }
        if(week.equals("Wed")){
            schedule=3;
        }
        if(week.equals("Thu")){
            schedule=4;
        }
        if(week.equals("Fri")){
            schedule=5;
        }
        if(week.equals("Sat")){
            schedule=6;
        }


        if(period.equals("1")){
            schedule+=10;
        }
        if(period.equals("2")){
            schedule+=20;
        }
        if(period.equals("3")){
            schedule+=30;
        }
        if(period.equals("4")){
            schedule+=40;
        }
        if(period.equals("5")){
            schedule+=50;
        }
        if(period.equals("6")){
            schedule+=60;
        }
        if(period.equals("7")){
            schedule+=70;
        }
        if(period.equals("8")){
            schedule+=80;
        }
        if(period.equals("*4")){
            schedule+=90;
        }


        return schedule;
    }
}





