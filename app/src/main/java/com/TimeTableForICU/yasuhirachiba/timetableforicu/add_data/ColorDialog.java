package com.TimeTableForICU.yasuhirachiba.timetableforicu.add_data;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.TimeTableForICU.yasuhirachiba.timetableforicu.R;

import java.util.ArrayList;

/**
 * Created by YasuhiraChiba on 16/08/23.
 */
public class ColorDialog extends DialogFragment {
    int ColorBtnId[]={R.id.c1,R.id.c2,R.id.c3,R.id.c4,R.id.c5,R.id.c6,R.id.c7,R.id.c8,R.id.c9,R.id.c10,R.id.c11,R.id.c12,R.id.c13,R.id.c14,R.id.c15,R.id.c16};
    ArrayList<Button> ColorBtnList=new ArrayList<Button>();
    TextView tvM1,tvM2,tvMT1,tvMT2,tvMT3,tvMT4,tvMB1,tvMB2,tvMB3,tvMB4;
    private View.OnClickListener ButtonClickListener = null;
    String colorName="";

    Dialog dialog;
    Long mInput = 0L;


    public static ColorDialog newInstance() {
        ColorDialog fragment = new ColorDialog();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //XMLとの紐付け
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.color_choice, null, false);


        //ダイアログの作成
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(view);

        for(int i=0;i<ColorBtnId.length;i++){
            Button bt=(Button)view.findViewById(ColorBtnId[i]);
            bt.setOnClickListener(ButtonClickListener);
            ColorBtnList.add(bt);
        }
        tvM1=(TextView)view.findViewById(R.id.color_Margin1);
        tvM2=(TextView)view.findViewById(R.id.color_Margin2);
        tvMB1=(TextView)view.findViewById(R.id.color_MarginBottom1);
        tvMB2=(TextView)view.findViewById(R.id.color_MarginBottom2);
        tvMB3=(TextView)view.findViewById(R.id.color_MarginBottom3);
        tvMB4=(TextView)view.findViewById(R.id.color_MarginBottom4);
        tvMT1=(TextView)view.findViewById(R.id.color_MarginTop1);
        tvMT2=(TextView)view.findViewById(R.id.color_MarginTop2);
        tvMT3=(TextView)view.findViewById(R.id.color_MarginTop3);
        tvMT4=(TextView)view.findViewById(R.id.color_MarginTop4);

/*

        Button bt1=(Button)view.findViewById(R.id.color_1);
        Button bt2=(Button)view.findViewById(R.id.color_2);
        Button bt3=(Button)view.findViewById(R.id.color_3);
        Button bt4=(Button)view.findViewById(R.id.color_4);

        bt1.setOnClickListener(ButtonClickListener);
        bt2.setOnClickListener( ButtonClickListener );
        bt3.setOnClickListener( ButtonClickListener );
        bt4.setOnClickListener( ButtonClickListener );
*/

        return dialog;
    }


    public void setOnButtonClickListener(View.OnClickListener listener) {
        this.ButtonClickListener = listener;
    }




    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width=(int)(metrics.widthPixels*0.8);
        int height=(int)(metrics.heightPixels*0.5);
        lp.width =width;
        lp.height = width;

        dialog.getWindow().setAttributes(lp);


        for(int i=0;i<ColorBtnId.length;i++){

            ColorBtnList.get(i).setHeight(width/5);
            ColorBtnList.get(i).setWidth(width/5);

        }
        int remeinderW=width-4*width/5;
        int remeinderH=width-4*width/5;

        tvM1.setWidth(remeinderW/2);
        tvM2.setWidth(remeinderW/2);

        tvMB1.setHeight(remeinderH/2);
        tvMB2.setHeight(remeinderH/2);
        tvMB3.setHeight(remeinderH/2);
        tvMB4.setHeight(remeinderH/2);

        tvMT1.setHeight(remeinderH/2);
        tvMT2.setHeight(remeinderH/2);
        tvMT3.setHeight(remeinderH/2);
        tvMT4.setHeight(remeinderH/2);



    }




    /*private class ClickL implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            colorName=(String)view.getTag();
            dialog.dismiss();
        }
    }*/

}

