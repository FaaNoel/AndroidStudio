package com.example.moviecatalogue.Fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviecatalogue.Adapter;
import com.example.moviecatalogue.Items;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.ViewModel.MovieVM;
import com.example.moviecatalogue.activity.DetailActivity;

import java.util.ArrayList;

import static com.example.moviecatalogue.activity.DetailActivity.EXTRA_DETAIL;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements Adapter.OnItemClickListener {

    Adapter adapter;
    MovieVM movieVM;

    ProgressBar sProgressBar;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        sProgressBar = v.findViewById(R.id.loading_film);



        adapter = new Adapter(getContext());
        adapter.setOnItemClickListener(MovieFragment.this);
        adapter.notifyDataSetChanged();

        movieVM = ViewModelProviders.of(getActivity()).get(MovieVM.class);
        movieVM.getShow().observe(MovieFragment.this, getShow);
        movieVM.getAPI();

        RecyclerView mRecyclerView = v.findViewById(R.id.film);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);

        return v;
    }

    private Observer<ArrayList<Items>> getShow = new Observer<ArrayList<Items>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Items> movieItems) {
            if (movieItems != null) {
                adapter.setmItems(movieItems);
                showLoading(false);
            }
        }
    };



    @Override
    public void onItemClick(int i) {
        Items items = new Items(movieVM.mitems.get(i).getInfo_film(), movieVM.mitems.get(i).getTitle_film(), movieVM.mitems.get(i).getDesc_film(), movieVM.mitems.get(i).getPhoto());

        Intent detail = new Intent(getContext(), DetailActivity.class);

        detail.putExtra(EXTRA_DETAIL, items);
        startActivity(detail);
    }
    private void showLoading(Boolean state) {
        if (state) {
            sProgressBar.setVisibility(View.VISIBLE);
        } else {
           sProgressBar.setVisibility(View.GONE);
        }
    }
}