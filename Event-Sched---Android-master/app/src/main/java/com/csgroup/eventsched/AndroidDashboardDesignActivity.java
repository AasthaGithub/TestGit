package com.csgroup.eventsched;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class AndroidDashboardDesignActivity extends Activity {
//    ImageButton btn_newsfeed,btn_friends ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_layout);

        /**
         * Creating all buttons instances
         * */
        // Dashboard News feed button
       ImageButton events = (ImageButton) findViewById(R.id.events);

        // Dashboard Friends button
        ImageButton btn_friends = (ImageButton) findViewById(R.id.videocall);

        // Dashboard Messages button
        ImageButton btn_messages = (ImageButton) findViewById(R.id.btn_messages);

        // Dashboard Places button
        ImageButton btn_places = (ImageButton) findViewById(R.id.btn_places);

        // Dashboard Events button
        ImageButton btn_events = (ImageButton) findViewById(R.id.btn_events);

        // Dashboard Photos button
        ImageButton btn_photos = (ImageButton) findViewById(R.id.btn_photos);

        /**
         * Handling all button click events
         * */

        // Listening to News Feed button click

        events.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(AndroidDashboardDesignActivity.this,HomeActivity.class);
                startActivity(i);
 //               AndroidDashboardDesignActivity.this.startActivity(i);
            }
        });

        // Listening Friends button click
//        btn_friends.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching News Feed Screen
//                Intent i = new Intent(getApplicationContext(), ConnetPage.class);
//                startActivity(i);
//            }
//        });
//
//        // Listening Messages button click
//        btn_messages.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching News Feed Screen
//                Intent i = new Intent(getApplicationContext(), NotificationPage.class);
//                startActivity(i);
//            }
//        });
//
//        // Listening to Places button click
//        btn_places.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching News Feed Screen
//                Intent i = new Intent(getApplicationContext(), CertificatesPage.class);
//                startActivity(i);
//            }
//        });
//
//        // Listening to Events button click
//        btn_events.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching News Feed Screen
//                Intent i = new Intent(getApplicationContext(), CalenderPage.class);
//                startActivity(i);
//            }
//        });
//
//        // Listening to Photos button click
//        btn_photos.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // Launching News Feed Screen
//                Intent i = new Intent(getApplicationContext(), AboutUs.class);
//                startActivity(i);
//            }
//        });
    }
}