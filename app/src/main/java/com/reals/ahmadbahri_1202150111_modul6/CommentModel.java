package com.reals.ahmadbahri_1202150111_modul6;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CommentModel {

    private String id, user, isi;

    public CommentModel() {
    }

    public CommentModel(String user, String isi) {
        this.user = user;
        this.isi = isi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
