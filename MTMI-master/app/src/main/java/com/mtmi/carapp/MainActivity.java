package com.mtmi.carapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
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


    private FloatingActionButton fab;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private GoogleApiClient client;

    private List<Araba> arabalar;
    private RecyclerView rv;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){

            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);


        fab= (FloatingActionButton) findViewById(R.id.eklebutonu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddingCarActivity.class);
                startActivity(intent);
            }
        });


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

        rv=(RecyclerView)findViewById(R.id.cv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);



        initializeData();
        initializeAdapter();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { //SWIPE OLAYI

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);


       /* mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {  //YENİLE OLAYI
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });*/


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
        if (id == R.id.action_settings)
        {
            Intent hakkimizda = new Intent(MainActivity.this, Hakkimizda.class);
            startActivity(hakkimizda);
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

    private void initializeData(){
        arabalar = new ArrayList<>();
        arabalar.add(new Araba("Mercedes", "CLK", "34 G 4402"));
        arabalar.add(new Araba("BMW", "X1", "34 GRS 36"));
        arabalar.add(new Araba("Audi", "A6", "34 DD 7980"));
        arabalar.add(new Araba("Renault", "Clio", "34 SR 773"));
        arabalar.add(new Araba("Ford", "Focus", "34 P 3360"));
        arabalar.add(new Araba("BMW", "X6", "34 S 7736"));
        arabalar.add(new Araba("Ford", "Fiesta", "34 GR 3126"));
    }

    private void initializeAdapter(){
        ArabaAdapter adapter = new ArabaAdapter(arabalar);
        rv.setAdapter(adapter);
    }



    /*private void refreshItems() {
        // Load items
        // ...

        // Load complete
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }*/







}
