package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ActivityWin extends AppCompatActivity
{
    static Activity activity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        setText(R.id.buttonMin, intent.getLongExtra("min", 0));
        setText(R.id.buttonMax, intent.getLongExtra("max", 0));
        setText(R.id.buttonAvg, intent.getLongExtra("avg", 0));
        setText(R.id.countFalseClick, intent.getLongExtra("falseStarts", 0));

        int round = intent.getIntExtra("round", 0);
        String caller = intent.getStringExtra("caller");

        findViewById(R.id.buttonRepeat).setOnClickListener(
        view -> startActivity(new Intent(ActivityWin.this, activity.getClass())));

        findViewById(R.id.buttonBack).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, RoundsActivity.class).
                         putExtra("avg", intent.getLongExtra("avg", 0)).
                         putExtra("round", round)));

        OnBackPressedCallback onBackPressed = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(ActivityWin.this, RoundsActivity.class));
                finish();
            }
        };

        this.getOnBackPressedDispatcher().addCallback(this, onBackPressed);
    }

    void setText(int id, long value)
    {
        TextView view = findViewById(id);
        view.setText(String.format(Locale.getDefault(), "%s %d", view.getText(), value));
    }
}
