package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRound7 extends AppCompatActivity
{
    RoundColorImplementation impl = new RoundColorImplementation(7,6);
    Resources resources;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round7);

        resources = getResources();
        impl.helpColor = findViewById(R.id.textView16);
        impl.buttonStart = findViewById(R.id.btnStart);
        impl.buttonMain[0] = findViewById(R.id.btnMainRound7_1);
        impl.buttonMain[1] = findViewById(R.id.btnMainRound7_2);
        impl.buttonMain[2] = findViewById(R.id.btnMainRound7_3);
        impl.buttonMain[3] = findViewById(R.id.btnMainRound7_4);
        impl.buttonMain[4] = findViewById(R.id.btnMainRound7_5);
        impl.buttonMain[5] = findViewById(R.id.btnMainRound7_6);

        impl.buttonFalseStartCatcher[0] = findViewById(R.id.btnFalseStartCatcherRound7_1);
        impl.buttonFalseStartCatcher[1] = findViewById(R.id.btnFalseStartCatcherRound7_2);
        impl.buttonFalseStartCatcher[2] = findViewById(R.id.btnFalseStartCatcherRound7_3);
        impl.buttonFalseStartCatcher[3] = findViewById(R.id.btnFalseStartCatcherRound7_4);
        impl.buttonFalseStartCatcher[4] = findViewById(R.id.btnFalseStartCatcherRound7_5);
        impl.buttonFalseStartCatcher[5] = findViewById(R.id.btnFalseStartCatcherRound7_6);

        impl.colorsType = getResources().getStringArray(R.array.MyColorsStr);
        impl.colorsCode = getResources().getStringArray(R.array.MyColorsHTML);
        impl.tips = getResources().getStringArray(R.array.FalseStartText);
        impl.lvResults = findViewById(R.id.results);
        impl.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, impl.gameResult.times);
        impl.lvResults.setAdapter(impl.adapter);

        impl.buttonStart.setOnClickListener(view -> impl.onBtnStart());

        for (Button button : impl.buttonMain)
            button.setOnClickListener(view -> impl.onBtnMain(ActivityRound7.this));

        for (Button button : impl.buttonFalseStartCatcher)
            button.setOnClickListener(view -> impl.onBtnFalseStart(ActivityRound7.this));
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ActivityRound7.this, RoundsActivity.class);
        startActivity(intent);
        finish();
    }
}
