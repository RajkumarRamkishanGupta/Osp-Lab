package com.osp.osplab.viewmodels;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.osp.osplab.controller.AppController;
import com.osp.osplab.models.News;

import org.json.JSONObject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsActivityViewModel extends ViewModel {

    private MutableLiveData<News> mNicePlaces = new MutableLiveData<>();
    //MutableLiveData<News> data = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init() {
        String tag_json_obj = "json_obj_req";

        String url = "https://newsapi.org/v2/top-headlines?Country=us&apiKey=2352d41b50804fcebefbbe2fde7d8b13";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        News user = gson.fromJson(String.valueOf(response), News.class);
                        mNicePlaces.setValue(user);
                        //mNicePlaces = data;

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    public LiveData<News> getNicePlaces() {
        return mNicePlaces;
    }


    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }
}
