package com.example.kyle.firstpick;

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
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




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
    }
}
