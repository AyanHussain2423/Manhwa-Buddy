package com.example.manwhabudyy;

import static com.bumptech.glide.Glide.get;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.manwhabudyy.Adapters.adapterlink;
import com.example.manwhabudyy.Model.model;
import com.example.manwhabudyy.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    adapterlink adapter;
    ArrayList<model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        binding.recycleview.setHasFixedSize(true);
        binding.recycleview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>(); // Initialize the ArrayList

        adapter = new adapterlink(this, list); // Create the adapter
        binding.recycleview.setAdapter(adapter); // Set the adapter to RecyclerView

        Intent intent = getIntent();
        String animename = intent.getStringExtra("animename");

// Attach a listener to retrieve the data
        database.getReference().child("Animes").child(animename)
                .child("chapter")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), "hooooooo", Toast.LENGTH_SHORT).show();
                // Iterate through the children of the 'chapter' node
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Get the key (e.g., "01") and value (e.g., "link1") of each child
                    String chapterNumber = childSnapshot.getKey();
                    model model = new model();
                    model.setChapter(childSnapshot.getKey());
                    model.setName(animename);
                    list.add(model);
                    // Do something with the chapter number and link
                    Log.d("Chapter",dataSnapshot.toString());
                    Log.d("Chapte",childSnapshot.toString());
                }adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Log.e("Firebase", "Error retrieving data", databaseError.toException());
            }
        });
        database.getReference().child("Animes").child(animename)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String url = snapshot.child("url1").getValue().toString();
                        String text = snapshot.child("text").getValue().toString();
                        binding.textanime.setText(text);
                        Glide.with(getApplicationContext())
                                .load(url)
                                .into(binding.images);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}