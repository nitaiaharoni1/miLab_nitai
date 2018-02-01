package com.example.nitai.ex_10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

public class MainFragment extends Fragment {
    private Socket mSocket;
    Button NotificationsButton;
    TextView coinQuote;
    EditText stockName;
    private String coinName;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainFragment", "socket");
        Coin app = (Coin) getActivity().getApplication();
        mSocket = app.getSocket();
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on("printCur", onNewPost);
        mSocket.connect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);

        return mainView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NotificationsButton = (Button) view.findViewById(R.id.getNotifications);
        stockName = (EditText) view.findViewById(R.id.coinName);
        coinQuote = (TextView) view.findViewById(R.id.coinText);

        NotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinName = stockName.getText().toString();
                stockName.setText("");
            }
        });
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mSocket.emit("printCur", coinName);
                }
            });
        }
    };


    private Emitter.Listener onNewPost = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String mes = args[0].toString();
                    coinQuote.setText(mes);
                }
            });
        }
    };

    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off("printCur", onNewPost);
    }

}