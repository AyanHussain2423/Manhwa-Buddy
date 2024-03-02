package com.example.manwhabudyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.manwhabudyy.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        if (auth.getCurrentUser()!=null){
            Intent intent = new Intent(getApplicationContext(), Homescreen.class);
            startActivity(intent);
            finish();
        }


        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.username.getText().toString();
                String pass = binding.password.getText().toString();
                if (email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Login.this, "No null acceptence", Toast.LENGTH_SHORT).show();
                }
                else {
                     auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             try {
                                 if (task.isSuccessful()) {
                                     FirebaseUser user = auth.getCurrentUser();
                                     HashMap map = new HashMap();
                                     map.put("UID", user.getUid());
                                     map.put("email", email);
                                     map.put("pass", pass);
                                     database.getReference().child("User").child(auth.getUid()).setValue(map);
                                     Intent intent = new Intent(Login.this, MainActivity.class);
                                     startActivity(intent);
                                 }
                             }
                            catch (Exception e){
                                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                         }
                     });

                }
            }
        });
    }
}