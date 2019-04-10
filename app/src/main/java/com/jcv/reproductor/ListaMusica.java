package com.jcv.reproductor;

/*@Author: Rahit GAY
* */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.jcv.reproductor.R.layout.activity_lista_musica;

public class ListaMusica extends AppCompatActivity {
    private ListView myListMusica;
    private Adaptador myAdaptador;
    Reprodcuccion reprodcuccion;

    Musica music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_lista_musica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Musica> object = (ArrayList<Musica>) args.getSerializable("ARRAYLIST");
        String  name = object.toString();
        //Log.d("TamaCargaMusica", String.valueOf(myList.get(0).getCancion()));
        //Log.d("TamaCargaMusica", String.valueOf(myList.get(1).getCancion()));
        //String  name = myList.toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        myListMusica = (ListView) findViewById(R.id.listaMusic);
        myAdaptador = new Adaptador(this,object);
        myListMusica.setAdapter(myAdaptador);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reproduciendo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /*public ArrayList<Musica> cargarMusica(){
        ArrayList<Musica> playList = new ArrayList<Musica>();
        playList.add(new Musica(R.drawable.tenor ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));
        playList.add(new Musica(R.drawable.dj ,"alema", "asdas"));

        return playList;
    }*/

}
