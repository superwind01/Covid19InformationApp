package com.example.covid19information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import model.Place;
import sharedPreferences.DataLocalManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceHasBeenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceHasBeenFragment extends Fragment {

    ListView listView;
    Button btnQrScan;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlaceHasBeenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QRFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceHasBeenFragment newInstance(String param1, String param2) {
        PlaceHasBeenFragment fragment = new PlaceHasBeenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Place> addPlace = new ArrayList<>();
        Place place1 = new Place(1, "15/12/2021", "11:50PM", "location|76965849653227|2094300a");
        Place place2 = new Place(2, "16/12/2021", "12:55AM", "location|76965849653227|2094300b");
        addPlace.add(place1);
        addPlace.add(place2);
        DataLocalManager.setListPlace(addPlace);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_has_been, container, false);
        ArrayList<Place> placesList = (ArrayList<Place>) DataLocalManager.getListPlace();

        listView = view.findViewById(R.id.list_place);
        ListViewAdapter adapter = new ListViewAdapter(placesList);
        listView.setAdapter(adapter);

        Button btnQrScan = (Button) view.findViewById(R.id.btn_qr_scan);
        btnQrScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager2 mViewPager = view.findViewById(R.id.view_pager);
                QRFragment qrFragment = new QRFragment();
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
                mViewPager.setAdapter(viewPagerAdapter);

            }
        });
        return view;
    }
}