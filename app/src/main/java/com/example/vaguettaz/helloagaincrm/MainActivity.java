package com.example.vaguettaz.helloagaincrm;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.vaguettaz.helloagaincrm.user.FirebaseManager;
import com.example.vaguettaz.helloagaincrm.user.LoginFragment;
import com.example.vaguettaz.helloagaincrm.user.RegisterFragment;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener, RegisterFragment.RegisterListener {

    private static final String TAG = "MainActivity";
    private static final String EXTRA_UID = "user_uid";
    private Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Init database
        Firebase.setAndroidContext(this);
        //View mainContainer = findViewById(R.id.mainContainer);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            // Handle item selection
            int i = item.getItemId();
            if (i == R.id.menuMainLoginItem) {
                displayLogin();
                return true;
            } else if (i == R.id.menuMainRegisterItem) {
                Log.d(TAG, "item register clicked");
                displayRegister();
                return true;
            } else {
                return super.onOptionsItemSelected(item);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public void displayLogin(){
        Log.d(TAG, "display login");
        LoginFragment loginFragment = new LoginFragment();
        changeFragment(R.id.wrapper, loginFragment);
    }

    public void displayRegister(){
        Log.d(TAG, "display register");
        RegisterFragment registerFragment = new RegisterFragment();
        changeFragment(R.id.wrapper, registerFragment);
    }

    private void changeFragment(int id, Fragment fragment) {
        Log.d(TAG, "change fragment");
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(id, fragment)
                .commit();
    }

    @Override
    public void onLoginClicked(CharSequence loginName, CharSequence loginPassword) {
        Log.d(TAG, "Coucou");
        authenticateUser(loginName, loginPassword);
    }

    @Override
    public void onRegisterClicked(CharSequence registerName, CharSequence registerPassword) {
        Log.d(TAG, "Coucou");
        registerUser(registerName, registerPassword);
    }


    private void authenticateUser(CharSequence registerName, CharSequence registerPassword){

        FirebaseManager.getInstance().authenticate(registerName, registerPassword, new FirebaseManager.FirebaseListener() {
            @Override
            public void onSuccess(String userID) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                System.out.println("l utilisateur est loggé");
            }

            @Override
            public void onError() {
                Snackbar.make(findViewById(R.id.mainContainer), "L'authentification a échoué", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "closed");
                            }
                        })
                        .show();
            }
        });

    }

    private void registerUser(CharSequence registerName, CharSequence registerPassword){

        // try to register to firebase

        FirebaseManager.getInstance().register(registerName, registerPassword, new FirebaseManager.FirebaseListener() {
            @Override
            public void onSuccess(String userID) {
                Snackbar.make(findViewById(R.id.mainContainer), "Utilisateur créé : " + userID, Snackbar.LENGTH_LONG)
                        .setAction("LOGIN", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "closed");
                            }
                        })
                        .show();
            }

            @Override
            public void onError() {
                Snackbar.make(findViewById(R.id.mainContainer), "L'inscription n'a pas fonctionné", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG, "closed");
                            }
                        })
                        .show();
            }
        });
    }


}
