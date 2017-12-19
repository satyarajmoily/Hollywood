package com.howaboutthis.satyaraj.hollywood;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.squareup.picasso.Picasso;

public class ImageView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.image_view);
        android.widget.ImageView imageView = findViewById(R.id.full_image_view);
        Bundle url = getIntent().getExtras();
        assert url != null;
        String mUrl = url.getString("imageUrl");

        Picasso.with(this).load(mUrl).resize(1100,1800).into(imageView);

    }
}
