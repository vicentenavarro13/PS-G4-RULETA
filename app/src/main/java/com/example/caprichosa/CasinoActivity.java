package com.example.caprichosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class CasinoActivity extends AppCompatActivity {
    private ImageView ruleta;
    private Context context;
    private static final String[] sectores = {"0", "32", "15", "19", "4", "21", "2", "25", "17", "34", "6", "27", "13", "36", "11", "30", "8", "23", "10", "5", "24", "16", "33", "1", "20", "14", "31", "9", "22", "18", "29", "7", "28", "12", "35", "3", "26"};
    private HashMap<String, Integer> apuestas = new HashMap<>();
    private static final int [] tamanosSector = new int [sectores.length];
    private static final Random  random = new Random();
    private boolean girando = false ;
    private int grados = 0;

    private EditText editTextApuesta;
    private TextView textViewRestantes;

    int currentValue = 10;

    int heldValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);
        context = this;
        final ImageView boton = findViewById(R.id.boton);
        editTextApuesta = findViewById(R.id.editTextText2apuestas);
        textViewRestantes = findViewById(R.id.textViewRestante);
        textViewRestantes.setText("10");

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
        apuestas = new HashMap<>();
        MediaPlayer song = MediaPlayer.create(this, R.raw.ruletagirando);
        song.start();
        String valoresString = editTextApuesta.getText().toString().trim();
        String[] valores = valoresString.split(" ");
        for (String valor : valores) {
            if (apuestas.get(valor) != null) {
                apuestas.put(valor, apuestas.get(valor)+1);
                heldValue++;
                currentValue--;
            } else {
                apuestas.put(valor, 1);
                heldValue++;
                currentValue--;
            }
        }
        String valueUpdated = "" + currentValue;
        textViewRestantes.setText(valueUpdated);
        grados = 0;
        while (grados == 0) {
            grados = random.nextInt(sectores.length+1);
        }
        RotateAnimation animacion = null;
        if (grados == sectores.length) {
            animacion = new RotateAnimation(0,(360 * sectores.length) + tamanosSector[0],
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        } else {
            animacion = new RotateAnimation(0,(360 * sectores.length) + tamanosSector[grados],
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        }
        int go_to = grados/360;

        animacion.setDuration(3600);

        animacion.setFillAfter(true);
        animacion.setInterpolator(new DecelerateInterpolator());
        animacion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int sectorIndex = sectores.length - grados;
                if (sectorIndex < 0) {
                    sectorIndex += sectores.length;
                }
                String valor = sectores[sectorIndex];

                MediaPlayer song;
                if (apuestas.get(valor) != null) {
                    song = MediaPlayer.create(context, R.raw.victorymusic);
                    Toast.makeText(CasinoActivity.this, "Ganó la apuesta en el número: " + valor, Toast.LENGTH_SHORT).show();
                    currentValue += (2 * heldValue);
                } else {
                    song = MediaPlayer.create(context, R.raw.lossmusic);
                    Toast.makeText(CasinoActivity.this, "Perdió la apuesta en el número: " + valor, Toast.LENGTH_SHORT).show();
                }

                song.start();
                heldValue = 0;
                girando = false;
                textViewRestantes.setText(String.valueOf(currentValue));
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
