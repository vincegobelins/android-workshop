package com.example.vaguettaz.helloagaincrm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends Activity {

    private static final String EXTRA_UID = "user_uid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        /*
        TextView uidDisplay = (TextView) findViewById(R.id.textUID);

        Intent intent = getIntent();

        if (intent != null) {
            uidDisplay.setText(intent.getStringExtra(EXTRA_UID));
        }*/
    }
}
