package com.example.manwhabudyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.manwhabudyy.Adapters.adapterlink;
import com.example.manwhabudyy.Adapters.link_adapter;
import com.example.manwhabudyy.Model.link_model;
import com.example.manwhabudyy.Model.model;
import com.example.manwhabudyy.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity{
    ActivityMain2Binding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    link_adapter adapter;
    ArrayList<link_model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        String chapterNumber = intent.getStringExtra("chapternumberakey");
        String animename = intent.getStringExtra("animename");
     // Log.d("chapter",chapterNumber);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        binding.Recycleview.setHasFixedSize(true);
        binding.Recycleview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>(); // Initialize the ArrayList

        adapter = new link_adapter(this, list); // Create the adapter
        binding.Recycleview.setAdapter(adapter); // Set the adapter to RecyclerView

        database.getReference().child("Animes").child(animename)
                .child("chapter").child(chapterNumber).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot snapshot1 : snapshot.getChildren()){

                            String key = snapshot1.getKey().toString();
                            String aa =  snapshot.child(key).getValue().toString();
                            link_model linkModel = new link_model();
                             linkModel.setUrl(aa);
                             list.add(linkModel);
                            Log.d("chapter", aa);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}