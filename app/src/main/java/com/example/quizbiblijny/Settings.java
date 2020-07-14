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
import java.util.List;

public class Settings extends AppCompatActivity {

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
        setContentView(R.layout.settings);

        QuestionCount = findViewById(R.id.editTextQuestionCount);
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

                Range = Integer.parseInt(QuestionCount.getText().toString());
                int errors = LevelValidation();

                if (errors == 0) {
                    Play();
                } else {
                    ToastError();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int LevelValidation() {
        int errors = 0;
        if(Easy && Medium && Hard) {
            if(Range < 1 || Range > 25) {
                errors++;
            }
        }
        if (Easy && Medium && !Hard) {
            if(Range < 1 || Range > 19) {
                errors++;
            }
        }
        if (Easy && !Medium && Hard) {
            if(Range < 1 || Range > 13) {
                errors++;
            }
        }
        if (!Easy && Medium && Hard) {
            if(Range < 1 || Range > 19) {
                errors++;
            }
        }
        if (Easy && !Medium && !Hard) {
            if(Range < 1 || Range > 6) {
                errors++;
            }
        }
        if (!Easy && Medium && !Hard) {
            if(Range < 1 || Range > 13) {
                errors++;
            }
        }
        if (!Easy && !Medium && Hard) {
            if(Range < 1 || Range > 6) {
                errors++;
            }
        }
        if (!Easy && !Medium && !Hard) {
            errors++;
        }
        return errors;
    }

    public void Play() {
        DoList();
        if(Random) {
            RandomList();
        }
        ((Variables) this.getApplication()).setRange(Range);

        //startActivity(new Intent(Settings.this, QuizActivity.class));
    }

    private void DoList() {
        //Premium 0
        if(Easy) {
            for(int i = 1; i <= 6; i++) {
                Questions.add(i);
            }
        }
        if(Medium) {
            for(int i = 7; i <= 19; i++) {
                Questions.add(i);
            }
        }
        if(Hard) {
            for(int i = 20; i <= 25; i++) {
                Questions.add(i);
            }
        }

        //Premium 1
        if(Easy) {
            for(int i = 1; i <= 6; i++) {
                Questions.add(i);
            }
            for(int i = 26; i <= 32; i++) {
                Questions.add(i);
            }
        }
        if(Medium) {
            for(int i = 7; i <= 19; i++) {
                Questions.add(i);
            }
            for(int i = 32; i <= 44; i++) {
                Questions.add(i);
            }
        }
        if(Hard) {
            for(int i = 20; i <= 25; i++) {
                Questions.add(i);
            }
            for(int i = 45; i <= 50; i++) {
                Questions.add(i);
            }
        }
    }

    private void RandomList() {

    }

    private void ToastError () {
        Toast.makeText(this, "Nie ma aż tyle pytań :(", Toast.LENGTH_SHORT).show();
    }
}
