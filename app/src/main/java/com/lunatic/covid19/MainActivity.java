package com.lunatic.covid19;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Iterator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<CoronaItem> coronaItemArrayList;
    private RequestQueue requestQueue;
    private TextView dailyDeaths, dailyConfirm, dailyReco, dateHeaders, totalDeath, totalConfirm,
            totalRecovered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailyConfirm = findViewById(R.id.dailyConfirm);
        dailyDeaths = findViewById(R.id.dailyDeath);
        dailyReco = findViewById(R.id.dailyRecovered);
        dateHeaders = findViewById(R.id.dateHeader);

        totalRecovered = findViewById(R.id.totalRecovered);
        totalConfirm = findViewById(R.id.totalConfirm);
        totalDeath = findViewById(R.id.totalDeath);


        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        coronaItemArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();


    }

    private String checkNullable(String s) {
        if (s.isEmpty())
            return "0";
        return s;
    }


    private void jsonParse() {

        String url = "https://data.covid19india.org/v4/min/data.min.json";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            String lastUpdated = "0", confirmed = "0", recovered = "0", deceased = "0", tested = "0", vaccinated1 = "0", vaccinated2 = "0", active = "0", todayDeceased = "0", todayRecovered = "0", todayConfirmed = "0", todayTested = "0";

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Iterator<String> keys = response.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (response.get(key) instanceof JSONObject) {
                            JSONObject stateJsonObject = (JSONObject) response.get(key);

                            String stateName = key;

                            JSONObject totalJsonObject = stateJsonObject.getJSONObject("total");
                            JSONObject metaJsonObject = stateJsonObject.getJSONObject("meta");
                            JSONObject deltaJsonObject = null;
                            if (stateJsonObject.has("delta"))
                                deltaJsonObject = stateJsonObject.getJSONObject("delta");

                            Log.d("Deb", "stateName = " + stateName);
                            lastUpdated = getFormattedDate(metaJsonObject.getString("last_updated"));
                            Log.d("Deb", "lastUpdated = " + lastUpdated);
                            Log.d("Deb", "totalJsonObject = " + totalJsonObject);
                            confirmed = totalJsonObject.getString("confirmed");
                            recovered = totalJsonObject.getString("recovered");
                            deceased = totalJsonObject.getString("deceased");
                            tested = totalJsonObject.getString("tested");
                            vaccinated1 = totalJsonObject.getString("vaccinated1");
                            vaccinated2 = totalJsonObject.getString("vaccinated2");
                            active = String.valueOf(Integer.parseInt(confirmed) - (Integer.parseInt(recovered) + Integer.parseInt(deceased)));
                            if (deltaJsonObject != null) {
                                if (deltaJsonObject.has("deceased"))
                                    todayDeceased = deltaJsonObject.getString("deceased");
                                Log.d("Deb", "todayDeceased = " + todayDeceased);
                                if (deltaJsonObject.has("recovered"))
                                    todayRecovered = deltaJsonObject.getString("recovered");
                                Log.d("Deb", "todayRecovered = " + todayRecovered);
                                if (deltaJsonObject.has("confirmed"))
                                    todayConfirmed = deltaJsonObject.getString("confirmed");
                                Log.d("Deb", "todayConfirmed = " + todayConfirmed);
                                if (deltaJsonObject.has("tested"))
                                    todayTested = deltaJsonObject.getString("tested");
                                Log.d("Deb", "todayTested = " + todayTested);
                            }
                            //pass all the detail to CoronaItem class
                            CoronaItem coronaItem = new CoronaItem(stateName, deceased, active, recovered, confirmed, lastUpdated, todayDeceased, todayRecovered, todayConfirmed,
                                    vaccinated1, vaccinated2, tested, todayTested);
                            coronaItemArrayList.add(coronaItem);
                        }
                    }

