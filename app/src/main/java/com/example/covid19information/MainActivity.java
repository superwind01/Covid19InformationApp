package com.example.covid19information;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private BottomNavigationView mBottomNavigationView;



    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //Khai bao bottomNavigationView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Gan ID cho 2 bien da khai bao
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Adapter cho mViewPager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(adapter);

        //Tao su kien khi chon vao icon o bottom navigation thi se gan gia tri cho mViewPager
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




}