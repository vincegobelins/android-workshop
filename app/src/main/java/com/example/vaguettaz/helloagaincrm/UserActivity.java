package com.example.vaguettaz.helloagaincrm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vaguettaz.helloagaincrm.user.FirebaseManager;

public class UserActivity extends AppCompatActivity {

    private static final String EXTRA_UID = "user_uid";
    private static final String TAG = "rge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarUser);
        setSupportActionBar(toolbar);
        /*
        TextView uidDisplay = (TextView) findViewById(R.id.textUID);

        Intent intent = getIntent();

        if (intent != null) {
            uidDisplay.setText(intent.getStringExtra(EXTRA_UID));
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int i = item.getItemId();
        if (i == R.id.menuUserLogoutItem) {
            logout();
            return true;
        }
        return false;
    }

    public void logout(){
        Log.d(TAG, "onClickLOGOUT");
        FirebaseManager.getInstance().logout();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
