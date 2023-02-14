package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWin extends AppCompatActivity
{
    private Intent intent;
    Button min, max, avg;
    TextView view;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        intent = getIntent();
        min = findViewById(R.id.buttonMin);         min.setText(new StringBuilder(min.getText()).append(intent.getLongExtra("min", 0)));
        max = findViewById(R.id.buttonMax);         max.setText(new StringBuilder(max.getText()).append(intent.getLongExtra("max", 0)));
        avg = findViewById(R.id.buttonAvg);         avg.setText(new StringBuilder(avg.getText()).append(intent.getLongExtra("avg", 0)));
        view = findViewById(R.id.countFalseClick);  view.setText(new StringBuilder(view.getText()).append(intent.getIntExtra("falseStarts", 0)));


        findViewById(R.id.buttonRepeat).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, ActivityRound1.class))
        );

        findViewById(R.id.buttonBack).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, RoundsActivity.class))
        );
    }
}
