package com.example.caprichosa;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RoulettesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulettes);

        Button ruletaPersonalizableButton = findViewById(R.id.ruleta1);
        Button ruletaCasinoButton = findViewById(R.id.casino);
        Button ruletaRusaButton = findViewById(R.id.rusa);
        Button ruletaFortunaButton = findViewById(R.id.suerte);
        Button ruletaRetosButton = findViewById(R.id.retos);


        ruletaPersonalizableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzar la actividad de la ruleta personalizable
                Intent intent = new Intent(RoulettesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ruletaCasinoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzar la actividad de la ruleta de casino
                Intent intent = new Intent(RoulettesActivity.this, CasinoActivity.class);
                startActivity(intent);
            }
        });

        ruletaRusaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzar la actividad de la ruleta rusa
                Intent intent = new Intent(RoulettesActivity.this, RussianActivity.class);
                startActivity(intent);
            }
        });


        ruletaFortunaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzar la actividad de la ruleta de la fortuna
                Intent intent = new Intent(RoulettesActivity.this, RuletaSuerteActivity.class);
                startActivity(intent);
            }
        });
        ruletaRetosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzar la actividad de la ruleta de retos
                Intent intent = new Intent(RoulettesActivity.this, RuletaRetosActivity.class);
                startActivity(intent);
            }
        });
    }
}
