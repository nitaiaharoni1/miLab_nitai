package com.example.nitai.ex3_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent;
        if(String.valueOf(v.getTag()) == "stark") {
            intent = new Intent(v.getContext(), starkList.class);
        } else {
            intent = new Intent(v.getContext(), lannisterList.class);
        }
        startActivity(intent);
    }
}
