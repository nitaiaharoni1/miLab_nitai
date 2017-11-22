package com.example.nitai.ex3_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitai on 11/15/2017.
 */

public class starkList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    protected void onCreat(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        ListItem listItem = new ListItem("John Snow");
        listItems.add((ListItem) listItems);

        listItem = new ListItem("Arya Stark");
        listItems.add((ListItem) listItems);

        listItem = new ListItem("Sansa Snow");
        listItems.add((ListItem) listItems);

        adapter = new MyAdapter(listItems, this);

        recyclerView.setAdapter(adapter);


    }

}
