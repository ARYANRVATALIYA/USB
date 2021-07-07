package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class SignupActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    RadioButton Student_Signup_Selection, Teacher_Signup_Selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Student_Signup_Selection = (RadioButton) findViewById(R.id.Student_Signup_Selection);
        Teacher_Signup_Selection = (RadioButton) findViewById(R.id.Teacher_Signup_Selection);

        Student_Signup_Selection.setOnCheckedChangeListener(this);
        Teacher_Signup_Selection.setOnCheckedChangeListener(this);

        Student_Signup_Selection.setChecked(true);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {

            case R.id.Student_Signup_Selection:
                if (isChecked) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.SignupSelectionContainer, new StudentSignup()).commit();
                }
                break;

            case R.id.Teacher_Signup_Selection:
                if (isChecked) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.SignupSelectionContainer, new TeacherSignup()).commit();
                }
                break;
        }
    }
}
