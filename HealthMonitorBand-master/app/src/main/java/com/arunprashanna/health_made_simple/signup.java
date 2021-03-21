package com.arunprashanna.health_made_simple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class signup extends AppCompatActivity {
    EditText signup_email,signup_pwd,signup_confirmpwd;
    TextView logintv;
    Button signupbtn;
    ProgressDialog mdialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_confirmpwd=(EditText) findViewById(R.id.signup_confirmpwd);
        signup_email=(EditText) findViewById(R.id.signup_email);
        signup_pwd=(EditText) findViewById(R.id.signup_pwd);
        signupbtn=(Button)findViewById(R.id.signupbtn);
        logintv=(TextView)findViewById(R.id.logintv);

        mdialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=signup_email.getText().toString();
                String pwd=signup_pwd.getText().toString();
                if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(signup.this,"Please fill in all the fields",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    signup_email.setError("Please enter the email id");
                    signup_email.requestFocus();
                }
                else if(pwd.isEmpty()){
                    signup_pwd.setError("Please enter the Password");
                    signup_pwd.requestFocus();
                }
                else{
                    mdialog.setMessage("Registering...");
                    mdialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(signup.this,MainActivity.class));
                                mdialog.dismiss();
                                finish();
                            }
                            else{
                                Toast.makeText(signup.this,"SignUp Unsuccessful! Try Again!!!",Toast.LENGTH_SHORT).show();
                                mdialog.dismiss();
                            }
                        }
                    });
                }
            }
        });
        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,login.class));
                finish();
            }
        });
    }
}