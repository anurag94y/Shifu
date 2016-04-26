package com.example.flamealchemist.jsoup_check.Popup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.flamealchemist.jsoup_check.Activity.FavouriteActivity;
import com.example.flamealchemist.jsoup_check.Activity.MainActivity;
import com.example.flamealchemist.jsoup_check.R;
import com.example.flamealchemist.jsoup_check.helper.DatabaseHelper;

/**
 * Created by Flame Alchemist on 11/14/2015.
 */
public class DisplayPopupFavourite extends DialogFragment {
    public long idt;
    String Link;
    DatabaseHelper db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        db = new DatabaseHelper(getActivity());
        builder.setMessage(R.string.name1)
                .setPositiveButton(R.string.name4, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!

                        db.deleteFavourite(idt);
                        getActivity().finish();
                        Intent intent = new Intent(getActivity(), FavouriteActivity.class);
                        startActivity(intent);
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


    public void setArguments(long id ,String Link) {
        this.idt =id;
        this.Link = Link;
    }
}
