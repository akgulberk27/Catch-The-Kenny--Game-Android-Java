package com.example.playcatchthekenny;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView exitImage = findViewById(R.id.exitImage);
        ImageView linkedinImage = findViewById(R.id.linkedinImage);
        ImageView githubImage = findViewById(R.id.githubImage);
        ImageView howToPlayImage = findViewById(R.id.howtoplayImage);
        ImageView difficultyImage = findViewById(R.id.difficultyImage);
        ImageView playImage = findViewById(R.id.playImage);

        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity5'i başlatmak için Intent kullanılır
                Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
                startActivity(intent);
            }
        });

        howToPlayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity3'ü açmak için Intent kullanılır
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        exitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uygulamayı kapat
                finish();
            }
        });

        linkedinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LinkedIn sayfanızın URL'sini belirtin
                String linkedinUrl = "https://www.linkedin.com/in/akgulberk51";

                // Intent ile bir WebView başlatarak LinkedIn sayfasına yönlendirme yapın
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));
                startActivity(intent);
            }
        });

        githubImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GitHub sayfanızın URL'sini belirtin
                String githubUrl = "https://github.com/akgulberk27";

                // Intent ile bir WebView başlatarak GitHub sayfasına yönlendirme yapın
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
                startActivity(intent);
            }
        });

        difficultyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity4'ü açmak için Intent kullanılır
                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}