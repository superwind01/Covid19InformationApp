package com.example.covid19information;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import api.VolleryResponseListener;
import api.VolleyService;
import model.ModelCommon;
import model.Today;
import model.Total;

public class Home extends AppCompatActivity {

    TextView txtTotalVietnamCases,txtTotalVietnamDeath,txtTotalVietnamRecovered,txtTotalVietnamTreating;
    TextView txtTodayVietnamCase,txtTodayVietnamDeath,txtTodayVietnamRecovered,txtTodayVietnamTreating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addEvents();
        addControls();


    }


    private void useVolley() {
        VolleyService.getRequest(this, new VolleryResponseListener() {
            @Override
            public void onErro(String mesage) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                //Khai bao cac thanh phan lay du lieu tu API
                Today today = response.getToday();
                Total total = response.getTotal();

                //Gan du lieu cho txt trong Home
                txtTodayVietnamCase.setText(String.valueOf(today.getInfoInternal().getCases()));
                txtTodayVietnamDeath.setText(String.valueOf(today.getInfoInternal().getDeath()));
                txtTodayVietnamRecovered.setText(String.valueOf(today.getInfoInternal().getRecovered()));
                txtTodayVietnamTreating.setText(String.valueOf(today.getInfoInternal().getTreating()));

                txtTotalVietnamCases.setText(String.valueOf(total.getInfoInternal().getCases()));
                txtTotalVietnamDeath.setText(String.valueOf(total.getInfoInternal().getDeath()));
                txtTotalVietnamRecovered.setText(String.valueOf(total.getInfoInternal().getRecovered()));
                txtTotalVietnamTreating.setText(String.valueOf(total.getInfoInternal().getTreating()));
            }
        });
    }

    private void addEvents() {
        Toast.makeText(Home.this, "Đang tải dữ liệu", Toast.LENGTH_SHORT).show();
        useVolley();
    }

    private void addControls() {
        txtTotalVietnamCases = findViewById(R.id.txt_total_cases);
        txtTotalVietnamDeath = findViewById(R.id.txt_total_death);
        txtTotalVietnamRecovered = findViewById(R.id.txt_total_recovered);
        txtTotalVietnamTreating = findViewById(R.id.txt_total_treating);

        txtTodayVietnamCase = findViewById(R.id.lbl_today_cases);
        txtTodayVietnamDeath = findViewById(R.id.lbl_today_death);
        txtTodayVietnamRecovered = findViewById(R.id.lbl_today_recovered);
        txtTodayVietnamTreating = findViewById(R.id.lbl_today_treating);
    }
}