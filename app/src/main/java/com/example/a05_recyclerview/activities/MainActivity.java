package com.example.a05_recyclerview.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a05_recyclerview.Memo.ItemTouchHelperCallback;
import com.example.a05_recyclerview.Memo.MemoAdapter;
import com.example.a05_recyclerview.R;
import com.example.a05_recyclerview.database.AppDatabaseHelper;
import com.example.a05_recyclerview.database.MemoDTO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MemoAdapter memoAdapter;
    private List<MemoDTO> listMemoDTO = new ArrayList<>();
    private EditText txtboxAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.liste_memo);

        listMemoDTO = AppDatabaseHelper.getDatabase(this).memoDAO().getListMemo();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int valeur = preferences.getInt("positionMemo", -1);

        if (valeur > -1) {
            Toast.makeText(this, "La derniere valeur cliquée est " + valeur, Toast.LENGTH_SHORT).show();
        }

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        memoAdapter = new MemoAdapter(listMemoDTO, this);
        recyclerView.setAdapter(memoAdapter);

//       Suppression / Modification d'un item
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(memoAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);


        Button btnAddMemo = findViewById(R.id.addMemo);
        txtboxAdd = findViewById(R.id.libelle_new_memo);

        btnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoDTO newMemoDTO = new MemoDTO(txtboxAdd.getText().toString());
                AppDatabaseHelper.getDatabase(v.getContext()).memoDAO().insert(newMemoDTO);
                txtboxAdd.setText("");
                listMemoDTO.add(newMemoDTO);
                memoAdapter.notifyItemInserted(listMemoDTO.size());
            }
        });

    }
}
