package com.example.covid19information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;

    TextView txtTotalVietnamCases,txtTotalVietnamDeath,txtTotalVietnamRecovered,txtTotalVietnamTreating;
    TextView txtTodayVietnamCase,txtTodayVietnamDeath,txtTodayVietnamRecovered,txtTodayVietnamTreating;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        if (savedInstanceState != null) {
            txtTotalVietnamCases = view.findViewById(R.id.txt_total_cases);
            txtTotalVietnamDeath = view.findViewById(R.id.txt_total_death);
            txtTotalVietnamRecovered = view.findViewById(R.id.txt_total_recovered);
            txtTotalVietnamTreating = view.findViewById(R.id.txt_total_treating);

            txtTodayVietnamCase = view.findViewById(R.id.lbl_today_cases);
            txtTodayVietnamDeath = view.findViewById(R.id.lbl_today_death);
            txtTodayVietnamRecovered = view.findViewById(R.id.lbl_today_recovered);
            txtTodayVietnamTreating = view.findViewById(R.id.lbl_today_treating);

            txtTotalVietnamCases.setText(savedInstanceState.getInt("string_total_cases")) ;
            txtTotalVietnamDeath.setText(savedInstanceState.getInt("string_total_death")) ;
            txtTotalVietnamRecovered.setText(savedInstanceState.getInt("string_total_recovered")) ;
            txtTotalVietnamTreating.setText(savedInstanceState.getInt("string_total_treating")) ;

            txtTodayVietnamCase.setText(savedInstanceState.getInt("string_today_cases")) ;
            txtTodayVietnamDeath.setText(savedInstanceState.getInt("string_today_death")) ;
            txtTodayVietnamRecovered.setText(savedInstanceState.getInt("string_today_recovered")) ;
            txtTodayVietnamTreating.setText(savedInstanceState.getInt("string_today_treating")) ;
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        // Inflate the layout for this fragment
        else{
                view = inflater.inflate(R.layout.fragment_home, container, false);

            }
        return view;
    }

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