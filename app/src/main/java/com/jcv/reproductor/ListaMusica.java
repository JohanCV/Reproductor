package com.jcv.reproductor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.jcv.reproductor.R.layout.activity_lista_musica;

public class ListaMusica extends AppCompatActivity {
    private ListView myListMusica;
    private Adaptador myAdaptador;
    Reprodcuccion reprodcuccion;
    ArrayList<Musica> cargarMusic = reprodcuccion.cargarMusica();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_lista_musica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("TamaCargaMusica", String.valueOf(reprodcuccion.cargarMusica().size()));
        myListMusica = (ListView) findViewById(R.id.listaMusic);
        myAdaptador = new Adaptador(this,cargarMusic);
        myListMusica.setAdapter(myAdaptador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reproduciendo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public ArrayList<Musica> cargarMusica(){
        ArrayList<Musica> playList = new ArrayList<Musica>();
       /* playList.add(new Musica(R.drawable.tenor ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
*/
        return playList;
    }

}
