package com.example.flamealchemist.jsoup_check.Background;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.flamealchemist.jsoup_check.Activity.FavouriteActivity;
import com.example.flamealchemist.jsoup_check.Tools.Compare_Price;
import com.example.flamealchemist.jsoup_check.Crawler_Package.New_Price;
import com.example.flamealchemist.jsoup_check.R;
import com.example.flamealchemist.jsoup_check.helper.DatabaseHelper;
import com.example.flamealchemist.jsoup_check.model.Favourites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Flame Alchemist on 11/16/2015.
 */
public class  NotificationGen  extends Service {
    private NotificationManager mManager;
    private New_Price np;
    private String new_price;
    private DatabaseHelper db;
    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
        System.out.println("Service Created");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId)
    {
        if(isConnectingToInternet()){
            List<Favourites> favourites = new ArrayList<>();
            db = new DatabaseHelper(getApplicationContext());
            favourites = db.getAllFavourites();
            System.out.println("---------------------- In Service -----------");
            np = new New_Price();
            Random rd = new Random();
            Compare_Price cp = new Compare_Price();
            for (Favourites fv : favourites) {
            //    fv.setPrice("50000");
                System.out.println(fv.getName() + " " + fv.getPrice());
                final New_Price np = new New_Price();
                final Favourites fav = new Favourites();
                fav.setLink(fv.getLink());
                Thread T1 = new Thread("T1") {
                    public void run() {
                        new_price = np.GetPrice(fav.getLink());
                    }
                };
                T1.start();
                try {
                    T1.join();
                } catch (InterruptedException e) {

                }
                if (cp.isBigger(fv.getPrice(), new_price) == 1) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.bg)
                            .setContentTitle("Hurry Price Goes Down")
                            .setContentText(fv.getName());
                    int not_id = rd.nextInt();
                    fv.setPrice(new_price);
                    db.updateFavourite(fv);
                    Intent targerintent = new Intent(getApplicationContext(), FavouriteActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, targerintent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mManager.notify(not_id, builder.build());
                }
            }
        }
        System.out.println("------------------- Ending Service -------------");
        return START_STICKY;
    }

    @Deprecated
    public boolean isConnectingToInternet(){
            ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifi.isConnected()) {
                return true;
            } else if (mobile.isConnected()) {
                return true;
            }
            return false;
    }
    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
