package com.example.unistudentbridge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView SignupOpener;

    EditText Username, Password;

    Button Student_Login, Teacher_Login;
    public static DATA_Base data_base;

    static Cursor LoggedInTeacherDetails;
    static Cursor LoggedInStudentDetails;
    private static final int STORAGE_PERMISSION_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoggedInTeacherDetails = null;
        LoggedInStudentDetails = null;

        data_base = new DATA_Base(getApplicationContext());

        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);

        Student_Login = (Button) findViewById(R.id.Student_Login_Button);
        Student_Login.setOnClickListener(this);

        Teacher_Login = (Button) findViewById(R.id.Teacher_Login_Button);
        Teacher_Login.setOnClickListener(this);

        SignupOpener = (TextView) findViewById(R.id.SignupOpener);
        SignupOpener.setText(Html.fromHtml("Don't have an account? <font color='#FFca28'>Sign up</font>"));
        SignupOpener.setOnClickListener(this);

        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE);

        findViewById(R.id.adminLogger).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(LoginActivity.this, AdminLoginActivity.class));
                return false;
            }
        });
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{permission},
                    requestCode);
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(LoginActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(LoginActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SignupOpener:
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                break;
            case R.id.Student_Login_Button:
                if (!Username.getText().toString().isEmpty() && !Password.getText().toString().isEmpty()) {

                    LoggedInStudentDetails = data_base.Student_Login(Username.getText().toString(), Password.getText().toString());
                    LoggedInStudentDetails.moveToFirst();
                    if (LoggedInStudentDetails.getCount() > 0) {
                        Toast.makeText(this, "Welcome  " + LoggedInStudentDetails.getString(2), Toast.LENGTH_SHORT).show();
                        Intent i1= new Intent(LoginActivity.this, StudentPanel.class);
                        Username.setText("");
                        Password.setText("");
                        startActivity(i1);
                    } else {
                        Toast.makeText(this, "Username or password is wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(Username.getText().toString().isEmpty()){
                        Username.setError("Username cannot be empty");
                    }
                    if(Password.getText().toString().isEmpty()){
                        Password.setError("Password cannot be empty");
                    }
                }
                break;
            case R.id.Teacher_Login_Button:
                if (!Username.getText().toString().isEmpty() && !Password.getText().toString().isEmpty()) {

                    LoggedInTeacherDetails = data_base.Teacher_Login(Username.getText().toString(), Password.getText().toString());
                    LoggedInTeacherDetails.moveToFirst();
                    if (LoggedInTeacherDetails.getCount() > 0) {
                        Toast.makeText(this, "Welcome " + LoggedInTeacherDetails.getString(2), Toast.LENGTH_SHORT).show();
                        Intent i2= new Intent(LoginActivity.this, TeacherPanel.class);
                        Username.setText("");
                        Password.setText("");
                        startActivity(i2);
                    } else {
                        Toast.makeText(this, "Username or password is wrong ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(Username.getText().toString().isEmpty()){
                        Username.setError("Username cannot be empty");
                    }
                    if(Password.getText().toString().isEmpty()){
                        Password.setError("Password cannot be empty");
                    }
                }
                break;
        }
    }
}
