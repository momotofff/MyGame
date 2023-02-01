package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoundsActivity extends AppCompatActivity
{
    Button round1, round2, round3, round4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);

        round1 = findViewById(R.id.start_round1);
        round2 = findViewById(R.id.start_round2);
        round3 = findViewById(R.id.start_round3);
        round4 = findViewById(R.id.start_round4);

        round1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RoundsActivity.this, ActivityRound1.class);
                startActivity(intent);
            }
        });
    }
}