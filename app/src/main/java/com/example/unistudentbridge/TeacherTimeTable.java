package com.example.unistudentbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.example.unistudentbridge.LoginActivity.LoggedInTeacherDetails;

public class TeacherTimeTable extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;
    Spinner TeacherTimeTableSemSelectionSpinner;

    Button EditTeacherTimetableButton;

    ImageView TeacherTimeTableImageView;

    DATA_Base data_base;

    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};

    byte[] selectimageByte=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_time_table);
        getSupportActionBar().setTitle("Teacher Time Table ");
        data_base = new DATA_Base(getApplicationContext());

        TeacherTimeTableSemSelectionSpinner = (Spinner) findViewById(R.id.TeacherTimeTableSemSelectionSpinner);
        ArrayAdapter Semadpter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Sem_Array);
        TeacherTimeTableSemSelectionSpinner.setAdapter(Semadpter);
        TeacherTimeTableImageView = (ImageView) findViewById(R.id.TeacherTimeTableImageView);

        try {
            TeacherTimeTableImageView.setImageBitmap(getImage(data_base.ReadTimeTable(LoggedInTeacherDetails.getString(5), TeacherTimeTableSemSelectionSpinner.getSelectedItem().toString())));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
            TeacherTimeTableImageView.setImageResource(R.drawable.chat);

        }

        TeacherTimeTableSemSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TeacherTimeTableImageView.setImageBitmap(getImage(data_base.ReadTimeTable(LoggedInTeacherDetails.getString(5), TeacherTimeTableSemSelectionSpinner.getSelectedItem().toString())));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    TeacherTimeTableImageView.setImageResource(R.drawable.chat);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TeacherTimeTableImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);

                /*Intent i= new Intent(Intent.ACTION_PICK);
                i.setType("image/*");

                startActivityForResult(i,PICK_IMAGE_REQUEST);*/
                //     Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                //      intent.setType("image/*");
                //     intent.putExtra("crop", "true");
                //     intent.putExtra("scale", true);
                //      intent.putExtra("outputX", 256);
                //       intent.putExtra("outputY", 256);
                //      intent.putExtra("aspectX", 1);
                //      intent.putExtra("aspectY", 1);
                //       intent.putExtra("return-data", true);
                return true;
            }
        });


        EditTeacherTimetableButton = (Button) findViewById(R.id.EditTeacherTimetableButton);

        EditTeacherTimetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectimageByte!=null){
                    if (data_base.WriteTimeTable(LoggedInTeacherDetails.getString(5), TeacherTimeTableSemSelectionSpinner.getSelectedItem().toString(), selectimageByte)) {
                        Toast.makeText(TeacherTimeTable.this, "Timetable Posted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TeacherTimeTable.this, "Unable to Post time Table", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
            }

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        /*if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            TeacherTimeTableImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            selectimageByte = getBytes(BitmapFactory.decodeFile(picturePath));

            // String picturePath contains the path of selected Image
        }*/
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            //TeacherTimeTableImageView.setImageURI(selectedImage);
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                TeacherTimeTableImageView.setImageBitmap(bitmap);
                selectimageByte=getBytes(bitmap);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
            /*String path;
            path=selectedImage.getPath();
            selectimageByte= getBytes(BitmapFactory.decodeFile(path));*/

        }

/*
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap Pic = extras.getParcelable("data");
                selectimageByte = getBytes(Pic);

            }
        }


        */
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}

