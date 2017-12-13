package com.example.nitai.ex04;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.alarmButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MainActivity","Clicked");
                Intent alarmIntentService = new Intent(view.getContext(), AlarmIntentService.class);
                alarmIntentService.setAction(AlarmIntentService.getActionAlarm());
                startService(alarmIntentService);

            }
        });
    }

    private class MyResultReceiver extends ResultReceiver {
        MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            String result = (String) resultData.get(AlarmIntentService.EXTRA_PARAM_RESULT_STRING);
            Log.i("MainActivity", "Result code from receiver " + resultCode);
            Log.i("MainActivity", "Result that came back - " + result);
        }
    }


}
