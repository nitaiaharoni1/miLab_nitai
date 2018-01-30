package com.example.nitai.ex_09;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

public class TimeF {
    private final static String REQUEST_URL = "http://localhost:5000/getTime";
    private RequestQueue _queue;

    public class TimeResponse {
        public String time;

        public TimeResponse(String time) {
            this.time = time;
        }
    }

    public interface TimeResponseListener {
        public void onResponse(TimeResponse response);
    }

    public TimeF(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private TimeResponse createErrorResponse() {
        return new TimeResponse(null);
    }

    public void dispatchRequest(final String city, final TimeResponseListener listener) {
        JSONObject requestObject = new JSONObject();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL, requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            TimeResponse res = new TimeResponse(response.getString("time"));
                            listener.onResponse(res);
                        } catch (JSONException e) {
                            listener.onResponse(createErrorResponse());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponse(createErrorResponse());
            }
        });

        _queue.add(req);
    }
}
