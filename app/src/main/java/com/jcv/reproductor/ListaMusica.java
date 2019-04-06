package com.jcv.reproductor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.jcv.reproductor.R.layout.activity_lista_musica;

public class ListaMusica extends AppCompatActivity {
    ListView simpleList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_lista_musica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpleList = (ListView)findViewById(R.id.listaMusic);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_lista_musica, countryList);
        simpleList.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reproduciendo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
