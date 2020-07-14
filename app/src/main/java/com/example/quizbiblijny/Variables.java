package com.example.quizbiblijny;

import android.app.Application;

import java.util.Random;

public class Variables extends Application {
    private int number, range;
    private boolean checkBoxEasy, checkBoxMedium, checkBoxHard;
    private boolean radioButtonSequence, radioButtonRandom;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(int Number) {
        this.number = Number;
    }

    public Integer getRange() {
        return this.range;
    }

    public void setRange(int Range) {
        this.range = Range;
    }

    public Boolean getEasy() {
        return this.checkBoxEasy;
    }

    public void setEasy(Boolean Easy) {
        this.checkBoxEasy = Easy;
    }

    public Boolean getMedium() {
        return this.checkBoxMedium;
    }

    public void setMedium(Boolean Medium) {
        this.checkBoxMedium = Medium;
    }

    public Boolean getHard() {
        return this.checkBoxHard;
    }

    public void setHard(Boolean Hard) {
        this.checkBoxHard = Hard;
    }

    public Boolean getSequence() {
        return this.radioButtonSequence;
    }

    public void setSequence(Boolean Sequence) {
        this.radioButtonSequence = Sequence;
    }

    public Boolean getRandom() {
        return this.radioButtonRandom;
    }

    public void setRandom(Boolean Random) {
        this.radioButtonRandom = Random;
    }
}
