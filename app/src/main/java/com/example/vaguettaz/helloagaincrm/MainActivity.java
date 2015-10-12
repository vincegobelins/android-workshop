package com.example.vaguettaz.helloagaincrm;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.vaguettaz.helloagaincrm.user.LoginFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //View mainContainer = findViewById(R.id.mainContainer);
        LoginFragment fragment = new LoginFragment();

        // instantiate Fragement
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainContainer, fragment)
                .commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuMainLoginItem){
            //login clicked
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public void onLoginClicked() {
        Log.d(TAG, "Coucou");
        Snackbar.make(findViewById(R.id.mainContainer), "Snackbar", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "closed");
                    }
                })
        .show();
    }


}
