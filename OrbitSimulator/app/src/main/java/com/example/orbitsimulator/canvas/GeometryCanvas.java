package com.example.orbitsimulator.canvas;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import android.view.View;

import androidx.annotation.Nullable;

import com.example.orbitsimulator.geometry.Geometry;

public class GeometryCanvas extends View {
    private float xCenterOfView;
    private float yCenterOfView;

    private int viewWidth;
    private int viewHeight;

    private Paint paint;
    private Geometry geometry;


    public GeometryCanvas(Context context) {
        super(context);
    }

    public GeometryCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        this.paint.setColor(Color.RED);
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setStrokeWidth(5);

        this.geometry = Geometry.getInstance();
    }
}
