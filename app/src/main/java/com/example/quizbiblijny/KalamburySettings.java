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

public class KalamburySettings extends AppCompatActivity {

    EditText QuestionCount;
    CheckBox checkBoxEasy, checkBoxMedium, checkBoxHard;
    RadioButton radioButtonSequence, radioButtonRandom;
    Button buttonPlay, buttonBack;

    public boolean Easy, Medium, Hard, Sequence, Random;
    public int Range;
    ArrayList<Integer> Questions = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kalambury_settings);

        checkBoxEasy = findViewById(R.id.checkboxEasy);
        checkBoxMedium = findViewById(R.id.checkboxMedium);
        checkBoxHard = findViewById(R.id.checkboxHard);
        radioButtonSequence = findViewById(R.id.rbSequence);
        radioButtonRandom = findViewById(R.id.rbRandom);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonBack = findViewById(R.id.buttonSettingBack);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxEasy.isChecked()) {
                    Easy = true;
                }
                if(!checkBoxEasy.isChecked()) {
                    Easy = false;
                }
                if(checkBoxMedium.isChecked()) {
                    Medium = true;
                }
                if(!checkBoxMedium.isChecked()) {
                    Medium = false;
                }
                if(checkBoxHard.isChecked()) {
                    Hard = true;
                }
                if(!checkBoxHard.isChecked()) {
                    Hard = false;
                }
                if(radioButtonSequence.isChecked()) {
                    Sequence = true;
                    Random = false;
                }
                if(radioButtonRandom.isChecked()) {
                    Sequence = false;
                    Random = true;
                }
                Range = 0;
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
        ((Variables) this.getApplication()).setRange(Range);
        startActivity(new Intent(KalamburySettings.this, KalamburyActivity.class));
    }
}