//                    JSONObject todayAndTotalDataJsonObject = todayAndTotalDataArray.getJSONObject(0);
//
//                    String dailyConfirmed = todayAndTotalDataJsonObject.getString("deltaconfirmed");
//                    String dailyDeath = todayAndTotalDataJsonObject.getString("deltadeaths");
//                    String dailyRec = todayAndTotalDataJsonObject.getString("deltarecovered");
//                    String dateHeader = todayAndTotalDataJsonObject.getString("lastupdatedtime").substring(0, 5);
//                    dateHeader = getFormattedDate(dateHeader);
//
//                    dailyConfirm.setText(dailyConfirmed);
//                    dailyReco.setText(dailyRec);
//                    dailyDeaths.setText(dailyDeath);
//                    dateHeaders.setText(dateHeader);
//
//                    //for Total Details
//                    //"todayAndTotalDataArray" holds data of all states
//                    //At index 0 of todayAndTotalDataArray "Total Details" is stored
//                    String totalDeathsFetched = todayAndTotalDataJsonObject.getString("deaths");
//                    String totalRecoverFetched = todayAndTotalDataJsonObject.getString("recovered");
//                    String totalConfirmedFetched = todayAndTotalDataJsonObject.getString("confirmed");
//
//                    totalConfirm.setText(totalConfirmedFetched);
//                    totalDeath.setText(totalDeathsFetched);
//                    totalRecovered.setText(totalRecoverFetched);
//
//
//                    //Secondly, fetch and display data for all states
//                    //that data is also present in todayAndTotalDataArray from index 1
//                    for (int i = 1; i < todayAndTotalDataArray.length(); i++) {
//                        JSONObject stateWiseArrayJSONObject = todayAndTotalDataArray.getJSONObject(i);
//                        String active = stateWiseArrayJSONObject.getString("active");
//                        String death = stateWiseArrayJSONObject.getString("deaths");
//                        String recovered = stateWiseArrayJSONObject.getString("recovered");
//                        String state = stateWiseArrayJSONObject.getString("state");
//                        String confirmed = stateWiseArrayJSONObject.getString("confirmed");
//                        String lastUpdated = stateWiseArrayJSONObject.getString("lastupdatedtime");
//
//                        //details of today cases for each individual state
//                        String todayActive = stateWiseArrayJSONObject.getString("deltaconfirmed");
//                        String todayDeath = stateWiseArrayJSONObject.getString("deltadeaths");
//                        String todayRecovered = stateWiseArrayJSONObject.getString("deltarecovered");
//
//
//                        //pass all the detail to CoronaItem class
//                        CoronaItem coronaItem = new CoronaItem(state, death, active, recovered, confirmed, lastUpdated, todayDeath, todayRecovered, todayActive);
//                        coronaItemArrayList.add(coronaItem);
//                    }
//
                    //setting the RecyclerView to display all information
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


    //formatting date from numeric to aplhabetic  like 03/04 to 03 April
    private String getFormattedDate(String dateHeader) {
        switch (dateHeader.substring(5, 7)) {
            case "01":
                return dateHeader.substring(0, 2) + " Jan";
            case "02":
                return dateHeader.substring(0, 2) + " Feb";
            case "03":
                return dateHeader.substring(0, 2) + " March";
            case "04":
                return dateHeader.substring(0, 2) + " April";
            case "05":
                return dateHeader.substring(0, 2) + " May";
            case "06":
                return dateHeader.substring(0, 2) + " June";
            case "07":
                return dateHeader.substring(0, 2) + " July";
            case "08":
                return dateHeader.substring(0, 2) + " Aug";
            case "09":
                return dateHeader.substring(0, 2) + " Sep";
            case "10":
                return dateHeader.substring(0, 2) + " Oct";
            case "11":
                return dateHeader.substring(0, 2) + " Nov";
            case "12":
                return dateHeader.substring(0, 2) + " Dec";
            default:
                return null;
        }
    }

    private String formatNumbers(String num) {
        String result = "";
        int count = 0;
        for (int i = num.length(); i >= 0; i--) {
            result += num.charAt(i);
            if (count % 2 == 0 && i > 0)
                result += ",";
        }
        return result;
    }
}