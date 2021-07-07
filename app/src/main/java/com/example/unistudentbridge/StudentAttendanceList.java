package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class StudentAttendanceList extends AppCompatActivity {

    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};
    String Departments_Array[] = new String[]{"CE", "CSE", "IT"};
    Spinner DS,SS;
    EditText IDstudent;
    ArrayAdapter adp;
    public static DATA_Base db;
    DecimalFormat df= new DecimalFormat("###.##");
    Cursor c;
    TextView t1,t2,t3,t4,tv1,tv2,tv3,tv4;
    Button SB;
    String sem;
    String dep;
    String id;
    int sub1P,sub1A;
    int sub2P,sub2A;
    int sub3P,sub3A;
    int flag=0;
    float sub1a,sub2a,sub3a,tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Overall Attendance");
        setContentView(R.layout.activity_student_attendance_list);
        db = new DATA_Base(this);

        DS= findViewById(R.id.deptSpinner);
        SS= findViewById(R.id.semSpinner);
        IDstudent= findViewById(R.id.editText1);
        t1=findViewById(R.id.sub1);
        t2=findViewById(R.id.sub2);
        t3=findViewById(R.id.sub3);
        t4=findViewById(R.id.sub4);
        tv1=findViewById(R.id.sub1T);
        tv2=findViewById(R.id.sub2T);
        tv3=findViewById(R.id.sub3T);
        tv4=findViewById(R.id.total);
        SB= findViewById(R.id.searchButton);

        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Departments_Array);
        DS.setAdapter(adp);
        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Sem_Array);
        SS.setAdapter(adp);
        DS.setSelection(0);
        SS.setSelection(0);

        SB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IDstudent.getText().toString().isEmpty())
                    Toast.makeText(StudentAttendanceList.this,"Enter ID",Toast.LENGTH_LONG).show();
                else{
                    sem=SS.getSelectedItem().toString();
                    dep=DS.getSelectedItem().toString();
                    id=IDstudent.getText().toString();
                    c = db.getStudentCursor(id,dep,sem);
                    if (c.getCount()==0)
                        Toast.makeText(StudentAttendanceList.this,"Student ID not found",Toast.LENGTH_LONG).show();
                    else{
                        switch (sem){
                            case "one":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"1sub1");
                                sub1A = db.getAbscentCount(c,"1sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("1sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else{
                                    flag++;
                                    sub1a = ((float)sub1P/(float)(sub1P+sub1A))*100;
                                    t1.setText("1sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    if (sub1a<75){
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a)+"%");
                                    }
                                    else{
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a)+"%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"1sub2");
                                sub2A = db.getAbscentCount(c,"1sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("1sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("1sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"1sub3");
                                sub3A = db.getAbscentCount(c,"1sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("1sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("1sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                            case "two":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"2sub1");
                                sub1A = db.getAbscentCount(c,"2sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("2sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub1a = ((float) sub1P / (float) (sub1P + sub1A)) * 100;
                                    t1.setText("2sub1 : " + sub1P + "/" + (sub1P + sub1A));
                                    if (sub1a < 75) {
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a) + "%");
                                    } else {
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a) + "%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"2sub2");
                                sub2A = db.getAbscentCount(c,"2sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("2sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("2sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"2sub3");
                                sub3A = db.getAbscentCount(c,"2sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("2sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("2sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                            case "three":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"3sub1");
                                sub1A = db.getAbscentCount(c,"3sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("3sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub1a = ((float) sub1P / (float) (sub1P + sub1A)) * 100;
                                    t1.setText("3sub1 : " + sub1P + "/" + (sub1P + sub1A));
                                    if (sub1a < 75) {
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a) + "%");
                                    } else {
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a) + "%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"3sub2");
                                sub2A = db.getAbscentCount(c,"3sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("3sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("3sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"3sub3");
                                sub3A = db.getAbscentCount(c,"3sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("3sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("3sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                            case "four":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"4sub1");
                                sub1A = db.getAbscentCount(c,"4sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("4sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub1a = ((float) sub1P / (float) (sub1P + sub1A)) * 100;
                                    t1.setText("4sub1 : " + sub1P + "/" + (sub1P + sub1A));
                                    if (sub1a < 75) {
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a) + "%");
                                    } else {
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a) + "%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"4sub2");
                                sub2A = db.getAbscentCount(c,"4sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("4sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("4sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"4sub3");
                                sub3A = db.getAbscentCount(c,"4sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("4sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("4sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                            case "five":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"5sub1");
                                sub1A = db.getAbscentCount(c,"5sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("5sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub1a = ((float) sub1P / (float) (sub1P + sub1A)) * 100;
                                    t1.setText("5sub1 : " + sub1P + "/" + (sub1P + sub1A));
                                    if (sub1a < 75) {
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a) + "%");
                                    } else {
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a) + "%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"5sub2");
                                sub2A = db.getAbscentCount(c,"5sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("5sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("5sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"5sub3");
                                sub3A = db.getAbscentCount(c,"5sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("5sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("5sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                            case "six":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"6sub1");
                                sub1A = db.getAbscentCount(c,"6sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("6sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub1a = ((float) sub1P / (float) (sub1P + sub1A)) * 100;
                                    t1.setText("6sub1 : " + sub1P + "/" + (sub1P + sub1A));
                                    if (sub1a < 75) {
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a) + "%");
                                    } else {
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a) + "%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"6sub2");
                                sub2A = db.getAbscentCount(c,"6sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("6sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("6sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"6sub3");
                                sub3A = db.getAbscentCount(c,"6sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("6sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("6sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                            case "seven":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"7sub1");
                                sub1A = db.getAbscentCount(c,"7sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("7sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub1a = ((float) sub1P / (float) (sub1P + sub1A)) * 100;
                                    t1.setText("7sub1 : " + sub1P + "/" + (sub1P + sub1A));
                                    if (sub1a < 75) {
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a) + "%");
                                    } else {
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a) + "%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"7sub2");
                                sub2A = db.getAbscentCount(c,"7sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("7sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("7sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"7sub3");
                                sub3A = db.getAbscentCount(c,"7sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("7sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("7sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                            case "eight":{
                                sub1A=0; sub1P=0; sub1a=0; flag=0;
                                sub1P = db.getPresentCount(c,"8sub1");
                                sub1A = db.getAbscentCount(c,"8sub1");
                                if(sub1P==0 && sub1A==0){
                                    t1.setText("8sub1 : "+sub1P+"/"+(sub1P+sub1A));
                                    tv1.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub1a = ((float) sub1P / (float) (sub1P + sub1A)) * 100;
                                    t1.setText("8sub1 : " + sub1P + "/" + (sub1P + sub1A));
                                    if (sub1a < 75) {
                                        tv1.setTextColor(Color.RED);
                                        tv1.setText(df.format(sub1a) + "%");
                                    } else {
                                        tv1.setTextColor(Color.rgb(0,255,0));
                                        tv1.setText(df.format(sub1a) + "%");
                                    }
                                }

                                sub2A=0; sub2P=0; sub2a=0;
                                sub2P = db.getPresentCount(c,"8sub2");
                                sub2A = db.getAbscentCount(c,"8sub2");
                                if(sub2P==0 && sub2A==0){
                                    t2.setText("8sub2 : "+sub2P+"/"+(sub2P+sub2A));
                                    tv2.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub2a = ((float) sub2P / (float) (sub2P + sub2A)) * 100;
                                    t2.setText("8sub2 : " + sub2P + "/" + (sub2P + sub2A));
                                    if (sub2a < 75) {
                                        tv2.setTextColor(Color.RED);
                                        tv2.setText(df.format(sub2a) + "%");
                                    } else {
                                        tv2.setTextColor(Color.rgb(0,255,0));
                                        tv2.setText(df.format(sub2a) + "%");
                                    }
                                }

                                sub3A=0; sub3P=0; sub3a=0;
                                sub3P = db.getPresentCount(c,"8sub3");
                                sub3A = db.getAbscentCount(c,"8sub3");
                                if(sub3P==0 && sub3A==0){
                                    t3.setText("8sub3 : "+sub3P+"/"+(sub3P+sub3A));
                                    tv3.setText("0%");
                                }
                                else {
                                    flag++;
                                    sub3a = ((float) sub3P / (float) (sub3P + sub3A)) * 100;
                                    t3.setText("8sub3 : " + sub3P + "/" + (sub3P + sub3A));
                                    if (sub3a < 75) {
                                        tv3.setTextColor(Color.RED);
                                        tv3.setText(df.format(sub3a) + "%");
                                    } else {
                                        tv3.setTextColor(Color.rgb(0,255,0));
                                        tv3.setText(df.format(sub3a) + "%");
                                    }
                                }

                                if (flag==0)
                                    tt=0;
                                else if(flag==1)
                                    tt=(sub1a+sub2a+sub3a)/1;
                                else if(flag==2)
                                    tt=(sub1a+sub2a+sub3a)/2;
                                else if(flag==3)
                                    tt=(sub1a+sub2a+sub3a)/3;

                                t4.setText("Total : ");
                                if (tt<75){
                                    tv4.setTextColor(Color.RED);
                                    tv4.setText(df.format(tt)+"%");
                                }
                                else{
                                    tv4.setTextColor(Color.rgb(0,255,0));
                                    tv4.setText(df.format(tt)+"%");
                                }
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
}


