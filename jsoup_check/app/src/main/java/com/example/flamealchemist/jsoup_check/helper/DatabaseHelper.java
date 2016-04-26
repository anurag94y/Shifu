package com.example.flamealchemist.jsoup_check.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.flamealchemist.jsoup_check.Tools.DateTime;
import com.example.flamealchemist.jsoup_check.model.Favourites;
import com.example.flamealchemist.jsoup_check.model.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flame Alchemist on 11/14/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "scrooge";

    // Table Names
    private static final String FAVOURITE = "favourite";
    private static final String NOTIFICATION = "notification";
    private static final String HISTORY = "history";

    // Common column names
    private static final String KEY_ID = "id";


    // FAVOURITE Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_LINK = "link";

    // History Table - column names
    private static final String KEY_QUERY = "query";

    // Notification Table - column names
    private static final String KEY_BODY = "body";
    private static final String KEY_VIEWED = "viewed";
    private static final String KEY_CREATED_AT = "created_at";
    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_FAVOURITE = "CREATE TABLE "
            + FAVOURITE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_PRICE + " TEXT," + KEY_LINK
            + " TEXT" + ")";


    private static final String CREATE_TABLE_HISTORY = "CREATE TABLE "
            + HISTORY + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_QUERY
            + " TEXT, " + KEY_CREATED_AT + " DATETIME" + ")";


    private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE "
            + NOTIFICATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BODY
            + " TEXT," + KEY_CREATED_AT + " DATETIME," + KEY_VIEWED
            + " BOOLEAN" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override

    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_FAVOURITE);
        db.execSQL(CREATE_TABLE_NOTIFICATION);
        db.execSQL(CREATE_TABLE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + FAVOURITE);

        // create new tables
        onCreate(db);
    }

    public long createFavourite( Favourites favourites ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, favourites.getName());
        values.put(KEY_PRICE, favourites.getPrice());
        values.put(KEY_LINK, favourites.getLink());

        // insert row
        long favourite_id = db.insert(FAVOURITE, null, values);

        return favourite_id;
    }

    public void deleteFavourite(long favourite_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FAVOURITE, KEY_ID + " = ?",
                new String[] { String.valueOf(favourite_id) });
    }

    public List<Favourites> getAllFavourites() {
        List<Favourites> fvs = new ArrayList<Favourites>();
        String selectQuery = "SELECT  * FROM " + FAVOURITE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Favourites fv = new Favourites();
                fv.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                fv.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                fv.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                fv.setLink(c.getString(c.getColumnIndex(KEY_LINK)));
                // adding to todo list
                fvs.add(fv);
            } while (c.moveToNext());
        }

        return fvs;
    }

    public long getFavouritesCount (){
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM " + FAVOURITE, null);
    }

    public int updateFavourite(Favourites favourites) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRICE, favourites.getPrice());
        values.put(KEY_NAME, favourites.getName());
        values.put(KEY_LINK, favourites.getLink());

        // updating row
        return db.update(FAVOURITE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(favourites.getId())});
    }

    public void createHistory(History history) {
        SQLiteDatabase db = this.getWritableDatabase();
        DateTime dt = new DateTime();
        ContentValues values = new ContentValues();
        values.put(KEY_QUERY, history.getQuery());
         values.put(KEY_CREATED_AT, dt.getDateTime());

        // insert row
        long history_id = db.insert(HISTORY, null, values);
        db.close();
    }

    public List<History> getAllHistory() {
        List<History> hst = new ArrayList<History>();
        String selectQuery = "SELECT  * FROM " + HISTORY;

     //   Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                History hs = new History();
                hs.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                hs.setQuery(c.getString(c.getColumnIndex(KEY_QUERY)));
                hs.setCreated_at(c.getString((c.getColumnIndex(KEY_CREATED_AT))));
                hst.add(hs);
            } while (c.moveToNext());
        }
        db.close();
        return hst;
    }

    public void deleteHistory(long history_id){
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(history_id);
        db.delete(HISTORY, KEY_ID + " = " + Long.toString(history_id),null);
        db.close();
    }

    public void deleteAllHistory(){
        List<History> hst = new ArrayList<History>();
        hst=getAllHistory();

        for(History hs:hst){
            deleteHistory(hs.getId());
        }
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
