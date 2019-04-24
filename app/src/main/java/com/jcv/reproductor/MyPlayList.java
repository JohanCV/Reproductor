package com.jcv.reproductor;
/*@Author: JCV **/
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class MyPlayList extends AppCompatActivity {

    private ListView myListMusica;
    private Adaptador myAdaptador;
    private Intent intent;
    private Bundle args;
    private ArrayList<Musica> mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_play_list);

        inicializadorMyPlayList();
    }

    protected void inicializadorMyPlayList(){
        intent = getIntent();
        args = intent.getBundleExtra("BundleLike");
        mySong = (ArrayList<Musica>) args.getSerializable("ArrayListLike");

        myListMusica = (ListView) findViewById(R.id.mylistMusic);
        myAdaptador = new Adaptador(this,mySong,true,"");
        myListMusica.setAdapter(myAdaptador);
    }
}
