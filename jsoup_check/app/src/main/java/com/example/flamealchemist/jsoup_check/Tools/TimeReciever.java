package com.example.flamealchemist.jsoup_check.Tools;

/**
 * Created by Flame Alchemist on 11/16/2015.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.flamealchemist.jsoup_check.Background.NotificationGen;

public class TimeReciever extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d("pftgbnhjnftghn","-------------In Reciever--------------");
        Intent service1 = new Intent(context, NotificationGen.class);
        context.startService(service1);

    }
}