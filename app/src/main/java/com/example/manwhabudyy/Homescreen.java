package com.example.manwhabudyy;

import static com.bumptech.glide.Glide.get;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.manwhabudyy.Adapters.homeadapter;
import com.example.manwhabudyy.Model.homemodel;
import com.example.manwhabudyy.databinding.ActivityHomescreenBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Homescreen extends AppCompatActivity {
    ActivityHomescreenBinding binding;
    FirebaseDatabase database;
    homeadapter adapter;
    ArrayList<homemodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomescreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        binding.recycleview.setHasFixedSize(true);
        binding.recycleview.setLayoutManager(new GridLayoutManager(this,3));

        list = new ArrayList<>(); // Initialize the ArrayList

        adapter = new homeadapter(this,list); // Create the adapter
        binding.recycleview.setAdapter(adapter); // Set the adapter to RecyclerView

        database.getReference().child("Animes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String key = snapshot1.getKey();
                    String url = snapshot.child(key).child("url").getValue().toString();
                    Log.d("childe",key);
                    homemodel homemodel = new homemodel();
                    homemodel.setAnimename(key);
                    homemodel.setUrl(url);
                    list.add(homemodel);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}