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
import android.widget.Toast;

import java.io.Serializable;
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

    ArrayList<Musica> playList = new ArrayList<>();
    MediaPlayer reproductor;

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
        inicializaReproductor(posicionCancion);
        eventos();
        album.setBackgroundResource(playList.get(posicionCancion).getFoto());
    }


    public void  eventos(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Reprodcuccion.this,ListaMusica.class);
                Bundle args = new Bundle();
                String  name = playList.toString();
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                args.putSerializable("ARRAYLIST",(Serializable)playList);
                intent.putExtra("BUNDLE",args);
                name = args.toString();
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
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

    public void inicializaReproductor(int posicion){
        reproductor = MediaPlayer.create(getApplicationContext(), playList.get(posicion).getMediaruta());
    }
    public void cargarMusica(){
        this.playList.add(new Musica(R.raw.darktranquility, R.drawable.fondo,"PUTO","QUESO"));
        this.playList.add(new Musica(R.raw.dreamingblue, R.drawable.fondo2,"CARAJO","SAD"));
        this.playList.add(new Musica(R.raw.alwaysbemyunicorn, R.drawable.fondo3,"PUTO","QUESO"));
        this.playList.add(new Musica(R.raw.skyskating, R.drawable.fondo4,"CARAJO","SEXO"));
    }

    public void reproducir(){
        //playList = (ArrayList<Musica>) getIntent().getSerializableExtra("objetoCancion");
        if (reproductor.isPlaying()){
            pausa();
            play.setBackgroundResource(android.R.drawable.ic_media_play);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            titulo.setText(playList.get(posicionCancion).getCancion());

        }else {

            reproductor.start();
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            titulo.setText(playList.get(posicionCancion).getCancion());

        }
    }
    public void pausa(){
        reproductor.pause();
        play.setBackgroundResource(android.R.drawable.ic_media_pause);
    }
    public void siguiente(){
        if (reproductor.isPlaying()){
            play.setBackgroundResource(android.R.drawable.ic_media_play);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            pausa();
            if (posicionCancion == 0  ){
                posicionCancion = posicionCancion+1;
                inicializaReproductor(posicionCancion);
            }else {
                if (posicionCancion == playList.size()){
                    posicionCancion = 0;
                    inicializaReproductor(posicionCancion);
                }
            }
        }else {
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
            posicionCancion = posicionCancion+1;
            inicializaReproductor(posicionCancion);
        }
        reproductor.start();

    }
    public void anterior(){
        if (posicionCancion == 0 ){
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            Log.d("TamanoListadecancion",String.valueOf(playList.size()));
            pausa();
            posicionCancion = playList.size()-1;
            reproductor.seekTo(0);
            reproductor.start();
        }else{
            if (reproductor.isPlaying()){
                album.setBackgroundResource(playList.get(posicionCancion).getFoto());
                pausa();
                reproductor.seekTo(0);
                play.setBackgroundResource(android.R.drawable.ic_media_pause);
                posicionCancion = posicionCancion-1;
            }else {
                posicionCancion = 0;
                reproductor.seekTo(0);
            }
        }

        reproductor.start();

    }

}
