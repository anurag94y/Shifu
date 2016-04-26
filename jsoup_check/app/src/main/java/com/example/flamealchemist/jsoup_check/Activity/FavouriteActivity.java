package com.example.flamealchemist.jsoup_check.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.flamealchemist.jsoup_check.Adapter.MyArrayAdapter;
import com.example.flamealchemist.jsoup_check.Popup.DisplayPopupFavourite;
import com.example.flamealchemist.jsoup_check.R;
import com.example.flamealchemist.jsoup_check.helper.DatabaseHelper;
import com.example.flamealchemist.jsoup_check.model.Favourites;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FavouriteActivity extends ListActivity {

    DatabaseHelper db;
    Vector<Long> Ids;
    Vector<String> Names, Prices, Links;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        TextView tx = (TextView)findViewById(R.id.headertext1);
        tx.setText("Favourites");
        db = new DatabaseHelper(getApplicationContext());
        List<Favourites> fvs = new ArrayList<Favourites>();
        Ids = new Vector<>();
        Names = new Vector<>();
        Prices = new Vector<>();
        Links = new Vector<>();

        fvs = db.getAllFavourites();
        for(Favourites fv: fvs){
            Log.e("Favourite",fv.getName()+" "+fv.getPrice()+" "+fv.getLink());
            Ids.add(fv.getId());
            Names.add(fv.getName());
            Prices.add(fv.getPrice());
            Links.add(fv.getLink());
        }

        MyArrayAdapter adapter = new MyArrayAdapter(getApplicationContext(), Names.toArray(new String[Names.size()]), Prices.toArray(new String[Prices.size()]));
        setListAdapter(adapter);
        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faourite, menu);
        return true;
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        String item = (String) getListAdapter().getItem(position);
        DisplayPopupFavourite displayPopup = new DisplayPopupFavourite();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        displayPopup.setArguments(Ids.get(position),Links.get(position));
        displayPopup.show(fragmentTransaction, "you");
    }

}
