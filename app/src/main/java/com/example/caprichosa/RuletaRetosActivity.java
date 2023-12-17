package com.example.caprichosa;



import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class RuletaRetosActivity extends AppCompatActivity {
    private ImageView ruleta;
    private static final String[] sectores = { "Tormento chino", "Bailar", "Pintar barba", "Pintar los labios", "Reto libre", "Invitar el jugo", "Invitar la botana", "Imitar un mimo", "Imitar una gallina", "Hacer como perro", "Todos 1 regalo",
            "Acostarse en suelo", "Apreton de mano", "Todos te saludan", "Todos 1 abrazo", "Mojar el pantalon", "Cargar 1 amigo", "3 sonidos animales", "Comer 1/4 ajo", "Todos despeinarme", "Cantar", "Todos me dan 10 $", "Comer jabon", "Comer una cebolla", "Ponerse de cabeza", "Estatua de marfil", "Pintar lentes", "Grita me como los mocos", "Comer chile", "Quitar 1 cabello", "Besar el suelo", "Todos me dan 1$", "Imitar un bebe", "Pintar bigote","10 lagartijas","Todos un pellisco"};
    private static final int[] tamanosSector = new int[sectores.length];
    private static final Random random = new Random();
    private boolean girando = false;
    private int grados = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruletaretos);
        mediaPlayer = MediaPlayer.create(this, R.raw.ruletasonido);

        final ImageView boton = findViewById(R.id.boton);
        ruleta = findViewById(R.id.ruleta);
        getTamano();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!girando) {
                    girando = true;
                    reproducirSonido();
                    girar();

                }

            }
        });

    }

    private void girar() {

        grados = random.nextInt(sectores.length - 1);

        RotateAnimation animacion = new RotateAnimation(0, (360 * sectores.length) + tamanosSector[grados],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animacion.setDuration(6700);
        animacion.setFillAfter(true);
        animacion.setInterpolator(new DecelerateInterpolator());
        animacion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mediaPlayer.start();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(RuletaRetosActivity.this, "Ha sacado " + sectores[sectores.length - (grados)], Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
                girando = false;


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ruleta.startAnimation(animacion);

    }
    private void reproducirSonido() {
        // Si el sonido está reproduciéndose, detén y reinicia
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
        mediaPlayer.start();
    }

    private void getTamano() {
        int tamanoSector = 360 / sectores.length;

        for (int i = 0; i < sectores.length; i++) {
            tamanosSector[i] = (i + 1) * tamanoSector;

        }
    }
}