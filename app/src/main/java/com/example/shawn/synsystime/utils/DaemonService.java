package com.example.shawn.synsystime.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DaemonService extends Service {
    public DaemonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
