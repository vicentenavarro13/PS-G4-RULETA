package com.example.caprichosa;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValores;
    private RelativeLayout relativeLayoutRuleta;
    private TextView textViewResultado;

    private ArrayList<Integer> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        colors = new ArrayList<>();
        if (extras != null) {
            ArrayList<String> colors_string = extras.getStringArrayList("list");
            for (String color : colors_string) {
                colors.add(Color.parseColor(color));
            }
        }
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
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
        int hasta = new Random().nextInt(360);
        RotateAnimation rotateAnimation = new RotateAnimation(0, -(360*5+hasta),
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        relativeLayoutRuleta.startAnimation(rotateAnimation);

        // Mostrar el resultado
        int contadorAngulo = 0;
        float contadorAngulo2 = angulo;
        while (contadorAngulo2 < hasta) {
            contadorAngulo2 += angulo;
            if (contadorAngulo == sectores.size()) {
                contadorAngulo = 0;
            }
            contadorAngulo++;
        }
        int resultadoIndex = contadorAngulo;
        if (resultadoIndex >= sectores.size()) {
            resultadoIndex = sectores.size()-1;
        }
        String resultado = sectores.get(resultadoIndex);
        int color = getRandomColor();
        textViewResultado.setText("¡Giraste la ruleta y obtuviste: " + resultado + "!");
        textViewResultado.setTextColor(color);

        // Dibujar la ruleta
        RuletaView ruletaView = new RuletaView(this, sectores, angulo, colors);
        relativeLayoutRuleta.addView(ruletaView);
    }

    private int getRandomColor() {
        Random random = new Random();
        if (colors.size() == 0) {
            return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        } else {
            return colors.get(random.nextInt(colors.size()));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        int itemId = item.getItemId();
        if (itemId == R.id.new_game) {//newGame();
            return true;
        } else if (itemId == R.id.help) {
            Intent intent = new Intent(this, ModficacionActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
