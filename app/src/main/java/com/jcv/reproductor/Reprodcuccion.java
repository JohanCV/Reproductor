package com.jcv.reproductor;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
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
    private Button playfirebase;
    private TextView titulo;
    private TextView duracion;
    private ImageView album;
    private int posicionCancion = 0;

    ArrayList<Musica> playList = new ArrayList<>();
    MediaPlayer reproductor;
    SeekBar seekBarSong;
    Thread updateSeekBarSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprodcuccion);


        cargarMusica();
        inicializaReproductor(posicionCancion);


        //seekBarProgress();
        //album.setBackgroundResource(playList.get(posicionCancion).getFoto());
        //album.setBackgroundResource(R.drawable.gradient_background);
       /* updateSeekBarSong = new Thread(){
            @Override
            public void run() {
                int totalDuration = reproductor.getDuration();
                int currentPosicion = 0;

                while (currentPosicion < totalDuration){
                    try {
                        sleep(500);
                        currentPosicion = reproductor.getCurrentPosition();
                        seekBarSong.setProgress(currentPosicion);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        seekBarSong.setMax(reproductor.getDuration());
        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                reproductor.seekTo(seekBar.getProgress());
            }
        });*/
        inicializandoBotones();
        eventos();
    }

    public void inicializandoBotones(){
        btnMenu = findViewById(R.id.btnMenu);
        play = findViewById(R.id.btnPlay);
        siguiente = findViewById(R.id.btnSiguiente);
        anterior = findViewById(R.id.btnAnterior);
        playfirebase = findViewById(R.id.btnStreamingFB);
        titulo = findViewById(R.id.txtViewTitulo);
        duracion = findViewById(R.id.textViewDuracion);
        album = findViewById(R.id.imageView);
    }
    public void  eventos(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Reprodcuccion.this,ListaMusica.class);
                Bundle args = new Bundle();
                //String  name = playList.toString();
                //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                if (reproductor.isPlaying()){
                    reproductor.pause();
                    args.putSerializable("ARRAYLIST",(Serializable)playList);
                    intent.putExtra("BUNDLE",args);
                    //name = args.toString();
                    //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    //reproductor.pause();
                    args.putSerializable("ARRAYLIST",(Serializable)playList);
                    intent.putExtra("BUNDLE",args);
                    //name = args.toString();
                    //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
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

        playfirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFb = new Intent(getApplicationContext(),StreamingFb.class);
                startActivity(intentFb);
            }
        });
    }

    public void inicializaReproductor(int posicion){
        reproductor = MediaPlayer.create(getApplicationContext(), playList.get(posicion).getMediaruta());
    }
    public void cargarMusica(){
        this.playList.add(new Musica(R.raw.alanwalker135, R.drawable.fondo,"0 135","QUESO",0,false));
        this.playList.add(new Musica(R.raw.alanwalkeralone, R.drawable.fondo2,"1 Alone","SAD",0,false));
        this.playList.add(new Musica(R.raw.alwaysbemyunicorn, R.drawable.fondo3,"2 PUTO","QUESO",0,false));
        this.playList.add(new Musica(R.raw.skyskating, R.drawable.fondo4,"3 CARAJO","SEXO",0,false));
        this.playList.add(new Musica(R.raw.alanwalker135, R.drawable.fondo,"4 135","QUESO",0,false));
        this.playList.add(new Musica(R.raw.alanwalkeralone, R.drawable.fondo2,"5 Alone","SAD",0,false));
        this.playList.add(new Musica(R.raw.alwaysbemyunicorn, R.drawable.fondo3,"6 PUTO","QUESO",0,false));
        this.playList.add(new Musica(R.raw.skyskating, R.drawable.fondo4,"7 CARAJO","SEXO",0,false));

    }
    public String toTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString;
        // Convert total duration into time
        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        // Add hours if there
        if(hours > 0){
            finalTimerString = hours + ":";
        }
        // Prepending 0 to seconds if it is one digit
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}
        finalTimerString = finalTimerString + minutes + ":" + secondsString;
        // return timer string
        return finalTimerString;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        duracion.setText(String.format("%s", toTimer(progress)));
    }
    /*
    public void seekBarProgress(){
        updateSeekBarSong = new Thread(){
            @Override
            public void run() {
                int totalDuration = reproductor.getDuration();
                int currentPosicion = 0;

                while (currentPosicion < totalDuration){
                    try {
                        sleep(500);
                        currentPosicion = reproductor.getCurrentPosition();
                        seekBarSong.setProgress(currentPosicion);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };


    }*/

    public void reproducir(){
        if (!reproductor.isPlaying()){
            playList.get(posicionCancion).setEstado(1);
            play.setBackgroundResource(R.drawable.mediapause);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            titulo.setText(playList.get(posicionCancion).getCancion());
            reproductor.start();
        }else {
            pausa();
        }
    }
    public void pausa(){
        if (reproductor.isPlaying()){
            reproductor.pause();
            play.setBackgroundResource(R.drawable.play);
        }
    }
    public void siguiente(){
       /* if(!reproductor.isPlaying()){
            posicionCancion = ((posicionCancion)>= playList.size()-1)?(posicionCancion = 0):(posicionCancion +1);
            inicializaReproductor(posicionCancion);
        }else{*/
            pausa();
            posicionCancion = ((posicionCancion)>= playList.size()-1)?(posicionCancion = 0):(posicionCancion +1);
            inicializaReproductor(posicionCancion);
        //}

        play.setBackgroundResource(android.R.drawable.ic_media_pause);
        album.setBackgroundResource(playList.get(posicionCancion).getFoto());
        titulo.setText(playList.get(posicionCancion).getCancion());

        reproductor.start();Toast.makeText( getApplicationContext(), "posicion Siguiente "+ posicionCancion , Toast.LENGTH_SHORT).show();
    }
    public void anterior(){
       /* if(!reproductor.isPlaying()){
            posicionCancion = ((posicionCancion )<= 0)?(posicionCancion = playList.size()-1):(posicionCancion -1);
            inicializaReproductor(posicionCancion);
        }else{*/
            pausa();
            posicionCancion = ((posicionCancion )<= 0)?(posicionCancion = playList.size()-1):(posicionCancion -1);
            inicializaReproductor(posicionCancion);
        //}

        play.setBackgroundResource(android.R.drawable.ic_media_pause);
        album.setBackgroundResource(playList.get(posicionCancion).getFoto());
        titulo.setText(playList.get(posicionCancion).getCancion());

        reproductor.start();Toast.makeText( getApplicationContext(), "posicion Anterior"+ posicionCancion , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        reproductor.pause();
    }
}
