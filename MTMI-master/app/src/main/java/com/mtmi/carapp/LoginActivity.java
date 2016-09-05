package com.mtmi.carapp;


import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageButton buttonGiris;
    public EditText EmailView;
    public EditText PasswordView;
    public ImageView imageView;
    public TextView TextViewsignUpGit;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    SharedPreferences preferences;
    //preferences için bir nesne tanımlıyorum.
    SharedPreferences.Editor editor;
    //preferences içerisine bilgi girmek için tanımlama

    public final static String MAILKEY = "E-posta";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

        EmailView = (EditText) findViewById(R.id.email);
        imageView = (ImageView) findViewById(R.id.imageView);
        PasswordView = (EditText) findViewById(R.id.password);
        buttonGiris=(ImageButton) findViewById(R.id.email_sign_in_button);
        TextViewsignUpGit=(TextView)findViewById(R.id.signUpGit);

        progressDialog=new ProgressDialog(this);

        buttonGiris.setOnClickListener(this);
        TextViewsignUpGit.setOnClickListener(this);


        EmailView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {

                imageView.getLayoutParams().height = 120;
                imageView.getLayoutParams().width = 120;

            }
        });

        PasswordView.setOnClickListener(new OnClickListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                imageView.getLayoutParams().height = 180;
                imageView.getLayoutParams().width = 180;
            }
        });*/

        mEmailSignInButton= (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                UserLogin();
            }
        });


        TextView mSignUpView = (TextView) findViewById(R.id.signUpGit);
        mSignUpView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent uyeolagit = new Intent(LoginActivity.this, SignUp.class);
                startActivity(uyeolagit);
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
       /* preferences= getSharedPreferences("profile", Context.MODE_PRIVATE);
        editor=preferences.edit();
        String emailbilgi=preferences.getString("eposta","");
        String sifrebilgi=preferences.getString("sifre","");
        EmailView.setText(emailbilgi);
        PasswordView.setText(sifrebilgi);*/


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.mtmi.carapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*editor.clear();
        editor.apply();
        EmailView.setText("");
        PasswordView.setText("");*/
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


    public void attemptLogin() {

        // Reset errors.
        EmailView.setError(null);
        PasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = EmailView.getText().toString();
        String password = PasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            PasswordView.setError(getString(R.string.error_field_required));
            focusView = PasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            PasswordView.setError(getString(R.string.error_invalid_password));
            focusView = PasswordView;
            cancel = true;
        }


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            EmailView.setError(getString(R.string.error_field_required));
            focusView = EmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            EmailView.setError(getString(R.string.error_invalid_email));
            focusView = EmailView;
            cancel = true;
        }


        if (internetErisimi()) {

            if (cancel != true) {
                Toast.makeText(this, "Giriş yapılıyor...", Toast.LENGTH_SHORT).show();
                Intent mesajIntent = new Intent(this, MainActivity.class);
                mesajIntent.putExtra(MAILKEY, email);
                startActivity(mesajIntent);
            }

        } else //internet yoksa
        {
           /* editor.putString("eposta", mailView.getText().toString());
            editor.putString("sifre", PasswordView.getText().toString());
            editor.commit();*/
            Intent hata = new Intent(this, hataActivity.class);
            startActivity(hata);

        }
}


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //selam
        return email.contains("@") && email.contains(".com");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }



        if (success) {
            finish();
        } else {
            PasswordView.setError(getString(R.string.error_incorrect_password));
            PasswordView.requestFocus();
        }
    }

    protected void onCancelled() {


    }


    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.mtmi.carapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    private void UserLogin(){
        String email= EmailView.getText().toString().trim();
        String password= PasswordView.getText().toString().trim();

        EmailView.setError(null);
        PasswordView.setError(null);

        boolean cancel2=false;
        View focusView=null;

        if (TextUtils.isEmpty(email)) {
            EmailView.setError(getString(R.string.error_field_required));
            focusView = EmailView;
            cancel2 = true;}
        else if (!isPasswordValid(email)) {
            EmailView.setError(getString(R.string.error_invalid_email));
            focusView = EmailView;
            cancel2 = true;}

        if (TextUtils.isEmpty(password)) {
            PasswordView.setError(getString(R.string.error_field_required));
            focusView = PasswordView;
            cancel2 = true;}

        progressDialog.setMessage("Giriş yapılıyor bekleyin...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        //start the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                    }

                    else{
                        Toast.makeText(LoginActivity.this,"Böyle bir kullanıcı yok",Toast.LENGTH_LONG).show();


                    }
                    progressDialog.dismiss();
                }
            });
    }

    @Override
    public void onClick(View v) {
        if(v==buttonGiris){
            UserLogin();
        }
        if(v==TextViewsignUpGit){
            finish();
            startActivity(new Intent(this,SignUp.class));
        }
    }
}



