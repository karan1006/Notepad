package com.example.dell.notepad;


public class NoteClass {

    private String title;
    private String note;

    public NoteClass(){}

    public NoteClass(String title, String note)
    {
        this.note = note;
        this.title = title;
    }

    public String getTitle()
    { return title;}

    public String getNote()
    { return note;}

}

