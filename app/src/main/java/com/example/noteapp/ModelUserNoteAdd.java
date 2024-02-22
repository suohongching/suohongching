package com.example.noteapp;

public class ModelUserNoteAdd {

    String id, note, uid;
    long timestamp;

    public ModelUserNoteAdd(){

    }

    public ModelUserNoteAdd(String id, String note, String uid, long timestamp) {
        this.id = id;
        this.note = note;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
