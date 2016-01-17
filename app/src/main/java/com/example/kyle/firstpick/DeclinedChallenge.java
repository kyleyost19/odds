package com.example.kyle.firstpick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import oddsapi.Challenge;
import oddsapi.OddsAPI;

public class DeclinedChallenge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declined_challenge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String challengeID = intent.getStringExtra("challengeID");

        //create odds instance
        OddsAPI oapi = new OddsAPI(getResources().getString(R.string.odds_server), getResources().getString(R.string.api_path));

        //get challenge obj
        Challenge toView = Challenge.getChallenge(oapi, Integer.parseInt(challengeID));

        TextView textViewDecline = (TextView) findViewById(R.id.textViewDecline);
        textViewDecline.setText("The challenge: 'What are the odds that you "+ toView.getChallengeDesc() +"' was declined");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
