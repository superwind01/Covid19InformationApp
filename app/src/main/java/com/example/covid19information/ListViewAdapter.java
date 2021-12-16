package com.example.covid19information;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.Place;

public class ListViewAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<Place> listPlace;

    ListViewAdapter(ArrayList<Place> listPlace){
        this.listPlace = listPlace;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listPlace.size();
    }

    @Override
    public Object getItem(int i) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listPlace.get(i);
    }

    @Override
    public long getItemId(int i) {
        //Trả về một ID của phần
        return listPlace.get(i).getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewPlace;
        viewPlace = View.inflate(parent.getContext(), R.layout.place_menu, null);

        //Bind sữ liệu phần tử vào View
        Place place = this.listPlace.get(position);
        TextView txtDate = viewPlace.findViewById(R.id.txt_place_date);
        TextView txtTime = viewPlace.findViewById(R.id.txt_place_time);
        TextView txtLocation = viewPlace.findViewById(R.id.txt_place_locations);

        txtDate.setText(place.getDate());
        txtTime.setText(place.getTime());
        txtLocation.setText(place.getLocations());

        return viewPlace;
    }
}
