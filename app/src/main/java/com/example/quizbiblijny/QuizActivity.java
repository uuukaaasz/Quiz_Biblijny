package com.example.quizbiblijny;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuizActivity extends AppCompatActivity {

    TextView textViewPoints, textViewShowPoints, textViewQuestion;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    Button buttonShowDescription, buttonCorrectAnwser, buttonCheckAnwser, buttonNextQuestion, buttonResult;
    DBController db;

    public int correctAnswer = 0;
    public int qNo = 1;
    public int points = 0;
    public int range;
    public int questionId = 0;
    ArrayList<Integer> Questions = new ArrayList<Integer>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewPoints = findViewById(R.id.textViewPoints);
        textViewShowPoints = findViewById(R.id.textViewShowPoints);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButtonC = findViewById(R.id.radioButtonC);
        buttonShowDescription = findViewById(R.id.buttonShowDescription);
        buttonCorrectAnwser = findViewById(R.id.buttonCorrectAnwser);
        buttonCheckAnwser = findViewById(R.id.buttonCheckAnwser);
        buttonNextQuestion = findViewById(R.id.buttonNextQuestion);
        buttonResult = findViewById(R.id.buttonResult);

        db = new DBController(getApplicationContext());

        Questions = ((Variables) this.getApplication()).getArray();
        questionId = Questions.get(qNo-1);
        range = ((Variables) this.getApplication()).getRange();
        displayQuestion(questionId);
        setNumber(questionId);

        textViewShowPoints.setText(points + "/" + range);

        buttonShowDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizActivity.this, FootNotes.class));
            }
        });

        buttonCorrectAnwser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAnswer == 1) {
                    radioButtonA.setTextColor(Color.GREEN);
                    radioButtonA.setChecked(true);
                    radioButtonB.setTextColor(Color.RED);
                    radioButtonC.setTextColor(Color.RED);
                }
                if(correctAnswer == 2) {
                    radioButtonA.setTextColor(Color.RED);
                    radioButtonB.setTextColor(Color.GREEN);
                    radioButtonB.setChecked(true);
                    radioButtonC.setTextColor(Color.RED);
                }
                if(correctAnswer == 3) {
                    radioButtonA.setTextColor(Color.RED);
                    radioButtonB.setTextColor(Color.RED);
                    radioButtonC.setTextColor(Color.GREEN);
                    radioButtonC.setChecked(true);
                }
                radioButtonA.setEnabled(false);
                radioButtonB.setEnabled(false);
                radioButtonC.setEnabled(false);

                buttonCheckAnwser.setVisibility(View.GONE);

                if(qNo >= range) {
                    buttonResult.setVisibility(View.VISIBLE);
                } else {
                    buttonNextQuestion.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonCheckAnwser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correctAnswer == 1) {
                    radioButtonA.setTextColor(Color.GREEN);
                    radioButtonB.setTextColor(Color.RED);
                    radioButtonC.setTextColor(Color.RED);
                }
                if (correctAnswer == 2) {
                    radioButtonA.setTextColor(Color.RED);
                    radioButtonB.setTextColor(Color.GREEN);
                    radioButtonC.setTextColor(Color.RED);
                }
                if (correctAnswer == 3) {
                    radioButtonA.setTextColor(Color.RED);
                    radioButtonB.setTextColor(Color.RED);
                    radioButtonC.setTextColor(Color.GREEN);
                }

                boolean ifCorrect = false;
                if (radioButtonA.isChecked() && correctAnswer == 1) {
                    toastCorrectAnwser();
                    ifCorrect = true;
                    points++;
                }
                if (radioButtonB.isChecked() && correctAnswer == 2) {
                    toastCorrectAnwser();
                    ifCorrect = true;
                    points++;
                }
                if (radioButtonC.isChecked() && correctAnswer == 3) {
                    toastCorrectAnwser();
                    ifCorrect = true;
                    points++;
                }

                if(!ifCorrect) toastWrongAnwser();

                radioButtonA.setEnabled(false);
                radioButtonB.setEnabled(false);
                radioButtonC.setEnabled(false);

                if(qNo >= range) {
                    buttonResult.setVisibility(View.VISIBLE);
                } else {
                    buttonNextQuestion.setVisibility(View.VISIBLE);
                }

                buttonCheckAnwser.setVisibility(View.GONE);
                buttonCorrectAnwser.setEnabled(false);

                setPoints(points);
                textViewShowPoints.setText(points + "/" + range);
            }
        });

        buttonNextQuestion.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                radioButtonA.setEnabled(true);
                radioButtonB.setEnabled(true);
                radioButtonC.setEnabled(true);

                radioButtonA.setChecked(false);
                radioButtonB.setChecked(false);
                radioButtonC.setChecked(false);

                radioButtonA.setTextColor(Color.BLACK);
                radioButtonB.setTextColor(Color.BLACK);
                radioButtonC.setTextColor(Color.BLACK);

                buttonNextQuestion.setVisibility(View.GONE);
                buttonCheckAnwser.setVisibility(View.VISIBLE);
                buttonCorrectAnwser.setEnabled(true);

                qNo++;
                questionId = Questions.get(qNo-1);
                setNumber(questionId);
                displayQuestion(questionId);
            }
        });

        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizActivity.this, Result.class));
            }
        });
    }

    private void displayQuestion(int Id) {
        List<SpinnerObject> ListQuestion = db.getQuestionById(Id);
        ShowQuestion(ListQuestion);
        List<SpinnerObject> ListA = db.getAById(Id);
        ShowA(ListA);
        List<SpinnerObject> ListB = db.getBById(Id);
        ShowB(ListB);
        List<SpinnerObject> ListC = db.getCById(Id);
        ShowC(ListC);
        List<SpinnerObject> ListCorrect = db.getCorrectById(Id);
        correctAnswer = GetCorrect(ListCorrect);
    }

    private void ShowQuestion(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            textViewQuestion.setText(o.getValue());
        }
    }

    private void ShowA(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            radioButtonA.setText(o.getValue());
        }
    }

    private void ShowB(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            radioButtonB.setText(o.getValue());
        }
    }

    private void ShowC(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            radioButtonC.setText(o.getValue());
        }
    }

    private Integer GetCorrect(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            String correct = o.getValue();
            if(correct.matches("1")) {
                return 1;
            }
            if(correct.matches("2")) {
                return 2;
            }
            if(correct.matches("3")) {
                return 3;
            }
            break;
        }
        return null;
    }

    public void toastCorrectAnwser() {
        Toast.makeText(this, "ŚWIETNIE! Dobra odpowiedź.", Toast.LENGTH_SHORT).show();
    }

    public void toastWrongAnwser() {
        Toast.makeText(this, "NIESTETY! Zła odpowiedź.", Toast.LENGTH_SHORT).show();
    }

    public void setPoints(int points) {
        ((Variables) this.getApplication()).setPoints(points);
    }

    public void setNumber(int number) {
        ((Variables) this.getApplication()).setNumber(number);
    }
}
