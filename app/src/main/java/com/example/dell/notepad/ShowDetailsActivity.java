package com.example.dell.notepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Dell on 04-08-2016.
 */
public class ShowDetailsActivity extends Activity {

    TextView titles,note;
    String titleA,noteA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetails);

        titles = (TextView) findViewById(R.id.bigtitle);
        note = (TextView) findViewById(R.id.bignotes);

        Bundle extras = getIntent().getExtras();
        titleA = extras.getString("title");
        titles.setText(titleA);
        noteA = extras.getString("note");
        note.setText(noteA);

    }

    public void DeleteNote(View view)
    {
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteNoteOnly(titleA,noteA);
        dbHelper.close();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}