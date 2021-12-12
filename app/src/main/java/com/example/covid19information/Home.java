package com.example.covid19information;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import api.VolleryResponseListener;
import api.VolleyService;
import model.Info;
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

//        // hide status bar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addControls();
        addEvents();
    }



    public void addEvents() {
                VolleyService.getRequest(this, new VolleryResponseListener() {
                    @Override
                    public void onErro(String mesage) {

                    }

                    @Override
                    public void onResponse(ModelCommon response) {
                //Khai bao cac thanh phan lay du lieu tu API
                if (response != null) {
                    Toast.makeText(Home.this, "Đang tải dữ liệu", Toast.LENGTH_SHORT).show();
                    final Info today = response.getToday().getInfoInternal();
                    Log.e("Log_today_home", today.toString());
                    final Total total = response.getTotal();
                    runOnUiThread(new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Gan du lieu cho txt trong Home
                            txtTodayVietnamCase.setText(String.valueOf(today.getCases()));
                            txtTodayVietnamDeath.setText(String.valueOf(today.getDeath()));
                            txtTodayVietnamRecovered.setText(String.valueOf(today.getRecovered()));
                            txtTodayVietnamTreating.setText(String.valueOf(today.getTreating()));

                            txtTotalVietnamCases.setText(String.valueOf(total.getInfoInternal().getCases()));
                            txtTotalVietnamDeath.setText(String.valueOf(total.getInfoInternal().getDeath()));
                            txtTotalVietnamRecovered.setText(String.valueOf(total.getInfoInternal().getRecovered()));
                            txtTotalVietnamTreating.setText(String.valueOf(total.getInfoInternal().getTreating()));
                        }

                    }));
                } else {
                    Toast.makeText(Home.this, "Không thể tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
                    }
                });
    }

    public void addControls()
    {
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