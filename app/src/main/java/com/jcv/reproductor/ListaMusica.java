package com.jcv.reproductor;

/*@Author: JCV

* */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Delayed;

import static com.jcv.reproductor.R.layout.activity_lista_musica;

public class ListaMusica extends AppCompatActivity {
    private ListView myListMusica;
    private Adaptador myAdaptador;
    private MediaPlayer play;
    Intent intentRecibido;
    Bundle args;
    ArrayList<Musica> object;
    CountDownTimer delay;
    int contador = 0;

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
        Toast.makeText(ListaMusica.this, "creando", Toast.LENGTH_SHORT).show();


        myListMusica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaMusica.this, "hola item", Toast.LENGTH_SHORT).show();
            }
        });
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mi Lista", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent pasandoMyPlayList = new Intent(getApplicationContext(), MyPlayList.class);
                startActivity(pasandoMyPlayList);
            }
        });*/
        Toast.makeText(ListaMusica.this, "float", Toast.LENGTH_SHORT).show();
        eventos();
        pasandoReproduccion();
    }

    protected void eventos(){

    }

    protected void pasandoReproduccion(){
        for (int i = 0; i < object.size(); i++) {


            if (object.get(i).getEstado() == 1){
                play = MediaPlayer.create(getApplicationContext(),object.get(i).getMediaruta());
                int estado = object.get(i).getEstado();
                String nombre = object.get(i).getCancion();
                Toast.makeText(this,String.valueOf(estado)+" "+ nombre,Toast.LENGTH_LONG).show();
                //play.pause();
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
