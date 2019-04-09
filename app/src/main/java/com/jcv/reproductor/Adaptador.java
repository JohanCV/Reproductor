package com.jcv.reproductor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        Musica itemMusica = (Musica) getItem(posicion);

        view = LayoutInflater.from(contexto).inflate(R.layout.item_musica_seleccionada,null);
        ImageView imgAlbum = (ImageView) view.findViewById(R.id.itemImgViewAlbum);
        TextView txtCancion = (TextView) view.findViewById(R.id.itemTextViewCancion);
        TextView txtGrupo = (TextView) view.findViewById(R.id.itemTextViewGrupo);
        Button btnLike = (Button) view.findViewById(R.id.itemButtonMeGusta);

        imgAlbum.setImageResource(itemMusica.getFoto());
        txtCancion.setText(itemMusica.getCancion());
        txtGrupo.setText(itemMusica.getGrupo());

        return view;
    }
}
