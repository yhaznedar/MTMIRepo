package com.mtmi.carapp;

import android.app.FragmentManager;
import android.content.ClipData;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mobiltek10 on 9/8/16.
 */

    public class ArabaAdapter extends RecyclerView.Adapter<ArabaAdapter.ArabaViewHolder> {

        private List<Araba> arabaList;


        public ArabaAdapter(List<Araba> arabaList) {
            this.arabaList = arabaList;
        }

        @Override
        public int getItemCount() {
            return arabaList.size();
        }

        @Override
        public void onBindViewHolder(ArabaViewHolder arabaViewHolder, int i) {
            Araba ci = arabaList.get(i);
            arabaViewHolder.varacMarka.setText(ci.aracMarka);
            arabaViewHolder.varacModel.setText(ci.aracModel);
            arabaViewHolder.varacPlaka.setText(ci.aracPlaka);



        }

        @Override
        public ArabaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.cardview, viewGroup, false);

            return new ArabaViewHolder(itemView);
        }





    public class ArabaViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {



        protected TextView varacMarka;
        protected TextView varacModel;
        protected TextView varacPlaka;



        public ArabaViewHolder(View v) {
            super(v);
            varacMarka =  (TextView) v.findViewById(R.id.arac_marka);
            varacModel = (TextView)  v.findViewById(R.id.arac_model);
            varacPlaka = (TextView)  v.findViewById(R.id.arac_plaka);



            v.setOnClickListener(this);


        }



        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            Toast.makeText(v.getContext(), varacMarka.getText()+"\n"+varacModel.getText()+"\n"+varacPlaka.getText(), Toast.LENGTH_SHORT).show();





        }
    }





}
