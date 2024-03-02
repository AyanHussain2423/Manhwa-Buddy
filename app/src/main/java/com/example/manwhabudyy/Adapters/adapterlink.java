package com.example.manwhabudyy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manwhabudyy.MainActivity2;
import com.example.manwhabudyy.R;
import com.example.manwhabudyy.databinding.PdflayBinding;
import com.example.manwhabudyy.Model.model;

import java.util.ArrayList;

public class adapterlink extends RecyclerView.Adapter<adapterlink.viewolder> {
    Context context;
    ArrayList<model> list;
    public adapterlink(Context context, ArrayList<model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public adapterlink.viewolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pdflay,parent,false);
        return new viewolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterlink.viewolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        model model = list.get(position);
        holder.binding.Chapter.setText(model.getChapter().toString());
        holder.binding.Anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity2.class);
                intent.putExtra("chapternumberakey",holder.binding.Chapter.getText());
                intent.putExtra("animename",model.getName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class viewolder extends RecyclerView.ViewHolder {
        PdflayBinding binding;
        public viewolder(@NonNull View itemView) {
            super(itemView);
            binding = PdflayBinding.bind(itemView);
        }
    }
}
