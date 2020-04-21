package com.csgroup.eventsched;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.content.Intent;

import androidx.annotation.NonNull;
//import android.support.design.widget.snackbar;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//import static com.csgroup.eventsched.RegisterActivity.EML;
//import static com.csgroup.eventsched.OTP.MBL;
//import static com.csgroup.eventsched.OTP.EML1;





public class VerifyPhoneActivity extends AppCompatActivity {

    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;

    //The edittext to input the code
    private EditText editTextCode;
    String mobile,email1;
    //firebase auth object
    private FirebaseAuth mAuth;
    String finalResult ;
    String HttpURL = "http://10.120.100.179/eventsched/v1//phone.php";
    //Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse1 = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verify);

        //initializing objects
        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);


        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        String mobile = intent.getStringExtra(OTP.MBL);
        //String email1 = intent.getStringExtra(OTP.EML1);
        sendVerificationCode(mobile);

        //Toast.makeText(VerifyPhoneActivity.this, email1, Toast.LENGTH_LONG).show();
        //Toast.makeText(VerifyPhoneActivity.this, mobile, Toast.LENGTH_LONG).show();

        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });

    }

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            PhoneRegisterFunction(mobile,email1);
                            Toast.makeText(VerifyPhoneActivity.this, "phone no updated", Toast.LENGTH_LONG).show();
                            //verification successful we will start the profile activity
                            Intent intent1 = new Intent(VerifyPhoneActivity.this, LoginActivity.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent1);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Something is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }
//Snackbar.make(findViewById(android.R.id.content), resp.getMessage(), Snackbar.LENGTH_LONG).show();R.id.parent
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();
                        }
                    }
                });
    }
    public void PhoneRegisterFunction(final String mobile, final String email1){

        class PhoneRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(VerifyPhoneActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                if (httpResponseMsg!=null) {
                    progressDialog.dismiss();

                    Toast.makeText(VerifyPhoneActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("mobile",params[0]);

                hashMap.put("email1",params[1]);


                finalResult = httpParse1.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        PhoneRegisterFunctionClass PhoneRegisterFunctionobj = new PhoneRegisterFunctionClass();

        PhoneRegisterFunctionobj.execute(mobile,email1);

    }

}
//$Sql_Query = "INSERT INTO users(phone) values ('$Phone') where email='$Email'";
//"UPDATE users SET phone='$Phone' WHERE email='$Email'";