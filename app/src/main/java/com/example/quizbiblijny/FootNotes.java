package com.example.quizbiblijny;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FootNotes extends AppCompatActivity {

    Button buttonBack;
    TextView title, description, foot;
    DBController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foot_notes);

        title = findViewById(R.id.Title);
        description = findViewById(R.id.Description);
        foot = findViewById(R.id.FootNote);
        buttonBack = findViewById(R.id.buttonFootBack);

        db = new DBController(getApplicationContext());

        int Id = ((Variables) this.getApplication()).getNumber();

        List<SpinnerObject> ListQuestion = db.getQuestionById(Id);
        ShowQuestion(ListQuestion);
        List<SpinnerObject> ListContent = db.getCytatById(Id);
        ShowContent(ListContent);
        List<SpinnerObject> ListFootNote = db.getFootById(Id);
        ShowFootNote(ListFootNote);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ShowQuestion(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            title.setText(o.getValue());
        }
    }

    private void ShowContent(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            description.setText(o.getValue());
        }
    }

    private void ShowFootNote(List<SpinnerObject> results)
    {
        for(SpinnerObject o: results){
            foot.setText(o.getValue());
        }
    }
}
