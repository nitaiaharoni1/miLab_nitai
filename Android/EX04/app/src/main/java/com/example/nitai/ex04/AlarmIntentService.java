package com.example.nitai.ex04;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class AlarmIntentService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_ALARM = "com.example.nitai.ex4_1.action.ALARM";
    private static final String EXTRA_PARAM_RECEIVER = "com.example.nitai.ex4_1.extra.PARAM_RECEIVER";
    public static final String EXTRA_PARAM_RESULT_STRING = "com.example.nitai.ex4_1.extra.PARAM_RESULT_STRING";

    private Set<String> quotes;

    public AlarmIntentService() {
        super("AlarmIntentService");
        this.quotes = new HashSet<String>();
        quotes.add("a");
        quotes.add("b");
        quotes.add("c");
        quotes.add("d");
        quotes.add("e");
        quotes.add("f");
    }

    public static String getActionAlarm() {
        return ACTION_ALARM;
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionAlarm(Context context, ResultReceiver resultReceiver) {
        Intent intent = new Intent(context, AlarmIntentService.class);
        intent.setAction(ACTION_ALARM);
        intent.putExtra(EXTRA_PARAM_RECEIVER, resultReceiver);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ALARM.equals(action)) {
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_PARAM_RECEIVER);
                handleActionAlarm(receiver);
            }
        }
    }

    /**
     * Handle action in the provided background thread with the provided
     * parameters.
     *
     * @param receiver
     */
    private void handleActionAlarm(ResultReceiver receiver) {
        Log.i("AlarmService", "started");
        String result = "";
        int itemNum = new Random().nextInt(quotes.size());
        int i = 0;
        for(String quote : quotes){
            if (i == itemNum) result = quote;
            i++;
        }
        final Bundle bundle = new Bundle();
        bundle.putString(EXTRA_PARAM_RESULT_STRING, result);
        receiver.send(0, bundle);
    }


}

