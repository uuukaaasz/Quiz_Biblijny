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
    DBController db;

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

        db = new DBController(getApplicationContext());
        db.RemoveAll();
        FillDatabase();
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

    public void FillDatabase() {
        db = new DBController(getApplicationContext());
        //1st part - 6x easy (1-6), 13x medium (26-38), 6x hard (76-81)
        db.InsertQuiz(new Quiz("easy", "Umywać ręce", "przesadnie dbać o czystość", "nie przyjmować odpowiedzialności", "zabierać się do jakiejś czynności", 2));
        db.InsertQuiz(new Quiz("easy", "Arka Noego", "symbol schronienia, ocalenia", "wyjątkowo brzydka łódź", "niebezpieczny pojazd", 1));
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

        //2nd part - 7x easy (7-13), 12x medium (39-50), 6x hard (82-87)
        db.InsertQuiz(new Quiz("easy", "Jabłko Adama", "rzadki gatunek jabłek", "potocznie zepsute jabłko", "grdyka, wystająca część krtani mężczyzny, przenośnie coś dławiącego, przeszkadzającego", 3));
        db.InsertQuiz(new Quiz("easy", "Dekalog", "dialog", "zbiór wykroczeń", "zasady postępowania w jakiejś dziedzinie", 3));
        db.InsertQuiz(new Quiz("easy", "Chleb powszedni", "jedzenie niskiej jakości ", "coś zwyczajnego, codziennego", "coś wyjątkowego, niespotykanego", 2));
        db.InsertQuiz(new Quiz("easy", "Rzucać kamieniem w kogoś", "oskarżać, potępiać kogoś", "mieć celne oko", "usprawiedliwiać kogoś", 1));
        db.InsertQuiz(new Quiz("easy", "Zakazany owoc", "coś, co należy się każdemu", "coś atrakcyjnego a niedozwolonego", "bardzo drogie jedzenie", 2));
        db.InsertQuiz(new Quiz("easy", "Przejść przez ucho igielne", "nawlec nitkę na igłę", "zdobyć coś w łatwy sposób", "dostać się gdzieś z trudem", 3));
        db.InsertQuiz(new Quiz("easy", "Chodzić po wodzie", "dokonywać rzeczy niemożliwych", "wygłupiać się", "przechodzić przez rzekę po moście", 1));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));

        //3rd part - 6x easy (14-19), 12x medium (51-62), 7x hard (88-94)
        db.InsertQuiz(new Quiz("easy", "Cymbał", "ktoś grający na wielu instrumentach", "człowiek ograniczony, gamoń, głupiec", "ktoś obdarowany szczególną mądrością", 2));
        db.InsertQuiz(new Quiz("easy", "Kto dołki kopie, ten sam w nie wpada", "niefrasobliwy robotnik", "przyjąć na siebie skutki własnych zaniedbań", "ktoś źle, traktujący innych zostanie ofiarą swoich działań", 3));
        db.InsertQuiz(new Quiz("easy", "Dobry pasterz", "ktoś bez wykształcenia", "człowiek udający, kogoś kim nie jest", "osoba dbająca o podwładnych, podopiecznych", 3));
        db.InsertQuiz(new Quiz("easy", "Oko za oko, ząb za ząb", "odwet, wyrównanie krzywd", "dbanie o odpowiednią cenę za towar lub usługę", "postępowanie z wielką czułością", 1));
        db.InsertQuiz(new Quiz("easy", "Płacz i zgrzytanie zębów", "smutek z powodu grzechu", "ciężka sytuacja, wielkie zmartwienie, cierpienie", "szczękanie zębami z powodu zimna", 2));
        db.InsertQuiz(new Quiz("easy", "Pocałunek Judasza", "nieszczery gest, maskujący wrogie zamiary; zdrada", "namiętny pocałunek", "gest przeprosin, pojednania", 1));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));

        //4th part - 6x easy (20-25), 13x medium (63-75), 6x hard (95-100)
        db.InsertQuiz(new Quiz("easy", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("easy", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("easy", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("easy", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("easy", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("easy", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("medium", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
        db.InsertQuiz(new Quiz("hard", "", "", "", "", 0));
    }
}
