package com.feigdev.hearty.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.feigdev.ble.lib.heart.BleHeartService;

public class HeartyManager extends BroadcastReceiver {
    private static final String TAG = "MainActivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == intent)
            return;

        String action = intent.getAction();
        if (Constants.KILL_SERVICE.equals(action)) {
            context.stopService(new Intent(context, HeartyLiveCardService.class));
            context.stopService(new Intent(context, BleHeartService.class));
        }
    }
}
