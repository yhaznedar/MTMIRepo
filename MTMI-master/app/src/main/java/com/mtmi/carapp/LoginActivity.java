package com.mtmi.carapp;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.preference.PreferenceManager;
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
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    SharedPreferences preferences;
    //preferences için bir nesne tanımlıyorum.
    SharedPreferences.Editor editor;
    //preferences içerisine bilgi girmek için tanımlama
    public EditText mEmailView;
    public EditText mPasswordView;
    public ImageView imageView;

    public final static String MAILKEY="E-posta";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        mEmailView = (EditText) findViewById(R.id.email);
        imageView = (ImageView) findViewById(R.id.imageView);
        mPasswordView = (EditText) findViewById(R.id.password);

        mEmailView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                imageView.getLayoutParams().height = 120;
                imageView.getLayoutParams().width = 120;
            }
        });

        mPasswordView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                imageView.getLayoutParams().height = 120;
                imageView.getLayoutParams().width = 120;
            }
        });


        ImageButton mEmailSignInButton = (ImageButton) findViewById(R.id.email_sign_in_button);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptLogin();
            }
        });



        TextView mSignUpView= (TextView) findViewById(R.id.signUpGit);
        mSignUpView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent uyeolagit=new Intent(LoginActivity.this,SignUp.class);
                startActivity(uyeolagit);
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
       /* preferences= getSharedPreferences("profile", Context.MODE_PRIVATE);
        editor=preferences.edit();
        String emailbilgi=preferences.getString("eposta","");
        String sifrebilgi=preferences.getString("sifre","");
        mEmailView.setText(emailbilgi);
        mPasswordView.setText(sifrebilgi);*/


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*editor.clear();
        editor.apply();
        mEmailView.setText("");
        mPasswordView.setText("");*/
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



    public void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email=mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;}

        else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;}


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }


        if (internetErisimi())
        {

            if (cancel !=true)
            {
                Toast.makeText(this, "Giriş yapılıyor...", Toast.LENGTH_SHORT).show();
                Intent mesajIntent = new Intent(this, MainActivity.class);
                mesajIntent.putExtra(MAILKEY, email);
                startActivity(mesajIntent);
            }

        }



        else //internet yoksa
        {
           /* editor.putString("eposta", mEmailView.getText().toString());
            editor.putString("sifre", mPasswordView.getText().toString());
            editor.commit();*/
            Intent hata=new Intent(this,hataActivity.class);
            startActivity(hata);
        }


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //selam
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    /**
     * Shows the progress UI and hides the login form.
     */

        protected void onPostExecute(final Boolean success) {

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }
        protected void onCancelled() {


        }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}



