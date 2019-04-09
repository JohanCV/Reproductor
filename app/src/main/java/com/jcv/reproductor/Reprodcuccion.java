package com.jcv.reproductor;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Reprodcuccion extends AppCompatActivity {

    private Button btnMenu;
    private Button play;
    private Button siguiente;
    private Button anterior;
    private TextView titulo;
    private ImageView album;
    private int posicionCancion = 0;

    ArrayList<Musica> playList = new ArrayList<Musica>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprodcuccion);

        btnMenu = findViewById(R.id.btnMenu);
        play = findViewById(R.id.btnPlay);
        siguiente = findViewById(R.id.btnSiguiente);
        anterior = findViewById(R.id.btnAnterior);
        titulo = findViewById(R.id.txtViewTitulo);
        album = findViewById(R.id.imageView);
        cargarMusica();
        eventos();
    }


    public void  eventos(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarMusica();
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
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anterior();
            }
        });
    }

    public ArrayList<Musica> cargarMusica(){
        //playList.add(new Musica(MediaPlayer.create(this,R.raw.skyskating) ,R.drawable.dj,"asad","assa"));
        //playList.add(new Musica(MediaPlayer.create(this,R.raw.lamentgoldenlight),R.drawable.dj,"asad","assa"));
        playList.add(new Musica(MediaPlayer.create(this,R.raw.darktranquility) ,R.drawable.dj,"asad","assa"));
        playList.add(new Musica(MediaPlayer.create(this,R.raw.alwaysbemyunicorn), R.drawable.dj,"asad","assa"));
        playList.add(new Musica(R.drawable.dj,"asad","assa"));
        playList.add(new Musica(R.drawable.dj,"asad","assa"));
        return playList;
    }
    public void reproducir(){
        playList = cargarMusica();
        if (playList.get(posicionCancion).getMedia().isPlaying()){
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
        playList.get(posicionCancion).getMedia().pause();
        play.setBackgroundResource(android.R.drawable.ic_media_pause);
    }
    public void siguiente(){

        if (posicionCancion == 0 || playList.get(posicionCancion).getMedia().isPlaying() ){
            pausa();
            posicionCancion = posicionCancion+1;

        }else {
            if (posicionCancion == 0 || ! playList.get(posicionCancion).getMedia().isPlaying()){
                pausa();
                posicionCancion = posicionCancion+1;

            }else{
                if (posicionCancion != 0 || ! playList.get(posicionCancion).getMedia().isPlaying()){
                    pausa();

                }
            }
        }

        playList.get(posicionCancion).getMedia().start();

    }
    public void anterior(){
        playList = cargarMusica();
        Log.d("TamanoListadecancion",String.valueOf(playList.size()));
        if (posicionCancion == 0 ){
            Log.d("TamanoListadecancion",String.valueOf(playList.size()));
            pausa();
            posicionCancion = playList.size()-1;
            playList.get(posicionCancion).getMedia().seekTo(0);
            playList.get(posicionCancion).getMedia().start();
        }else{
            if (playList.get(posicionCancion).getMedia().isPlaying()){
                pausa();
                playList.get(posicionCancion).getMedia().seekTo(0);
                play.setBackgroundResource(android.R.drawable.ic_media_pause);
                posicionCancion = posicionCancion-1;
            }else {
                posicionCancion = 0;
                playList.get(posicionCancion).getMedia().seekTo(0);
            }
        }

        playList.get(posicionCancion).getMedia().start();

    }

}
