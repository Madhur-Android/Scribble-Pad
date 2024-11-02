package com.muhasib.scribble_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private Button button_save, button_clear;
    private GestureOverlayView gesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesture = (GestureOverlayView) findViewById(R.id.gestures);
        button_save = (Button) findViewById(R.id.save_button);
        button_clear = (Button) findViewById(R.id.clear_button);

        button_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    Bitmap gestureImg = gesture.getGesture().toBitmap(100, 100,
                            8, Color.BLACK);

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    gestureImg.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    byte[] bArray = bos.toByteArray();

                    Intent intent = new Intent(MainActivity.this, Activity2.class);

                    intent.putExtra("draw", bArray);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "No draw on the string", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the gesture overlay
                gesture.cancelClearAnimation();  // Cancel any ongoing animations
                gesture.clear(false);  // true ensures it will redraw without any gestures
            }
        });
    }
}