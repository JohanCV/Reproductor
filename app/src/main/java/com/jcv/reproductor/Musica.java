package com.jcv.reproductor;

import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.TextView;

public class Musica {
    MediaPlayer media;
    String tituloCancion;
    String grupoNombre;
    int albumFoto;

    public Musica(MediaPlayer media, String tituloCancion, String grupoNombre, int albumFoto) {
        this.media = media;
        this.tituloCancion = tituloCancion;
        this.grupoNombre = grupoNombre;
        this.albumFoto = albumFoto;
    }

    public MediaPlayer getMedia() {
        return media;
    }

    public void setMedia(MediaPlayer media) {
        this.media = media;
    }

    public String getTituloCancion() {
        return tituloCancion;
    }

    public void setTituloCancion(String tituloCancion) {
        this.tituloCancion = tituloCancion;
    }

    public String getGrupoNombre() {
        return grupoNombre;
    }

    public void setGrupoNombre(String grupoNombre) {
        this.grupoNombre = grupoNombre;
    }

    public int getAlbumFoto() {
        return albumFoto;
    }

    public void setAlbumFoto(int albumFoto) {
        this.albumFoto = albumFoto;
    }
}
