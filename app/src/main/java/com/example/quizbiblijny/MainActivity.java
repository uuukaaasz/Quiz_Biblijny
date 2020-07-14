package com.example.quizbiblijny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
        btnSettings = findViewById(R.id.buttonPremium);
        btnExit = findViewById(R.id.buttonExit);

        ibEng = findViewById(R.id.imgButtonEng);
        ibPol = findViewById(R.id.imgButtonPol);
        ibPol.setVisibility(View.GONE);

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

        db = new DBController(getApplicationContext());
        db.RemoveAll();
        FillDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStartK:
                moveTo(Settings.class);
                break;
            case R.id.buttonStartQ:
                moveTo(Settings.class);
                break;
            case R.id.buttonLearn:
                moveTo(Rules.class);
                break;
            case R.id.buttonPremium:
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

        db.InsertCytat(new Cytaty("Piłat widząc, że nic nie osiąga, a wzburzenie raczej wzrasta, wziął wodę i umył ręce wobec tłumu, mówiąc: Nie jestem winny krwi tego Sprawiedliwego.","Mt 27, 24"));
        db.InsertCytat(new Cytaty("A potem Pan rzekł do Noego: Wejdź wraz z całą twą rodziną do arki (...). Bo za siedem dni spuszczę na ziemię deszcz (...).","Rdz 7, 1.4"));
        db.InsertCytat(new Cytaty("Wtedy Jezus rzekł do Piotra: Schowaj miecz swój do pochwy, bo wszyscy, którzy za miecz chwytają, od miecza giną.","Mt 26, 52"));
        db.InsertCytat(new Cytaty("Mojżesz wyciągnął rękę nad morze, a Pan cofnął wody gwałtownym wiatrem (...). Wody się rozstąpiły, a Izraelici szli przez środek morza (...).","Wj 14, 21-22"));
        db.InsertCytat(new Cytaty("A jeśli Ja palcem Bożym wyrzucam złe duchy, to istotnie przyszło już do was królestwo Boże.","Łk 11, 20"));
        db.InsertCytat(new Cytaty("Głos wołającego na pustyni: Przygotujcie drogę Panu, prostujcie ścieżki dla Niego! (...) drogi kręte niech się staną prostymi.","Łk 3, 4-5"));

        db.InsertCytat(new Cytaty("Zstąpiłem, aby go wyrwać z ręki Egiptu i wyprowadzić z tej ziemi do ziemi żyznej i przestronnej, do ziemi, która opływa w mleko i miód.","Wj 3, 8"));
        db.InsertCytat(new Cytaty("Żona Lota, która szła za  Lotem, obejrzała sięna Sodomę i Gomorę i stała się słupem soli.","Rdz 19, 26"));
        db.InsertCytat(new Cytaty("A Słowo stało się ciałem i zamieszkało wśród nas.","J 1, 14"));
        db.InsertCytat(new Cytaty("[Jan] odpowiedział: Jam głos wołającego na pustyni: Prostujcie drogę Pańską.","J 1, 23"));
        db.InsertCytat(new Cytaty("Oto biały koń, a siedzący na nim miał łuk. I dano mu wieniec, i wyruszył jako zwycięzca, by [jeszcze] zwyciężać...","Ap 6, 2"));
        db.InsertCytat(new Cytaty("Wtedy otworzyły się im obojgu oczy i poznali, że są nadzy; spletli więc gałązki figowe i zrobili sobie przepaski.","Rdz 3, 7"));
        db.InsertCytat(new Cytaty("Powiedz nam czy wolno płacić podatek Cezarowi, czy nie? (...) rzekł do nich [Jezus]: Oddajcie Cezarowi to, co należy do Cezara, a Bogu to, co należy do Boga.","Mt 22, 17. 21"));
        db.InsertCytat(new Cytaty("Pan mówił: Zstąpiłem, aby go wyrwać z ręki Egiptu i wyprowadzić z tej ziemi do ziemi żyznej i przestronnej, do ziemi, która opływa w mleko i miód.","Wj 3, 8"));
        db.InsertCytat(new Cytaty("Wy jesteście solą dla ziemi. Lecz jeśli sól utraci swój smak, czymże ją posolić? Na nic się już nie przyda, chyba na wyrzucenie i podeptanie przez ludzi.","Mt 5, 13"));
        db.InsertCytat(new Cytaty("Jeśli w jakim miejscu was nie przyjmą i nie będą słuchać, wychodząc stamtąd strząśnijcie proch z nóg waszych na świadectwo dla nich!","Mk 6,11"));
        db.InsertCytat(new Cytaty("Nadejdzie siedem lat obfitości wielkiej w całym Egipcie. A po nich nastanie siedem lat głodu.","Rdz 41, 29-30"));
        db.InsertCytat(new Cytaty("Ojcze mój, jeśli nie może ominąć Mnie ten kielich, i muszę go wypić, niech się stanie wola Twoja!","Mt 26, 42"));
        db.InsertCytat(new Cytaty("Natychmiast jakby łuski spadły z oczu Szawła i odzyskał wzrok (...).","Dz 9, 18"));

        db.InsertCytat(new Cytaty("Oto posąg bardzo wielki (...). a oto odłączył się kamień, (...) i ugodził posąg w jego stopy z żelaza i gliny, i połamał je.","Dn 2, 31.34"));
        db.InsertCytat(new Cytaty("Jam jest Alfa i Omega, mówi Pan Bóg, Który jest, Który był i Który przychodzi, Wszechmogący.","Ap 1, 8"));
        db.InsertCytat(new Cytaty("Jeden ze sług arcykapłana, (...) rzekł: Czyż nie ciebie widziałem razem z Nim w ogrodzie? Piotr znowu zaprzeczył i natychmiast kogut zapiał.","J 18, 26-27"));
        db.InsertCytat(new Cytaty("[Jezus] zaczął mówić najpierw do swoich uczniów: Strzeżcie się kwasu, to znaczy obłudy faryzeuszów.","Łk 12, 1"));
        db.InsertCytat(new Cytaty("Kto gardzi Mną i nie przyjmuje słów moich, ten ma swego sędziego: słowo, które powiedziałem, ono to będzie go sądzić w dniu ostatecznym.","J 12, 48"));
        db.InsertCytat(new Cytaty("Uczeni w Piśmie i faryzeusze, obłudnicy! podobni jesteście do grobów pobielanych, które z zewnątrz wyglądają pięknie, lecz wewnątrz pełne są kości trupich","Mt 23, 27"));

        //2nd part - 7x easy (7-13), 12x medium (39-50), 6x hard (82-87)
        db.InsertQuiz(new Quiz("easy", "Jabłko Adama", "rzadki gatunek jabłek", "potocznie zepsute jabłko", "grdyka, wystająca część krtani mężczyzny, przenośnie coś dławiącego, przeszkadzającego", 3));
        db.InsertQuiz(new Quiz("easy", "Dekalog", "dialog", "zbiór wykroczeń", "zasady postępowania w jakiejś dziedzinie", 3));
        db.InsertQuiz(new Quiz("easy", "Chleb powszedni", "jedzenie niskiej jakości ", "coś zwyczajnego, codziennego", "coś wyjątkowego, niespotykanego", 2));
        db.InsertQuiz(new Quiz("easy", "Rzucać kamieniem w kogoś", "oskarżać, potępiać kogoś", "mieć celne oko", "usprawiedliwiać kogoś", 1));
        db.InsertQuiz(new Quiz("easy", "Zakazany owoc", "coś, co należy się każdemu", "coś atrakcyjnego a niedozwolonego", "bardzo drogie jedzenie", 2));
        db.InsertQuiz(new Quiz("easy", "Przejść przez ucho igielne", "nawlec nitkę na igłę", "zdobyć coś w łatwy sposób", "dostać się gdzieś z trudem", 3));
        db.InsertQuiz(new Quiz("easy", "Chodzić po wodzie", "dokonywać rzeczy niemożliwych", "wygłupiać się", "przechodzić przez rzekę po moście", 1));
        db.InsertQuiz(new Quiz("medium", "Złoty cielec", "pomnik bohatera, o którym wszyscy zapomnieli", "wartości materialne, które stały się bożkiem", "coś bezwartościowego", 2));
        db.InsertQuiz(new Quiz("medium", "Przebaczać 77 razy", "przebaczać zawsze", "jest kres, miara wybaczania", "przebaczyć warunkowo", 1));
        db.InsertQuiz(new Quiz("medium", "Miłosierny samarytanin", "człowiek, który pomaga żeby samemu odnieść korzyść", "osoba udająca kogoś innego", "człowiek pomagający potrzebującym", 3));
        db.InsertQuiz(new Quiz("medium", "Gałązka oliwna", "gałąź, której nie można złamać", "znak pokoju, zgody, pojednania", "symbol obfitości, dobrobytu", 2));
        db.InsertQuiz(new Quiz("medium", "Kamień węgielny", "podstawa, początek czegoś", "bogate zasoby naturalne jakiegoś obszaru", "słaba podstawa, fundament, na którym nie da zbudować niczego trwałego", 1));
        db.InsertQuiz(new Quiz("medium", "Wygnanie z raju", "wybawienie z trudnej sytuacji", "awans", "wyrzucenie z miejsca, gdzie panuje dostatek i szczęście", 3));
        db.InsertQuiz(new Quiz("medium", "Dwóm panom służyć", "być szczególnie podatnym na wpływy innych osób", "nie działać w żadnej ze spraw z pełnym zaangażowaniem", "brak zaangażowania w swoje obowiązki", 2));
        db.InsertQuiz(new Quiz("medium", "Kto sieje wiatr, zbiera burzę", "poczynania niekonsekwentnego rolnika", "brak konsekwencji w działaniach", "wywołując zamęt, niezgodę można doznać negatywnych konsekwencji swoich działań", 3));
        db.InsertQuiz(new Quiz("medium", "Kozioł ofiarny", "osoba, którą poświęca się w walce, by odnieść zwycięstwo", "osoba, na którą niesłuszne zrzuca się całą odpowiedzialność", "ktoś ofiarnie pomagający potrzebującym", 2));
        db.InsertQuiz(new Quiz("medium", "Dawid i Goliat", "szczególnie zażyła przyjaźń", "nierówna walka bez szansy na zwycięstwo", "pojedynek, w którym spryt i mądrość zwyciężają brutalną siłę", 3));
        db.InsertQuiz(new Quiz("medium", "Ciemności egipskie", "nieprzeniknione ciemności", "jedna z atrakcji turystycznych w Egipcie", "brak światła, który nie sprawia problemu", 1));
        db.InsertQuiz(new Quiz("medium", "Dom zbudowany na piasku", "coś zbudowanego na solidnym fundamencie", "rzecz niepewna, bez trwałych podstaw", "budowla wzniesiona przez dziecko", 2));
        db.InsertQuiz(new Quiz("hard", "Marność nad marnościami", "okrzyk człowieka szczęśliwego", "przemijalność i ulotność wszystkiego, niestałość", "szczególnie niesmaczna potrawa", 2));
        db.InsertQuiz(new Quiz("hard", "Apokalipsa", "zakazana literatura", "katastrofa, wielka tragedia", "wiadomości ekonomiczne", 2));
        db.InsertQuiz(new Quiz("hard", "Exodus", "emigracja", "korzystna inwestycja", "atrakcyjna wycieczka", 1));
        db.InsertQuiz(new Quiz("hard", "Zakon Adama", "zakon męski", "siły zbrojne", "małżeństwo", 3));
        db.InsertQuiz(new Quiz("hard", "Wszystkie znaki na niebie i ziemi", "konstelacje gwiazd", "informacje wprowadzające w błąd", "zwiastun, wskazówka, zapowiedź", 3));
        db.InsertQuiz(new Quiz("hard", "Salomonowy wyrok", "wyrok sprawiedliwy, zaskakujący mądrością", "wyrok niesprawiedliwy", "wyrok korzystny dla kogoś, kto przekupił sędziego", 1));

        //3rd part - 6x easy (14-19), 12x medium (51-62), 7x hard (88-94)
        db.InsertQuiz(new Quiz("easy", "Cymbał", "ktoś grający na wielu instrumentach", "człowiek ograniczony, gamoń, głupiec", "ktoś obdarowany szczególną mądrością", 2));
        db.InsertQuiz(new Quiz("easy", "Kto dołki kopie, ten sam w nie wpada", "niefrasobliwy robotnik", "przyjąć na siebie skutki własnych zaniedbań", "ktoś źle, traktujący innych zostanie ofiarą swoich działań", 3));
        db.InsertQuiz(new Quiz("easy", "Dobry pasterz", "ktoś bez wykształcenia", "człowiek udający, kogoś kim nie jest", "osoba dbająca o podwładnych, podopiecznych", 3));
        db.InsertQuiz(new Quiz("easy", "Oko za oko, ząb za ząb", "odwet, wyrównanie krzywd", "dbanie o odpowiednią cenę za towar lub usługę", "postępowanie z wielką czułością", 1));
        db.InsertQuiz(new Quiz("easy", "Płacz i zgrzytanie zębów", "smutek z powodu grzechu", "ciężka sytuacja, wielkie zmartwienie, cierpienie", "szczękanie zębami z powodu zimna", 2));
        db.InsertQuiz(new Quiz("easy", "Pocałunek Judasza", "nieszczery gest, maskujący wrogie zamiary; zdrada", "namiętny pocałunek", "gest przeprosin, pojednania", 1));
        db.InsertQuiz(new Quiz("medium", "Budować na opoce", "zabierać się do pracy bez odpowiedniego przygotowania", "zaczynać coś nie będąc pewnym powodzenia", "budować na solidnym fundamencie", 3));
        db.InsertQuiz(new Quiz("medium", "Wdowi grosz", "niewielka suma ofiarowana na jakiś cel przez osobę niezamożną", "emerytura", "sowity datek", 1));
        db.InsertQuiz(new Quiz("medium", "Nie pozostał kamień na kamieniu", "wprawić w zakłopotanie", "wyczerpanie surowców na danym terenie", "zniszczyć coś doszczętnie", 3));
        db.InsertQuiz(new Quiz("medium", "Być jak z krzyża zdjętym", "zostać uratowanym", "być wyczerpanym fizycznie, przygnębionym", "dochodzić do siebie po długiej chorobie", 2));
        db.InsertQuiz(new Quiz("medium", "Krzew gorejący", "cud, cudowny znak", "zjawiska szczególnie interesujące naukowców", "krzewy, których liście jesienią mają intensywne kolory", 1));
        db.InsertQuiz(new Quiz("medium", "Niebieski ptak", "próżniak, lekkoduch, marzyciel, leń", "legendarny okaz ornitologiczny", "ktoś ubierający się pstrokato", 1));
        db.InsertQuiz(new Quiz("medium", "Wieża Babel", "obiekt architektoniczny wzbudzający zachwyt", "zamęt, bałagan", "szkoła językowa", 2));
        db.InsertQuiz(new Quiz("medium", "Syn marnotrawny", "człowiek zatwardziały w swoich poglądach, zachowaniu", "człowiek, który zawinił, lecz w porę opamiętał się i nawrócił", "pierworodny syn", 2));
        db.InsertQuiz(new Quiz("medium", "Rzeź niewiniątek", "bójka, w której biorą udział dzieci", "zdarzenie, w którym padło wiele niewinnych ofiar", "agresywne zachowania dzieci", 2));
        db.InsertQuiz(new Quiz("medium", "Mieć z kimś krzyż pański", "mieć z kimś wiele przygód", "towarzyszyć komuś w trudnych sytuacjach", "mieć z kimś problem, zmartwienie", 3));
        db.InsertQuiz(new Quiz("medium", "Od Adama i Ewy", "od samego początku", "najnowsza historia", "dziedzictwo", 1));
        db.InsertQuiz(new Quiz("medium", "Manna z nieba", "groźne zjawiska atmosferyczne", "nieoczekiwany dar, coś bardzo upragnionego, przychodzącego nieoczekiwanie bez wysiłku", "kłopotliwy dar", 2));
        db.InsertQuiz(new Quiz("hard", "W stroju Adama (lub Ewy)", "w stroju wykonanym z naturalnych materiałów", "nago", "w przebraniu", 2));
        db.InsertQuiz(new Quiz("hard", "Kainowa zbrodnia", "kradzież", "nieuczciwa inwestycja", "bratobójstwo", 3));
        db.InsertQuiz(new Quiz("hard", "Golgota", "cierpienie, męka", "miejsce szczęśliwości", "sanktuarium", 1));
        db.InsertQuiz(new Quiz("hard", "Hiobowe wieści", "dobre wiadomości", "zmanipulowane informacje", "tragiczna wiadomość", 3));
        db.InsertQuiz(new Quiz("hard", "Nikt nie jest prorokiem we własnym kraju", "łatwiej znaleźć uznanie u obcych niż u swoich", "zapomnieć o swoich korzeniach", "nie wiedzieć, co powiedzieć", 1));
        db.InsertQuiz(new Quiz("hard", "Przenieść się na łono Abrahama", "przeprowadzić się do bardziej luksusowego mieszkania", "awansować", "umrzeć", 3));
        db.InsertQuiz(new Quiz("hard", "Służyć mamonie", "uważać relację z mamą za najważniejszą w życiu", "podporządkować życie zarabianiu pieniędzy", "służyć dobrej sprawie", 2));

        //4th part - 6x easy (20-25), 13x medium (63-75), 6x hard (95-100)
        db.InsertQuiz(new Quiz("easy", "Włos z głowy nie spadnie", "wysokiej jakości usługi fryzjerskie", "niebezpieczeństwo, zagrożenie życia", "ktoś jest bezpieczny", 3));
        db.InsertQuiz(new Quiz("easy", "Siódma pieczęć", "ostateczna tajemnica", "przesadna biurokracja", "niezawodne zabezpieczenie", 1));
        db.InsertQuiz(new Quiz("easy", "Nadstawić drugi policzek", "witać się z kimś", "nie odpowiadać przemocą na przemoc, złem za zło", "wykazać chęć na kolejny pocałunek", 2));
        db.InsertQuiz(new Quiz("easy", "Droga krzyżowa", "atrakcyjna trasa turystyczna", "niebezpieczna droga", "czas pełen trosk, udręczeń, problemów", 3));
        db.InsertQuiz(new Quiz("easy", "Z pustego i Salomon nie naleje", "informacja o braku napojów na rodzinnych uroczystościach", "bez odpowiednich środków nawet najlepsi ludzie nie są w stanie zrobić czegoś wartościowego", "kłopoty finansowe", 2));
        db.InsertQuiz(new Quiz("easy", "Rozdzierać szaty", "lamentować, ubolewać", "niszczyć odzież", "szaleć z radości", 1));
        db.InsertQuiz(new Quiz("medium", "Być na świeczniku", "zajmować wysokie, eksponowane stanowisko", "być pod presją najbliższej rodziny", "być ignorowanym przez innych", 1));
        db.InsertQuiz(new Quiz("medium", "Wilk w owczej skórze", "ktoś udający kogoś przychylnego a ukrywający swoje złe zamiary", "przebieraniec", "człowiek przekonujący o swojej niezwykłości", 1));
        db.InsertQuiz(new Quiz("medium", "Niewierny Tomasz", "ktoś pozornie wątpiący a wewnętrznie przekonany do czegoś", "zdrajca, oszust", "ktoś nieufny, chcący wszystko sprawdzić; niedowiarek", 3));
        db.InsertQuiz(new Quiz("medium", "Judaszowe srebrniki", "monety cenne dla kolekcjonerów", "zapłata za zdradę, haniebnie zarobione pieniądze", "ostatnie pieniądze przeznaczone na szlachetny cel", 2));
        db.InsertQuiz(new Quiz("medium", "Eden", "miejsce najwyższej szczęśliwości, niebo, raj; raj utracony", "miejsce zamieszkania poza miastem", "miejsce cierpień i nieszczęścia", 1));
        db.InsertQuiz(new Quiz("medium", "Zabłąkana owca", "ktoś niewinny, fałszywie oskarżony", "ktoś, kto popełnił błąd, zrobił coś nagannego, grzesznik, odstępca", "ktoś, kto ma złą orientację w terenie", 2));
        db.InsertQuiz(new Quiz("medium", "Tchnąć w coś życie", "wystawić sztukę teatralną", "sprawić, że coś przestało działać", "spowodować korzystne zmiany, ożywić coś, napełnić nową energią", 3));
        db.InsertQuiz(new Quiz("medium", "Miecz obosieczny", "kiepska broń", "działanie, które oprócz pożądanego skutku może przynieść również niepożądane efekty", "broń niezwykle skuteczna", 2));
        db.InsertQuiz(new Quiz("medium", "Plagi egipskie", "coś bardzo dokuczliwego, klęska, kara", "pola uprawne w Egipcie", "obfitość na wzór kraju Faraona", 1));
        db.InsertQuiz(new Quiz("medium", "Wiara góry przenosi", "ludzie zebrani razem mogą dokonać rzeczy niemożliwych", "stwierdzenie, że nie jestem w stanie niczego dokonać", "pozytywne nastawienie, powodujące, że człowiek czuje się silny i ma przekonanie, że może wszystko zrobić", 3));
        db.InsertQuiz(new Quiz("medium", "Widzieć drzazgę w czyimś oku", "doceniać dobre cechy innych ludzi i brać z nich przykład", "być spostrzegawczym", "zauważać drobne wady u innych nie widząc własnych wielkich wad", 3));
        db.InsertQuiz(new Quiz("medium", "Posypać głowę popiołem", "poddać się uciążliwym zabiegom dla poprawienia wyglądu", "przyznać się do winy, okazać skruchę, żałować", "okazać radość z powodów religijnych", 2));
        db.InsertQuiz(new Quiz("medium", "Kusić jak wąż", "namawiać do złego, kusić podstępnie", "zachęcać do działalności dobroczynnej", "zachowywać się nieadekwatnie do sytuacji", 1));
        db.InsertQuiz(new Quiz("hard", "Przekuć miecze na lemiesze", "wziąć się do pracy", "uzbroić się", "oczekiwany czas pokoju", 3));
        db.InsertQuiz(new Quiz("hard", "Modlitwa celnika", "modlitwa uwielbienia", "modlitwa pokorna", "długie przemówienie", 2));
        db.InsertQuiz(new Quiz("hard", "Trąba jerychońska", "krzykacz", "ktoś o szczególnych zdolnościach oratorskich", "osoba niema", 1));
        db.InsertQuiz(new Quiz("hard", "Poznać po owocach", "poznać kogoś po długiej rozłące dzięki jakiemuś charakterystycznemu szczegółowi", "poznać czy postępowanie jest dobre czy złe po efektach", "szczególne uzdolnienia w dziedzinie botaniki", 2));
        db.InsertQuiz(new Quiz("hard", "Nie znać dnia ani godziny", "dezorientacja, stan zagubienia", "żyć beztrosko", "nie wiedzieć, kiedy coś się stanie, żyć w niepewności, w niepokoju", 3));
        db.InsertQuiz(new Quiz("hard", "Rzucać perły przed wieprze", "wielkodusznie działać na rzecz ludzi potrzebujących", "dawać komuś coś, co może mu zaszkodzić", "robić coś ważnego dla ludzi, którzy tego nie doceniają", 3));
    }
}
