package com.example.quizbiblijny;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class QuizView extends AppCompatActivity {

    TextView textViewPoints, textViewShowPoints, textViewQuestion;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    Button buttonShowDescription, buttonCorrectAnwser, buttonCheckAnwser, buttonKalamburyBack;
    DBController db;

    public int correctAnswer = 0;
    public int qNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_view);

        textViewPoints = findViewById(R.id.textViewPoints);
        textViewShowPoints = findViewById(R.id.textViewShowPoints);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButtonC = findViewById(R.id.radioButtonC);
        buttonShowDescription = findViewById(R.id.buttonShowDescription);
        buttonCorrectAnwser = findViewById(R.id.buttonCorrectAnwser);
        buttonCheckAnwser = findViewById(R.id.buttonCheckAnwser);
        buttonKalamburyBack = findViewById(R.id.buttonKalamburyBack);

        db = new DBController(getApplicationContext());
        qNo = ((Variables) this.getApplication()).getNumber();
        displayQuestion(qNo);

        buttonShowDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizView.this, FootNotes.class));
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
                buttonKalamburyBack.setVisibility(View.VISIBLE);
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
                }
                if (radioButtonB.isChecked() && correctAnswer == 2) {
                    toastCorrectAnwser();
                    ifCorrect = true;
                }
                if (radioButtonC.isChecked() && correctAnswer == 3) {
                    toastCorrectAnwser();
                    ifCorrect = true;
                }

                if(!ifCorrect) toastWrongAnwser();

                radioButtonA.setEnabled(false);
                radioButtonB.setEnabled(false);
                radioButtonC.setEnabled(false);

                buttonCheckAnwser.setVisibility(View.GONE);
                buttonKalamburyBack.setVisibility(View.VISIBLE);
                buttonCorrectAnwser.setEnabled(false);
            }
        });

        buttonKalamburyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
}
