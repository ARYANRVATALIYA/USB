package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.unistudentbridge.LoginActivity.LoggedInStudentDetails;

public class StudentDepartmentChat extends AppCompatActivity {
    DATA_Base data_base;

    TextView DepartmentChat;

    EditText SendChatEditText;

    Button SendChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_department_chat);
        getSupportActionBar().setTitle(LoggedInStudentDetails.getString(5) + " Department ChatRoom");

        data_base = new DATA_Base(getApplicationContext());
        DepartmentChat = (TextView) findViewById(R.id.StudentDepartmentChat);
        SendChatEditText = (EditText) findViewById(R.id.SendStudentChatEditText);
        SendChatButton = (Button) findViewById(R.id.SendStudentChatButton);

        String msg = data_base.ReadChat(LoggedInStudentDetails.getString(5), LoggedInStudentDetails.getString(6));

        if (msg.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {

            DepartmentChat.setText(msg);
        }


        SendChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (data_base.WriteChat(LoggedInStudentDetails.getString(5), LoggedInStudentDetails.getString(6), LoggedInStudentDetails.getString(0) + ":" + SendChatEditText.getText().toString())) {
                    Toast.makeText(StudentDepartmentChat.this, "Sent", Toast.LENGTH_SHORT).show();
                    SendChatEditText.setText("");
                    String msg = data_base.ReadChat(LoggedInStudentDetails.getString(5), LoggedInStudentDetails.getString(6));
                    DepartmentChat.setText(msg);


                } else
                    Toast.makeText(StudentDepartmentChat.this, "not sent", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
