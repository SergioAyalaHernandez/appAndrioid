package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentActivity;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
    private GestureDetectorCompat gestureDetector;
    private GestureDetectorCompat gestureDetector2;
    private TextView textView;

    private TextView gestureTextView;
    private Button backButton;
    private Button forwardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        gestureDetector = new GestureDetectorCompat(this, new MainActivity3.MyGestureListener());
        gestureDetector2 = new GestureDetectorCompat(this, this);
        backButton = findViewById(R.id.backButton);
        forwardButton = findViewById(R.id.forwardButton);
        gestureTextView = findViewById(R.id.gestureTextView);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        gestureDetector2.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        gestureTextView.setText("Toque único confirmado");
        animateTextViewScale();
        return false;
    }

    private void animateTextViewScale() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(gestureTextView, View.SCALE_X, 1f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(gestureTextView, View.SCALE_Y, 1f, 1.2f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent e) {
        gestureTextView.setText("Doble toque mi so");
        animateTextViewRotation();
        return false;
    }

    private void animateTextViewRotation() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(gestureTextView, View.ROTATION, 0f, 360f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotation);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        gestureTextView.setText("Evento Double Tap");
        animateTextViewColorChange();
        return false;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        gestureTextView.setText("Sostenido");
        animateTextViewShadow();
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {
        gestureTextView.setText("Sostenido");
    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        gestureTextView.setText("onSingleTapUp");
        animateTextViewTextColorChange();
        return false;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        gestureTextView.setText("arrastrado");
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
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
                        Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                        gestureTextView.setText("Izquierda a derecha");
                        startActivity(intent);
                        return true;
                    } else {
                        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                        gestureTextView.setText("Derecha a izquierda");
                        startActivity(intent);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private void animateTextViewColorChange() {
        ObjectAnimator textColor = ObjectAnimator.ofArgb(gestureTextView, "textColor", Color.BLUE, Color.RED);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(textColor);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void animateTextViewShadow() {
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator shadowColor = ObjectAnimator.ofFloat(gestureTextView, "shadowRadius", 0f, 10f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(shadowColor);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void animateTextViewTextColorChange() {
        ObjectAnimator textColor = ObjectAnimator.ofArgb(gestureTextView, "textColor", Color.BLACK, Color.GREEN);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(textColor);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void animateTextViewVibration() {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(gestureTextView, View.TRANSLATION_X, 0f, 10f, -10f, 10f, -10f, 0f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(gestureTextView, View.TRANSLATION_Y, 0f, 10f, -10f, 10f, -10f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX, translationY);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void animateTextViewTranslation() {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(gestureTextView, View.TRANSLATION_X, 0f, 200f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX);
        animatorSet.setDuration(500);
        animatorSet.start();
    }
}



