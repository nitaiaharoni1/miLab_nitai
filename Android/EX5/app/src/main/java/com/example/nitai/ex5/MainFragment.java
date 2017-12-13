package com.example.nitai.ex5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View screen = inflater.inflate(R.layout.fragment_main, container, false);
        final Button button = screen.findViewById(R.id.searchButton);
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        final String url = "https://www.googleapis.com/customsearch/v1?q=";
        final String cx_key = "&cx=007115896535282779850%3Azjkhmuunzj4&key=AIzaSyChY7AM_eKI_7fzdt0pLLlBdFcortvNiGc";


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                new Thread(new Runnable() {
                    String input = ((TextView) screen.findViewById(R.id.titleText)).getText().toString();
                    public void run() {
                        Log.i("Thread", input);
                        StringRequest req = new StringRequest(Request.Method.GET, url+input +cx_key, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Log.d("MainActivityFragment", "Response - " + response);
                                ((TextView) screen.findViewById(R.id.labelText)).setText(response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("MainActivityFragment", "Encountered error - " + error);
                            }
                        });
                        queue.add(req);
                    }
                }).start();
            }
        });

        // Inflate the layout for this fragment
        return screen;
    }


}