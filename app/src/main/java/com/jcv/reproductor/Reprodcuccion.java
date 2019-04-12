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

                if (reproductor.isPlaying()){
                    reproductor.pause();
                    args.putSerializable("ARRAYLIST",(Serializable)playList);
                    intent.putExtra("BUNDLE",args);
                    name = args.toString();
                    Toast.makeText(getApplicationContext(),"play "+ name, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    reproductor.pause();
                    args.putSerializable("ARRAYLIST",(Serializable)playList);
                    intent.putExtra("BUNDLE",args);
                    name = args.toString();
                    Toast.makeText(getApplicationContext(),"pause "+ name, Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }



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
        this.playList.add(new Musica(R.raw.alanwalker135, R.drawable.fondo,"135","Alan Walker",0));
        this.playList.add(new Musica(R.raw.alanwalkeralone, R.drawable.fondo2,"Alone","Alan Walker",0));
        this.playList.add(new Musica(R.raw.alwaysbemyunicorn, R.drawable.fondo3,"Always","Youtube",0));
        this.playList.add(new Musica(R.raw.skyskating, R.drawable.fondo4,"Sky","Youtube",0));
    }

    public void pausa(){
        reproductor.pause();
        play.setBackgroundResource(android.R.drawable.ic_media_play);
    }
    public void reproducir(){
        if (reproductor.isPlaying()){
            pausa();
        }else {
            reproductor.start();
            playList.get(posicionCancion).setEstado(1);
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            titulo.setText(playList.get(posicionCancion).getCancion());

        }
    }
    public void siguiente(){

        if (reproductor.isPlaying()){
            pausa();
            posicionCancion = posicionCancion+1;
            inicializaReproductor(posicionCancion);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            titulo.setText(playList.get(posicionCancion).getCancion());
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
            Toast.makeText(getApplicationContext(), playList.size() +" "+ posicionCancion,
                    Toast.LENGTH_LONG).show();
            if (playList.size() == posicionCancion +1){
                posicionCancion = -1;
            }
        }else {
            posicionCancion = posicionCancion+1;
            inicializaReproductor(posicionCancion);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
        reproductor.start();

    }
    public void anterior(){
        if (reproductor.isPlaying()){
            pausa();
            if (posicionCancion ==0){
                posicionCancion = playList.size()-1;
                inicializaReproductor(posicionCancion);

            }else {
                posicionCancion = posicionCancion-1;
                inicializaReproductor(posicionCancion);
            }
        }else{
            posicionCancion = playList.size()-1;
            inicializaReproductor(posicionCancion);
        }
        album.setBackgroundResource(playList.get(posicionCancion).getFoto());
        titulo.setText(playList.get(posicionCancion).getCancion());
        play.setBackgroundResource(android.R.drawable.ic_media_pause);
        reproductor.start();

    }

}
