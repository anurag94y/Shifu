package com.example.flamealchemist.jsoup_check.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.flamealchemist.jsoup_check.Adapter.MyArrayAdapter;
import com.example.flamealchemist.jsoup_check.Popup.DisplayPopupHistory;
import com.example.flamealchemist.jsoup_check.R;
import com.example.flamealchemist.jsoup_check.helper.DatabaseHelper;
import com.example.flamealchemist.jsoup_check.model.History;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class HistoryActivity extends ListActivity {
    ProgressDialog progressDialog;
    Vector<String> Dates,Queries;
    Vector<Long> Ids;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        TextView tx = (TextView)findViewById(R.id.headertext2);
        tx.setText("Previous Searches");
        db = new DatabaseHelper(getApplicationContext());
        List<History> histories = new ArrayList<History>();
        histories = db.getAllHistory();
        Dates = new Vector<String>();
        Queries = new Vector<String>();
        Ids = new Vector<Long>();
        Collections.reverse(histories);
        for (History hs:histories){
            System.out.println(hs.getCreated_at()+" "+hs.getId()+" "+hs.getQuery());
            Dates.add(hs.getCreated_at());
            Queries.add(hs.getQuery());
            Ids.add(hs.getId());
        }

        MyArrayAdapter adapter = new MyArrayAdapter(getApplicationContext(), Dates.toArray(new String[Dates.size()]), Queries.toArray(new String[Queries.size()]));
        setListAdapter(adapter);
        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        String item = (String) getListAdapter().getItem(position);
        DisplayPopupHistory displayPopup = new DisplayPopupHistory();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        System.out.println(Ids.get(position)+" "+Queries.get(position));
        displayPopup.setArguments(Queries.get(position),Ids.get(position));
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
