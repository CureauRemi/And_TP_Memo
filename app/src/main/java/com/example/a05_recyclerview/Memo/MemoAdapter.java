package com.example.a05_recyclerview.Memo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a05_recyclerview.R;
import com.example.a05_recyclerview.activities.DetailActivity;
import com.example.a05_recyclerview.database.MemoDTO;
import com.example.a05_recyclerview.fragment.DetailFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.Collections;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    public static String TAG = "MemoAdapter";
    private List<MemoDTO> listeMemoDTO;
    private AppCompatActivity activity;

    public MemoAdapter(List<MemoDTO> listeMemoDTO) {
        this.listeMemoDTO = listeMemoDTO;
    }

    public MemoAdapter(List<MemoDTO> listeMemoDTO, AppCompatActivity activity) {
        this.activity = activity;
        this.listeMemoDTO = listeMemoDTO;
    }

    //    Changement de la position d'un item dans la liste
    public boolean onItemMove(int positionDebut, int positionFin) {
        Collections.swap(listeMemoDTO, positionDebut, positionFin);
        notifyItemMoved(positionDebut, positionFin);
        return true;
    }

    //    Suppression d'un item
    public void onItemDismiss(int position) {
        if (position > -1) {
            listeMemoDTO.remove(position);
            notifyItemRemoved(position);
        }
    }


    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewMemo = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item_liste, parent, false);
        return new MemoViewHolder(viewMemo);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {
        holder.textViewLibelleMemo.setText(listeMemoDTO.get(position).intitule);
    }

    @Override
    public int getItemCount() {
        return listeMemoDTO.size();
    }

    public class MemoViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewLibelleMemo;

        public MemoViewHolder(final View itemView) {
            super(itemView);
            textViewLibelleMemo = itemView.findViewById(R.id.libelle_memo);

            textViewLibelleMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    final MemoDTO memoDTO = listeMemoDTO.get(getAdapterPosition());
                    final
//                        Sauvegarde de la position
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putInt("positionMemo", getAdapterPosition());
                    editor.apply();


                    // client HTTP :
                    AsyncHttpClient client = new AsyncHttpClient();
//                  paramètres :
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("memo", memoDTO.intitule);

                    // #region Call API http://httpbin.org/post
//                  appel :

//                    client.post("http://httpbin.org/post", requestParams, new AsyncHttpResponseHandler()
//                    {
//                        @Override
//                        public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                            String retour = new String(response);
//
//                            Gson gson = new Gson();
//                            RetourWS retourWS = gson.fromJson(retour, RetourWS.class);
//
////                            Toast.makeText(v.getContext(), retourWS.form.memo, Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                            Log.e(TAG, error.toString());
//                            Toast.makeText(v.getContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    });

//                    #endregion

                    if (activity.findViewById(R.id.fragment_detail_memo) == null) {
                        Intent intent = new Intent(v.getContext(), DetailActivity.class);
                        intent.putExtra("memo", memoDTO.intitule);
                        v.getContext().startActivity(intent);
                    } else {
//                            Fragment
                        DetailFragment fragment = new DetailFragment();
                        Bundle bundle = new Bundle();
//                              Passage de parametre dans le fragment
                        bundle.putString(DetailFragment.EXTRA_PARAMETRE, memoDTO.intitule);
                        fragment.setArguments(bundle);

//                              Fragment Manager
                        FragmentManager fragmentManager = activity.getSupportFragmentManager();

//                              Transaction
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_detail_memo, fragment, "detail");
                        fragmentTransaction.commit();
                    }
                }
            });
        }
    }
}
