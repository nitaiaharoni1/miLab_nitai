package com.example.nitai.ex_10;

import android.app.Application;
import java.net.URISyntaxException;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


public class Coin extends Application {
    String server = "http://localhost:5000";
    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(server);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}