package com.example.flamealchemist.jsoup_check.Activity;

        import java.util.Calendar;
        import java.util.LinkedHashMap;
        import java.util.Vector;

        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.flamealchemist.jsoup_check.Background.NotificationGen;
        import com.example.flamealchemist.jsoup_check.Crawler_Package.Crawler;
        import com.example.flamealchemist.jsoup_check.Tools.GetSet;
        import com.example.flamealchemist.jsoup_check.R;
        import com.example.flamealchemist.jsoup_check.helper.DatabaseHelper;
        import com.example.flamealchemist.jsoup_check.model.History;

public class MainActivity extends Activity {

//    private static final String URL = "http://www.flipkart.com/search?q=moto+g3+8&as=off&as-show=on&otracker=start";
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    private String query="";
    private PendingIntent pendingIntent;
    private String his="";
    ProgressDialog progressDialog;
    Vector<String> Names, Links, Prices;
    Crawler crawler;
    DatabaseHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());

    //    EditText E = (EditText)findViewById(R.id.productName);
    //    Button srchbtn = (Button) findViewById(R.id.btnData);
    //    Typeface txt = Typeface.createFromAsset(getAssets(), "fonts/JosefinSlab-Regular.ttf");
    //    srchbtn.setTypeface(txt);
    //    E.setTypeface(txt);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent myIntent = new Intent(MainActivity.this, NotificationGen.class);
        pendingIntent = PendingIntent.getService(MainActivity.this,0,myIntent,0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 6*60*60*1000, pendingIntent);

    //   startService(myIntent);

        db.closeDB();
    }

    public void showHistory(View v){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void  showFavourites(View v){
        Intent intent = new Intent(this,FavouriteActivity.class);
        startActivity(intent);
    }

    @Deprecated
    public boolean isConnectingToInternet(){
        ConnectivityManager connec = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi.isConnected()) {
            return true;
        } else if (mobile.isConnected()) {
            return true;
        }
        return false;
    }
    public void sendmessage(View v){
        if(isConnectingToInternet()) {
            EditText E1 = (EditText) findViewById(R.id.productName);
            his = E1.getText().toString().trim();
            String[] Arr = his.split(" ");
            query = "";
            //   Toast.makeText(this, Integer.toString(Arr.length), Toast.LENGTH_LONG).show();
            if (!Arr[0].isEmpty()) {
                query += Arr[0];
                for (int i = 1; i < Arr.length; i++) {
                    query += '+';
                    query += Arr[i];
                }
                //    Intent intent = new Intent(this , ResultActivity.class);
                //    intent.putExtra(EXTRA_MESSAGE , query);
                //    startActivity(intent);

                crawler = new Crawler(query);
                new FetchWebsiteData().execute();

            } else {
                Toast.makeText(this, "Please Enter Product Name", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Check you Internet connection", Toast.LENGTH_LONG).show();
        }
    }
    private class FetchWebsiteData extends AsyncTask<Void, Void, Void> {
        String websiteTitle, websiteDescription;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                EditText E1 = (EditText)findViewById(R.id.productName);
                History history = new History(his);
                db.createHistory(history);
                crawler.Crawl();
            } catch (InterruptedException e){

          }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            GetSet getSet = new GetSet();
            getSet.set(crawler.GetNames(), crawler.GetPrices(), crawler.GetLinks());
            LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
            hashMap.put("key", getSet);
            Intent intent = new Intent(getApplicationContext() ,ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bundleobj", hashMap);
            intent.putExtras(bundle);
            startActivity(intent);
            progressDialog.dismiss();
        }
    }
}