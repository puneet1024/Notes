package com.example.notes;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.provider.SyncStateContract;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity (tableName = "note_table")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long note_id;


    private String description;

    private String title;

    private int priority;

    public Note( String title, String description,int priority){
        this.description = description;
        this.title = title;
        this.priority = priority;
    }

    @Ignore
    public Note(){}

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        if(note_id != note.note_id)
            return false;
        return title != null ? title.equals(note.title) : note.title == null;
    }

    @Override
    public int hashCode() {
        int result = (int) note_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note_id=" + note_id +
                ", content='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
