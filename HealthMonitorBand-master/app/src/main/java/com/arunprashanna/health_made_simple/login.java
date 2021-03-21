package com.arunprashanna.health_made_simple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;

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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText login_email,login_pwd;
    Button loginbtn;
    TextView signuptv;
    FirebaseAuth firebaseAuth;
    ProgressDialog mdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email=(EditText)findViewById(R.id.login_email);
        login_pwd=(EditText)findViewById(R.id.login_pwd);
        loginbtn=(Button)findViewById(R.id.loginbtn);
        signuptv=(TextView)findViewById(R.id.signuptv);
        mdialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=login_email.getText().toString();
                String pwd=login_pwd.getText().toString();
                if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(login.this,"Please fill in all the fields",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    login_email.setError("Please enter the email id");
                    login_email.requestFocus();
                }
                else if(pwd.isEmpty()){
                    login_pwd.setError("Please enter the Password");
                    login_pwd.requestFocus();
                }
                else{
                    mdialog.setMessage("Logging In...");
                    mdialog.show();
                    firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(login.this,MainActivity.class));
                                mdialog.dismiss();
                                finish();
                            }
                            else{
                                Toast.makeText(login.this, "Invalid Login! Please try again!", Toast.LENGTH_SHORT).show();
                                mdialog.dismiss();
                            }
                        }
                    });
                }
            }
        });

        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,signup.class));
            }
        });
    }

}