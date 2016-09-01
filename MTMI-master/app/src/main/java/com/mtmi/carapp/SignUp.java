package com.mtmi.carapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button buttonKaydet;
    private EditText edittextAd;
    private EditText edittextMail;
    private EditText edittextSifre;
    private EditText edittextSifreTekrar;
    private EditText edittextDogumGunu;


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
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonKaydet = (Button) findViewById(R.id.buttonKaydet);
        edittextAd = (EditText) findViewById(R.id.edittextAd);
        edittextMail = (EditText) findViewById(R.id.edittextMail);
        edittextSifre = (EditText) findViewById(R.id.edittextSifre);
        edittextSifreTekrar = (EditText) findViewById(R.id.edittextSifreTekrar);
        edittextDogumGunu = (EditText) findViewById(R.id.dgmTrh);



        buttonKaydet.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void registerUser() {
        String name = edittextAd.getText().toString().trim();
        String mail = edittextMail.getText().toString().trim();
        String sifre = edittextSifre.getText().toString().trim();
        String sifretekrar = edittextSifreTekrar.getText().toString().trim();
        String dogumgunu = edittextDogumGunu.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "lütfen isminizi giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "lütfen mailinizi giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(sifre)) {
            Toast.makeText(this, "lütfen sifrenizi giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(sifretekrar)) {
            Toast.makeText(this, "lütfen sifrenizi giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(dogumgunu)) {
            Toast.makeText(this, "lütfen d.tarihinizi giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("üye kaydı yapılıyor..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(mail, sifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "kayıt başarılı.", Toast.LENGTH_SHORT).show();

                            Intent uyeolagit=new Intent(SignUp.this,LoginActivity.class);
                            startActivity(uyeolagit);


                        } else {
                            Toast.makeText(SignUp.this, "kayıt başarısız..tekrar dene.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



    @Override
    public void onClick(View v) {

        if (v == buttonKaydet) {
            registerUser();

        }
       /* if (v == textViewLogin) {

            Intent uyeolagit=new Intent(SignUp.this,LoginActivity.class);
            startActivity(uyeolagit);
            //will open login activity here
        }*/


    }

    @Override
    public void onStart() {
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
}









