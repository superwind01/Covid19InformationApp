package com.example.covid19information;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import api.VolleryResponseListener;
import api.VolleyService;
import model.ModelCommon;
import model.Today;
import model.Total;

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
    Button btnVietnam, btnWorld;
    int pick_item ;

    // Khai bao text view o HomeFragment
    TextView txtTotalCases,txtTotalDeath,txtTotalRecovered,txtTotalTreating;
    TextView txtTodayCase,txtTodayDeath,txtTodayRecovered,txtTodayTreating;
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

        pick_item = 0;
        //Do du lieu tu API vao textview
        addEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        btnVietnam = view.findViewById(R.id.btn_vietnam);
        btnWorld = view.findViewById(R.id.btn_world);

        // pick item = 0  thi se mo trang vietnam, pic item = 1 thi mo trang world
        btnVietnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick_item = 0;
                addEvents();
            }
        });

        btnWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pick_item = 1;
                addEvents();
            }
        });

        return view;
    }

    //Lay Request tu VolleyService
    public void addEvents() {
        VolleyService.getRequest(getActivity(), new VolleryResponseListener() {
            @Override
            public void onErro(String mesage) {

            }

            @Override
            public void onResponse(ModelCommon response) {
                //Khai bao cac thanh phan lay du lieu tu API
                if (response != null) {
                    Toast.makeText(getActivity(), "Đang tải dữ liệu", Toast.LENGTH_SHORT).show();
                    final Today today = response.getToday();
                    Log.e("Log_today_home", today.toString());
                    final Total total = response.getTotal();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Gan ID cho text view
                            addControls();
                            //Gan du lieu cua vietnam cho txt va lbl
                            if(pick_item == 0) {
                                txtTodayCase.setText(String.valueOf(today.getInfoInternal().getCases()));
                                txtTodayDeath.setText(String.valueOf(today.getInfoInternal().getDeath()));
                                txtTodayRecovered.setText(String.valueOf(today.getInfoInternal().getRecovered()));
                                txtTodayTreating.setText(String.valueOf(today.getInfoInternal().getTreating()));

                                txtTotalCases.setText(String.valueOf(total.getInfoInternal().getCases()));
                                txtTotalDeath.setText(String.valueOf(total.getInfoInternal().getDeath()));
                                txtTotalRecovered.setText(String.valueOf(total.getInfoInternal().getRecovered()));
                                txtTotalTreating.setText(String.valueOf(total.getInfoInternal().getTreating()));
                            }
                            //Gan du lieu cua world cho txt va lbl
                            else
                            {
                                txtTodayCase.setText(String.valueOf(today.getInfoWorld().getCases()));
                                txtTodayDeath.setText(String.valueOf(today.getInfoWorld().getDeath()));
                                txtTodayRecovered.setText(String.valueOf(today.getInfoWorld().getRecovered()));
                                txtTodayTreating.setText(String.valueOf(today.getInfoWorld().getTreating()));

                                txtTotalCases.setText(String.valueOf(total.getInfoWorld().getCases()));
                                txtTotalDeath.setText(String.valueOf(total.getInfoWorld().getDeath()));
                                txtTotalRecovered.setText(String.valueOf(total.getInfoWorld().getRecovered()));
                                txtTotalTreating.setText(String.valueOf(total.getInfoWorld().getTreating()));
                            }
                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "Không thể tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addControls()
    {
        txtTotalCases = view.findViewById(R.id.txt_total_cases);
        txtTotalDeath = view.findViewById(R.id.txt_total_death);
        txtTotalRecovered = view.findViewById(R.id.txt_total_recovered);
        txtTotalTreating = view.findViewById(R.id.txt_total_treating);

        txtTodayCase = view.findViewById(R.id.lbl_today_cases);
        txtTodayDeath = view.findViewById(R.id.lbl_today_death);
        txtTodayRecovered = view.findViewById(R.id.lbl_today_recovered);
        txtTodayTreating = view.findViewById(R.id.lbl_today_treating);
    }


}