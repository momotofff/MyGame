package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRound6 extends AppCompatActivity
{
    RoundClickImplementation impl = new RoundClickImplementation(6,8);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round6);

        impl.buttonStart = findViewById(R.id.btnStart);
        impl.buttonMain[0] = findViewById(R.id.btnMainRound6_1);
        impl.buttonMain[1] = findViewById(R.id.btnMainRound6_2);
        impl.buttonMain[2] = findViewById(R.id.btnMainRound6_3);
        impl.buttonMain[3] = findViewById(R.id.btnMainRound6_4);
        impl.buttonMain[4] = findViewById(R.id.btnMainRound6_5);
        impl.buttonMain[5] = findViewById(R.id.btnMainRound6_6);
        impl.buttonMain[6] = findViewById(R.id.btnMainRound6_7);
        impl.buttonMain[7] = findViewById(R.id.btnMainRound6_8);

        impl.buttonFalseStartCatcher[0] = findViewById(R.id.btnFalseStartCatcherRound6_1);
        impl.buttonFalseStartCatcher[1] = findViewById(R.id.btnFalseStartCatcherRound6_2);
        impl.buttonFalseStartCatcher[2] = findViewById(R.id.btnFalseStartCatcherRound6_3);
        impl.buttonFalseStartCatcher[3] = findViewById(R.id.btnFalseStartCatcherRound6_4);
        impl.buttonFalseStartCatcher[4] = findViewById(R.id.btnFalseStartCatcherRound6_5);
        impl.buttonFalseStartCatcher[5] = findViewById(R.id.btnFalseStartCatcherRound6_6);
        impl.buttonFalseStartCatcher[6] = findViewById(R.id.btnFalseStartCatcherRound6_7);
        impl.buttonFalseStartCatcher[7] = findViewById(R.id.btnFalseStartCatcherRound6_8);
        impl.tips = getResources().getStringArray(R.array.FalseStartText);
        impl.lvResults = findViewById(R.id.results);
        impl.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, impl.gameResult.times);
        impl.lvResults.setAdapter(impl.adapter);

        impl.buttonStart.setOnClickListener(view -> impl.onBtnStart());

        for (Button button : impl.buttonMain)
            button.setOnClickListener(view -> impl.onBtnMain(ActivityRound6.this));

        for (Button button : impl.buttonFalseStartCatcher)
            button.setOnClickListener(view -> impl.onBtnFalseStart(ActivityRound6.this));
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ActivityRound6.this, RoundsActivity.class);
        startActivity(intent);
        finish();
    }
}
