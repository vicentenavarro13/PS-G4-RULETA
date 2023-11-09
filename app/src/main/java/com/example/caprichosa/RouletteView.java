package com.example.caprichosa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.view.View;
import java.util.Random;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RouletteView extends Drawable {
    private Paint[] sectorPaints;
    private String[] opciones;
    private Random random;

    public RouletteView(String[] opciones) {
        this.opciones = opciones;
        this.random = new Random();
        initializePaints();
    }

    private void initializePaints() {
        sectorPaints = new Paint[opciones.length];
        for (int i = 0; i < opciones.length; i++) {
            Paint paint = new Paint();
            paint.setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            sectorPaints[i] = paint;
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        float radius = Math.min(width, height) / 2;
        float centerX = width / 2.0f;
        float centerY = height / 2.0f;
        float startAngle = 0;

        for (int i = 0; i < opciones.length; i++) {
            float sweepAngle = (360.0f / opciones.length);
            Paint currentPaint = sectorPaints[i];

            // Dibuja el sector
            canvas.drawArc(0, 0, width, height, startAngle, sweepAngle, true, currentPaint);

            // Dibuja el texto en el centro del sector
            String texto = opciones[i];
            float textX = (float) (centerX + radius * 0.8 * Math.cos(Math.toRadians(startAngle + sweepAngle / 2)));
            float textY = (float) (centerY + radius * 0.8 * Math.sin(Math.toRadians(startAngle + sweepAngle / 2)));
            canvas.drawText(texto, textX, textY, currentPaint);

            startAngle += sweepAngle;
        }
    }
    // Draw a red circle in the center



    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
