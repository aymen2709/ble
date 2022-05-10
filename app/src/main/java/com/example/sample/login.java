package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {
    private TextView forgetpass;
    private Button btlogin2;
    private FirebaseAuth mAuth;
    private EditText editemail,editpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    forgetpass=(TextView) findViewById(R.id.forget_pass);
    forgetpass.setOnClickListener(this);
    btlogin2=(Button) findViewById(R.id.demarrer);
    btlogin2.setOnClickListener(this);
    editemail=(EditText) findViewById(R.id.email);
    editpass=(EditText) findViewById(R.id.pass);
    mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.demarrer:
        userLogin();
        break;
    }}



    private void userLogin() {

       String email = editemail.getText().toString().trim();
       String pass = editpass.getText().toString().trim();
        if(email.isEmpty()){
            editemail.setError("please enter a valid email");
            editemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("please enter a valid email!");
            editemail.requestFocus();
            return;}
        if(pass.isEmpty()){
            editpass.setError("pass is required ");
            editpass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){

                        startActivity(new Intent(login.this,configuration.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(login.this,"check your email",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(login.this,"failed to login ",Toast.LENGTH_LONG).show();
                }
            }
        });
}}
