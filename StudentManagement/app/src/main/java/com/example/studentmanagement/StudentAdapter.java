package com.example.studentmanagement;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentAdapter extends BaseAdapter{
    Cursor cursor;
//    List<String> mssvs;
//    List<String> names;

    public StudentAdapter (Cursor cursor) {
        this.cursor = cursor;
//        mssvs = new ArrayList<>();
//        names = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        return cursor.moveToPosition(i);
    }

    @Override
    public long getItemId(int i) {
        cursor.moveToPosition(i);
//        return cursor.getString(0);
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_students_layout, viewGroup, false);
        TextView mssv = view.findViewById(R.id.mssv);
        TextView textName = view.findViewById(R.id.name);
        TextView ngay_sinh = view.findViewById(R.id.birthday);
        TextView email = view.findViewById(R.id.email);

        cursor.moveToPosition(i);
        String mssvString = cursor.getString(0);
        String name = cursor.getString(1);
        String birthday = cursor.getString(2);
        String emailString = cursor.getString(3);

        mssv.setText(mssvString);
        textName.setText(name);
        ngay_sinh.setText(birthday);
        email.setText(emailString);

//        mssvs.add(mssvString);
//        names.add(name);
        return view;
    }
}
