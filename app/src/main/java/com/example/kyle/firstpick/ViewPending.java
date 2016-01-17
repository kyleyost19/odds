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
import oddsapi.User;

public class ViewPending extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String resultString = "";

        Intent intent = getIntent();
        String challengeID = intent.getStringExtra("challengeID");
        String myName = intent.getStringExtra("myName");

        //create odds instance
        OddsAPI oapi = new OddsAPI(getResources().getString(R.string.odds_server), getResources().getString(R.string.api_path));

        //get challenge obj
        Challenge toView = Challenge.getChallenge(oapi, Integer.parseInt(challengeID));

        //find out challenger name
        String challenger = (User.getUser(oapi, toView.getChallenger())).getName();
        String challengee = (User.getUser(oapi, toView.getChallengee())).getName();
        resultString += challenger;
        resultString += " challenged ";
        resultString += challengee;
        resultString += ": What are the odds ";
        resultString += toView.getChallengeDesc();
        //resultString += toView.getChallengeDesc();

        if(toView.getChallengerVal() == 0)      //challenger has not responded
        {
            resultString += "\n" + challenger + " has not given a value." + "\n";
        }
        else
        {
            resultString += "\n" + challenger + " value: " + Integer.toString(toView.getChallengerVal()) + "\n";
        }

        if(toView.getChallengeeVal() == 0)      //challengee has not resonded
        {
            resultString += "\n" + challengee + " has not given a value." + "\n";
        }
        else
        {
            resultString += "\n" + challengee + " value: " + Integer.toString(toView.getChallengeeVal()) + "\n";
        }


        resultString += "";
        TextView textViewPending = (TextView) findViewById(R.id.textViewPending);
        textViewPending.setText(resultString);

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
