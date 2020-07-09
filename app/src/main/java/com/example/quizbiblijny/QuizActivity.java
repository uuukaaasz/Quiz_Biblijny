package com.example.quizbiblijny;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView textViewPoints, textViewShowPoints, textViewQuestion;
    RadioButton radioButtonA, radioButtonB, radioButtonC;
    Button buttonShowDescription, buttonCorrectAnwser, buttonCheckAnwser, buttonNextQuestion;
    DBController db;

    public int correctAnswer = 0;
    public int qNo = 1;
    public int points = 0;

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

        db = new DBController(getApplicationContext());
        db.RemoveAll();
        FillDatabase();

        String filename = "questionNumber";
        //BufferedReader brTest = new BufferedReader(new FileReader(filename));

        displayQuestion(qNo);

        buttonShowDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 09.07.2020 context description from datebase
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
                buttonNextQuestion.setVisibility(View.VISIBLE);
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

                buttonCheckAnwser.setVisibility(View.GONE);
                buttonNextQuestion.setVisibility(View.VISIBLE);
            }
        });

        buttonNextQuestion.setOnClickListener(new View.OnClickListener() {
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

                textViewShowPoints.setText(points + "/15");
                // TODO: 09.07.2020 total points 
                qNo++;
                displayQuestion(qNo);
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

    public void FillDatabase() {
        db = new DBController(getApplicationContext());
        db.InsertQuiz(new Quiz("easy", "Umywać ręce", "przesadnie dbać o czystość", "nie przyjmować odpowiedzialności", "zabierać się do jakiejś czynności", 2));
        db.InsertQuiz(new Quiz("easy", "Arka Noego", "symbol schronienia, ocalenia", "symbol schronienia, ocalenia", "niebezpieczny pojazd", 1));
        db.InsertQuiz(new Quiz("easy", "Kto mieczem wojuje, ten od miecza ginie", "ktoś tak pochłonięty swoją pasją, że poświęca się jej do końca życia", "umiejętność doboru środków do osiągnięcia określonego celu", "sposób walki obraca się przeciwko temu, kto go używa", 3));
        db.InsertQuiz(new Quiz("easy", "Przejście przez Morze Czerwone", "próba wiary", "daremna wędrówka", "lęk przed wodą", 1));
        db.InsertQuiz(new Quiz("easy", "Palec boży", "przypomnienie o karze", "boża interwencja, znak, opatrzność", "schorzenie palców", 2));
        db.InsertQuiz(new Quiz("easy", "Chodzić krętymi ścieżkami", "być nietrzeźwym", "szukać woli Bożej", "wybierać niekoniecznie proste rozwiązania sytuacji", 3));
        db.InsertQuiz(new Quiz("medium", "Kraina mlekiem i miodem płynąca", "kraina występująca tylko w bajkach", "kraj, obszar urodzajny, żyzny, dostatni", "miejsce w którym nie da się żyć", 2));
        db.InsertQuiz(new Quiz("medium", "Zmienić się w słup soli", "stać się kimś odpornym na wszelkie przeciwności", "znieruchomieć, stanąć nieruchomo z wrażenia lub przerażenia", "być mistrzem kamuflażu", 2));
        db.InsertQuiz(new Quiz("medium", "Słowo stało się ciałem", "coś się urzeczywistniło, zapowiedź stała się faktem", "obietnica nie została spełniona", "niezgodność tego co się mówi z postępowaniem", 1));
        db.InsertQuiz(new Quiz("medium", "Głos wołającego na puszczy", "donośny głos", "zabieranie głosu w imieniu ubogich", "słowa, apele pozostające bez odzewu", 3));
        db.InsertQuiz(new Quiz("medium", "Jeźdźcy apokalipsy", "ludzie doskonale jeżdżący konno", "zwiastuny nieszczęścia, zagłady", "osoby ryzykownie kierujący pojazdami", 2));
        db.InsertQuiz(new Quiz("medium", "Listek figowy", "coś przynoszącego szczęście", "ubranie z naturalnych materiałów", "osłona czegoś, co z jakichś względów chciałoby się ukryć; symbol wstydliwości, przysłona nagości", 1));
        db.InsertQuiz(new Quiz("medium", "Oddać cesarzowi, co cesarskie, a Bogu, co boskie", "odznaczać się szczególną hojnością", "być kimś obłudnym", "dać każdemu to, co mu się należy", 3));
        db.InsertQuiz(new Quiz("medium", "Ziemia obiecana", "coś obiecanego, ale niezrealizowanego", "miejsce szczęśliwości i bogactwa, wydające się rajem; osiągnięty cel, szczęście", "majątek zapisany w testamencie", 2));
        db.InsertQuiz(new Quiz("medium", "Sól ziemi", "cenne złoża surowców", "podstawowy składnik potrawy", "grupa wartościowych ludzi jakiejś społeczności", 3));
        db.InsertQuiz(new Quiz("medium", "Strzepnąć proch z sandałów", "okazać, że ktoś jest niegodny tego, co mamy mu do zaproponowania", "dbać o czystość stroju", "być pewnym siebie", 1));
        db.InsertQuiz(new Quiz("medium", "Lata tłuste i chude", "czas ciągłego, nieskutecznego stosowania diet", "czasy powodzenia, obfitości i następujący po nich okres kryzysu", "czas ciągłych konfliktów", 2));
        db.InsertQuiz(new Quiz("medium", "Wypić kielich goryczy", "doświadczenie cierpienia, boleści", "skosztować unikalnego napoju", "pić zachłannie", 1));
        db.InsertQuiz(new Quiz("medium", "Łuski spadają z oczu", "patrzeć pożądliwie", "mieć problemy ze wzrokiem", "ktoś dostrzega prawdę, pozbawia się złudzeń", 3));
        db.InsertQuiz(new Quiz("hard", "Kolos na glinianych nogach", "coś bardzo potężnego, niezwyciężonego", "niepozorna osoba mająca niezwykłe umiejętności", "coś jawiącego się, jako potężne, a w rzeczywistości jest słabe", 3));
        db.InsertQuiz(new Quiz("hard", "Być alfą i omegą", "być nieudacznikiem", "nie wiedzieć, kim się jest", "wiedzieć wszystko z danej dziedziny, być ekspertem", 3));
        db.InsertQuiz(new Quiz("hard", "Mieć pietra", "mieć coś cennego", "mieć wiele odwagi", "bać się", 3));
        db.InsertQuiz(new Quiz("hard", "Faryzeusz", "ktoś obłudny", "ktoś niezwykle utalentowany", "wzór pobożności", 1));
        db.InsertQuiz(new Quiz("hard", "Sądny dzień", "dzień narodzin ważnej osoby", "dzień ślubu", "popłoch, zamieszanie, rozgardiasz  wywołane jakimiś wydarzeniami", 3));
        db.InsertQuiz(new Quiz("hard", "Grób pobielany", "osoba odświętnie ubrana", "człowiek obłudny", "grobowiec", 2));
    }
}
