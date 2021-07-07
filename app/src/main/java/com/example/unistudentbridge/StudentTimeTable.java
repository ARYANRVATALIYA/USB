package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.unistudentbridge.LoginActivity.LoggedInStudentDetails;

public class StudentTimeTable extends AppCompatActivity {
    DATA_Base data_base;
    Spinner StudentTimeTableSemSelectionSpinner;

    ImageView StudentNoticeShowerImageView;

    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_time_table);
        getSupportActionBar().setTitle("Student Time Tables");

        data_base = new DATA_Base(getApplicationContext());

        StudentNoticeShowerImageView = (ImageView) findViewById(R.id.StudentNoticeShowerImageView);


        StudentTimeTableSemSelectionSpinner = (Spinner) findViewById(R.id.StudentTimeTableSemSelectionSpinner);
        ArrayAdapter Semadpter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Sem_Array);
        StudentTimeTableSemSelectionSpinner.setAdapter(Semadpter);

        StudentTimeTableSemSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    StudentNoticeShowerImageView.setImageBitmap(getImage(data_base.ReadTimeTable(LoggedInStudentDetails.getString(5), StudentTimeTableSemSelectionSpinner.getSelectedItem().toString())));
                } catch (Exception e) {
                    Toast.makeText(StudentTimeTable.this, "No data found", Toast.LENGTH_SHORT).show();
                    StudentNoticeShowerImageView.setImageResource(R.drawable.chat);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
