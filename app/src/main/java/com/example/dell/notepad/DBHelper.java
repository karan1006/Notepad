package com.example.dell.notepad;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;


public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "notepadDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "Notepad";
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_NOTE = "note";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTEPAD_TABLE = "create table Notepad(title TEXT, note TEXT)";

        db.execSQL(CREATE_NOTEPAD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addNote(NoteClass nc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(COL_TITLE, nc.getTitle());
            values.put(COL_NOTE, nc.getNote());
            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
        Toast.makeText(context, "" + nc.getTitle() + "  " + nc.getNote(), Toast.LENGTH_SHORT).show();
        return true;
    }

    public List<String> getTitlesOnly() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select title from " + TABLE_NAME, null);
        List<String> titlestr = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                titlestr.add(cursor.getString(0));
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        } else {
            titlestr = null;
            cursor.close();
            db.close();
        }
        return titlestr;

    }

    public List<String> getNotesOnly() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select note from " + TABLE_NAME, null);
        List<String> notestr = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                notestr.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        else{
            notestr = null; }
        cursor.close();
        db.close();
        return notestr;
    }

    public void deleteNoteOnly(String title,String note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"title = ? and note = ?",new String[] {title,note});
    }
}
