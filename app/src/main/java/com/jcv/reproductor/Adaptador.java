package com.jcv.reproductor;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    private ArrayList<Musica> myListMusic;

    public Adaptador(Context contexto, ArrayList<Musica> myListMusic) {
        this.contexto = contexto;
        this.myListMusic = myListMusic;
    }

    @Override
    public int getCount() {
        return myListMusic.size();
    }

    @Override
    public Object getItem(int posicion) {
        return myListMusic.get(posicion);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        final Musica itemMusica = (Musica) getItem(posicion);
        final MediaPlayer play = MediaPlayer.create(contexto,itemMusica.getMediaruta());
        final int mediaFileLength;
        final int realTimeLength;


        view = LayoutInflater.from(contexto).inflate(R.layout.item_musica_seleccionada,null);
        final ImageView imgAlbum = (ImageView) view.findViewById(R.id.itemImgViewAlbum);
        TextView txtCancion = (TextView) view.findViewById(R.id.itemTextViewCancion);
        TextView txtGrupo = (TextView) view.findViewById(R.id.itemTextViewGrupo);
        final TextView txtDuracion = (TextView) view.findViewById(R.id.itemTextViewDuracion);
        final Button btnLike = (Button) view.findViewById(R.id.itemButtonMeGusta);
        final Button btnPlay =  (Button) view.findViewById(R.id.itemButtonPlay);

        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setMax(play.getDuration());

        imgAlbum.setImageResource(itemMusica.getFoto());
        txtCancion.setText(itemMusica.getCancion());
        txtGrupo.setText(itemMusica.getGrupo());
        mediaFileLength = play.getDuration();
        realTimeLength = mediaFileLength;

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemMusica.isFavoritos()){
                    btnLike.setBackgroundResource(R.drawable.megusta);
                    itemMusica.setFavoritos(false);
                    Toast.makeText(contexto, "Desagregaste la cancion Favoritos ", Toast.LENGTH_SHORT).show();
                }else{
                    btnLike.setBackgroundResource(R.drawable.meencanta);
                    itemMusica.setFavoritos(true);
                    Toast.makeText(contexto, "Agregaste la cancion a Favoritos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play.isPlaying()){
                    play.pause();
                    btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                    Toast.makeText(contexto, "Pausado "+itemMusica.getCancion(), Toast.LENGTH_SHORT).show();
                }else {
                    play.start();

                    btnPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                    Toast.makeText(contexto, "Reproduciendo "+itemMusica.getCancion(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
}
