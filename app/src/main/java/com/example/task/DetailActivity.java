package com.example.task;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.imageviewactivitydetail);
        TextView textViewName = findViewById(R.id.nameactivitydetail);
        TextView textViewStatus = findViewById(R.id.statusactivitydetail);
        TextView textViewSpecies = findViewById(R.id.speciesactivitydetail);

        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("name");
        String status = bundle.getString("status");
        String species = bundle.getString("species");
        String image = bundle.getString("image");

        Glide.with(this).load(image).into(imageView);
        textViewName.setText(name);
        textViewStatus.setText(status);
        textViewSpecies.setText(species);

    }
}
