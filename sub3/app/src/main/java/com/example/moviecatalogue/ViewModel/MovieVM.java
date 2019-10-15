package com.example.moviecatalogue.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviecatalogue.Items;
import com.example.moviecatalogue.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieVM extends AndroidViewModel {

    private MutableLiveData<ArrayList<Items>> items = new MutableLiveData<>();
    public ArrayList<Items> mitems = new ArrayList<>();
    RequestQueue rq;
    String url;

    public MovieVM(@NonNull Application application) {
        super(application);
        rq = Volley.newRequestQueue(application);
        url = application.getResources().getString(R.string.api_movie);
    }


    public void getAPI() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    int length = jsonArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("title");
                        String photo = result.getString("poster_path");
                        String overview = result.getString("overview");
                        String realease = result.getString("release_date");
                        Log.d("title", title);
                        Items items = new Items(realease,title,overview,photo);

                        mitems.add(items);

                    }

                    items.postValue(mitems);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        rq.add(request);
    }

    public LiveData<ArrayList<Items>> getShow() {

        return items;
    }
}