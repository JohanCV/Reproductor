package com.jcv.reproductor;

import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.TextView;

public class Musica {
    private MediaPlayer media;
    private int foto;
    private String cancion;
    private String grupo;

    public Musica(MediaPlayer media, int foto, String cancion, String grupo) {
        this.media = media;
        this.foto = foto;
        this.cancion = cancion;
        this.grupo = grupo;
    }

    public Musica(int foto, String cancion, String grupo) {
        this.foto = foto;
        this.cancion = cancion;
        this.grupo = grupo;
    }

    public MediaPlayer getMedia() {
        return media;
    }

    public void setMedia(MediaPlayer media) {
        this.media = media;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
