package com.example.kyle.firstpick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class LastStep extends AppCompatActivity {

    private String challengeID;

    private String myName;

    private EditText editTextGuess;

    //private Challenge toView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_step);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String resultString = "";

        Intent intent = getIntent();
        challengeID = intent.getStringExtra("challengeID");
        myName = intent.getStringExtra("myName");

        /*
        toView = Challenge.searchByID(challengeID);
        if(toView.getChallenger().equals(myName)){
            resultString=toView.getChallengee() + " thinks that the odds are\n 1 in "+
                toView.getValRange() +"\nWhat do you think "+ toView.getChallengee() +"'s value is?";
        }
        else{
            resultString=toView.getChallenger() + " thinks that the odds are\n 1 in "+
                toView.getValRange() +"\nWhat do you think "+ toView.getChallenger() +"'s value is?";
        }
        */

        TextView textViewMain = (TextView) findViewById(R.id.textViewMain);
        textViewMain.setText(resultString);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editTextGuess = (EditText) findViewById(R.id.editTextGuess);

        editTextGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Integer guess;

                    try {
                        guess = Integer.parseInt(editTextGuess.getText().toString());
                    } catch (NumberFormatException e) {
                        guess = 1;
                        editTextGuess.setText("1");
                    }

                    return true;
                }
                return false;
            }
        });
    }

    public void submitGuess(View view){
        //Update challenge with winner
        int guess;
        EditText editTextGuess = (EditText) findViewById(R.id.editTextGuess);
        try{
            guess = Integer.parseInt(editTextGuess.getText().toString());
        }
        catch(NumberFormatException e){
            //guess = toView.getValRange();
        }
        /*
        if(toView.getChallengeeVal() ==
        */
        Intent intent = new Intent(this, ViewResult.class);
        intent.putExtra("challengeID",challengeID);
        intent.putExtra("myName", myName);
        startActivity(intent);
    }

}
