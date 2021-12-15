package com.example.covid19information;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Info_activity extends AppCompatActivity {
    // Khai bao cac truogn du lieu
    Button btn_Save, btn_Return;
    EditText edtFullName, edtBirthday, edtPhoneNo, edtId, edtEmail;
    RadioButton rbMale, rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //Lay du lieu tu cac ediText
        edtFullName = findViewById(R.id.editText_FullName);
        edtBirthday = findViewById(R.id.editText_Birthday);
        edtPhoneNo = findViewById(R.id.editText_PhoneNumber);
        edtId = findViewById(R.id.editText_Id);
        edtEmail = findViewById(R.id.editText_Email);
        rbMale = findViewById(R.id.RadioButton_Male);
        rbFemale = findViewById(R.id.radioButton_Female);
        btn_Save = findViewById(R.id.btn_Save);
        btn_Return = findViewById(R.id.btn_return);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

        btn_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    // Luu du lieu
    public void saveInfo(){
        String fullName = "Họ tên:" + edtFullName.getText().toString() +", ";
        String birthDay = "Ngày thánh năm sinh" + edtBirthday.getText().toString() +", ";
        String phoneNo = "Số điện thoại" + edtPhoneNo.getText().toString() +", ";
        String id = "Số CMND/CCCD/Passport" + edtId.getText().toString() +", ";
        String email = "Email" + edtEmail.getText().toString();

        try{
            FileOutputStream fileOutputStream = openFileOutput("SaveInfo.txt", MODE_PRIVATE);
            fileOutputStream.write(fullName.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(birthDay.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(phoneNo.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(id.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(email.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

            Toast.makeText(getApplicationContext(),"đã lưu thông tin",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}