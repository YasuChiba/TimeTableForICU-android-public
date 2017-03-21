package com.TimeTableForICU.yasuhirachiba.timetableforicu.class_info;

public class Student {

    /** 名前 **/
    private String mName;

    /** 年齢 **/
    private int mAge;

    /** 性別 **/
    private String mGender;

    public Student(String name, int age, String gender) {
        mName = name;
        mAge = age;
        mGender = gender;
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    public String getGender() {
        return mGender;
    }
}
