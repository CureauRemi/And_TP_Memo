package com.example.a05_recyclerview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.a05_recyclerview.R;


public class DetailFragment extends Fragment {

    public static String EXTRA_PARAMETRE = "EXTRA_PARAMETRE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null && getView() != null) {
            String argument = getArguments().getString(EXTRA_PARAMETRE);
            TextView txtView = getView().findViewById(R.id.libelle_detail_memo);
            txtView.setText(argument);

        }
    }
}
