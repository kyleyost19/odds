package com.example.kyle.firstpick;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

public class FindFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scroller);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get intent and determine what they are doing
        //looking at friends profiles OR picking friend to challenge
        Intent intent = getIntent();
        //if(intent.getStringExtra("type") == "challenge")
        //{
            TextView intentMessage = new TextView(getApplicationContext());
            intentMessage.setText("Find a Friend to Challenge");
            linearLayout.addView(intentMessage);
        //}

/*
        new GraphRequest(
                AccessToken.getCurrentAccessToken(), "/me/friends", null, HttpMethod.GET,

                new GraphRequest.Callback() {
                    //inner callback function to handle the response
                    public void onCompleted(GraphResponse response) {

                        JSONArray res = new JSONArray();
                        res = response.getJSONArray();
                        try {
                            //iterate over result set of facebook friends to show
                            for (int x = 0; x < res.length(); x++) {
                                TextView textView = new TextView(getApplicationContext());
                                JSONObject temp = res.getJSONObject(x);
                                textView.setText(temp.getString("name"));
                                linearLayout.addView(textView);
                            }
                        }
                        catch(Exception e)
                        {

                        }

                    }
                }
        ).executeAsync();
        */



        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/taggable_friends?limit=500",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */

                       // JSONObject json = new response.getJSONObject();
                        JSONObject result = response.getJSONObject();
                        try
                        {
                            JSONArray res = result.getJSONArray("data");
                            LinearLayout linlay = (LinearLayout) findViewById(R.id.scroller);
                            linlay.setBackgroundColor(Color.TRANSPARENT);

                            //iterate over result set of facebook friends to show
                            for (int x = 0; x < res.length(); x++) {
                                TextView textView = new TextView(FindFriends.this);
                                JSONObject temp = res.getJSONObject(x);
                                String name = temp.getString("name");
                                textView.setText(name);
                                linlay.addView(textView);
                            }
                        }
                        catch(Exception e)
                        {

                        }
                    }
                }
        ).executeAsync();

    }
}
