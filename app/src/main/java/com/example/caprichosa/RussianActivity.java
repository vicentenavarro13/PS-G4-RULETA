package com.example.caprichosa;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import kotlin.jvm.internal.BooleanSpreadBuilder;

public class RussianActivity extends AppCompatActivity {
    private ImageView ruleta;
    private static final String[] sectores = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final int [] tamanosSector = new int [sectores.length];
    private static final Random  random = new Random();
    private boolean girando = false ;
    private int grados = 0;
    private ArrayList<Boolean> balas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_russian);
        balas = new ArrayList<>();
        Random random = new Random();
        for (String s : sectores) {
            balas.add(random.nextBoolean());
        }

        final ImageView boton = findViewById(R.id.boton);
        ruleta = findViewById(R.id.ruleta);
        getTamano();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!girando){
                    girando = true;
                    girar();

                }

            }
        });

    }
    private void girar(){

        grados = random.nextInt(sectores.length-1);

        RotateAnimation animacion = new RotateAnimation(0,(360 * sectores.length) + tamanosSector[grados],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animacion.setDuration(3600);
        animacion.setFillAfter(true);
        animacion.setInterpolator(new DecelerateInterpolator());
        animacion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int valor = sectores.length - (grados - 1);
                if (balas.get(valor) == true) {
                    Toast.makeText(RussianActivity.this, "Adios", Toast.LENGTH_SHORT).show();
                    try {
                        sleep(1000*3);
                        System.exit(0);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(RussianActivity.this, "Sobrevives", Toast.LENGTH_SHORT).show();
                }
                girando = false;


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ruleta.startAnimation(animacion);

    }
    private void getTamano () {
        int tamanoSector = 360/ sectores.length;

        for (int i=0 ; i < sectores.length ; i++){
            tamanosSector[i] = (i+1) * tamanoSector;

        }
    }
}
