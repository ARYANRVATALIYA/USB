package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText e1, e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminloginactivity);

        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        b1 = (Button) findViewById(R.id.onAdminLoginCheck);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!e1.getText().toString().isEmpty() && !e2.getText().toString().isEmpty()) {


                    if (e1.getText().toString().equals("admin") && e2.getText().toString().equals("password")) {
                        Intent i = new Intent(AdminLoginActivity.this, DashboardAdmin.class);
                        e1.setText("");
                        e2.setText("");
                        startActivity(i);
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    if (e1.getText().toString().isEmpty())
                        e1.setError("cant be empty");
                    else
                        e2.setError("cant be empty");
                }
            }
        });
    }
}
