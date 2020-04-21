package com.csgroup.eventsched;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.csgroup.eventsched.RegisterActivity.EML;


public class OTP extends AppCompatActivity {


    private EditText editTextMobile;
    public static final String MBL = "com.csgroup.eventsched.mobile" ;
    //public static final String EML1 = "com.csgroup.eventsched.email" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        Intent intent = getIntent();
        final String mobile= intent.getStringExtra(RegisterActivity.EML);


        TextView textView = findViewById(R.id.textView11);
        textView.setText(mobile);

        //TextView textView = findViewById(R.id.new);
        //textView.setText(bias);

        //editTextMobile = findViewById(R.id.editTextMobile);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String mobile = textView.getText().toString().trim();
                //String mobile = mobile.trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent1 = new Intent(OTP.this, VerifyPhoneActivity.class);
                intent1.putExtra(MBL, mobile);
                //intent1.putExtra(EML1, email2);


                startActivity(intent1);
                //Toast.makeText(OTP.this, email2, Toast.LENGTH_LONG).show();

            }
        });
    }

}

