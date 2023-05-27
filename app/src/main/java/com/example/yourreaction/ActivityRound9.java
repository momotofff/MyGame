package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRound9 extends AppCompatActivity
{
    RoundColorImplementation impl = new RoundColorImplementation(9,10);
    Resources resources;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round9);

        resources = getResources();
        impl.helpColor = findViewById(R.id.textView16);
        impl.buttonStart = findViewById(R.id.btnStart);
        impl.buttonMain[0] = findViewById(R.id.btnMainRound9_1);
        impl.buttonMain[1] = findViewById(R.id.btnMainRound9_2);
        impl.buttonMain[2] = findViewById(R.id.btnMainRound9_3);
        impl.buttonMain[3] = findViewById(R.id.btnMainRound9_4);
        impl.buttonMain[4] = findViewById(R.id.btnMainRound9_5);
        impl.buttonMain[5] = findViewById(R.id.btnMainRound9_6);
        impl.buttonMain[6] = findViewById(R.id.btnMainRound9_7);
        impl.buttonMain[7] = findViewById(R.id.btnMainRound9_8);
        impl.buttonMain[8] = findViewById(R.id.btnMainRound9_9);
        impl.buttonMain[9] = findViewById(R.id.btnMainRound9_10);

        impl.buttonFalseStartCatcher[0] = findViewById(R.id.btnFalseStartCatcherRound9_1);
        impl.buttonFalseStartCatcher[1] = findViewById(R.id.btnFalseStartCatcherRound9_2);
        impl.buttonFalseStartCatcher[2] = findViewById(R.id.btnFalseStartCatcherRound9_3);
        impl.buttonFalseStartCatcher[3] = findViewById(R.id.btnFalseStartCatcherRound9_4);
        impl.buttonFalseStartCatcher[4] = findViewById(R.id.btnFalseStartCatcherRound9_5);
        impl.buttonFalseStartCatcher[5] = findViewById(R.id.btnFalseStartCatcherRound9_6);
        impl.buttonFalseStartCatcher[6] = findViewById(R.id.btnFalseStartCatcherRound9_7);
        impl.buttonFalseStartCatcher[7] = findViewById(R.id.btnFalseStartCatcherRound9_8);
        impl.buttonFalseStartCatcher[8] = findViewById(R.id.btnFalseStartCatcherRound9_9);
        impl.buttonFalseStartCatcher[9] = findViewById(R.id.btnFalseStartCatcherRound9_10);

        impl.colorsType = getResources().getStringArray(R.array.MyColorsStr);
        impl.colorsCode = getResources().getStringArray(R.array.MyColorsHTML);
        impl.tips = getResources().getStringArray(R.array.FalseStartText);
        impl.lvResults = findViewById(R.id.results);
        impl.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, impl.gameResult.times);
        impl.lvResults.setAdapter(impl.adapter);

        impl.buttonStart.setOnClickListener(view -> impl.onBtnStart());

        for (Button button : impl.buttonMain)
            button.setOnClickListener(view -> impl.onBtnMain(ActivityRound9.this));

        for (Button button : impl.buttonFalseStartCatcher)
            button.setOnClickListener(view -> impl.onBtnFalseStart(ActivityRound9.this));
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ActivityRound9.this, RoundsActivity.class);
        startActivity(intent);
        finish();
    }
}
