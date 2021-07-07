package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.unistudentbridge.LoginActivity.LoggedInTeacherDetails;

public class TeacherNotice extends AppCompatActivity {

    EditText TeacherPostNoticeEditText;

    Button TeacherPostNoticeButton;

    Spinner TeacherNoticeSemSelectionSpinner;

    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};


    DATA_Base data_base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notice);
        getSupportActionBar().setTitle("Teacher Notices Upload");

        data_base = new DATA_Base(getApplicationContext());

        TeacherPostNoticeEditText = (EditText) findViewById(R.id.TeacherPostNoticeEditText);

        TeacherNoticeSemSelectionSpinner = (Spinner) findViewById(R.id.TeacherNoticeSemSelectionSpinner);
        ArrayAdapter Semadpter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Sem_Array);
        TeacherNoticeSemSelectionSpinner.setAdapter(Semadpter);

        TeacherPostNoticeButton = (Button) findViewById(R.id.TeacherPostNoticeButton);
        TeacherPostNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = LoggedInTeacherDetails.getString(2) + " : " + "(" + new java.util.Date().toString() + ")" + "   " + TeacherPostNoticeEditText.getText().toString() + "\n\n";
                Toast.makeText(TeacherNotice.this, msg, Toast.LENGTH_SHORT).show();
                if (!TeacherPostNoticeEditText.getText().toString().isEmpty() && data_base.WriteNotice(LoggedInTeacherDetails.getString(5), TeacherNoticeSemSelectionSpinner.getSelectedItem().toString(), msg)) {
                    Toast.makeText(TeacherNotice.this, "Notice is Posted Successfully !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TeacherNotice.this, "Empty Notice can't be Posted", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
