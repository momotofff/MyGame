package com.example.yourreaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// TODO: Add false start processing
// TODO: Add results processing (min, max, avg, median maybe?)
// TODO: Show first screen only at first run

public class ActivityRound1 extends AppCompatActivity
{
    Button buttonStart;
    Button buttonTap;
    ListView lvResults;

    final ArrayList<Long> times = new ArrayList<>();
    final TimeCounter timeCounter = new TimeCounter();
    final int TriesCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        Intent intent = getIntent();
        buttonStart = findViewById(R.id.buttonStart);
        buttonTap = findViewById(R.id.buttonTap);

        lvResults = findViewById(R.id.results);
        final ArrayAdapter<Long> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, times);
        lvResults.setAdapter(adapter);

        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buttonStart.setEnabled(false);
                buttonTap.setEnabled(true);

                times.clear();
                adapter.notifyDataSetChanged();

                beginNewRound();
            }
        });

        buttonTap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                timeCounter.finish();
                times.add(timeCounter.getLeadTime());
                adapter.notifyDataSetChanged();
                timeCounter.reset();

                if (times.size() >= TriesCount)
                {
                    buttonStart.setEnabled(true);
                    buttonTap.setEnabled(false);
                    return;
                }

                beginNewRound();
            }
        });
    }

    private void beginNewRound()
    {
        buttonTap.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                buttonTap.setVisibility(View.VISIBLE);
                timeCounter.start();
            }
        },(int) (3000 * Math.random() + 500));
    }
}