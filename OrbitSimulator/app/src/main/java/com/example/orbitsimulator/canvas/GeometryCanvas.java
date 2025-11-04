package com.example.orbitsimulator.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.orbitsimulator.geometry.ElementTypes;
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

    /**
     * O onMeasure não é necessário porque o GeometryCanvas não define seu próprio tamanho,
     * ele se adapta ao tamanho definido para ele pelo Layout a qual foi inserido.
     *
     * @param widthMeasureSpec horizontal space requirements as imposed by the parent.
     *                         The requirements are encoded with
     *                         {@link android.view.View.MeasureSpec}.
     * @param heightMeasureSpec vertical space requirements as imposed by the parent.
     *                         The requirements are encoded with
     *                         {@link android.view.View.MeasureSpec}.
     *
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.viewWidth = w;
        this.viewHeight = h;
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        this.xCenterOfView = viewWidth / 2f;
        this.yCenterOfView = viewHeight / 2f;
        drawDataGeometry(canvas);
    }

    private void drawDataGeometry(Canvas canvas) {
        float x;
        float y;
        float r;
        float teta;

        float sx = (float) geometry.getScaleX();
        float sy = (float) geometry.getScaleY();
        for (ElementTypes element : geometry.getGeometrySetIncrement()) {
            r = (float) element.getPosition().getRadius();
            teta = (float) element.getPosition().getAngle();
            x = (float) (xCenterOfView + sx * r * Math.cos(teta));
            y = (float) (yCenterOfView - sy * r * Math.sin(teta));
            paint.setColor(Color.parseColor(element.getColor().toString()));
            canvas.drawCircle(x, y, (float) element.getSize(), paint);
        }
    }

    public void updateImage() {
        invalidate();
    }
}
