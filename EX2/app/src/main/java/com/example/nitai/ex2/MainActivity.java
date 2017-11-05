package com.example.nitai.ex2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void submitFunction(View view){
        EditText nameField = (EditText) findViewById(R.id.textField);
        Toast.makeText(this,"Your name is: " + nameField.getText(), Toast.LENGTH_LONG ).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
