package com.example.a05_recyclerview.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a05_recyclerview.R;
import com.example.a05_recyclerview.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String memo = getIntent().getStringExtra("memo");

//        Fragment
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
//        Passage de parametre dans le fragment
        bundle.putString(DetailFragment.EXTRA_PARAMETRE, memo);
        fragment.setArguments(bundle);

//        Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

//        Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.conteneur_detail, fragment, "detail");
        fragmentTransaction.commit();
    }

}
