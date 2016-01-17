package com.example.kyle.firstpick;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class ViewResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String resultString = "";

        Intent intent = getIntent();
        String challengeID = intent.getStringExtra("challengeID");
        String myName = intent.getStringExtra("myName");



        /*
        Challenge toView = Challenge.searchByID(challengeID);
        if(toView.getStatus().equals("unfinished")){
            throw new RuntimeException();
        }
        else if(toView.getStatus().equals("challengerWins")){
            if(toView.getChallenger().equals(myName)){
                resultString = "You Win! \nCongrats on besting fate, now you just have to" +
                 " make sure your opponent follows through on the bet..."
            }
            else{
                resultString = "You Lose! \nBut don't worry you can *definitely* win the next round";
            }
        }
        else if(toView.getStatus().equals("challengeeWins")){
            if(toView.getChallengee().equals(myName)){
                resultString = "You Win! \nCongrats on besting fate, now you just have to" +
                 " make sure your opponent follows through on the bet..."
            }
            else{
                resultString = "You Lose! \nBut don't worry you can *definitely* win the next round";
            }
        }
        else{
            resultString = "Nobody Wins! \nTry again if you're feeling lucky";
        }
        */

        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewResult.setText(resultString);

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
