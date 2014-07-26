package io.hearty.hearty.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.hearty.ble.lib.heart.BleHeartService;

public class HeartyManager extends BroadcastReceiver {
    private static final String TAG = "MainActivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == intent)
            return;

        if (Constants.KILL_SERVICE.equals(intent.getAction())) {
            context.stopService(new Intent(context, HeartyLiveCardService.class));
            context.stopService(new Intent(context, BleHeartService.class));
        }
    }
}
