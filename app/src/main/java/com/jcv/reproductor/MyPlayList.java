package com.jcv.reproductor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyPlayList extends AppCompatActivity {

    private ListView myListMusica;
    private Adaptador myAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_play_list);

        myListMusica = (ListView) findViewById(R.id.mylistMusic);
        myAdaptador = new Adaptador(this,cargarMusica());
        myListMusica.setAdapter(myAdaptador);
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
