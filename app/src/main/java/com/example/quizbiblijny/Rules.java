package com.example.quizbiblijny;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rules extends AppCompatActivity {

    TextView textViewRulesK;
    TextView textViewRulesQ;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);

        textViewRulesK = findViewById(R.id.textViewRulesK);
        textViewRulesQ = findViewById(R.id.textViewRulesQ);
        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
