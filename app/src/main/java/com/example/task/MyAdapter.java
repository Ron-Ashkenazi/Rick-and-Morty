package com.example.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<Item>  items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item = items.get(position);
        holder.nameView.setText(items.get(position).getName());
        holder.statusView.setText(items.get(position).getStatus());
        Glide.with(context).load(items.get(position).getImage()).into(holder.imageView);

        holder.info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", item.getName());
                bundle.putString("status", item.getStatus());
                bundle.putString("species", item.getSpecies());
                bundle.putString("image", item.getImage());

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        holder.info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("image", item.getImage());

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameView, statusView;
        LinearLayout linearLayout;
        Button info1;
        Button info2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            nameView = itemView.findViewById(R.id.name);
            statusView = itemView.findViewById(R.id.status);
            linearLayout = itemView.findViewById(R.id.main_layout);
            info1 = itemView.findViewById(R.id.buttonInfo1);
            info2 = itemView.findViewById(R.id.buttonInfo2);
        }
    }

}
