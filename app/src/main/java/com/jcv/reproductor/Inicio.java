package com.jcv.reproductor;
/*@Author: JCV **/
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {

    private ImageView logo;
    private TextView bienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        inicializarInicio();
        eventos();
    }

    protected void inicializarInicio(){
        logo = findViewById(R.id.imageViewEscuchando);
        bienvenida = findViewById(R.id.textViewBienvenido);

        Animation animacionInicio = AnimationUtils.loadAnimation(this,R.anim.transicion_inicio);
        logo.startAnimation(animacionInicio);
        bienvenida.startAnimation(animacionInicio);
    }
    protected void eventos(){
        final Intent intent = new Intent(this, Reproduccion.class);

        Thread timerTransicion = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(intent);
                    finish();
                }
            }
        };

        timerTransicion.start();
    }
}
