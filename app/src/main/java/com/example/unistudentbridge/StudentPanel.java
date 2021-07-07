package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.unistudentbridge.LoginActivity.LoggedInStudentDetails;

public class StudentPanel extends AppCompatActivity {
    LinearLayout StudentNoticeOpener, StudentTimeTableOpener, StudentStudyMaterialOpener, StudentAttendanceOpener, StudentChatOpener;
    MyBounceInterpolator interpolator;
    Animation myAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

        getSupportActionBar().setTitle("Welcome " + LoggedInStudentDetails.getString(2) +" to Student Panel");
        TextView LoggedinStudentDepartment = (TextView) findViewById(R.id.LoggedStudentDepartment);
        LoggedinStudentDepartment.setText(LoggedInStudentDetails.getString(5) + " Department ");


        StudentNoticeOpener = (LinearLayout) findViewById(R.id.StudentNoticeOpener);
        StudentTimeTableOpener = (LinearLayout) findViewById(R.id.StudentTimeTableOpener);
        StudentStudyMaterialOpener = (LinearLayout) findViewById(R.id.StudentStudyMaterialOpener);
        StudentAttendanceOpener = (LinearLayout) findViewById(R.id.StudentAttendanceOpener);
        StudentChatOpener = (LinearLayout) findViewById(R.id.StudentChatOpener);

        //Animation Stuff
        interpolator = new MyBounceInterpolator(0.11, 30);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounc);
        myAnim.setInterpolator(interpolator);

        StudentNoticeOpener.startAnimation(myAnim);
        StudentTimeTableOpener.startAnimation(myAnim);
        StudentStudyMaterialOpener.startAnimation(myAnim);
        StudentAttendanceOpener.startAnimation(myAnim);
        StudentChatOpener.startAnimation(myAnim);

        final Handler[] handler = {new Handler()};
        StudentNoticeOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(StudentPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentNoticeOpener.startAnimation(myAnim);
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, StudentNotice.class));
                    }
                }, 750);
            }
        });
        StudentTimeTableOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(StudentPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentTimeTableOpener.startAnimation(myAnim);
                handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, StudentTimeTable.class));
                    }
                }, 750);
            }
        });
        StudentStudyMaterialOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(StudentPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentStudyMaterialOpener.startAnimation(myAnim);
                handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, Student_StudyMaterial.class));
                    }
                }, 750);
            }
        });
        StudentAttendanceOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(StudentPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentAttendanceOpener.startAnimation(myAnim);
                handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, Student_Attendance.class));
                    }
                }, 750);
            }
        });
        StudentChatOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(StudentPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentChatOpener.startAnimation(myAnim);
                handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, StudentDepartmentChat.class));
                    }
                }, 750);
            }
        });


        findViewById(R.id.LoggedStudentDepartment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
/*
    @Override
    public void onClick(View view) {

        Handler handler = new Handler();

        switch (view.getId()) {

            case R.id.StudentNoticeOpener:
                myAnim = AnimationUtils.loadAnimation(this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentNoticeOpener.startAnimation(myAnim);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, StudentNotice.class));
                    }
                }, 750);
                break;

            case R.id.StudentTimeTableOpener:
                myAnim = AnimationUtils.loadAnimation(this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentTimeTableOpener.startAnimation(myAnim);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, StudentTimeTable.class));
                    }
                }, 750);
                break;


            case R.id.StudentStudyMaterialOpener:
                myAnim = AnimationUtils.loadAnimation(this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentStudyMaterialOpener.startAnimation(myAnim);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, Student_StudyMaterial.class));
                    }
                }, 750);
                break;


            case R.id.StudentLabManualOpener:
                myAnim = AnimationUtils.loadAnimation(this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentLabManualOpener.startAnimation(myAnim);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, Student_LabManual.class));
                    }
                }, 750);
                break;

            case R.id.StudentChatOpener:
                myAnim = AnimationUtils.loadAnimation(this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                StudentChatOpener.startAnimation(myAnim);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StudentPanel.this, StudentDepartmentChat.class));
                    }
                }, 750);
                break;


        }
    }*/
}
