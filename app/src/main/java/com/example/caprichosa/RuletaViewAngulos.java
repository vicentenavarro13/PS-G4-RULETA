package com.example.caprichosa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RuletaViewAngulos extends View {

    private List<String> sectores;
    private float[] angulos;

    private ArrayList<Integer> colors;

    public RuletaViewAngulos(Context context, List<String> sectores, float[] angulos, ArrayList<Integer> colors) {
        super(context);
        this.sectores = sectores;
        this.angulos = angulos;
        this.colors = colors;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int totalSectores = sectores.size();
        float inicioAngulo = 0;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        int counterSectores = 0;
        for (String valor : sectores) {
            paint.setColor(getRandomColor());
            canvas.drawArc(0, 0, getWidth(), getHeight(), inicioAngulo, angulos[counterSectores], true, paint);

            // Ajustar el tamaño del texto
            paint.setColor(Color.WHITE);
            paint.setTextSize(100);
            float textX = (float) (getWidth() / 2 + getWidth() / 3 * Math.cos(Math.toRadians((inicioAngulo + angulos[counterSectores] / 2))));
            float textY = (float) (getHeight() / 2 + getHeight() / 3 * Math.sin(Math.toRadians((inicioAngulo + angulos[counterSectores] / 2))));
            canvas.drawText(valor, textX, textY, paint);

            inicioAngulo += angulos[counterSectores];
            counterSectores++;
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
    public float[] getAngulos() {
        return angulos;
    }
}
