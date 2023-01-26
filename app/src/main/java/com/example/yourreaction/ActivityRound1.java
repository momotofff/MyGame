package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class ActivityRound1 extends AppCompatActivity
{
    Button buttonStart;
    Button tap;
    static ArrayList<View> textViews;
    static TimeCounter timeCounter;
    static int[] timeSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        Intent intent = getIntent();
        timeSleep = intent.getIntArrayExtra("name");
        textViews = findViewById(R.id.results).getTouchables();
        buttonStart = findViewById(R.id.buttonStart);
        tap = findViewById(R.id.buttonTap);
        timeCounter = new TimeCounter();
        tap.setClickable(false);
        
        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                buttonStart.setClickable(false);

                for (int i = 0; i < textViews.size(); ++i)
                {
                    tap.setVisibility(View.INVISIBLE);

                    try {Thread.sleep(timeSleep[i]);}
                    catch (InterruptedException e) {e.printStackTrace();}
                }
            }
        });

        tap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < textViews.size(); ++i) {
                    tap.setVisibility(View.INVISIBLE);
                    try {
                        Thread.sleep(timeSleep[i]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tap.setClickable(true);
                    tap.setVisibility(Button.VISIBLE);
                    TimeCounter.start();
                    //tap(i);
                }
            }
        });

    //public void tap(int i)
    //{
        //View view = new View(textViews.get(i).getContext());
        //textViews.add(i, view);
    //}
    }
}