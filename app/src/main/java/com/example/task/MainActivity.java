package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<Item>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        Button sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortItemsByName();
            }
        });

        androidx.appcompat.widget.SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterItems(newText);
                return true;
            }
        });

        fetchItems();

    }

    private void fetchItems() {
        String url = "https://rickandmortyapi.com/api/character";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray resultsObject = response.getJSONArray("results");
                            Log.d("API_RESPONSE", response.toString());

                            for (int i = 0; i < resultsObject.length(); i++) {
                                JSONObject jsonObject = (JSONObject) resultsObject.get(i);
                                String name = jsonObject.getString("name");
                                String status = jsonObject.getString("status");
                                String species = jsonObject.getString("species");
                                String image = jsonObject.getString("image");

                                Item item = new Item(image, name, status, species);
                                items.add(item);
                            }

                            // Move adapter setup outside the loop
                            MyAdapter adapter = new MyAdapter(MainActivity.this, items);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"not working", Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void sortItemsByName() {
        // Sort the items alphabetically by name
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getName().compareToIgnoreCase(item2.getName());
            }
        });

        // Update the RecyclerView with the sorted items
        MyAdapter adapter = new MyAdapter(MainActivity.this, items);
        recyclerView.setAdapter(adapter);
    }

    private void filterItems(String query) {
        // Filter items based on the query
        List<Item> filteredList = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }

        // Update the RecyclerView with the filtered items
        MyAdapter adapter = new MyAdapter(MainActivity.this, filteredList);
        recyclerView.setAdapter(adapter);
    }
}