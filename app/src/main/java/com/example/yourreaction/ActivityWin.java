package com.example.yourreaction;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityWin extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        findViewById(R.id.buttonRepeat).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, ActivityRound1.class))
        );

        findViewById(R.id.buttonBack).setOnClickListener(
            view -> startActivity(new Intent(ActivityWin.this, RoundsActivity.class))
        );
    }
}
