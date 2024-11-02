package com.muhasib.scribble_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ImageView image = (ImageView) findViewById(R.id.image_saved);

        ByteArrayInputStream imageStreamClient = new ByteArrayInputStream(
                getIntent().getExtras().getByteArray("draw"));
        image.setImageBitmap(BitmapFactory.decodeStream(imageStreamClient));
    }
}