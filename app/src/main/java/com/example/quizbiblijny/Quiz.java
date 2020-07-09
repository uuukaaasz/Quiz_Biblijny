package com.example.quizbiblijny;

public class Quiz {
    public Integer id;
    public String difficult_level;
    public String question;
    public String anwser_one;
    public String anwser_two;
    public String anwser_three;
    public Integer anwser_correct;

    public Quiz() {
    }

    public Quiz(String Difficult, String Question, String One, String Two, String Three, Integer Correct) {
        this.difficult_level = Difficult;
        this.question = Question;
        this.anwser_one = One;
        this.anwser_two = Two;
        this.anwser_three = Three;
        this.anwser_correct = Correct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        id = Id;
    }

    public String getDifficult_level() {
        return difficult_level;
    }

    public void setDifficult_level(String Difficult) {
        this.difficult_level = Difficult;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String Question) {
        this.question = Question;
    }

    public String getAnwser_one() {
        return anwser_one;
    }

    public void setAnwser_one(String One) {
        this.anwser_one = One;
    }

    public String getAnwser_two() {
        return anwser_two;
    }

    public void setAnwser_two(String Two) {
        this.anwser_two = Two;
    }

    public String getAnwser_three() {
        return anwser_three;
    }

    public void setAnwser_three(String Three) {
        this.anwser_three = Three;
    }

    public Integer getAnwser_correct() {
        return anwser_correct;
    }

    public void setAnwser_correct(Integer Correct) {
        this.anwser_correct = Correct;
    }
}
