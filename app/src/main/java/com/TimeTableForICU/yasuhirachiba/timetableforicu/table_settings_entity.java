package com.TimeTableForICU.yasuhirachiba.timetableforicu;

/**
 * Created by YasuhiraChiba on 16/08/31.
 */
public class table_settings_entity {

    private boolean syllabusJapanese=true;
    private boolean settingsJapanes=true;
    private boolean period6=true;
    private boolean period7=true;
    private boolean period8=false;

    private boolean saturday=true;

    private int fontsize=8;

    private boolean textslide=true;


    public void setPeriod6(boolean period6){
        this.period6=period6;
    }
    public void setPeriod7(boolean period7){
        this.period7=period7;
    }
    public void setPeriod8(boolean period8){
        this.period8=period8;
    }

    public void setSaturday(boolean saturday){
        this.saturday=saturday;
    }

    public void setSyllabusJapanese(boolean japanese){
        this.syllabusJapanese=japanese;
    }
    public void setSettingsJapanese(boolean japanese){
        this.settingsJapanes=japanese;
    }

    public void setFontsize(int fontsize){
        this.fontsize=fontsize;
    }

    public void setTextslide(boolean slide){
        this.textslide=slide;
    }

    public boolean getPeriod6(){
        return period6;
    }
    public boolean getPeriod7(){
        return period7;
    }
    public boolean getPeriod8(){
        return period8;
    }

    public boolean getSaturday(){
        return saturday;
    }

    public boolean getSyllabusJapanese(){
        return syllabusJapanese;
    }

    public boolean getSettingsJapanese(){
        return settingsJapanes;
    }

    public int getFontsize(){
        return fontsize;
    }
    public boolean getTexslide(){
        return textslide;
    }
}
