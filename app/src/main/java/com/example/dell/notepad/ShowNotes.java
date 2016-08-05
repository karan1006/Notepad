package com.example.dell.notepad;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShowNotes extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listView;
    String title,note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        dbHelper = new DBHelper(this);

        final List<String> titlelist = dbHelper.getTitlesOnly();
        final List<String> noteList = dbHelper.getNotesOnly();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titlelist);

        listView = (ListView) findViewById(R.id.notesList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                title= titlelist.get(i);
                note = noteList.get(i);

                Intent intent = new Intent(getApplicationContext(),ShowDetailsActivity.class);

                intent.putExtra("title",title);
                intent.putExtra("note",note);
                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);
    }
}
