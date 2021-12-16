package com.example.covid19information;

import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    // Khai bao cac truogn du lieu
    Button btn_Save, btn_Return;
    EditText edtFullName, edtBirthday, edtPhoneNo, edtId, edtEmail;
    RadioButton rbMale, rbFemale;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_info, container, false);
        //Lay du lieu tu cac ediText
        edtFullName = view.findViewById(R.id.editText_FullName);
        edtBirthday = view.findViewById(R.id.editText_Birthday);
        edtPhoneNo = view.findViewById(R.id.editText_PhoneNumber);
        edtId = view.findViewById(R.id.editText_Id);
        edtEmail = view.findViewById(R.id.editText_Email);
        rbMale = view.findViewById(R.id.RadioButton_Male);
        rbFemale = view.findViewById(R.id.radioButton_Female);
        btn_Save = view.findViewById(R.id.btn_Save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

        return view;
    }

    // Luu du lieu
    public void saveInfo(){
        String fullName = "Họ tên:" + edtFullName.getText().toString() +", ";
        String birthDay = "Ngày thánh năm sinh" + edtBirthday.getText().toString() +", ";
        String phoneNo = "Số điện thoại" + edtPhoneNo.getText().toString() +", ";
        String id = "Số CMND/CCCD/Passport" + edtId.getText().toString() +", ";
        String email = "Email" + edtEmail.getText().toString();

        try{
            FileOutputStream fileOutputStream = getActivity().openFileOutput("SaveInfo.txt", MODE_PRIVATE);
            fileOutputStream.write(fullName.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(birthDay.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(phoneNo.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(id.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(email.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

            Toast.makeText(getActivity(),"đã lưu thông tin",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}