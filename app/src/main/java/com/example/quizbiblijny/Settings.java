package com.example.quizbiblijny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    EditText QuestionCount;
    CheckBox checkBoxEasy, checkBoxMedium, checkBoxHard;
    RadioButton radioButtonSequence, radioButtonRandom;
    Button buttonSaveChanges, buttonBack;

    public boolean Easy, Medium, Hard, Sequence, Random;
    public int Range;

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
        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        buttonBack = findViewById(R.id.buttonSettingBack);

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
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

                SaveChanges();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void SaveChanges() {
        ((Variables) this.getApplication()).setEasy(Easy);
        ((Variables) this.getApplication()).setMedium(Medium);
        ((Variables) this.getApplication()).setHard(Hard);
        ((Variables) this.getApplication()).setSequence(Sequence);
        ((Variables) this.getApplication()).setRandom(Random);
        ((Variables) this.getApplication()).setRange(Range);
        Toast.makeText(this, Easy + " " + Medium + " " + Hard + " " + Sequence + " " + Random + " " + Range, Toast.LENGTH_SHORT).show();
    }
}
