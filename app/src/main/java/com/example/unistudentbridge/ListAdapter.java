package com.example.unistudentbridge;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListAdapter extends BaseAdapter {
    ArrayList<String> nameList;
    ArrayList<String> id;
    ArrayList<Boolean> attendanceList;
    Activity activity;
    String subject;
    String semester;
    String dep;

    public ListAdapter(Activity activity, ArrayList<String> nameList, String subject, String sem, ArrayList<String> id, String d) {
        this.nameList = nameList;
        this.activity = activity;
        this.subject = subject;
        this.id=id;
        semester=sem;
        dep=d;
        attendanceList = new ArrayList<>();
        for (int j = 0; j < nameList.size(); j++) {
            attendanceList.add(new Boolean(true));
        }
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.attendance_list, null);
        }
        final int pos = position;
        TextView textView = (TextView) v.findViewById(R.id.attendanceName);
        textView.setText(nameList.get(position));
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.attMarker);

        boolean itemChecked = this.attendanceList.get(position);
        checkBox.setChecked(itemChecked);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendanceList.set(pos, checkBox.isChecked());
            }
        });
        return v;
    }

    public void saveAll() {
        for (int i = 0; i < nameList.size(); i++) {
            int sts = 1;
            if (attendanceList.get(i))
                sts = 1;
            else sts = 0;
            String qu = "INSERT INTO ATTENDANCE_TABLE VALUES('" + Teacher_Attendance.time + "'," +
                    "" + Teacher_Attendance.period + "," +
                    "'" + id.get(i) + "'," +
                    "'" + nameList.get(i) + "'," +
                    "'" + subject + "'," +
                    "'" + semester + "'," +
                    "'" + dep + "'," +
                    "" + sts + ");";
            TeacherPanel.database.execAction(qu);
            Toast.makeText(activity,"Attendance uploaded",Toast.LENGTH_LONG).show();
            activity.finish();
        }
    }
}
