package com.example.manwhabudyy.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.manwhabudyy.Model.link_model;
import com.example.manwhabudyy.Model.model;
import com.example.manwhabudyy.R;
import com.example.manwhabudyy.databinding.LinkurlactivityBinding;
import com.example.manwhabudyy.databinding.PdflayBinding;

import java.util.ArrayList;

public class link_adapter extends RecyclerView.Adapter<link_adapter.viewholder> {
    Context context;
    ArrayList<link_model> list;

    public link_adapter(Context context, ArrayList<link_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public link_adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.linkurlactivity,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull link_adapter.viewholder holder, int position) {
        link_model model = list.get(position);
        Glide.with(context)
                .load(model.getUrl()) // Apply RequestOptions
                .into(holder.binding.Mangaimage);
        Log.d("chill",model.getUrl());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        LinkurlactivityBinding binding;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            binding = LinkurlactivityBinding.bind(itemView);
        }
    }
}
