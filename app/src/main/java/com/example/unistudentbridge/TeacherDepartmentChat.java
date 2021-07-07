package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.unistudentbridge.LoginActivity.LoggedInTeacherDetails;

public class TeacherDepartmentChat extends AppCompatActivity {
    DATA_Base data_base;

    TextView DepartmentChat;

    EditText SendChatEditText;
    Spinner StudentSemSelection;
    Button SendChatButton;
    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_department_chat);

        getSupportActionBar().setTitle(LoggedInTeacherDetails.getString(5) + " Department ChatRoom");

        data_base = new DATA_Base(getApplicationContext());

        DepartmentChat = (TextView) findViewById(R.id.TeacherDepartmentChat);
        StudentSemSelection = (Spinner) findViewById(R.id.StudentSemSelection);
        SendChatEditText = (EditText) findViewById(R.id.SendTeacherChatEditText);

        StudentSemSelection.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sem_Array));

        StudentSemSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String msg = data_base.ReadChat(LoggedInTeacherDetails.getString(5), StudentSemSelection.getSelectedItem().toString());

                DepartmentChat.setText(msg);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SendChatButton = (Button) findViewById(R.id.SendTeacherChatButton);
        SendChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SendChatEditText.getText().toString().isEmpty() && data_base.WriteChat(LoggedInTeacherDetails.getString(5), StudentSemSelection.getSelectedItem().toString(), LoggedInTeacherDetails.getString(2) + ": " + SendChatEditText.getText().toString())) {
                    Toast.makeText(TeacherDepartmentChat.this, "Message Sent", Toast.LENGTH_SHORT).show();
                    SendChatEditText.setText("");
                    String msg = data_base.ReadChat(LoggedInTeacherDetails.getString(5), StudentSemSelection.getSelectedItem().toString());
                    DepartmentChat.setText(msg);

                } else {
                    Toast.makeText(TeacherDepartmentChat.this, "You need to Write in Order to Send Message", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
