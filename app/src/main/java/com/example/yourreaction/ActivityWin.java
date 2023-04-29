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
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        GameResult gameResult = (GameResult) bundle.getSerializable("gameResult");

        setText(R.id.buttonMin, gameResult.min);
        setText(R.id.buttonMax, gameResult.max);
        setText(R.id.buttonAvg, gameResult.avg);
        setText(R.id.countFalseClick, gameResult.falseStarts);

        findViewById(R.id.buttonRepeat).setOnClickListener(
        view -> startActivity(new Intent(ActivityWin.this, gameResult.activity.getClass())));

        findViewById(R.id.buttonBack).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, RoundsActivity.class).putExtras(bundle)));

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
