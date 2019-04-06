package com.jcv.reproductor;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Reprodcuccion extends AppCompatActivity {

    Button play;
    Button stop;
    Button siguiente;
    Button anterior;
    TextView titulo;
    ImageView album;

    List<Musica> playList = new ArrayList<Musica>();
    List<MediaPlayer> media = new ArrayList<MediaPlayer>();
    MediaPlayer mediaPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprodcuccion);

        play = findViewById(R.id.btnPlay);
        stop = findViewById(R.id.btnStop);
        siguiente = findViewById(R.id.btnSiguiente);
        anterior = findViewById(R.id.btnAnterior);
        titulo = findViewById(R.id.txtViewTitulo);
        album = findViewById(R.id.imageView);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducir();
            }
        });
    }
    public void cargarMusica(){
        mediaPlay = MediaPlayer.create(this,R.raw.dreamingblue);
        playList.add(new Musica(mediaPlay ,titulo, album));
    }
    public void reproducir(){
        cargarMusica();
        if (playList.get(0).getMedia().isPlaying()){
            pausa();
            play.setBackgroundResource(android.R.drawable.ic_media_play);
            album.setBackgroundResource(R.drawable.dj);
            titulo.setText("TU MUSICA");

        }else {
            playList.get(0).getMedia().start();
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
            album.setBackgroundResource(R.drawable.tenor);
            titulo.setText("Marica NENA");

        }
    }
    public void pausa(){
        playList.get(0).getMedia().pause();
    }
}
