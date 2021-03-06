package com.example.quizbiblijny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Settings extends AppCompatActivity {

    EditText QuestionCount;
    RadioButton radioButtonSequence, radioButtonRandom;
    Button buttonPlay, buttonBack;

    public boolean Sequence, Random;
    public int Range;
    ArrayList<Integer> Questions = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        QuestionCount = findViewById(R.id.editTextQuestionCount);
        radioButtonSequence = findViewById(R.id.rbSequence);
        radioButtonRandom = findViewById(R.id.rbRandom);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonBack = findViewById(R.id.buttonSettingBack);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonSequence.isChecked()) {
                    Sequence = true;
                    Random = false;
                }
                if(radioButtonRandom.isChecked()) {
                    Sequence = false;
                    Random = true;
                }
                Range = Integer.parseInt(QuestionCount.getText().toString());
                Play();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Play() {
        if(Random) {
            for(int i = 1; i <= Range; i++) {
                Questions.add(i);
            }
            Collections.shuffle(Questions);
        }
        if(Sequence){
            for(int i = 1; i <= Range; i++) {
                Questions.add(i);
            }
        }
        ((Variables) this.getApplication()).setRange(Range);
        ((Variables) this.getApplication()).setArray(Questions);
        startActivity(new Intent(Settings.this, QuizActivity.class));
    }
}
