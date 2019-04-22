package com.jcv.reproductor;

/*@Author: JCV

* */
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static com.jcv.reproductor.R.layout.activity_lista_musica;

public class ListaMusica extends AppCompatActivity {
    private ListView myListMusica;
    private Adaptador myAdaptador;
    private MediaPlayer play;
    private Button btnplay;
    private int mediaFileLength;
    private int realTimeLength;
    Intent intentRecibido;
    Bundle args;
    ArrayList<Musica> object;
    CountDownTimer delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_lista_musica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        intentRecibido = getIntent();
        args = intentRecibido.getBundleExtra("BUNDLE");
        object = (ArrayList<Musica>) args.getSerializable("ARRAYLIST");

        myListMusica = (ListView) findViewById(R.id.listaMusic);
        myAdaptador = new Adaptador(this,object);
        myListMusica.setAdapter(myAdaptador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mi Lista", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent pasandoMyPlayList = new Intent(getApplicationContext(), MyPlayList.class);
                Bundle args = new Bundle();
                ArrayList<Musica> mySongs = new ArrayList<>();
                for (int i = 0; i <object.size() ; i++) {
                    if (object.get(i).isFavoritos()){
                        mySongs.add(object.get(i));
                    }
                }
                args.putSerializable("ArrayListLike",(Serializable)mySongs);
                pasandoMyPlayList.putExtra("BundleLike",args);
                startActivity(pasandoMyPlayList);
            }
        });
        eventos();
        pasandoReproduccion();
    }

    protected void eventos(){
        myListMusica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionCancion, long id) {
                Intent intent = new Intent(ListaMusica.this,Reprodcuccion.class);
                Bundle argumentos = new Bundle();
                argumentos.putInt("Argumentos",positionCancion);
                intent.putExtra("Bundle",argumentos);
                String name = argumentos.toString();
                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }

        });

    }



    protected void pasandoReproduccion(){
        for (int i = 0; i < object.size(); i++) {

/*
            if (object.get(i).getEstado() == 1){
                play = MediaPlayer.create(getApplicationContext(),object.get(i).getMediaruta());
                int estado = object.get(i).getEstado();
                String nombre = object.get(i).getCancion();
                Toast.makeText(this,String.valueOf(estado)+" "+ nombre,Toast.LENGTH_LONG).show();
                play.pause();
                delay  = new CountDownTimer(3000,10000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                       play.start();
                    }
                }.start();

                break;
            }*/
            if (object != null){
                play.stop();
                play.release();
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"Back",Toast.LENGTH_LONG).show();
        play.pause();
    }
}
