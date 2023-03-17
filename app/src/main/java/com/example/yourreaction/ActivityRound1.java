package com.example.yourreaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class ActivityRound1 extends AppCompatActivity
{
    MechanicsRound mr = new MechanicsRound(1);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        mr.buttonStart = findViewById(R.id.btnStart);
        mr.buttonMain[0] = findViewById(R.id.btnMain);
        mr.buttonFalseStartCatcher[0] = findViewById(R.id.btnFalseStartCatcher);
        mr.tips = getResources().getStringArray(R.array.FalseStartText);
        mr.lvResults = findViewById(R.id.results);
        mr.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mr.gameResult.times);
        mr.lvResults.setAdapter(mr.adapter);

        load();

        mr.buttonStart.setOnClickListener(view -> mr.onBtnStart());

        for (Button button : mr.buttonMain)
            button.setOnClickListener(view -> mr.onBtnMain(ActivityRound1.this));

        for (Button button : mr.buttonFalseStartCatcher)
            button.setOnClickListener(view -> mr.onBtnFalseStart(ActivityRound1.this));
    }

    private void load()
    {

    }
}