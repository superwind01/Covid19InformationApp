package com.example.covid19information;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import api.VolleryResponseListener;
import api.VolleyService;
import model.ModelCommon;
import model.Today;
import model.Total;

public class MainActivity extends AppCompatActivity {
    // Khai bao text view o HomeFragment
    TextView txtTotalVietnamCases,txtTotalVietnamDeath,txtTotalVietnamRecovered,txtTotalVietnamTreating;
    TextView txtTodayVietnamCase,txtTodayVietnamDeath,txtTodayVietnamRecovered,txtTodayVietnamTreating;

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //Khai bao bottomNavigationView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tao
        if(savedInstanceState != null)
        {
            addControls();
            txtTotalVietnamCases.setText(savedInstanceState.getInt("string_total_cases")) ;
            txtTotalVietnamDeath.setText(savedInstanceState.getInt("string_total_death")) ;
            txtTotalVietnamRecovered.setText(savedInstanceState.getInt("string_total_recovered")) ;
            txtTotalVietnamTreating.setText(savedInstanceState.getInt("string_total_treating")) ;

            txtTodayVietnamCase.setText(savedInstanceState.getInt("string_today_cases")) ;
            txtTodayVietnamDeath.setText(savedInstanceState.getInt("string_today_death")) ;
            txtTodayVietnamRecovered.setText(savedInstanceState.getInt("string_today_recovered")) ;
            txtTodayVietnamTreating.setText(savedInstanceState.getInt("string_today_treating")) ;
        }
        else {
            //Do du lieu tu API vao textview
            addEvents();
        }

        BottomNavigationView navigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_body,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        //Mo fragment moi sau khi chon icon o bottom navigation
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){@Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId())
            {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.nav_qr:
                    fragment = new QRFragment();
                    break;

                case R.id.nav_health:
                    fragment = new HealthFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_body, fragment).commit();
            return true;
        }
        });


    }

    ///////////////////////////////////////////////////////////////////////////////////////////


    //Lay Request tu VolleyService
    public void addEvents() {
        VolleyService.getRequest(this, new VolleryResponseListener() {
            @Override
            public void onErro(String mesage) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                //Khai bao cac thanh phan lay du lieu tu API
                if (response != null) {
                    Toast.makeText(MainActivity.this, "Đang tải dữ liệu", Toast.LENGTH_SHORT).show();
                    final Today today = response.getToday();
                    Log.e("Log_today_home", today.toString());
                    final Total total = response.getTotal();
                    runOnUiThread(new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Gan ID cho text view
                            addControls();
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

                    }));
                } else {
                    Toast.makeText(MainActivity.this, "Không thể tải được dữ liệu", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putString("string_total_cases",txtTotalVietnamCases.toString());
        outState.putString("string_total_death", txtTotalVietnamDeath.toString());
        outState.putString("string_total_recovered", txtTotalVietnamRecovered.toString());
        outState.putString("string_total_treating", txtTotalVietnamTreating.toString());

        outState.putString("string_today_cases", txtTodayVietnamCase.toString());
        outState.putString("string_today_death", txtTodayVietnamDeath.toString());
        outState.putString("string_today_recovered", txtTodayVietnamRecovered.toString());
        outState.putString("string_today_treating", txtTodayVietnamTreating.toString());
        super.onSaveInstanceState(outState);
    }
}