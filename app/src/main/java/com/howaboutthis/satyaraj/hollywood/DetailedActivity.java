package com.howaboutthis.satyaraj.hollywood;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);


        final ImageView imageView = findViewById(R.id.activity_image);
        TextView mDescription = findViewById(R.id.description);
        TextView mVotingCount = findViewById(R.id.voting_count);
        TextView mPopularity = findViewById(R.id.popularity);
        TextView mDate = findViewById(R.id.date);
        TextView mRating = findViewById(R.id.rating);
        TextView mGenre = findViewById(R.id.genre);

        Bundle bundle = getIntent().getExtras();
        @SuppressWarnings("ConstantConditions") final String mUrl = bundle.getString("imageUrl");

        String title = bundle.getString("title");
        String genre = bundle.getString("genre");

        collapsingToolbar.setTitle(title);

        String description = bundle.getString("description");
        String rating = bundle.getDouble("rating")+"/10";
        String date = bundle.getString("date");
        String voting_count = String.valueOf(bundle.getLong("votingCount"));
        String popularity = String.valueOf(bundle.getDouble("popularity"));
        String backdrop = bundle.getString("backdrop");

        mDescription.setText(description);
        mDate.setText(date);
        mGenre.setText(genre);
        mVotingCount.setText(voting_count);
        mPopularity.setText(popularity);
        mRating.setText(rating);
        Picasso.with(this).load(backdrop).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this, com.howaboutthis.satyaraj.hollywood.ImageView.class);
                intent.putExtra("imageUrl",mUrl);
                startActivity(intent);
            }

    });
    }
    @Override
    public boolean  onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
