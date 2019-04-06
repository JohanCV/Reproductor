package com.jcv.reproductor;

import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.TextView;

public class Musica {
    MediaPlayer media;
    TextView titulo;
    ImageView album;

    public Musica(MediaPlayer media, TextView titulo, ImageView album) {
        this.media = media;
        this.titulo = titulo;
        this.album = album;
    }

    public MediaPlayer getMedia() {
        return media;
    }

    public void setMedia(MediaPlayer media) {
        this.media = media;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public ImageView getAlbum() {
        return album;
    }

    public void setAlbum(ImageView album) {
        this.album = album;
    }
}
