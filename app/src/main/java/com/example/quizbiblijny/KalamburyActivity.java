package com.example.quizbiblijny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class KalamburyActivity extends AppCompatActivity {

    TextView textViewPoints, textViewShowPoints, textViewQuestion;
    Button buttonSource, buttonQuiz, buttonCorrect, buttonWrong, buttonBack;
    DBController db;

    public int range;
    public int points = 0;
    public int qNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalambury);

        textViewPoints = findViewById(R.id.textViewPoints);
        textViewShowPoints = findViewById(R.id.textViewShowPoints);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonSource = findViewById(R.id.buttonSource);
        buttonQuiz = findViewById(R.id.buttonQuiz);
        buttonCorrect = findViewById(R.id.buttonCorrect);
        buttonWrong = findViewById(R.id.buttonUncorrect);
        buttonBack = findViewById(R.id.buttonBack);

        db = new DBController(getApplicationContext());

        range = ((Variables) this.getApplication()).getRange();
        textViewShowPoints.setText(points + "/" + range);

        displayQuestion(qNo);

        buttonSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumber(qNo);
                startActivity(new Intent(KalamburyActivity.this, FootNotes.class));
            }
        });

        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumber(qNo);
                startActivity(new Intent(KalamburyActivity.this, QuizView.class));
            }
        });

        buttonCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points++;
                range++;
                textViewShowPoints.setText(points + "/" + range);
                qNo++;
                displayQuestion(qNo);
            }
        });

        buttonWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                range++;
                textViewShowPoints.setText(points + "/" + range);
                qNo++;
                displayQuestion(qNo);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayQuestion(int Id) {
        List<SpinnerObject> ListQuestion = db.getQuestionById(Id);
        ShowQuestion(ListQuestion);
    }

    private void ShowQuestion(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            textViewQuestion.setText(o.getValue());
        }
    }

    public void setNumber(int number) {
        ((Variables) this.getApplication()).setNumber(number);
    }
}
