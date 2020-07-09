package com.example.quizbiblijny;

public class SpinnerObject {
    private int Id;
    private String Value;

    public SpinnerObject (int databaseId, String databaseValue) {
        this.Id = databaseId;
        this.Value = databaseValue;
    }

    public int getId () {
        return Id;
    }

    public String getValue () {
        return Value;
    }

    @Override
    public String toString () {
        return Value;
    }
}
