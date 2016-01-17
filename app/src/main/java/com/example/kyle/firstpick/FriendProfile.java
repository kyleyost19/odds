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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;


public class FriendProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //recieve intent from
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        final String name = intent.getStringExtra("name");

        //allow image on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //print their facebook image
        try {
            URL imageURL = new URL("https://graph.facebook.com/" + user_id + "/picture?type=large");
            Bitmap pic = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
            ImageView img = (ImageView) findViewById(R.id.pro_pic_friend);
            img.setImageBitmap(pic);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set their name under their pic
        TextView username = (TextView) findViewById(R.id.username);
        username.setText(name);

        //add challenge button
        Button btn = (Button) findViewById(R.id.challenge_btn);
        btn.setText("Challenge " + name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SendChallenge.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });


    }

}
