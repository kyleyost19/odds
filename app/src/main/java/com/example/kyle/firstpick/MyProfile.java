package com.example.kyle.firstpick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;

import org.json.JSONObject;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;

import oddsapi.Challenge;
import oddsapi.OddsAPI;
import oddsapi.User;

public class MyProfile extends AppCompatActivity {

    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //init stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //allow image on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //receive intentfrom the thing could be login
        Intent intent = getIntent();

        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
        if(loggedIn) {
            user_id = AccessToken.getCurrentAccessToken().getUserId();
        }
        else {
            user_id = intent.getStringExtra("user_id");
        }
            String auth = intent.getStringExtra("auth");


            try {
                URL imageURL = new URL("https://graph.facebook.com/" + user_id + "/picture?type=large");
                Bitmap pic = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
                ImageView img = (ImageView) findViewById(R.id.pro_pic);
                img.setImageBitmap(pic);
            } catch (Exception e) {
                e.printStackTrace();
            }




        //Get the ArrayList of challenges for the given user
        OddsAPI myOddsAPI = new OddsAPI(getResources().getString(R.string.odds_server),
                getResources().getString(R.string.api_path));


        LinearLayout linlay = (LinearLayout) findViewById(R.id.scroller);
        linlay.setBackgroundColor(Color.TRANSPARENT);

        final User me = User.getUserByFbID(myOddsAPI, user_id);
        ArrayList<Challenge> userChallenges = Challenge.getUserChallenges(myOddsAPI, me.getID());
        for(final Challenge nextChallenge : userChallenges){
            LinearLayout HL = new LinearLayout(MyProfile.this);
            HL.setOrientation(LinearLayout.HORIZONTAL);
            HL.setBackgroundColor(Color.LTGRAY);
            HL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            //create textview
            TextView textView = new TextView(MyProfile.this);
            String name = nextChallenge.getChallengeDesc();
            textView.setText(name.substring(0, Math.min(name.length(), 25))+"...");
            textView.setTypeface(null, Typeface.BOLD);
            textView.setGravity(Gravity.LEFT);
            HL.addView(textView);

            //add button to challenge person
            Button btn = new Button(MyProfile.this);
            btn.setGravity(Gravity.RIGHT);
            btn.setText("View challenge");
            //btn.setBackgroundColor(Color.WHITE);


            HL.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    if (nextChallenge.getStatus().equals("initial") && me.getID() == nextChallenge.getChallengee()) {
                        intent = new Intent(getApplicationContext(), RespondToChallenge.class);
                    } else if (nextChallenge.getStatus().equals("first_response") && me.getID() == nextChallenge.getChallenger()) {
                        intent = new Intent(getApplicationContext(), LastStep.class);
                    } else if (nextChallenge.getStatus().equals("declined")) {
                        intent = new Intent(getApplicationContext(), DeclinedChallenge.class);
                    } else if (nextChallenge.getStatus().equals("finished") && me.getID() == nextChallenge.getChallengee()) {
                        intent = new Intent(getApplicationContext(), ViewResult.class);
                    } else {
                        intent = new Intent(getApplicationContext(), ViewPending.class);
                    }
                    intent.putExtra("challengeID", nextChallenge.getId());
                    intent.putExtra("myName", me.getName());
                    startActivity(intent);
                }
            });

            //add onclick event listener
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                }
            });

            linlay.addView(HL);
        }

            //use this data to get user record from database and
            //
    }






    //go to find friends to pick a friend for a challenge
    public void sendChallenge(View view)
    {
        Intent intent = new Intent(getApplicationContext(), FindFriends.class);
        //intent.putExtra("user_name", user_id);
        startActivity(intent);
    }




}
