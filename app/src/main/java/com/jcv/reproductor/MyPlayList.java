package com.jcv.reproductor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyPlayList extends AppCompatActivity {

    private ListView myListMusica;
    private Adaptador myAdaptador;
    Button btnLike;
    Intent intent;
    Bundle args;
    ArrayList<Musica> mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_play_list);
        btnLike = findViewById(R.id.itemButtonMeGusta);
        btnLike.setVisibility(View.INVISIBLE);
        intent = getIntent();
        args = intent.getBundleExtra("BundleLike");
        mySong = (ArrayList<Musica>) args.getSerializable("ArrayListLike");

        myListMusica = (ListView) findViewById(R.id.mylistMusic);
        myAdaptador = new Adaptador(this,mySong);
        myListMusica.setAdapter(myAdaptador);

    }
}
