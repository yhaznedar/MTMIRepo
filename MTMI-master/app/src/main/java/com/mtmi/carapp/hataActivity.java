package com.mtmi.carapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class hataActivity extends AppCompatActivity {

    TextView yenidenDene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hata);

        yenidenDene= (TextView) findViewById(R.id.yenidenDene);

        yenidenDene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {

                if (internetErisimi())

                {


                        Intent log = new Intent(hataActivity.this, LoginActivity.class);
                        startActivity(log);



                }
                else
                {

                        InternetHata();
                }



            }
        });
    }

    public boolean internetErisimi() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected()) {

            return true;

        } else {

            return false;

        }

    }

    public void InternetHata()
    {

        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Bağlantı hatası");
        alertDialog.setMessage("Lütfen internet bağlantınızın olduğundan emin olunuz.");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE ,"Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();


    }



}
