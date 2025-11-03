package com.example.orbitsimulator;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orbitsimulator.geometry.Geometry;
import com.example.orbitsimulator.geometry.SolidCircle;

public class MainActivity extends AppCompatActivity {

    private Geometry geometry;
    private boolean spinning = false;
    private Handler handler;
    private SeekBar horizontalScale;
    private SeekBar verticalScale;
    private SeekBar rotationVelocity;
    private GeometryCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        geometry = Geometry.getInstance();
        //Uso de method reference para passar uma Supplier, ou seja uma fábrica de uma determinada classe.
        geometry.populateGeometrySet(SolidCircle::new);



    }
    public void onClickStop(View view){
        spinning = false;
    }
    public void onClickStart(View view){
        spinning =  true;
        beginTimer();
    }

    private void beginTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                geometry.boost(Float.valueOf(rotationVelocity.getProgress()) / 50);
                geometry.setScaleX(Float.valueOf(horizontalScale.getProgress()) / 50);
                geometry.setScaleY(Float.valueOf(verticalScale.getProgress()) / 50);
                canvas.updateImage();
                if (spinning) {
                    handler.postDelayed(this, 15);
                }
            }
        });
    }
}