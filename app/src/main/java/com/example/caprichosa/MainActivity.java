package com.example.caprichosa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView ruleta;


    private RouletteView rouletteView;
    private static final String[] sectores = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final int [] tamanosSector = new int [sectores.length];
    private static final Random  random = new Random();
    private boolean girando = false ;
    private int grados = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText editTextOpciones = findViewById(R.id.editTextOpciones);
        final ImageView boton = findViewById(R.id.boton);
        final ImageView image = findViewById(R.id.ruleta);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opcionesTexto = editTextOpciones.getText().toString();
                String[] opciones = opcionesTexto.split("\n");


                rouletteView = new RouletteView(opciones);

                if (image != null && rouletteView != null) {
                    image.setImageDrawable(rouletteView);
                    image.setContentDescription(getResources().getString(R.string.roulette));
                }
                getTamano();
                girar(image);

            }


        });

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
    }
    private void girar(ImageView image){

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
                int indiceResultado = (sectores.length - (grados - 1)) % sectores.length;
                String resultado = sectores[indiceResultado];
                int resultadoNumerico = Integer.parseInt(resultado);
                Toast.makeText(MainActivity.this, "Ha sacado el número " + resultadoNumerico, Toast.LENGTH_SHORT).show();

                girando = false;


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.startAnimation(animacion);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }
    private void getTamano () {
        int tamanoSector = 360/ sectores.length;

        for (int i=0 ; i < sectores.length ; i++){
            tamanosSector[i] = (i+1) * tamanoSector;

        }
    }
}