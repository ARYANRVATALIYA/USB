package com.example.unistudentbridge;

import android.app.Activity;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

class StudentListView extends BaseAdapter {

    Activity activity;
    String dep;
    String sem;


    public StudentListView(Activity activity, String dep, String sem){
        this.activity=activity;
        this.dep=dep;
        this.sem=sem;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(activity);
        View v = lf.inflate(R.layout.student_attendance_list,null);
        Cursor cursor;
        cursor=StudentAttendanceList.db.getAttendance(dep,sem);
        return v;
    }
}