package com.mtmi.carapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class hataActivity extends AppCompatActivity {

    TextView yenidenDene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hata);

        yenidenDene= (TextView) findViewById(R.id.yenidenDene);

        yenidenDene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log= new Intent(hataActivity.this, LoginActivity.class);
                startActivity(log);

            }
        });
    }
}
