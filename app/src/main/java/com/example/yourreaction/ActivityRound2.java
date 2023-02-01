package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ActivityRound2 extends AppCompatActivity
{
    Button buttonStart;
    Button buttonTap1, buttonTap2;
    ListView lvResults;

    final ArrayList<Long> times = new ArrayList<>();
    final TimeCounter timeCounter = new TimeCounter();
    final int TriesCount = 10;

    @SuppressLint("`MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round2);

        buttonStart = findViewById(R.id.buttonStart);
        buttonTap1 = findViewById(R.id.buttonTap1);
        buttonTap2 = findViewById(R.id.buttonTap2);

        lvResults = findViewById(R.id.results);
        final ArrayAdapter<Long> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, times);
        lvResults.setAdapter(adapter);

        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.buttonTap1:
                        timeCounter.finish();
                        times.add(timeCounter.getLeadTime());
                        adapter.notifyDataSetChanged();
                        timeCounter.reset();

                        if (times.size() >= TriesCount)
                        {
                            buttonStart.setEnabled(true);
                            buttonTap1.setEnabled(false);
                            return;
                        }

                        beginNewRound();

                    case R.id.buttonTap2:
                        timeCounter.finish();
                        times.add(timeCounter.getLeadTime());
                        adapter.notifyDataSetChanged();
                        timeCounter.reset();

                        if (times.size() >= TriesCount)
                        {
                            buttonStart.setEnabled(true);
                            buttonTap1.setEnabled(false);
                            return;
                        }

                        beginNewRound();
                    case R.id.buttonStart:
                        buttonStart.setEnabled(false);
                        buttonTap1.setEnabled(true);
                        buttonTap2.setEnabled(true);

                        times.clear();
                        adapter.notifyDataSetChanged();

                        beginNewRound();
                }
            }
        };
    }



    private void beginNewRound()
    {
        buttonTap1.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                buttonTap1.setVisibility(View.VISIBLE);
                timeCounter.start();
            }
        }, (int) (3000 * Math.random() + 500));
    }

    private void buttonsTaps()
    {

    }
}
