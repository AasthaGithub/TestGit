package com.csgroup.eventsched;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {
    private Button btnLogin1, btnRegister;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        btnLogin1 = (Button) findViewById(R.id.btnLogin1);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(OptionsActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(OptionsActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

        });
    }
}



//        btnLogin1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity();
//                finish();
//
//            }
//        });

//        public void logined(View view){
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        public void registered(View view){
//            Intent intent = new Intent(this,RegisterActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.options);
//        btnLogin1 = (Button) findViewById(R.id.btnLogin1);
//        btnRegister = (Button) findViewById(R.id.btnRegister);
//
//    }
//<intent-filter>
//<action android:name = "android.intent.action.VIEW" />
//<action android:name = ".LAUNCH" />
//<category android:name = "android.intent.category.DEFAULT" />
//
//</intent-filter>



