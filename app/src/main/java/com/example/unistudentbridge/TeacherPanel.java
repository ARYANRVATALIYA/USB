package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.unistudentbridge.LoginActivity.LoggedInTeacherDetails;

public class TeacherPanel extends AppCompatActivity {

    LinearLayout TeacherNoticeOpener, TeacherTimeTableOpener, TeacherStudyMaterialOpener, TeacherAttendanceOpener, TeacherChatOpener;
    MyBounceInterpolator interpolator;
    Animation myAnim;
    public static DATA_Base database;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);
        database = new DATA_Base(TeacherPanel.this);
        activity=this;

        getSupportActionBar().setTitle("Welcome " + LoggedInTeacherDetails.getString(2));
        TextView LoggedinTeacherDepartment = (TextView) findViewById(R.id.LoggedTeacherDepartment);
        LoggedinTeacherDepartment.setText(LoggedInTeacherDetails.getString(5) + " Department ");


        TeacherNoticeOpener = (LinearLayout) findViewById(R.id.TeacherNoticeOpener);
        TeacherTimeTableOpener = (LinearLayout) findViewById(R.id.TeacherTimeTableOpener);
        TeacherStudyMaterialOpener = (LinearLayout) findViewById(R.id.TeacherStudyMaterialOpener);
        TeacherAttendanceOpener = (LinearLayout) findViewById(R.id.TeacherAttendanceOpener);
        TeacherChatOpener = (LinearLayout) findViewById(R.id.TeacherChatOpener);

        //Animation Stuff
        interpolator = new MyBounceInterpolator(0.11, 30);
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounc);
        myAnim.setInterpolator(interpolator);

        TeacherNoticeOpener.startAnimation(myAnim);
        TeacherTimeTableOpener.startAnimation(myAnim);
        TeacherStudyMaterialOpener.startAnimation(myAnim);
        TeacherAttendanceOpener.startAnimation(myAnim);
        TeacherChatOpener.startAnimation(myAnim);

        final Handler[] handler = {new Handler()};
        TeacherNoticeOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(TeacherPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                TeacherNoticeOpener.startAnimation(myAnim);
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TeacherPanel.this, TeacherNotice.class));
                    }
                }, 750);
            }
        });
        TeacherTimeTableOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(TeacherPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                TeacherTimeTableOpener.startAnimation(myAnim);
                handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TeacherPanel.this, TeacherTimeTable.class));
                    }
                }, 750);
            }
        });
        TeacherStudyMaterialOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(TeacherPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                TeacherStudyMaterialOpener.startAnimation(myAnim);
                handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TeacherPanel.this, Teacher_StudyMaterial.class));
                    }
                }, 750);
            }
        });
        TeacherAttendanceOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(TeacherPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                TeacherAttendanceOpener.startAnimation(myAnim);
                Activity activity = TeacherPanel.this;
                FragmentManager fm = activity.getFragmentManager();
                createRequest request = new createRequest();
                request.show(fm, "Select");
               /* handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TeacherPanel.this, Teacher_Attendance.class));
                    }
                }, 500);*/
            }
        });
        TeacherChatOpener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnim = AnimationUtils.loadAnimation(TeacherPanel.this, R.anim.bounc);
                interpolator = new MyBounceInterpolator(0.11, 30);
                myAnim.setInterpolator(interpolator);
                TeacherChatOpener.startAnimation(myAnim);
                handler[0] = new Handler();
                handler[0].postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TeacherPanel.this, TeacherDepartmentChat.class));
                    }
                }, 750);
            }
        });

        findViewById(R.id.LoggedTeacherDepartment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
