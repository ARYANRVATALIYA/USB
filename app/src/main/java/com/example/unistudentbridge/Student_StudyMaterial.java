package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

import static com.example.unistudentbridge.LoginActivity.LoggedInStudentDetails;

public class Student_StudyMaterial extends AppCompatActivity {


    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};

    Student_StudyMaterial_Adpter adpter;

    Spinner Student_StudyMaterial_Sem_SelectionSpinner;
    ListView Student_StudyMaterial_Listview;



    File CurrentDirectory;

    List<File> FilesInDirectory = new ArrayList<File>();
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_study_material);

        mContext = Student_StudyMaterial.this;

        Objects.requireNonNull(getSupportActionBar()).setTitle("Student Study Material");

        Student_StudyMaterial_Sem_SelectionSpinner = (Spinner) findViewById(R.id.Student_StudeyMaterial_Sem_SelectionSpinner);
        Student_StudyMaterial_Sem_SelectionSpinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, Sem_Array));


        Student_StudyMaterial_Listview = (ListView) findViewById(R.id.Student_StudyMaterial_Listview);
        adpter = new Student_StudyMaterial.Student_StudyMaterial_Adpter();
        Student_StudyMaterial_Listview.setAdapter(adpter);

        //Student_StudyMaterial_Upload_Button = (Button) findViewById(R.id.Student_StudyMaterial_Upload_Button);

       /* Student_StudyMaterial_Upload_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FilePickerBuilder.getInstance().setMaxCount(1)
                        .setActivityTheme(R.style.AppTheme)
                        .pickDocument(Student_StudyMaterial.this);
            }
        });*/


        Student_StudyMaterial_Sem_SelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adpter.ReloadFiles();
                        adpter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    System.out.println(filePaths.get(0));

                    moveFile(new File(filePaths.get(0)), new File(Environment.getExternalStorageDirectory() + File.separator + "StudyMaterial" + LoggedInStudentDetails.getString(5) + Student_StudyMaterial_Sem_SelectionSpinner.getSelectedItem().toString() + File.separator));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adpter.ReloadFiles();
                            adpter.notifyDataSetChanged();
                        }
                    });
                }
        }


    }


    private void moveFile(File file, File dir) {
        File newFile = new File(dir, file.getName());
        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            outputChannel = new FileOutputStream(newFile).getChannel();
            inputChannel = new FileInputStream(file).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
            inputChannel.close();
            file.delete();
            Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            Toast.makeText(this, "Failed to upload", Toast.LENGTH_SHORT).show();
        } finally {
            if (inputChannel != null) try {
                inputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (outputChannel != null) try {
                outputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    class Student_StudyMaterial_Adpter extends BaseAdapter {


        public Student_StudyMaterial_Adpter() {


            ReloadFiles();
        }

        void ReloadFiles() {

            FilesInDirectory = new ArrayList<File>();

            CurrentDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "StudyMaterial" + LoggedInStudentDetails.getString(5) + Student_StudyMaterial_Sem_SelectionSpinner.getSelectedItem().toString());

            System.out.println(CurrentDirectory);

            if (!CurrentDirectory.exists()) {
                CurrentDirectory.mkdir();
                Toast.makeText(Student_StudyMaterial.this, "New Directory Created", Toast.LENGTH_SHORT).show();
            }


            File[] files = CurrentDirectory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    FilesInDirectory.add(file);
                }
            }
            if (FilesInDirectory.size() > 0) {
                Toast.makeText(Student_StudyMaterial.this, "Files Loaded", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Student_StudyMaterial.this, "Nothing to show", Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        public int getCount() {
            return FilesInDirectory.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(Student_StudyMaterial.this);
            view = inflater.inflate(R.layout.student_files_rawlayout, viewGroup, false);

            TextView Fileheading = (TextView) view.findViewById(R.id.StudentFileName);
            Fileheading.setText(FilesInDirectory.get(i).getName());
            Fileheading.setTag(i);
            Fileheading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = (int) view.getTag();
                    try{
                        Uri photoURI = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", FilesInDirectory.get(index));
                        Intent target = new Intent(Intent.ACTION_VIEW);
                        target.setDataAndType(photoURI, "application/pdf");
                        target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        Intent intent = Intent.createChooser(target, "Open File");
                        startActivity(intent);
                    }
                    catch(Exception e){
                        Toast.makeText(Student_StudyMaterial.this,"ERROR",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });

            return view;
        }
    }

}
