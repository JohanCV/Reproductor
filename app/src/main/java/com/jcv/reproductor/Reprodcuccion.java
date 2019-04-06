package com.jcv.reproductor;

import android.content.Intent;
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

    Button btnMenu;
    Button play;
    Button stop;
    Button siguiente;
    Button anterior;
    TextView titulo;
    ImageView album;

    List<Musica> playList = new ArrayList<Musica>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprodcuccion);

        btnMenu = findViewById(R.id.btnMenu);
        play = findViewById(R.id.btnPlay);
        stop = findViewById(R.id.btnStop);
        siguiente = findViewById(R.id.btnSiguiente);
        anterior = findViewById(R.id.btnAnterior);
        titulo = findViewById(R.id.txtViewTitulo);
        album = findViewById(R.id.imageView);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListaMusica.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducir();
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente();
            }
        });
    }

    public void cargarMusica(){
        playList.add(new Musica(MediaPlayer.create(this,R.raw.dreamingblue) ,titulo, album));
        playList.add(new Musica(MediaPlayer.create(this,R.raw.skyskating) ,titulo, album));
        playList.add(new Musica(MediaPlayer.create(this,R.raw.lamentgoldenlight) ,titulo, album));
        playList.add(new Musica(MediaPlayer.create(this,R.raw.darktranquility) ,titulo, album));
        playList.add(new Musica(MediaPlayer.create(this,R.raw.alwaysbemyunicorn) ,titulo,album));
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
    public void siguiente(){
        playList.get(1).getMedia().start();

    }

}
