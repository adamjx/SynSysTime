package com.example.shawn.synsystime.utils;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.shawn.synsystime.SNTPURL;
import com.example.shawn.synsystime.SntpClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DaemonService extends Service
{
    private static final String TAG = "SntpClient";
    /*Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    Log.i(TAG , (String) msg.obj);
                    Toast.makeText(DaemonService.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };*/
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i(TAG,"DaemonService started");
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String time = modifySystemTime();
                Log.i(TAG , time);
               /* if(!"null".equals(time))
                {
                    Message msg = handler.obtainMessage(1);
                    msg.obj = time;
                    handler.sendMessage(msg);
                }*/
            }
        }).start();
        return super.onStartCommand(intent,flags,startId);
    }
    private String modifySystemTime()
    {
        SntpClient sntpClient = new SntpClient();
        int i = 0;
        while(i< SNTPURL.SERVER_LIST.length&&!sntpClient.requestTime(SNTPURL.SERVER_LIST[i],500))
        {
            i++;
        }
        if(i<SNTPURL.SERVER_LIST.length)
        {
            long now = sntpClient.getNtpTime() + SystemClock.elapsedRealtime() - sntpClient.getNtpTimeReference();
            Date date = new Date(now);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SystemClock.setCurrentTimeMillis(now);
            return format.format(date);
        }
        return "null";
    }
}
