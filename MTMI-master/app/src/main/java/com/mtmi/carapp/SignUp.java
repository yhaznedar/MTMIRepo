package com.mtmi.carapp;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp extends AppCompatActivity  {
    public Button buttonKaydet;
    public EditText mPersonName;
    public EditText mEmailView;
    public EditText mPasswordView;
    public EditText mPasswordView2;
    public EditText mBirthDate;
    public TextView textViewLogin;
    public boolean cancel2 = false;
    Calendar myCalendar = Calendar.getInstance();
    String myFormat = "dd/MM/yyyy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonKaydet = (Button) findViewById(R.id.buttonKaydet);
        mPersonName = (EditText) findViewById(R.id.edittextAd);
        mEmailView = (EditText) findViewById(R.id.edittextMail);
        mPasswordView = (EditText) findViewById(R.id.edittextSifre);
        mPasswordView2 = (EditText) findViewById(R.id.edittextSifreTekrar);
        mBirthDate = (EditText) findViewById(R.id.dgmTrh);
        textViewLogin= (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent uyeolagit=new Intent(SignUp.this,LoginActivity.class);
                startActivity(uyeolagit);
            }
        });

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();




        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mBirthDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        mBirthDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                new DatePickerDialog(SignUp.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        mBirthDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SignUp.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




    }

    private void registerUser()
    {
        String name = mPersonName.getText().toString().trim();
        String mail = mEmailView.getText().toString().trim();
        String sifre = mPasswordView.getText().toString().trim();
        String sifretekrar = mPasswordView2.getText().toString().trim();
        String dogumgunu = mBirthDate.getText().toString().trim();


        mPersonName.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordView2.setError(null);
        View focusView = null;

        if (TextUtils.isEmpty(name)) {
            mPersonName.setError(getString(R.string.error_field_required));
            focusView = mPersonName;
            cancel2 = true;
        }


        if (TextUtils.isEmpty(mail)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel2 = true;
        } else if (!isPasswordValid(mail)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel2 = true;
        }

        if (TextUtils.isEmpty(sifre)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel2 = true;
        }

        if (TextUtils.isEmpty(dogumgunu)) {
            mBirthDate.setError(getString(R.string.error_field_required));
            focusView = mBirthDate;
            cancel2 = true;
        } else if (!isPasswordValid(sifre)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel2 = true;
        }


        if (TextUtils.isEmpty(sifretekrar)) {

            mPasswordView2.setError(getString(R.string.error_field_required));
            focusView = mPasswordView2;
            cancel2 = true;
        } else if (!isPasswordValid(sifre)) {
            mPasswordView2.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView2;
            cancel2 = true;
        }

        if (!sifre.equals(sifretekrar)) {
            mPasswordView.setError(getString(R.string.error_matching));
            focusView = mPasswordView;
            cancel2 = true;
        }
        if (internetErisimi()) {
            if (cancel2 != true)

            {
                progressDialog.setMessage("İşlem yapılıyor...");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(mail, sifre)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    progressDialog.dismiss();
                                    Toast.makeText(SignUp.this, "Kayıt başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                    Intent girisgit = new Intent(SignUp.this, MainActivity.class);
                                    startActivity(girisgit);


                                } else
                                    Toast.makeText(SignUp.this, "Kayıt olma işlemi başarız. Lütfen tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        });
            }
        }
        else

        {
            Intent hata = new Intent(SignUp.this, hataActivity.class);
            startActivity(hata);
        }

        cancel2 = false;
    }



    @Override
    public void onStart()
        {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SignUp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.mtmi.carapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SignUp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.mtmi.carapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }
    private boolean isEmailValid(String mail) {
        //TODO: Replace this with your own logic
        //selam
        return mail.contains("@") && mail.contains(".com");
    }

    private boolean isPasswordValid(String sifre) {
        //TODO: Replace this with your own logic
        return sifre.length() > 5;
    }

    public boolean internetErisimi() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected()) {

            return true;

        } else {

            return false;

        }

    }







}









