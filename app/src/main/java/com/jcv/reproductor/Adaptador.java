package com.jcv.reproductor;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

        view = LayoutInflater.from(contexto).inflate(R.layout.item_musica_seleccionada,null);
        ImageView imgAlbum = (ImageView) view.findViewById(R.id.itemImgViewAlbum);
        TextView txtCancion = (TextView) view.findViewById(R.id.itemTextViewCancion);
        TextView txtGrupo = (TextView) view.findViewById(R.id.itemTextViewGrupo);
        Button btnLike = (Button) view.findViewById(R.id.itemButtonMeGusta);
        LinearLayout linearLayout = view.findViewById(R.id.linearLayoutPlay);
        imgAlbum.setImageResource(itemMusica.getFoto());
        txtCancion.setText(itemMusica.getCancion());
        txtGrupo.setText(itemMusica.getGrupo());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto, "TEXTOOO", Toast.LENGTH_SHORT).show();
                MediaPlayer play = MediaPlayer.create(contexto,itemMusica.getMediaruta());

                play.start();
            }
        });
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto, "asadasd", Toast.LENGTH_SHORT).show();
                MediaPlayer play = MediaPlayer.create(contexto,itemMusica.getMediaruta());
                play.start();
            }
        });
        return view;
    }
}
