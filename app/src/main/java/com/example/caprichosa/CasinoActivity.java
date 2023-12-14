package com.example.caprichosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class CasinoActivity extends AppCompatActivity {
    private ImageView ruleta;
    private Context context;
    private static final String[] sectores = {"0", "32", "15", "19", "4", "21", "2", "25", "17", "34", "37", "6", "27", "13", "36", "11", "30", "8", "23", "10", "5", "24", "16", "33", "1", "20", "14", "31", "9", "22", "18", "29", "7", "28", "12", "35", "3", "26"};
    private HashMap<String, Integer> apuestas = new HashMap<>();
    private static final int[] tamanosSector = new int[sectores.length];
    private static final Random random = new Random();
    private boolean girando = false;
    private int grados = 0;

    private EditText editTextApuesta;
    private EditText editTextMontoApuesta;
    private EditText editTextNumeroApuesta;
    private TextView textViewRestantes;
    private TextView textViewSaldo;
    private ImageView roulette;

    private int currentValue = 100; // Saldo inicial
    private int heldValue = 0;
    private int multiplicadorGanancia = 36; // Ajusta según las reglas de tu juego
    private double montoApuesta = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ruletaSize", ""+sectores.length);
        setContentView(R.layout.activity_casino);
        context = this;
        final ImageView boton = findViewById(R.id.boton);
        roulette = findViewById(R.id.ruleta);
        editTextApuesta = findViewById(R.id.editTextText2apuestas);
        editTextMontoApuesta = findViewById(R.id.editTextMontoApuesta);
        editTextNumeroApuesta = findViewById(R.id.editTextNumeroApuesta);
        textViewRestantes = findViewById(R.id.textViewRestante);
        textViewSaldo = findViewById(R.id.textViewSaldo);
        textViewRestantes.setText("100");
        textViewSaldo.setText("Saldo: " + currentValue);

        ruleta = findViewById(R.id.ruleta);
        getTamano();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!girando) {
                    girando = true;
                    realizarApuesta();
                }
            }
        });
    }

    private void girar() {
        roulette.setImageResource(R.drawable.casino);
       // apuestas = new HashMap<>();
        MediaPlayer song = MediaPlayer.create(this, R.raw.ruletagirando);
        song.start();
        /*
        String valoresString = editTextApuesta.getText().toString().trim();
        String[] valores = valoresString.split(" ");
        for (String valor : valores) {
            if (apuestas.get(valor) != null) {
                apuestas.put(valor, apuestas.get(valor) + 1);
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
        */
        grados = 1;
        grados = random.nextInt(sectores.length);
        int gradoCounter = 0;
        int getme = 0;
        while (gradoCounter < grados) {
            getme += tamanosSector[gradoCounter];
            gradoCounter++;
        }
        RotateAnimation animacion = null;
        animacion = new RotateAnimation(0, -((360*10)+getme),
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        int go_to = grados / 360;

        animacion.setDuration(3600);

        animacion.setFillAfter(true);
        animacion.setInterpolator(new DecelerateInterpolator());
        animacion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int sectorIndex = grados;
                String valor = sectores[sectorIndex];
                Log.d("valor_obtenido", valor);

                MediaPlayer song;
                if (apuestas.get(valor) != null || apuestas.get("") != null && valor.equals("0")) {
                    song = MediaPlayer.create(context, R.raw.victorymusic);
                    Toast.makeText(CasinoActivity.this, "Ganaste en el número: " + valor, Toast.LENGTH_SHORT).show();
                    currentValue += (apuestas.get(valor) * multiplicadorGanancia);
                    textViewSaldo.setText("Saldo: "+currentValue);
                } else {
                    song = MediaPlayer.create(context, R.raw.lossmusic);
                    Toast.makeText(CasinoActivity.this, "Perdiste en el número: " + valor, Toast.LENGTH_SHORT).show();
                }

                song.start();
                heldValue = 0;
                girando = false;
                textViewRestantes.setText(String.valueOf(currentValue));
                textViewSaldo.setText("Saldo: " + currentValue);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ruleta.startAnimation(animacion);
    }

    private void realizarApuesta() {
        apuestas = new HashMap<>();
        String montoString = editTextMontoApuesta.getText().toString().trim();
        String numeroString = editTextNumeroApuesta.getText().toString().trim();
        Log.d("valor_monto", numeroString);

        if (montoString.isEmpty() || numeroString.isEmpty()) {
            Toast.makeText(CasinoActivity.this, "Ingrese el monto y el número de la apuesta", Toast.LENGTH_SHORT).show();
            girando = false; // Detener el giro si faltan datos
            return;
        }

        double montoApuesta = Double.parseDouble(montoString);
        int numeroApuesta = Integer.parseInt(numeroString);

        if (montoApuesta > currentValue) {
            Toast.makeText(CasinoActivity.this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
            girando = false; // Detener el giro si no hay saldo suficiente
            return;
        }

        // Resta el monto de la apuesta del saldo
        currentValue -= montoApuesta;
        textViewRestantes.setText(String.valueOf(currentValue));
        textViewSaldo.setText("Saldo: " + currentValue);
        apuestas.put(numeroString,Integer.parseInt(montoString));



        // Llama al método girar() para iniciar la animación de la ruleta
        girar();
    }

    private void getTamano() {
        int tamanoSector = 360/sectores.length;
        for (int i = 0; i < sectores.length; i++) {
            if (i % 2 != 0) {
                tamanosSector[i] = 1+tamanoSector;
            } else {
                tamanosSector[i] = tamanoSector;
            }
        }
    }
}
