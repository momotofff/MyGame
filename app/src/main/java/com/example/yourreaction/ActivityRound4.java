package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRound4 extends AppCompatActivity
{
    RoundClickImplementation impl = new RoundClickImplementation(4);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round4);

        impl.buttonStart = findViewById(R.id.btnStart);
        impl.buttonMain[0] = findViewById(R.id.btnMainRound4_1);
        impl.buttonMain[1] = findViewById(R.id.btnMainRound4_2);
        impl.buttonMain[2] = findViewById(R.id.btnMainRound4_3);
        impl.buttonMain[3] = findViewById(R.id.btnMainRound4_4);
        impl.buttonFalseStartCatcher[0] = findViewById(R.id.btnFalseStartCatcherRound4_1);
        impl.buttonFalseStartCatcher[1] = findViewById(R.id.btnFalseStartCatcherRound4_2);
        impl.buttonFalseStartCatcher[2] = findViewById(R.id.btnFalseStartCatcherRound4_3);
        impl.buttonFalseStartCatcher[3] = findViewById(R.id.btnFalseStartCatcherRound4_4);
        impl.tips = getResources().getStringArray(R.array.FalseStartText);
        impl.lvResults = findViewById(R.id.results);
        impl.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, impl.gameResult.times);
        impl.lvResults.setAdapter(impl.adapter);

        impl.buttonStart.setOnClickListener(view -> impl.onBtnStart());

        for (Button button : impl.buttonMain)
            button.setOnClickListener(view -> impl.onBtnMain(ActivityRound4.this));

        for (Button button : impl.buttonFalseStartCatcher)
            button.setOnClickListener(view -> impl.onBtnFalseStart(ActivityRound4.this));
    }
}
