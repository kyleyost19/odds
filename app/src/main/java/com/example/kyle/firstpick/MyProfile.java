package com.example.kyle.firstpick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;

public class MyProfile extends AppCompatActivity {

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
        if(intent != null)
        {
            final String user_id = intent.getStringExtra("user_id");
            String auth = intent.getStringExtra("auth");


            try {
                URL imageURL = new URL("https://graph.facebook.com/" + user_id + "/picture?type=large");
                Bitmap pic = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
                ImageView img = (ImageView) findViewById(R.id.pro_pic);
                img.setImageBitmap(pic);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        //Get the ArrayList of challenges for the given user

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
