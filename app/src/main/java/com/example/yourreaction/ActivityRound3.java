package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRound3 extends AppCompatActivity
{
    RoundImplementation impl = new RoundImplementation(3);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round3);

        impl.buttonStart = findViewById(R.id.btnStart);
        impl.buttonMain[0] = findViewById(R.id.btnMainRound2_1);
        impl.buttonMain[1] = findViewById(R.id.btnMainRound2_2);
        impl.buttonMain[2] = findViewById(R.id.btnMainRound2_3);
        impl.buttonFalseStartCatcher[0] = findViewById(R.id.btnFalseStartCatcherRound2_1);
        impl.buttonFalseStartCatcher[1] = findViewById(R.id.btnFalseStartCatcherRound2_2);
        impl.buttonFalseStartCatcher[2] = findViewById(R.id.btnFalseStartCatcherRound2_3);
        impl.tips = getResources().getStringArray(R.array.FalseStartText);
        impl.lvResults = findViewById(R.id.results);
        impl.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, impl.gameResult.times);
        impl.lvResults.setAdapter(impl.adapter);

        impl.buttonStart.setOnClickListener(view -> impl.onBtnStart());

        for (Button button : impl.buttonMain)
            button.setOnClickListener(view -> impl.onBtnMain(ActivityRound3.this));

        for (Button button : impl.buttonFalseStartCatcher)
            button.setOnClickListener(view -> impl.onBtnFalseStart(ActivityRound3.this));

        impl.onBack(this);
    }
}
