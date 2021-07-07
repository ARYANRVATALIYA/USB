package com.example.unistudentbridge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AdminAccountShower extends AppCompatActivity {

    ListView studentListView;
    AccShowerAdpt adp;
    RadioButton r1, r2;
    Cursor c;
    DATA_Base db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_detailsshowing);

        studentListView = (ListView) findViewById(R.id.studentListView);
        adp = new AccShowerAdpt();
        r1 = (RadioButton) findViewById(R.id.studentSelection);
        r2 = (RadioButton) findViewById(R.id.teacherSelection);
        db = new DATA_Base(this);

        studentListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                new AlertDialog.Builder(AdminAccountShower.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                c.moveToPosition(position);

                                if (r1.isChecked()) {
                                    db.deleteStudent(c.getString(0));
                                    Toast.makeText(AdminAccountShower.this, "Deleted", Toast.LENGTH_SHORT).show();

                                    c = db.getAllStudentData();
                                    adp = new AccShowerAdpt();
                                    studentListView.setAdapter(adp);
                                    studentListView.invalidate();

                                } else {
                                    db.deleteTeacher(c.getString(0));
                                    Toast.makeText(AdminAccountShower.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    c = db.getAllTeacherData();
                                    adp = new AccShowerAdpt();
                                    studentListView.setAdapter(adp);
                                    studentListView.invalidate();

                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                return false;
            }
        });

        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    c = db.getAllStudentData();
                    adp = new AccShowerAdpt();
                    studentListView.setAdapter(adp);
                    studentListView.invalidate();
                }
            }
        });


        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    c = db.getAllTeacherData();
                    adp = new AccShowerAdpt();
                    studentListView.setAdapter(adp);
                    studentListView.invalidate();
                }
            }
        });
    }
    class AccShowerAdpt extends BaseAdapter {

        @Override
        public int getCount() {
            return c.getCount();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater lf = LayoutInflater.from(AdminAccountShower.this);
            c.moveToPosition(position);
            View v = lf.inflate(R.layout.person_raw_layout, parent, false);
            TextView t1 = (TextView) v.findViewById(R.id.idshowing);
            t1.setText("Id :" + c.getString(0));
            TextView t2 = (TextView) v.findViewById(R.id.name);
            t2.setText("Name :" + c.getString(2));
            TextView t3 = (TextView) v.findViewById(R.id.dept);
            t3.setText("Dept :" + c.getString(5));
            TextView t4 = (TextView) v.findViewById(R.id.sem);
            try {
                t4.setText("Sem :" + c.getString(6));
            }
            catch (Exception e){
                t4.setText("");
            }

            return v;
        }
    }
}
