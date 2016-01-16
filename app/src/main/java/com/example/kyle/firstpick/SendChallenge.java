package com.example.kyle.firstpick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SendChallenge extends AppCompatActivity {

    private String sendToName;

    private String sendFromName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_challenge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView header = (TextView) findViewById(R.id.textViewHeader);
        header.setText("Send a Challenge to " + name + " \n\nWhat are the odds that you ");
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    public void sendAndReturn(View view){

        //send a challenge with the sender info the receiver info and the text from the input field

        Intent intent = new Intent(this, MyProfile.class);
        startActivity(intent);
    }

}
