package com.example.currency;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class CurrencyFragment extends Fragment {
    View view;
    Spinner fromSpinner;
    Spinner toSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_currency, container, false);
        fromSpinner = view.findViewById(R.id.fromSpinner);
        toSpinner = view.findViewById(R.id.toSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.planets_array, android.R.layout.simple_dropdown_item_1line);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}