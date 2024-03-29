package com.example.moviecatalog.fragment;


import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalog.Detail_item;
import com.example.moviecatalog.R;
import com.example.moviecatalog.adapter.Adapter;
import com.example.moviecatalog.adapter.Items;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class show_fragment extends Fragment implements Adapter.OnItemClickListener {

    String[] nameData;
    String[] overviewData;
    String[] descData;
    TypedArray photoData;
        Adapter adapter;
        RecyclerView rv;
        ArrayList<Items> mMovieTvItems;
        Activity context;
        View v;

    public show_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_show_fragment, container, false);
        rv = v.findViewById(R.id.show);

        rv.setHasFixedSize(true);

        mMovieTvItems = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        prepare();
        additem();
        return v;
    }

    public void prepare() {
        nameData =  getResources().getStringArray(R.array.tittle_show);
        overviewData = getResources().getStringArray(R.array.realeas_show);
        descData = getResources().getStringArray(R.array.info_show);
        photoData =  getResources().obtainTypedArray(R.array.img_show);
    }

    private void additem() {
        ArrayList<Items> items = new ArrayList<>();

        for (int i = 0; i < nameData.length; i++) {
            Items movie = new Items();
            movie.setImage_film(photoData.getResourceId(i, -1));
            movie.setTitle_film(nameData[i]);
            movie.setInfo_film(overviewData[i]);
            movie.setDesc_film(descData[i]);
            items.add(movie);
        }

        adapter = new Adapter(getContext(),items);
        adapter.setOnItemClickListener(show_fragment.this);
        rv.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int position) {
        Items items = new Items();

        items.setImage_film(photoData.getResourceId(position, -1));
        items.setTitle_film(nameData[position]);
        items.setInfo_film(overviewData[position]);
        items.setDesc_film(descData[position]);
        Intent intent = new Intent(getContext(),Detail_item.class);
        intent.putExtra(Detail_item.EXTRA_MOVIES,items);
        startActivity(intent);
    }
}