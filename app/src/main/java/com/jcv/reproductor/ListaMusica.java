package com.jcv.reproductor;

/*@Author: Rahit GAY
* */
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        Intent intentRecibido = getIntent();
        Bundle args = intentRecibido.getBundleExtra("BUNDLE");
        final ArrayList<Musica> object = (ArrayList<Musica>) args.getSerializable("ARRAYLIST");

        myListMusica = (ListView) findViewById(R.id.listaMusic);
        myAdaptador = new Adaptador(this,object);
        myListMusica.setAdapter(myAdaptador);

        myListMusica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicionCancion, long l) {
                MediaPlayer play = MediaPlayer.create(getApplicationContext(),object.get(0).getMediaruta());
                Intent intentPasandoCancion = new Intent(getApplicationContext(),Reprodcuccion.class);
                intentPasandoCancion.putExtra("objetoCancion",object.get(posicionCancion));
                Toast.makeText(getApplicationContext(), "Reproduciendo "+object.get(0).getCancion(), Toast.LENGTH_SHORT).show();
                startActivity(intentPasandoCancion);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mi Lista", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent pasandoMyPlayList = new Intent(getApplicationContext(), MyPlayList.class);
                startActivity(pasandoMyPlayList);
            }
        });
    }
}
