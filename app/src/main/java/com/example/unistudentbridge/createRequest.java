package com.example.unistudentbridge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


public class createRequest extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.pick_period, null);
        final DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        final EditText hour = (EditText) v.findViewById(R.id.periodID);


        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth() + 1;
                        int year = datePicker.getYear();
                        String date = year + "-" + month + "-" + day;
                        if (!hour.getText().toString().isEmpty()) {
                            Intent launchinIntent = new Intent(TeacherPanel.activity, Teacher_Attendance.class);
                            launchinIntent.putExtra("DATE", date);
                            launchinIntent.putExtra("PERIOD", hour.getText().toString());
                            startActivity(launchinIntent);
                        }
                        else{
                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}