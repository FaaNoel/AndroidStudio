package com.example.moviecatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalog.adapter.Items;

public class Detail_item extends AppCompatActivity {
    public static final String EXTRA_MOVIES="EXTRA_MOVIES";
        TextView title,desc,info;
        ImageView photo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        Items movieItems= getIntent().getParcelableExtra(EXTRA_MOVIES);
        title = findViewById(R.id.txt_tittle);
        desc = findViewById(R.id.txt_wktrilis);
        info = findViewById(R.id.txt_detail);
        photo = findViewById(R.id.img_movie);

        title.setText(movieItems.getTitle_film());
        desc.setText(movieItems.getDesc_film());
        info.setText(movieItems.getInfo_film());
        photo.setImageResource(movieItems.getImage_film());
    }


}
