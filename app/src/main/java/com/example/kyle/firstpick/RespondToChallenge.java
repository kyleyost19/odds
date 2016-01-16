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

public class RespondToChallenge extends AppCompatActivity {

    //private Challenge currentChal;

    private EditText editTextRange;

    private EditText editTextMyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respond_to_challenge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textViewMain = (TextView) findViewById(R.id.textViewMain);
        //replace name and insert challenge with appropriate text
        textViewMain.setText("*name* challenged you!\n\nWhat are the odds that you *insert challenge*?");


        editTextRange = (EditText) findViewById(R.id.editTextRange);


        editTextMyValue = (EditText) findViewById(R.id.editTextMyValue);


        editTextMyValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Integer range;
                    Integer value;

                    try{
                        value = Integer.parseInt(editTextMyValue.getText().toString());
                    }
                    catch (NumberFormatException e){
                        value = 5;
                    }

                    try{
                        range = Integer.parseInt(editTextRange.getText().toString());
                    }
                    catch (NumberFormatException e){
                        range = value;
                        editTextRange.setText(range.toString());
                    }


                    if (value > range){
                        editTextMyValue.setText(value.toString());
                    }
                    return true;
                }
                return false;
            }
        });



    }

    public void acceptAndReturn(View view) {
        Integer range;
        Integer value;
        try{
            range = Integer.parseInt(editTextRange.getText().toString());
        }
        catch (NumberFormatException e){
            range = 5;
            editTextRange.setText(range.toString());
        }
        try{
            value = Integer.parseInt(editTextMyValue.getText().toString());
        }
        catch (NumberFormatException e){
            value = 5;
        }

        if (value > range){
            editTextMyValue.setText(value.toString());
        }

        //send updates to challenge object
        Intent intent = new Intent(this, MyProfile.class);
        intent.putExtra("status", "accept");
        startActivity(intent);
    }

    public void declineAndReturn(View view) {
        Intent intent = new Intent(this, MyProfile.class);
        intent.putExtra("status", "decline");
        startActivity(intent);
    }
}
