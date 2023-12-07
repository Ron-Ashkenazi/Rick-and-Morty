package com.example.task;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageView = findViewById(R.id.imageViewSecond);

        Bundle bundle = getIntent().getExtras();

        String image = bundle.getString("image");

        Glide.with(this).load(image).into(imageView);


    }
}
