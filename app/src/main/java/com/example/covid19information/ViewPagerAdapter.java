package com.example.covid19information;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            default:
                return new HomeFragment();
            case 0:
                return new HomeFragment();

            case 1:
                return new PlaceHasBeenFragment();

            case 2:
                return new InfoFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
