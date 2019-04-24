package com.jcv.reproductor;
/*@Author: JCV **/
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    private ArrayList<Musica> myListMusic;
    private boolean esUsable;
    private String urlSong;

    public Adaptador(Context contexto, ArrayList<Musica> myListMusic,boolean esUsable,String urlSong) {
        this.contexto = contexto;
        this.myListMusic = myListMusic;
        this.esUsable = esUsable;
        this.urlSong = urlSong;
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

        view = LayoutInflater.from(contexto).inflate(R.layout.item_musica_seleccionada,null);
        final ImageView imgAlbum = (ImageView) view.findViewById(R.id.itemImgViewAlbum);
        TextView txtCancion = (TextView) view.findViewById(R.id.itemTextViewCancion);
        TextView txtGrupo = (TextView) view.findViewById(R.id.itemTextViewGrupo);
        final TextView txtDuracion = (TextView) view.findViewById(R.id.itemTextViewDuracion);
        final Button btnLike = (Button) view.findViewById(R.id.itemButtonMeGusta);
        final Button btnPlay =  (Button) view.findViewById(R.id.itemButtonPlay);
        final Button btnShare =  (Button) view.findViewById(R.id.itemButtonCompartir);

        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setMax(play.getDuration());

        imgAlbum.setImageResource(itemMusica.getFoto());
        txtCancion.setText(itemMusica.getCancion());
        txtGrupo.setText(itemMusica.getGrupo());

        if (esUsable){
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
        }else {
            btnLike.setVisibility(View.INVISIBLE);
        }
        if (esUsable) {
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (play.isPlaying()) {
                        play.pause();
                        btnPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                        Toast.makeText(contexto, "Pausado " + itemMusica.getCancion(), Toast.LENGTH_SHORT).show();
                    } else {
                        play.start();

                        btnPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                        Toast.makeText(contexto, "Reproduciendo " + itemMusica.getCancion(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else {
            btnPlay.setVisibility(View.INVISIBLE);
        }
        if (esUsable){
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, itemMusica.getRutafirebase());
                    contexto.startActivity(Intent.createChooser(intent,"Compartiendo Musica"));
                }
            });
        }else {
            btnShare.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    public String getUrlSong() {
        return urlSong;
    }

    public void setUrlSong(String urlSong) {
        this.urlSong = urlSong;
    }
}
