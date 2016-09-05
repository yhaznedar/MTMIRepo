package com.mtmi.carapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String[] car_name;
    TypedArray car_pic;
    String[] status;
    String[] contactType;

    List<RowItem> rowItems;
    ListView carListview;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        View hView =  navigationView.getHeaderView(0);
        TextView gelenMail= (TextView)hView.findViewById(R.id.gelenMail);
        gelenMail.setText(user.getEmail());
        Intent intent=getIntent();





        rowItems = new ArrayList<RowItem>();
        car_name=getResources().getStringArray(R.array.CarName);
        car_pic=getResources().obtainTypedArray(R.array.CarPicture);
        status= getResources().getStringArray(R.array.status);
        contactType= getResources().getStringArray(R.array.ConcactType);

        for(int i=0;i<car_name.length;i++){
            RowItem item = new RowItem(car_name[i],car_pic.getResourceId(i,-1),status[i],contactType[i]);
            rowItems.add(item);
        }

        carListview = (ListView) findViewById(R.id.listCar);
        RowAdapter adapter = new RowAdapter(this,rowItems);
        carListview.setAdapter(adapter);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_car)
        {

        }

        else if (id == R.id.nav_notification )

        {

        }
        else if (id == R.id.nav_userper)
        {


        } else if (id == R.id.nav_logout)
        {


                        firebaseAuth.signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();

        }





        else if (id == R.id.nav_app_oyla)
        {


                    try
                    {
                        Intent viewIntent = new Intent("android.intent.action.VIEW",Uri.parse("https://play.google.com/store/apps/details?id=com.spotify.music&hl=tr"));
                        startActivity(viewIntent);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Google Play Mağazasına bağlanılamadı.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }



}
