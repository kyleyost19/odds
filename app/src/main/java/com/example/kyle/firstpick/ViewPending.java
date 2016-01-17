package com.example.kyle.firstpick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
        /*
        Challenge toView = Challenge.searchByID(challengeID);
        if(toView.getChallenger().equals(myName)){
            resultString+=toView.getChallengee();
        }
        else{
            resultString+=toView.getChallenger();
        }
        */
        resultString += " has not yet responded to the challenge 'What are the odds that you ";
        //resultString += toView.getChallengeDesc();
        resultString += "'";
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
