package com.example.caprichosa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


import java.util.List;

public class RuletaView extends View {

    private List<String> sectores;
    private float angulo;

    private ArrayList<Integer> colors;

    public RuletaView(Context context, List<String> sectores, float angulo, ArrayList<Integer> colors) {
        super(context);
        this.sectores = sectores;
        this.angulo = angulo;
        this.colors = colors;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int totalSectores = sectores.size();
        float inicioAngulo = 0;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        for (String valor : sectores) {
            paint.setColor(getRandomColor());
            canvas.drawArc(0, 0, getWidth(), getHeight(), inicioAngulo, angulo, true, paint);

            // Ajustar el tamaño del texto
            paint.setColor(Color.WHITE);
            paint.setTextSize(100);
            float textX = (float) (getWidth() / 2 + getWidth() / 3 * Math.cos(Math.toRadians((inicioAngulo + angulo / 2))));
            float textY = (float) (getHeight() / 2 + getHeight() / 3 * Math.sin(Math.toRadians((inicioAngulo + angulo / 2))));
            canvas.drawText(valor, textX, textY, paint);

            inicioAngulo += angulo;
        }
    }



    private int getRandomColor() {
        Random random = new Random();
        if (colors.size() == 0) {
            return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        } else {
            return colors.get(random.nextInt(colors.size()));
        }
    }
}

