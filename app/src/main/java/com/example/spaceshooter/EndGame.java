package com.example.spaceshooter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EndGame extends AppCompatActivity {

    TextView userScore;
    Button dialogButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_end);
        int points = getIntent().getExtras().getInt("points");
        userScore = findViewById(R.id.userPoints);
        userScore.setText("" + points);
        dialogButton = (Button) findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        DialogClass dialog = new DialogClass();
        dialog.show(getSupportFragmentManager(), "our dialog");
    }

    public void reset(View view) {
        Intent intent = new Intent(EndGame.this, Start.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
