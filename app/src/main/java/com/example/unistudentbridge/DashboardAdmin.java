package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashboardAdmin extends AppCompatActivity {
    DATA_Base db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        db = new DATA_Base(DashboardAdmin.this);
    }

    public void resetChat(View view) {
        db.resetChat();
        Toast.makeText(DashboardAdmin.this, "Chat Reseted", Toast.LENGTH_SHORT).show();
    }


    public void resetNotice(View view) {
        db.resetNotice();
        Toast.makeText(DashboardAdmin.this, "Notice Reseted", Toast.LENGTH_SHORT).show();
    }

    public void resetTimeTable(View view) {
        db.resetTimeTable();
        Toast.makeText(DashboardAdmin.this, "Time Table Reseted", Toast.LENGTH_SHORT).show();
    }

    public void accountShower(View view) {
        startActivity(new Intent(DashboardAdmin.this, AdminAccountShower.class));
    }

    public void logOut(View view) {
        DashboardAdmin.this.finish();
    }
}
