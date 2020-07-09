package com.example.quizbiblijny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStartK, btnStartQ, btnRules, btnSettings, btnExit;
    ImageButton ibPol, ibEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartK = findViewById(R.id.buttonStartK);
        btnStartQ = findViewById(R.id.buttonStartQ);
        btnRules = findViewById(R.id.buttonLearn);
        btnSettings = findViewById(R.id.buttonSettings);
        btnExit = findViewById(R.id.buttonExit);

        ibEng = findViewById(R.id.imgButtonEng);
        ibEng.setVisibility(View.GONE);
        ibPol = findViewById(R.id.imgButtonPol);

        btnStartK.setOnClickListener(this);
        btnStartQ.setOnClickListener(this);
        btnRules.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        ibPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ibPol.setVisibility(View.GONE);
                ibEng.setVisibility(View.VISIBLE);
            }
        });

        ibEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ibEng.setVisibility(View.GONE);
                ibPol.setVisibility(View.VISIBLE);
            }
        });

        Integer[] questionId = new Integer[25];
        for(int i = 0; i < 25; i++) {
            questionId[i] = i+1;
        }
        List<Integer> ListToShuffle = Arrays.asList(questionId);
        Collections.shuffle(ListToShuffle);

        String filename = "questionNumber";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            for (Integer s : ListToShuffle) {
                outputStream.write(s.toString().getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStartK:
                moveTo(KalamburyActivity.class);
                break;
            case R.id.buttonStartQ:

                moveTo(QuizActivity.class);
                break;
            case R.id.buttonLearn:
                moveTo(Rules.class);
                break;
            case R.id.buttonSettings:
                moveTo(Settings.class);
                break;
            case R.id.buttonExit:
                finish();
                System.exit(0);
        }
    }

    public void moveTo(Class c) {
        Intent intent = new Intent(getApplicationContext(), c);
        startActivity(intent);
    }
}
