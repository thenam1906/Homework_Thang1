package com.example.linhphan.hw_notepad;

/**
 * Created by Linh Phan on 10/8/2017.
 */

public class NoteModel {

    String title;
    String description;

    public NoteModel(String title, String description) {

        this.title = title;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
