package com.example.flamealchemist.jsoup_check.Popup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.flamealchemist.jsoup_check.R;
import com.example.flamealchemist.jsoup_check.helper.DatabaseHelper;
import com.example.flamealchemist.jsoup_check.model.Favourites;

/**
 * Created by Flame Alchemist on 11/12/2015.
 */
public class DisplayPopup extends DialogFragment {
    public String Name, Price, Link;

    DatabaseHelper db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        db = new DatabaseHelper(getActivity());
        builder.setMessage(R.string.name1)
                .setPositiveButton(R.string.name2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!

                        long num_fav = db.getFavouritesCount();
                        if (num_fav < 5) {
                            Favourites favourites = new Favourites(Name, Price, Link);

                            db.createFavourite(favourites);
                        } else {
                            Toast.makeText(getActivity(), "You already have 5 Favourites. Please" +
                                    " delete the existed to add more.", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.name3, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(Link));
                        startActivity(i);
                    }
                });
        // Create the AlertDialog object and return it
        db.closeDB();
        return builder.create();
    }


    public void setArguments(String name, String link, String price){
        Name = name;
        Price = price;
        Link = link;
    }
}
