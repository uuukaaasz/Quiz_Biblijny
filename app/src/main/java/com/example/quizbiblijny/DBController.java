package com.example.quizbiblijny;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context){
        super(context, "baza.db" ,null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.init(db);
    }

    private void init(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists quiz (" +
                        "id integer primary key autoincrement," +
                        "difficult_level text," +
                        "question text," +
                        "anwser_one text," +
                        "anwser_two text," +
                        "anwser_three text," +
                        "anwser_correct integer);" + "");
        db.execSQL(
                "create table if not exists cytaty (" +
                        "id integer primary key autoincrement," +
                        "content text);" + "");
    }

    //--------------------------Quiz requests--------------------------
    public void InsertQuiz(Quiz quiz) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("difficult_level", quiz.getDifficult_level());
        values.put("question", quiz.getQuestion());
        values.put("anwser_one", quiz.getAnwser_one());
        values.put("anwser_two", quiz.getAnwser_two());
        values.put("anwser_three", quiz.getAnwser_three());
        values.put("anwser_correct", quiz.getAnwser_correct());
        db.insertOrThrow("quiz",null, values);
    }

    public List<SpinnerObject> getQuestionById(int Id) {
        List<SpinnerObject> read = new ArrayList<SpinnerObject>();
        String query =  "SELECT quiz.id, quiz.question" +
                " FROM" + " quiz" +
                " WHERE quiz.id = " + Id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.rawQuery(query, null);
        while (kursor.moveToNext()) {
            read.add(new SpinnerObject(kursor.getInt(0), kursor.getString(1)));
        }
        return read;
    }

    public List<SpinnerObject> getAById(int Id) {
        List<SpinnerObject> read = new ArrayList<SpinnerObject>();
        String query =  "SELECT quiz.id, quiz.anwser_one" +
                " FROM" + " quiz" +
                " WHERE quiz.id = " + Id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.rawQuery(query, null);
        while (kursor.moveToNext()) {
            read.add(new SpinnerObject(kursor.getInt(0), kursor.getString(1)));
        }
        return read;
    }

    public List<SpinnerObject> getBById(int Id) {
        List<SpinnerObject> read = new ArrayList<SpinnerObject>();
        String query =  "SELECT quiz.id, quiz.anwser_two" +
                " FROM" + " quiz" +
                " WHERE quiz.id = " + Id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.rawQuery(query, null);
        while (kursor.moveToNext()) {
            read.add(new SpinnerObject(kursor.getInt(0), kursor.getString(1)));
        }
        return read;
    }

    public List<SpinnerObject> getCById(int Id) {
        List<SpinnerObject> read = new ArrayList<SpinnerObject>();
        String query =  "SELECT quiz.id, quiz.anwser_three" +
                " FROM" + " quiz" +
                " WHERE quiz.id = " + Id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.rawQuery(query, null);
        while (kursor.moveToNext()) {
            read.add(new SpinnerObject(kursor.getInt(0), kursor.getString(1)));
        }
        return read;
    }

    public List<SpinnerObject> getCorrectById(int Id) {
        List<SpinnerObject> read = new ArrayList<SpinnerObject>();
        String query =  "SELECT quiz.id, quiz.anwser_correct" +
                " FROM" + " quiz" +
                " WHERE quiz.id = " + Id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.rawQuery(query, null);
        while (kursor.moveToNext()) {
            read.add(new SpinnerObject(kursor.getInt(0), kursor.getString(1)));
        }
        return read;
    }

    //--------------------------Cytaty requests--------------------------
    public void InsertCytat(Cytaty cytaty) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", cytaty.getContent());
        db.insertOrThrow("cytaty",null, values);
    }

    public List<SpinnerObject> getCytatById(int Id) {
        List<SpinnerObject> read = new ArrayList<SpinnerObject>();
        String query =  "SELECT cytaty.id, cytaty.content" +
                " FROM" + " cytaty" +
                " WHERE cytaty.id = " + Id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.rawQuery(query, null);
        while (kursor.moveToNext()) {
            read.add(new SpinnerObject(kursor.getInt(0), kursor.getString(1)));
        }
        return read;
    }

    public void RemoveAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS quiz");
        db.execSQL("DROP TABLE IF EXISTS cytaty");
        init(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
