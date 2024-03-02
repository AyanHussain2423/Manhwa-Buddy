package com.example.manwhabudyy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.manwhabudyy.MainActivity;
import com.example.manwhabudyy.Model.homemodel;
import com.example.manwhabudyy.R;
import com.example.manwhabudyy.databinding.HomescreenRecycleBinding;

import java.util.ArrayList;

public class homeadapter extends RecyclerView.Adapter<homeadapter.viewholder> {
    Context context;
    ArrayList<homemodel> list;

    public homeadapter(Context context, ArrayList<homemodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public homeadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.homescreen_recycle,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull homeadapter.viewholder holder, int position) {
        homemodel homemodel = list.get(position);

        Glide.with(context)
                .load(homemodel.getUrl()) // Apply RequestOptions
                .into(holder.binding.animeimage);

        holder.binding.textView4.setText(homemodel.getAnimename());
        holder.binding.Animecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("animename",homemodel.getAnimename());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        HomescreenRecycleBinding binding;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            binding = HomescreenRecycleBinding.bind(itemView);
        }
    }
}
