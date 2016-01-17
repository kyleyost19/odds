package com.example.kyle.firstpick;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
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
        //ListView listView = (ListView) findViewById(R.id.scroll_items);

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
                "/me/friends",
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
                            for (int x = 0; x < res.length(); x++)
                            {
                                LinearLayout HL = new LinearLayout(FindFriends.this);
                                HL.setOrientation(LinearLayout.HORIZONTAL);
                                HL.setBackgroundColor(Color.LTGRAY);
                                HL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT));

                                //create textview
                                TextView textView = new TextView(FindFriends.this);
                                JSONObject temp = res.getJSONObject(x);
                                final String name = temp.getString("name");
                                final String user_id = temp.getString("id");
                                textView.setText(name);
                                textView.setTypeface(null, Typeface.BOLD);
                                textView.setGravity(Gravity.LEFT);
                                //textView.getLayoutParams().width = 100;
                                HL.addView(textView);

                                //add button to challenge person
                                Button btn = new Button(FindFriends.this);
                                btn.setGravity(Gravity.RIGHT);
                                btn.setText("challenge");
                                //btn.setBackgroundColor(Color.WHITE);

                                //set the layout for the button
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(50, 10, 0, 10);
                                HL.addView(btn, layoutParams);

                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getApplicationContext(), SendChallenge.class);
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                    }
                                });

                                //text click goes to friendProfile to view them
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        // TODO Auto-generated method stub
                                        Intent intent = new Intent(getApplicationContext(), FriendProfile.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("user_id", user_id);
                                        startActivity(intent);
                                    }
                                });

                                linlay.addView(HL);
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();

    }
}
