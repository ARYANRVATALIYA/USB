package com.example.unistudentbridge;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.regex.Pattern;

public class StudentSignup extends Fragment {

    View studentSignupView;

    String Sem_Array[] = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight"};
    String Departments_Array[] = new String[]{"CE", "CSE", "IT"};

    ArrayAdapter adp;


    DATA_Base data_base;

    EditText Student_Enroll, Student_Password, Student_ConfirmPass, Student_Name, Student_Email, Student_Phone;

    Spinner Student_Department, Student_Sem;

    Button Student_Signup_Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        studentSignupView = inflater.inflate(R.layout.fragment_student_signup, container, false);

        data_base = new DATA_Base(getActivity());

        Student_Enroll = (EditText) studentSignupView.findViewById(R.id.Student_Signup_Enroll);
        Student_Password = (EditText) studentSignupView.findViewById(R.id.Student_Signup_Password);
        Student_ConfirmPass = (EditText) studentSignupView.findViewById(R.id.Student_Signup_Confirm_Password);
        Student_Name = (EditText) studentSignupView.findViewById(R.id.Student_Signup_Name);
        Student_Email = (EditText) studentSignupView.findViewById(R.id.Student_Signup_Confirm_Email);
        Student_Phone = (EditText) studentSignupView.findViewById(R.id.Student_Signup_Phone_Number);


        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        final Pattern pat = Pattern.compile(emailRegex);


        Student_Department = (Spinner) studentSignupView.findViewById(R.id.Student_Signup_Department_Spinner);
        Student_Sem = (Spinner) studentSignupView.findViewById(R.id.Student_Signup_Sem_Spinner);

        adp = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, Departments_Array);
        Student_Department.setAdapter(adp);

        adp = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, Sem_Array);
        Student_Sem.setAdapter(adp);

        Student_Signup_Button = (Button) studentSignupView.findViewById(R.id.SignupButton_Student);

        Student_Signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Student_Enroll.getText().toString().isEmpty() ||
                    Student_Password.getText().toString().isEmpty() ||
                    Student_ConfirmPass.getText().toString().isEmpty() ||
                    Student_Name.getText().toString().isEmpty() ||
                    Student_Email.getText().toString().isEmpty() ||
                    Student_Phone.getText().toString().isEmpty() ){

                    if(Student_Enroll.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Student_Enroll.setError("Enter Enroll ID");
                    }
                    else if(Student_Password.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Student_Password.setError("Enter Password");
                    }
                    else if(Student_ConfirmPass.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Student_ConfirmPass.setError("Enter Password");
                    }
                    else if(Student_Name.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Student_Name.setError("Enter Name");
                    }
                    else if(Student_Email.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Student_Email.setError("Enter Email");
                    }
                    else if(Student_Phone.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "Signup Failure", Toast.LENGTH_SHORT).show();
                        Student_Phone.setError("Enter Phone");
                    }
                }
                else{
                    if (!Student_Password.getText().toString().equals(Student_ConfirmPass.getText().toString())){
                        Toast.makeText(getActivity(),"Password does not match",Toast.LENGTH_LONG).show();
                    }
                    else if(pat.matcher(Student_Email.getText().toString()).matches()){
                        if(data_base.CheckAcc_Student(Student_Enroll.getText().toString())){
                            Student_Enroll.setError("ID already registered");
                        }
                        else{
                            data_base.Student_Signup(Student_Enroll.getText().toString(), Student_Password.getText().toString(), Student_Name.getText().toString(), Student_Email.getText().toString(), Student_Phone.getText().toString(), Student_Department.getSelectedItem().toString(), Student_Sem.getSelectedItem().toString());
                            Toast.makeText(getActivity(), "Signup Succesfully & Please Login", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    }

                }
            }
        });
        return studentSignupView;
    }


}