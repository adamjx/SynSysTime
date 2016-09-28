package com.example.shawn.synsystime.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarmreceiver extends BroadcastReceiver
{
    private static final String TAG = "SntpClient";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("arui.alarm.action")) {
            Log.i(TAG,"Alarmreceiver received");
            Intent i = new Intent();
            i.setClass(context, DaemonService.class);
            context.startService(i);
        }
    }
}
