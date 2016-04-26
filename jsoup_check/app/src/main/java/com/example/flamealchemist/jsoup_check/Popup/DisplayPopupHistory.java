package com.example.flamealchemist.jsoup_check.Popup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.flamealchemist.jsoup_check.Crawler_Package.Crawler;
import com.example.flamealchemist.jsoup_check.Tools.GetSet;
import com.example.flamealchemist.jsoup_check.Activity.HistoryActivity;
import com.example.flamealchemist.jsoup_check.R;
import com.example.flamealchemist.jsoup_check.Activity.ResultActivity;
import com.example.flamealchemist.jsoup_check.helper.DatabaseHelper;
import com.example.flamealchemist.jsoup_check.model.History;

import java.util.LinkedHashMap;

/**
 * Created by Flame Alchemist on 11/14/2015.
 */
public class DisplayPopupHistory extends DialogFragment {
    public String Query;
    long idt;
    ProgressDialog progressDialog;
    Crawler crawler;
    DatabaseHelper db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        db = new DatabaseHelper(getActivity());
        builder.setMessage(R.string.name1)
                .setPositiveButton(R.string.name5, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (isConnectingToInternet()) {
                                    History history = new History(Query);
                                    db.createHistory(history);
                                    String query = "";
                                    String[] Arr = Query.split(" ");

                                    query = "";

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
                                        new FetchWebsiteData(getActivity()).execute();
                                  /*  getActivity().finish();
                                    Intent intent = new Intent(getContext(), HistoryActivity.class);
                                    startActivity(intent);*/


                                    }
                                } else {
                                    Toast.makeText(getActivity(), "Check you Internet connection", Toast.LENGTH_LONG).show();

                                }

                            }
                        }


                )
                .

                        setNegativeButton(R.string.name4, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                        System.out.println(idt);
                                        db.deleteHistory(idt);
                                        //   db.closeDB();
                                        getActivity().finish();
                                        Intent intent = new Intent(getActivity(), HistoryActivity.class);
                                        startActivity(intent);
                                    }
                            }

                    );
                    // Create the AlertDialog object and return it
                    db.closeDB();
                    return builder.create();


                }


    public void setArguments(String query,long Id){
        Query = query;
        idt = Id;
        System.out.println("aa " + query + " " + Id + " this " + this.Query + " " + this.idt);
    }
    private class FetchWebsiteData extends AsyncTask<Void, Void, Void> {

        Context context;
        FetchWebsiteData(Context m){
            this.context = m;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

                try {
                    System.out.println("Before Crawler");
               crawler.Crawl();
                    System.out.println("Out of Crawler");
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
            Intent intent = new Intent(context ,ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bundleobj", hashMap);
            intent.putExtras(bundle);
            context.startActivity(intent);
            progressDialog.dismiss();
        }
    }

    @Deprecated
    public boolean isConnectingToInternet(){
        ConnectivityManager connec = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi.isConnected()) {
            return true;
        } else if (mobile.isConnected()) {
            return true;
        }
        return false;
    }
}
