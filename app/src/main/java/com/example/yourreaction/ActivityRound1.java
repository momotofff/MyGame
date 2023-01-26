package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityRound1 extends AppCompatActivity
{
    Button buttonStart;
    Button tap;
    static ArrayList<View> textViews;
    static TimeCounter timeCounter;
    static int[] timeSleep;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        res = findViewById(R.id.res0);

        Intent intent = getIntent();
        timeSleep = intent.getIntArrayExtra("name");
        textViews = findViewById(R.id.results).getTouchables();
        buttonStart = findViewById(R.id.buttonStart);
        tap = findViewById(R.id.buttonTap);
        timeCounter = new TimeCounter();

        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tap.setVisibility(View.INVISIBLE);
                buttonStart.setClickable(false);
                TimeCounter.start();

                for (int i = 0; i < timeSleep.length; ++i)
                {
                    try
                    {
                        Thread.sleep(timeSleep[i]);
                        tap.setVisibility(View.VISIBLE);
                    }
                    catch (InterruptedException e) {e.printStackTrace();}
                }
            }
        });

        tap.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view)
            {
                synchronized (res) {
                    res.setText(Long.toString(TimeCounter.getLeadTime()));
                }
            }
        });
    }
}