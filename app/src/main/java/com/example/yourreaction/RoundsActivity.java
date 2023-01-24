package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoundsActivity extends AppCompatActivity
{
    int[] timeSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);

        Button startGame = findViewById(R.id.start_round1);
        timeSleep = TimerClass.getTimer();

        startGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RoundsActivity.this, ActivityRound1.class);
                intent.putExtra("name", timeSleep);
                startActivity(intent);
            }
        });
    }
}