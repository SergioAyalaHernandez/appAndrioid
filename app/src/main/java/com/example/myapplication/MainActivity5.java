package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {
    private GestureDetectorCompat gestureDetector;
    private Button backButton;
    private Button forwardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        gestureDetector = new GestureDetectorCompat(this, new MainActivity5.MyGestureListener());

        setContentView(R.layout.activity_main5);

        backButton = findViewById(R.id.backButton);
        forwardButton = findViewById(R.id.forwardButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            float diffX = event2.getX() - event1.getX();
            float diffY = event2.getY() - event1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        // Deslizar de izquierda a derecha
                        Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
                        startActivity(intent);
                        return true;
                    } else {
                        // Deslizar de derecha a izquierda
                        Intent intent = new Intent(MainActivity5.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    }
                }
            }
            return false;
        }
    }
}