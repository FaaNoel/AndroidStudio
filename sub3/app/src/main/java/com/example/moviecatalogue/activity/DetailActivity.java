package com.example.moviecatalogue.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogue.Items;
import com.example.moviecatalogue.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DETAIL="extra_detail";
    TextView title,desc,info;
    ImageView photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);




        title = findViewById(R.id.txt_tittle);
        desc = findViewById(R.id.txt_wktrilis);
        info = findViewById(R.id.txt_detail);
        photo = findViewById(R.id.img_movie);
        Show();
    }

    private void Show() {

        Items items = getIntent().getParcelableExtra(EXTRA_DETAIL);
        title.setText(items.getTitle_film());
        desc.setText(items.getDesc_film());
        info.setText(items.getInfo_film());
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+ items.getPhoto()).into(photo);
    }
}
