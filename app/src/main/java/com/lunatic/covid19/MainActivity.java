package com.lunatic.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CoronaItem> coronaItemArrayList;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        coronaItemArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();


    }


    private void jsonParse() {

        String url = "https://api.covid19india.org/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //fetching array named articles
                    JSONArray jsonArray = response.getJSONArray("statewise");

                    //creating jsonarray object for each array hence use loop
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject articlesObject = jsonArray.getJSONObject(i);
                        String active = articlesObject.getString("active");
                        String death = articlesObject.getString("deaths");
                        String recovered = articlesObject.getString("recovered");
                        String state = articlesObject.getString("state");
                        String confirmed = articlesObject.getString("confirmed");

                        CoronaItem coronaItem = new CoronaItem(state, death, active, recovered, confirmed);
                        coronaItemArrayList.add(coronaItem);
                    }

                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, coronaItemArrayList);
                    recyclerView.setAdapter(recyclerViewAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);

    }


}