package com.example.notetaking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NoteDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "NoteDB";
    private static final String DB_TABLE = "DBTable";

    //colunms name for database table
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_TITLE = "title";

    NoteDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table, specify table name, etc.
        // (id INT PRIMARY KEY, title TEXT,
        // content TEXT, date TEXT, time TEXT)
        String query = "CREATE TABLE "+ DB_TABLE + " (" +
                KEY_ID+ " INT PRIMARY KEY"+
                KEY_TITLE + " TEXT, "+
                KEY_CONTENT+ " TEXT, "+
                KEY_DATE + " TEXT.)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion>=newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    /**
     * Add a note in NoteDatabase
     * @param note
     * @return
     */
    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TIME, note.getTime());
        c.put(KEY_DATE, note.getDate());
        c.put(KEY_CONTENT, note.getContent());
        c.put(KEY_TITLE, note.getTitle());

        long ID = db.insert(DB_TABLE, null, c);

        //Log
        Log.d("inserted", " ID -> " + ID);
        return ID;

    }

    public Note getNote(long id){
       // select * from database where ID is 1
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT,KEY_DATE, KEY_TIME}, KEY_ID+"?=",
            new String[]{String.valueOf(id)}, null, null, null);
        

        return null;
    }
}
