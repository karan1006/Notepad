package com.example.dell.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addNote;
    private Button showAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote= (Button) findViewById(R.id.addNote);
        showAll= (Button) findViewById(R.id.showAll);

        addNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WriteNotesActivity.class);
                startActivity(intent);

            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),ShowNotes.class);
                startActivity(intent);


            }
        });
    }
}
