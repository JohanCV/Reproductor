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

    ArrayList<Musica> playList = new ArrayList<Musica>();
    MediaPlayer reproductor;
    int musica1 = R.raw.alwaysbemyunicorn;
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
        reproductor = MediaPlayer.create(getApplicationContext(), playList.get(posicionCancion).getMediaruta());
        eventos();
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
                //intent = serializableMetodo(intent);
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

    public void cargarMusica(){
        //playList.add(new Musica(MediaPlayer.create(this,R.raw.skyskating) ,R.drawable.dj,"asad","assa"));
        //playList.add(new Musica(MediaPlayer.create(this,R.raw.lamentgoldenlight),R.drawable.dj,"asad","assa"));
        this.playList.add(new Musica(musica1,R.drawable.dj,"RAHOT GAY","CAMA"));
        this.playList.add(new Musica(R.raw.darktranquility, R.drawable.dj,"PUTO","QUESO"));
        this.playList.add(new Musica(R.raw.dreamingblue, R.drawable.dj,"CARAJO","SEXO"));
        this.playList.add(new Musica(musica1,R.drawable.dj,"JAYSON","CAMA"));
        this.playList.add(new Musica(R.raw.darktranquility, R.drawable.dj,"PUTO","QUESO"));
        this.playList.add(new Musica(R.raw.dreamingblue, R.drawable.dj,"CARAJO","SEXO"));


        // playList.add(new Musica(R.drawable.dj,"asad","assa"));
        //playList.add(new Musica(R.drawable.dj,"asad","assa"));
        //return playList;
    }
  /*  public Intent serializableMetodo(Intent objetoPasar){
        cargarMusica();
        objetoPasar = new Intent(this,ListaMusica.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("key_Paso_Objetos",playList);
        objetoPasar.putExtra("BUNDLE",mBundle);
        //startActivity(objetoPasar);
        return objetoPasar;
    }*/

    public void reproducir(){

        if (reproductor.isPlaying()){
            pausa();
            play.setBackgroundResource(android.R.drawable.ic_media_play);
            album.setBackgroundResource(R.drawable.dj);
            titulo.setText("TU MUSICA");

        }else {
            reproductor.start();
            play.setBackgroundResource(android.R.drawable.ic_media_pause);
            album.setBackgroundResource(R.drawable.tenor);
            titulo.setText("Marica NENA");

        }
    }
    public void pausa(){
        reproductor.pause();
        play.setBackgroundResource(android.R.drawable.ic_media_pause);
    }
    public void siguiente(){

        if (posicionCancion == 0 || reproductor.isPlaying() ){
            pausa();
            posicionCancion = posicionCancion+1;

        }else {
            if (posicionCancion == 0 || ! reproductor.isPlaying()){
                pausa();
                posicionCancion = posicionCancion+1;

            }else{
                if (posicionCancion != 0 || ! reproductor.isPlaying()){
                    pausa();

                }
            }
        }

        reproductor.start();

    }
    public void anterior(){
        cargarMusica();
        Log.d("TamanoListadecancion",String.valueOf(playList.size()));
        if (posicionCancion == 0 ){
            Log.d("TamanoListadecancion",String.valueOf(playList.size()));
            pausa();
            posicionCancion = playList.size()-1;
            reproductor.seekTo(0);
            reproductor.start();
        }else{
            if (reproductor.isPlaying()){
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
