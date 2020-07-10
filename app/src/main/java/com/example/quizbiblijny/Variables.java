package com.example.quizbiblijny;

import android.app.Application;

public class Variables extends Application {
    private int number;
    private int range;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(int Number) { this.number = Number; }

    public Integer getRange() {
        return this.range;
    }

    public void setRange(int Range) { this.range = Range; }

}
