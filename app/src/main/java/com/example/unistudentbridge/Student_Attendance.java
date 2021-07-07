package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import static com.example.unistudentbridge.LoginActivity.LoggedInStudentDetails;

public class Student_Attendance extends AppCompatActivity {

    /*String Subsem1[] = new String[]{"1sub1","1sub2","1sub3"};
    String Subsem2[] = new String[]{"2sub1","2sub2","2sub3"};
    String Subsem3[] = new String[]{"3sub1","3sub2","3sub3"};
    String Subsem4[] = new String[]{"4sub1","4sub2","4sub3"};
    String Subsem5[] = new String[]{"5sub1","5sub2","5sub3"};
    String Subsem6[] = new String[]{"6sub1","6sub2","6sub3"};
    String Subsem7[] = new String[]{"7sub1","7sub2","7sub3"};
    String Subsem8[] = new String[]{"8sub1","8sub2","8sub3"};*/
    DecimalFormat df= new DecimalFormat("###.##");
    String sem;
    String dep;
    String id;
    DATA_Base db;
    Cursor c;
    TextView t1,t2,t3,t4,studentName,studentID,tv1,tv2,tv3,tv4;
    int sub1P,sub1A;
    int sub2P,sub2A;
    int sub3P,sub3A;
    int flag=0;
    float sub1a,sub2a,sub3a,tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        getSupportActionBar().setTitle("Overall Attendance");
        studentName=findViewById(R.id.studentName);
        studentID=findViewById(R.id.studentID);
        t1=findViewById(R.id.sub1);
        t2=findViewById(R.id.sub2);
        t3=findViewById(R.id.sub3);
        t4=findViewById(R.id.sub4);
        tv1=findViewById(R.id.sub1T);
        tv2=findViewById(R.id.sub2T);
        tv3=findViewById(R.id.sub3T);
        tv4=findViewById(R.id.total);
        db = new DATA_Base(this);

        id = LoggedInStudentDetails.getString(0);
        sem = LoggedInStudentDetails.getString(6);
        dep = LoggedInStudentDetails.getString(5);

        studentName.setText(LoggedInStudentDetails.getString(2));
        studentID.setText("ID : "+id);
        c = db.getStudentCursor(id,dep,sem);

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
    public void logOut(View view){
        Student_Attendance.this.finish();
    }
}