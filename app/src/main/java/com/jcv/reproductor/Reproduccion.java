package com.jcv.reproductor;
/*@Author: JCV **/
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;

public class Reproduccion extends AppCompatActivity {

    private Button btnMenu;
    private Button play;
    private Button siguiente;
    private Button anterior;
    private Button playfirebase;
    private TextView titulo;
    private TextView duracion;
    private ImageView album;
    private int posicionCancion = 0;

    private ArrayList<Musica> playList = new ArrayList<>();
    private MediaPlayer reproductor;
    private SeekBar seekBarSong;
    private long currentSongLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reprodcuccion);

        cargarMusica();
        inicializaReproductor(posicionCancion);
        inicializandoVistas();
        eventos();
    }

    public void inicializandoVistas(){
        btnMenu = findViewById(R.id.btnMenu);
        play = findViewById(R.id.btnPlay);
        siguiente = findViewById(R.id.btnSiguiente);
        anterior = findViewById(R.id.btnAnterior);
        playfirebase = findViewById(R.id.btnStreamingFB);
        titulo = findViewById(R.id.txtViewTitulo);
        duracion = findViewById(R.id.textViewDuracion);
        album = findViewById(R.id.imageView);
        seekBarSong = (SeekBar) findViewById(R.id.seekBarReproduccion);
    }
    public void  eventos(){
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Reproduccion.this,ListaMusica.class);
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
    public void reproducir(){currentSongLength = reproductor.getDuration();
        if (!reproductor.isPlaying()){
            currentSongLength = reproductor.getDuration();
            playList.get(posicionCancion).setEstado(1);
            play.setBackgroundResource(R.drawable.mediapause);
            album.setBackgroundResource(playList.get(posicionCancion).getFoto());
            titulo.setText(playList.get(posicionCancion).getCancion());
            reproductor.start();

            final Handler myHandler = new Handler();
            Reproduccion.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(reproductor != null){
                        int mCurrentPosition = reproductor.getCurrentPosition() / 2500;
                        seekBarSong.setProgress(mCurrentPosition);
                        final long minutes=(reproductor.getCurrentPosition()/1000)/60;
                        final int seconds= (int) ((reproductor.getCurrentPosition()/1000)%60);
                        duracion.setText(minutes+ ":"+seconds);
                    }
                    myHandler.postDelayed(this,1000);
                }
            });
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
