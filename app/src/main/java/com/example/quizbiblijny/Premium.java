package com.example.quizbiblijny;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Premium extends AppCompatActivity {

    EditText premiumCode;
    TextView Premium1, Premium2, Premium3;
    Button buttonRedeem, buttonBack;

    private String premiumCode1 = "1a2b3c";
    private String premiumCode2 = "4d5e6f";
    private String premiumCode3 = "7g8h9i";

    String local_path = Environment.getExternalStorageDirectory().getAbsolutePath();
    File path = new File(local_path);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premium);

        premiumCode = findViewById(R.id.premiumCode);
        Premium1 = findViewById(R.id.Premium1);
        Premium2 = findViewById(R.id.Premium2);
        Premium3 = findViewById(R.id.Premium3);
        buttonRedeem = findViewById(R.id.buttonRedeem);
        buttonBack = findViewById(R.id.buttonBackToMain);

        buttonRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkCode = premiumCode.getText().toString();
                if(checkCode.equals(premiumCode1) || checkCode.equals(premiumCode2) || checkCode.equals(premiumCode3)) {
                    checkThisCode(checkCode);
                    premiumCode.setText("");
                } else {
                    WrongCode();
                    premiumCode.setTextColor(Color.RED);
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public String checkThisCode(String code) {

        String fileName = "config";
        String result = "0";

        try {
            File config = new File(path, fileName);
            FileWriter writer = new FileWriter(config);
            FileReader reader = new FileReader(config);

            if (!config.exists()) {
                writer.append("1");
                writer.flush();
                writer.close();
            } else {
                BufferedReader bufReader = new BufferedReader(reader);
                String line;

                while ((line = bufReader.readLine()) != null) {
                    if(line.equals(Premium1)) {
                        result = "Już wykorzystano ten kod.";
                        return result;
                    }
                    if(line.equals(Premium2)) {
                        result = "Już wykorzystano ten kod.";
                        return result;
                    }
                    if(line.equals(Premium3)) {
                        result = "Już wykorzystano ten kod.";
                        return result;
                    }
                    writer.append(line).append("\n");
                    result = "Kod PREMIUM aktywowany pomyślnie.";
                }
                bufReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public void WrongCode() {
        Toast.makeText(this, "Błędny kod!", Toast.LENGTH_SHORT).show();
    }
}
