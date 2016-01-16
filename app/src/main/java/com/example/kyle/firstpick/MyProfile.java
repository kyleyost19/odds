package com.example.kyle.firstpick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //init stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //receive intentfrom the thing could be login
        Intent intent = getIntent();
        if(intent != null)
        {
            String user_id = intent.getStringExtra("user_id");
            String auth = intent.getStringExtra("auth");

            //use this data to get user record from database and
            //
        }




    }

    //go to find friends to pick a friend for a challenge
    public void sendChallenge(View view)
    {
        Intent intent = new Intent(getApplicationContext(), FindFriends.class);
        //intent.putExtra("user_name", user_id);
        intent.putExtra("type", "challenge");
        startActivity(intent);
    }




}
