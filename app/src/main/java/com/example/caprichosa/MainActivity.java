package com.example.caprichosa;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValores;
    private RelativeLayout relativeLayoutRuleta;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValores = findViewById(R.id.editTextValores);
        relativeLayoutRuleta = findViewById(R.id.relativeLayoutRuleta);
        textViewResultado = findViewById(R.id.textViewResultado);
    }

    public void generarRuleta(View view) {
        String valoresString = editTextValores.getText().toString().trim();
        String[] valores = valoresString.split(" ");

        List<String> sectores = new ArrayList<>();
        for (String valor : valores) {
            if (!valor.isEmpty()) {
                sectores.add(valor);
            }
        }

        if (sectores.size() < 2) {
            textViewResultado.setText("Ingrese al menos dos valores para generar la ruleta.");
            return;
        }

        int totalSectores = sectores.size();
        float angulo = 360f / totalSectores;

        // Limpiar el RelativeLayout antes de agregar nuevos sectores
        relativeLayoutRuleta.removeAllViews();

        // Realizar la animación de giro
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360 * 5 + new Random().nextInt(360),
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        relativeLayoutRuleta.startAnimation(rotateAnimation);

        // Mostrar el resultado
        int resultadoIndex = new Random().nextInt(totalSectores);
        String resultado = sectores.get(resultadoIndex);
        int color = getRandomColor();
        textViewResultado.setText("¡Giraste la ruleta y obtuviste: " + resultado + "!");
        textViewResultado.setTextColor(color);

        // Dibujar la ruleta
        RuletaView ruletaView = new RuletaView(this, sectores, angulo);
        relativeLayoutRuleta.addView(ruletaView);
    }

    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
