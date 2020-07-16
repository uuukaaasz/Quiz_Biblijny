package com.example.quizbiblijny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    TextView scoreResult;
    Button buttonBack, buttonRestart;
    public int points, range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        scoreResult = findViewById(R.id.scoreResult);
        buttonBack = findViewById(R.id.buttonBackToMain);
        buttonRestart = findViewById(R.id.buttonRestart);

        points = ((Variables) this.getApplication()).getPoints();
        range = ((Variables) this.getApplication()).getRange();

        scoreResult.setText(points+"/"+range);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result.this, MainActivity.class));
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result.this, Settings.class));
            }
        });
    }
}