package com.example.flamealchemist.jsoup_check.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.example.flamealchemist.jsoup_check.Tools.GetSet;
import com.example.flamealchemist.jsoup_check.Adapter.MyArrayAdapter;
import com.example.flamealchemist.jsoup_check.Popup.DisplayPopup;
import com.example.flamealchemist.jsoup_check.R;

import java.util.HashMap;
import java.util.Vector;

public class ResultActivity extends ListActivity {


    private Vector <String> Names ,Prices, Links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tx = (TextView)findViewById(R.id.headertext);
        tx.setText("Scrooge Result");
        Intent intent = getIntent();
        Names =  new Vector<>();
        Prices = new Vector<>();
        Links = new Vector<>();
        Bundle bundle = new Bundle();
        bundle = intent.getExtras();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap = (HashMap<String, Object>) bundle.getSerializable("bundleobj");
        GetSet getSet = (GetSet) hashMap.get("key");
        Names = getSet.GetNames();
        Prices = getSet.GetPrices();
        Links = getSet.GetLinks();

        MyArrayAdapter adapter = new MyArrayAdapter(this, Names.toArray(new String[Names.size()]), Prices.toArray(new String[Prices.size()]));
        setListAdapter(adapter);

    }

    protected void onListItemClick(ListView l, View v, int position, long id) {

        String item = (String) getListAdapter().getItem(position);
        DisplayPopup displayPopup = new DisplayPopup();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        displayPopup.setArguments(Names.get(position),Links.get(position),Prices.get(position));
        displayPopup.show(fragmentTransaction, "you");

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
