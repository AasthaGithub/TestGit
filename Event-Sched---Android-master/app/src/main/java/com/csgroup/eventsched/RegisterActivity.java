package com.csgroup.eventsched;

//public class {
//}

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import java.util.HashMap;


public class RegisterActivity  extends AppCompatActivity {
    public static final String EML= "com.csgroup.eventsched.EMAIL" ;

    Button register, log_in;
    //EditText First_Name, Last_Name, Email, Password ;
    String Name_Holder, Email_Holder, GenderHolder, PasswordHolder,PhoneHolder;
    //EditText name,email,gender,password;
    EditText Name,Email,Gender,Password,Phone;
    String finalResult ;
    String HttpURL = "http://10.120.100.179/eventsched/v1//register.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);//act_main

        //Assign Id'S
        Name = (EditText)findViewById(R.id.editTextname);
        Email = (EditText)findViewById(R.id.editTextemail);
        Gender = (EditText)findViewById(R.id.editTextgender);
        Password = (EditText)findViewById(R.id.editTextpassword);
        Phone= (EditText)findViewById(R.id.editTextphone);

        register = (Button)findViewById(R.id.Submit);
        log_in = (Button)findViewById(R.id.Login);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(Name_Holder, Email_Holder, GenderHolder, PasswordHolder,PhoneHolder);
                    Toast.makeText(RegisterActivity.this, "Registered successfully! Now, Please LOGIN!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, OTP.class);
                    //String message = editText.getText().toString().toUpperCase();
                    intent.putExtra(EML,PhoneHolder);//key and value

                    startActivity(intent);



                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(RegisterActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){

        Name_Holder = Name.getText().toString();
        Email_Holder = Email.getText().toString();
        GenderHolder = Gender.getText().toString();
        PasswordHolder = Password.getText().toString();
        PhoneHolder = Phone.getText().toString();

        if(TextUtils.isEmpty(Name_Holder) || TextUtils.isEmpty(Email_Holder) || TextUtils.isEmpty( GenderHolder ) || TextUtils.isEmpty(PasswordHolder)||TextUtils.isEmpty(PhoneHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String name, final String email, final String gender, final String password,final String phone){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(RegisterActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                if(httpResponseMsg!=null){
                    // Do you work here on success
                    progressDialog.dismiss();

                    Toast.makeText(RegisterActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

                }else{
                    // null response or Exception occur
                    Toast.makeText(RegisterActivity.this,"Unlucky", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("name",params[0]);

                hashMap.put("email",params[1]);

                hashMap.put("gender",params[2]);

                hashMap.put("password",params[3]);
                hashMap.put("phone",params[4]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(name,email,gender,password,phone);

    }

}


//
//if(mysqli_query($con,$Sql_Query))
//        {
//        echo 'Registration Successfully';
//        }
//        else
//        {
//        echo 'Something went wrong';
//        }
//        }
//        }
//        mysqli_close($con);
