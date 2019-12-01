package com.example.volleyparsepicasso;

public class Imagen {
    int id;
    String thumb_url;

    public Imagen(int id, String thumb_url) {
        this.id = id;
        this.thumb_url = thumb_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }
}
