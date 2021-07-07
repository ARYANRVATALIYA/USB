package com.example.unistudentbridge;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class TeacherSignup extends Fragment {

    View teacherSignupView;

    String Departments_Array[] = new String[]{"CE", "CSE", "IT"};

    ArrayAdapter adp;


    DATA_Base data_base;

    EditText Teacher_Enroll, Teacher_Password, Teacher_ConfirmPass, Teacher_Name, Teacher_Email, Teacher_Phone;

    Spinner Teacher_Department;

    Button Teacher_Signup_Button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        teacherSignupView = inflater.inflate(R.layout.fragment_teacher_signup, container, false);

        data_base = new DATA_Base(getActivity());

        Teacher_Enroll = (EditText) teacherSignupView.findViewById(R.id.Teacher_Signup_Enroll);
        Teacher_Password = (EditText) teacherSignupView.findViewById(R.id.Teacher_Signup_Password);
        Teacher_ConfirmPass = (EditText) teacherSignupView.findViewById(R.id.Teacher_Signup_Confirm_Password);
        Teacher_Name = (EditText) teacherSignupView.findViewById(R.id.Teacher_Signup_Name);
        Teacher_Email = (EditText) teacherSignupView.findViewById(R.id.Teacher_Signup_Confirm_Email);
        Teacher_Phone = (EditText) teacherSignupView.findViewById(R.id.Teacher_Signup_Phone_Number);

        Teacher_Department = (Spinner) teacherSignupView.findViewById(R.id.Teacher_Signup_Department_Spinner);

        adp = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, Departments_Array);
        Teacher_Department.setAdapter(adp);

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        final Pattern pat = Pattern.compile(emailRegex);


        Teacher_Signup_Button = (Button) teacherSignupView.findViewById(R.id.SignupButton_Teacher);

        Teacher_Signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Teacher_Enroll.getText().toString().isEmpty() ||
                        Teacher_Password.getText().toString().isEmpty() ||
                        Teacher_ConfirmPass.getText().toString().isEmpty() ||
                        Teacher_Name.getText().toString().isEmpty() ||
                        Teacher_Email.getText().toString().isEmpty() ||
                        Teacher_Phone.getText().toString().isEmpty() ){

                    if(Teacher_Enroll.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Teacher_Enroll.setError("Enter Enroll ID");
                    }
                    else if(Teacher_Password.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Teacher_Password.setError("Enter Password");
                    }
                    else if(Teacher_ConfirmPass.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Teacher_ConfirmPass.setError("Enter Password");
                    }
                    else if(Teacher_Name.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Teacher_Name.setError("Enter Name");
                    }
                    else if(Teacher_Email.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Teacher_Email.setError("Enter Email");
                    }
                    else if(Teacher_Phone.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Teacher_Phone.setError("Enter Phone");
                    }
                }
                else{
                    if (!Teacher_Password.getText().toString().equals(Teacher_ConfirmPass.getText().toString())){
                        Toast.makeText(getActivity(),"Password does not match",Toast.LENGTH_LONG).show();
                    }
                    else if(pat.matcher(Teacher_Email.getText().toString()).matches()){
                        if(data_base.CheckAcc_Teacher(Teacher_Enroll.getText().toString())){
                            Teacher_Enroll.setError("ID already registered");
                        }
                        else{
                            data_base.Teacher_Signup(Teacher_Enroll.getText().toString(), Teacher_Password.getText().toString(), Teacher_Name.getText().toString(), Teacher_Email.getText().toString(), Teacher_Phone.getText().toString(), Teacher_Department.getSelectedItem().toString());
                            Toast.makeText(getActivity(), "Signup Succesfully & Please Login", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    }

                }
            }
        });
        return teacherSignupView;
    }
}
