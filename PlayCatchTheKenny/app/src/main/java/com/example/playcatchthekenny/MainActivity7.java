package com.example.playcatchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity7 extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    TextView highScoreText; // Yeni eklenen yüksek skor metni
    int score;
    int highScore; // Yeni eklenen yüksek skor değişkeni
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String HIGH_SCORE_KEY = "HighScore";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Button backButton7 = findViewById(R.id.backButton7);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        highScoreText = findViewById(R.id.highScoreText); // Yeni eklenen yüksek skor metni
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleScreenTouch(event);
                return true; // true döndürerek, tıklamanın diğer olayları engelliyoruz
            }
        });

        backButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity2'ye geri dönmek için Intent kullanılır
                Intent intent = new Intent(MainActivity7.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // Önceki yüksek skoru SharedPreferences'ten al
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        highScore = prefs.getInt(HIGH_SCORE_KEY, 0);

        highScoreText.setText("High Score: " + highScore);

        hideImages();

        score =0;
        highScore = 0;

        new CountDownTimer(20000,1000){
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished/1000    );
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                if (score > highScore) {
                    highScore = score; // Eğer şu anki skor, mevcut en yüksek skordan büyükse, yüksek skoru güncelle
                }
                // Yüksek skoru göster
                highScoreText.setText("High Score: " + highScore);


                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity7.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        @SuppressLint("UnsafeIntentLaunch") Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity7.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                int savedHighScore = prefs.getInt(HIGH_SCORE_KEY, 0);

                // Eğer mevcut yüksek skordan daha yüksek bir skor elde edildiyse güncelle
                if (score > savedHighScore) {
                    // SharedPreferences üzerindeki yüksek skoru güncelle
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt(HIGH_SCORE_KEY, score);
                    editor.apply();

                    // Yüksek skoru güncelle
                    highScore = score;
                    highScoreText.setText("High Score: " + highScore);
                }

            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    public void increaseScore (View view){
        score++;
        scoreText.setText("Score: " + score);

    }

    public void hideImages(){
        handler = new Handler();

        runnable = new Runnable() {

            @Override
            public void run() {

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,400);
            }
        };

        handler.post(runnable);
    }

    private void handleScreenTouch(MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                // Tıklanan yerde Kenny'nin görünüp görünmediğini kontrol et
                if (!isPointOnKenny(x, y)) {
                    decreaseScore();
                }
                break;
        }
    }

    private boolean isPointOnKenny(float x, float y) {
        for (ImageView image : imageArray) {
            if (image.getVisibility() == View.VISIBLE) {
                int[] location = new int[2];
                image.getLocationOnScreen(location);
                int imageX = location[0];
                int imageY = location[1];

                if (x >= imageX && x <= (imageX + image.getWidth()) &&
                        y >= imageY && y <= (imageY + image.getHeight())) {
                    return true; // Tıklanan yerde Kenny görünüyorsa true döndür
                }
            }
        }
        return false; // Tıklanan yerde Kenny görünmüyorsa false döndür
    }

    @SuppressLint("SetTextI18n")
    private void decreaseScore() {
        if (score > 0) {
            score--;
            scoreText.setText("Score: " + score);
        }
    }

}