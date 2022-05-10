package com.example.sample;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
public class register extends AppCompatActivity implements View.OnClickListener{
    private EditText editemail,editpass,editconfirmpass,editfullname,editage;
    private Button register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth=FirebaseAuth.getInstance();

        register=(Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        editemail=(EditText) findViewById(R.id.email);
        editfullname=(EditText) findViewById(R.id.fullname);
        editage=(EditText) findViewById(R.id.age);
        editpass=(EditText) findViewById(R.id.pass);
    }
            @Override
            public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                bregister();
                break;}}
    private void bregister(){

        final String email = editemail.getText().toString().trim();
     final  String pass = editpass.getText().toString().trim();
        final String fullname=editfullname.getText().toString().trim();
        final String age=editage.getText().toString().trim();

        if(fullname.isEmpty()){
            editfullname.setError("name is required!");
            editfullname.requestFocus();
            return;
        }
        if(email.isEmpty()) {
            editemail.setError("email is required!");
            editemail.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("please provide valid email");
            editemail.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editage.setError("age is required!");
            editage.requestFocus();
            return;}

        if(pass.isEmpty()){
            editpass.setError("pass is required!");
            editpass.requestFocus();
            return;
    }
        if(pass.length()<6){
        editpass.setError("min pass lenght 6");
        editpass.requestFocus();
        return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                     User user=new User(fullname,age,email);
                     FirebaseDatabase.getInstance().getReference("Users")
                             .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                             .setValue(user)
               .addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()) {
                           Toast.makeText(register.this,"user has been registred",Toast.LENGTH_LONG).show();
                           Intent intent
                                   = new Intent(register.this, login.class);
                                    startActivity(intent);
                     }  else {
                           Toast.makeText(register.this,"failed to  registred try again",Toast.LENGTH_LONG).show();}
                      }
               });





                 }else{
                     Toast.makeText(register.this,"failed to  registred",Toast.LENGTH_LONG).show();
                 }
                    }});}}