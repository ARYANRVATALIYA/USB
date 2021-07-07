package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.unistudentbridge.LoginActivity.LoggedInStudentDetails;

public class StudentNotice extends AppCompatActivity {
    TextView StudentNoticeShowerTextView;
    DATA_Base data_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notice);
        getSupportActionBar().setTitle("Student Notices");
        data_base = new DATA_Base(this);

        StudentNoticeShowerTextView = (TextView) findViewById(R.id.StudentNoticeShowerTextView);

        Toast.makeText(this, LoggedInStudentDetails.getString(5), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, LoggedInStudentDetails.getString(6), Toast.LENGTH_SHORT).show();
        Cursor CurrentNotice = data_base.ReadNotice(LoggedInStudentDetails.getString(5), LoggedInStudentDetails.getString(6));

        while (CurrentNotice.moveToNext()) {
            StudentNoticeShowerTextView.append(CurrentNotice.getString(2) + "\n\n");
        }
    }
}
