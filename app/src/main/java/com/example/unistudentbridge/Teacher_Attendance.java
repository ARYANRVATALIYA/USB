package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Teacher_Attendance extends AppCompatActivity {

    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};
    String Departments_Array[] = new String[]{"CE", "CSE", "IT"};
    String Subsem1[] = new String[]{"1sub1","1sub2","1sub3"};
    String Subsem2[] = new String[]{"2sub1","2sub2","2sub3"};
    String Subsem3[] = new String[]{"3sub1","3sub2","3sub3"};
    String Subsem4[] = new String[]{"4sub1","4sub2","4sub3"};
    String Subsem5[] = new String[]{"5sub1","5sub2","5sub3"};
    String Subsem6[] = new String[]{"6sub1","6sub2","6sub3"};
    String Subsem7[] = new String[]{"7sub1","7sub2","7sub3"};
    String Subsem8[] = new String[]{"8sub1","8sub2","8sub3"};
    String defaultSubject[]= new String[]{"Select sem first"};
    Spinner Teacher_Attendance_SemSpinner;
    Spinner Teacher_Attendance_SubSpinner;
    Spinner Teacher_Attendance_DepSpinner;
    ArrayAdapter adp;
    ArrayAdapter<String> adp1;
    public static String time,period;
    ListView listView;
    ListAdapter adapter;
    ArrayList<String> names;
    ArrayList<String> id;
    Activity thisActivity = this;
    DATA_Base data_base;
    Button savebtn;
    Button showbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance);
        getSupportActionBar().setTitle("Set Attendance");

        time= getIntent().getStringExtra("DATE");
        period= getIntent().getStringExtra("PERIOD");
        data_base= new DATA_Base(this);

        Teacher_Attendance_SemSpinner= findViewById(R.id.Sem_Select_Spinner);
        Teacher_Attendance_SubSpinner= findViewById(R.id.Sub_Select_Spinner);
        Teacher_Attendance_DepSpinner= findViewById(R.id.Teacher_Attendance_Dep_SelectionSpinner);
        savebtn = findViewById(R.id.AttendanceSaveButton);
        showbtn = findViewById(R.id.Teacher_Attendance_Show_Button);
        listView= findViewById(R.id.AttendanceList);
        names = new ArrayList<>();
        id = new ArrayList<>();

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_Attendance.this,StudentAttendanceList.class);
                startActivity(intent);
            }
        });

        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Sem_Array);
        Teacher_Attendance_SemSpinner.setAdapter(adp);
        adp1= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,Departments_Array){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        Teacher_Attendance_DepSpinner.setAdapter(adp1);
        Teacher_Attendance_DepSpinner.setSelection(0);
        Teacher_Attendance_SemSpinner.setSelection(0);
        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem1);
        Teacher_Attendance_SubSpinner.setAdapter(adp);

        Teacher_Attendance_SemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSem= parent.getItemAtPosition(position).toString();
                switch (selectedSem){
                    case "one": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem1);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                    case "two": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem2);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                    case "three": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem3);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                    case "four": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem4);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                    case "five": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem5);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                    case "six": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem6);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                    case "seven": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem7);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                    case "eight": {
                        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,Subsem8);
                        Teacher_Attendance_SubSpinner.setAdapter(adp);
                        LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Teacher_Attendance_SubSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoadListview(Teacher_Attendance_DepSpinner.getSelectedItem().toString());
                boolean b= TeacherPanel.database.CheckAttendance(time,period,Teacher_Attendance_SubSpinner.getSelectedItem().toString());
                if (b){
                    Toast.makeText(Teacher_Attendance.this, "Attendance already taken for this subject on this time", Toast.LENGTH_LONG).show();
                    savebtn.setEnabled(false);
                }
                else
                    savebtn.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Teacher_Attendance_DepSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selectedDep= parent.getItemAtPosition(position).toString();
               LoadListview(selectedDep);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.saveAll();
            }
        });
    }
    public void LoadListview(String dept){
        names.clear();
        id.clear();
        Cursor cursor=data_base.getStudent(dept,Teacher_Attendance_SemSpinner.getSelectedItem().toString());
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(Teacher_Attendance.this,"No data found",Toast.LENGTH_LONG).show();
        } else {
            int ctr = 0;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                names.add(cursor.getString(2));
                id.add(cursor.getString(0));
                cursor.moveToNext();
                ctr++;
            }
            if (ctr == 0) {
                Toast.makeText(getBaseContext(), "No Students Found", Toast.LENGTH_LONG).show();
            }
        }
        adapter = new ListAdapter(thisActivity, names, Teacher_Attendance_SubSpinner.getSelectedItem().toString(),Teacher_Attendance_SemSpinner.getSelectedItem().toString(),id,dept);
        listView.setAdapter(adapter);
    }
}


