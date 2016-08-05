package com.example.dell.notepad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteNotesActivity extends AppCompatActivity {


    private Button saveButton;

    EditText addTitle, addNote;

    String title;
    String note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notes);

        saveButton = (Button) findViewById(R.id.save);

        addTitle = (EditText) findViewById(R.id.title);
        addNote = (EditText) findViewById(R.id.note);

    }

    public void SaveDetails(View view) {

        final DBHelper dbHelper = new DBHelper(this);

        title = addTitle.getText().toString();
        note= addNote.getText().toString();

        if (title.equals("") || note.equals(""))
        {
            Toast.makeText(this,"Please fill details",Toast.LENGTH_SHORT).show();
            finish();
        }

        else {

            NoteClass noteClass = new NoteClass(title, note);

                Boolean state = dbHelper.addNote(noteClass);

            if(state){

                Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show();

            }
            else {

                Toast.makeText(this,"Note not Saved",Toast.LENGTH_SHORT).show();

            }

            dbHelper.close();
            this.finish();


        }



    }
}