package com.example.shawn.synsystime;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SntpClient";
    private TextView time_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*time_tv = (TextView) findViewById(R.id.time_tv);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String time = modifySystemTime();
                if(!"null".equals(time))
                {
                    Message msg = handler.obtainMessage(1);
                    msg.obj = time;
                    handler.sendMessage(msg);
                }
            }
        }).start();*/
        sendBroadcast(new Intent("arui.boot.action"));
    }
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    Log.i(TAG , (String) msg.obj);
                    time_tv.setText((String) msg.obj);
                    break;
            }
        }
    };
    private String modifySystemTime()
    {
        SntpClient sntpClient = new SntpClient();
        int i = 0;
        while(i<SNTPURL.SERVER_LIST.length&&!sntpClient.requestTime(SNTPURL.SERVER_LIST[i],500))
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
