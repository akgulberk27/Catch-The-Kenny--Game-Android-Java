package com.example.playcatchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Button chooseDifficultyButton = findViewById(R.id.chooseDifficultyButton);
        Button backButtonDifficulty = findViewById(R.id.backButtonDifficulty);

        backButtonDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity2'ye geri dönmek için Intent kullanılır
                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        chooseDifficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDifficultyDialog();
            }
        });
    }

    private void showDifficultyDialog() {
        // Zorluk seviyesi seçimini içeren bir dialog oluştur
        final CharSequence[] difficultyOptions = {"Easy", "Medium", "Hard"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
        builder.setTitle("Choose Difficulty");
        builder.setItems(difficultyOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Kullanıcının seçtiği zorluk seviyesini burada kullanabilirsin
                String selectedDifficulty = difficultyOptions[which].toString();
                // Seçilen zorluk seviyesine göre gerekli işlemleri yapabilirsin
                openActivityBasedOnDifficulty(selectedDifficulty);

            }
        });
        builder.show();
    }

    private void openActivityBasedOnDifficulty(String selectedDifficulty) {
        Intent intent;

        switch (selectedDifficulty) {
            case "Easy":
                intent = new Intent(MainActivity4.this, MainActivity5.class);
                break;
            case "Medium":
                intent = new Intent(MainActivity4.this, MainActivity6.class);
                break;
            case "Hard":
                intent = new Intent(MainActivity4.this, MainActivity7.class);
                break;
            default:
                intent = new Intent(MainActivity4.this, MainActivity5.class); // Default to MainActivity5
                break;
        }

        startActivity(intent);
    }
}