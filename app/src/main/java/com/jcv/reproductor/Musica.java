package com.jcv.reproductor;

import android.media.MediaPlayer;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class Musica implements Serializable {
    private int mediaruta;
    private int foto;
    private String cancion;
    private String grupo;
    private int estado;
    private boolean favoritos;

    public Musica(int mediaruta, int foto, String cancion, String grupo, int estado,boolean favoritos) {
        this.mediaruta = mediaruta;
        this.foto = foto;
        this.cancion = cancion;
        this.grupo = grupo;
        this.estado = estado;
        this.favoritos = favoritos;
    }

    public int getMediaruta() {
        return mediaruta;
    }

    public void setMediaruta(int mediaruta) {
        this.mediaruta = mediaruta;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isFavoritos() {
        return favoritos;
    }

    public void setFavoritos(boolean favoritos) {
        this.favoritos = favoritos;
    }
}
