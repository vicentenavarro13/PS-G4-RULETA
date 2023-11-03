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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RouletteView extends Drawable {
    private final Paint redPaint;
    private final Paint bluePaint;
    private final Paint greenPaint;

    private int segments;

    private ShapeDrawable drawable;
    public RouletteView(int segments) {
        redPaint = new Paint();
        redPaint.setARGB(255, 255, 0, 0);
        bluePaint = new Paint();
        bluePaint.setARGB(255,0,0,255);
        greenPaint = new Paint();
        greenPaint.setARGB(255,0,255,0);
        this.segments = segments;
    }

    protected void onDraw(Canvas canvas) {
        drawable.draw(canvas);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        float radius = Math.min(width, height) / 2;
        int multiple = 1;
        Paint currentPaint = redPaint;
        for (float segmentcounter = 0; segmentcounter < 360; segmentcounter+=(360.0 /segments)) {
            canvas.drawArc((float) 0, (float) 0, (float) width, height, segmentcounter, (float) (360.0 / segments),true,currentPaint);
            if (currentPaint.equals(redPaint)) {
                currentPaint = greenPaint;
            } else if (currentPaint.equals(greenPaint)) {
                currentPaint = bluePaint;
            } else {
                currentPaint = redPaint;
            }
        }
        // Draw a red circle in the center

    }

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
