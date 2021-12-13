package com.example.covid19information;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import api.VolleryResponseListener;
import api.VolleyService;
import model.ModelCommon;
import model.Today;
import model.Total;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private BottomNavigationView mBottomNavigationView;

    // Khai bao text view o HomeFragment
    TextView txtTotalVietnamCases,txtTotalVietnamDeath,txtTotalVietnamRecovered,txtTotalVietnamTreating;
    TextView txtTodayVietnamCase,txtTodayVietnamDeath,txtTodayVietnamRecovered,txtTodayVietnamTreating;

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //Khai bao bottomNavigationView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Do du lieu tu API vao textview
        addEvents();

        //Gan ID cho 2 bien da khai bao
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Adapter cho mViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(adapter);

        //Tao su kien khi chom vao icon o bottom navigation thi se gan gia tri cho mViewPager
        mBottomNavigationView.setOnItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if(id == R.id.nav_home){
                mViewPager.setCurrentItem(0);
            }
            else if(id == R.id.nav_qr){
                mViewPager.setCurrentItem(1);
            }
            else
                mViewPager.setCurrentItem(2);
            return true;
        });

        //Tao su kien khi Chuyen sang trang khac thi Checked item o bottom navigation
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;

                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.nav_qr).setChecked(true);
                        break;

                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.nav_health).setChecked(true);
                        break;

                }
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
                    runOnUiThread(new Thread(() -> {
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

}