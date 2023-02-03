package com.example.yourreaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

// TODO: Add false start processing
// TODO: Add results processing (min, max, avg, median maybe?)
// TODO: Show first screen only at first run

public class ActivityRound1 extends AppCompatActivity
{
    Button buttonStart;
    Button[] buttonTapArray = new Button[1];
    Button[] buttonTapArrayTransparent = new Button[1];
    ListView lvResults;
    List<Boolean> isFail = new ArrayList<>();

    final ArrayList<Long> times = new ArrayList<>();
    final TimeCounter timeCounter = new TimeCounter();
    final int TriesCount = 10;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        buttonStart = findViewById(R.id.buttonBack);
        buttonTapArray[0] = findViewById(R.id.buttonTap1);
        buttonTapArrayTransparent[0] = findViewById(R.id.buttonTap2);

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
                    case R.id.buttonBack:
                    {
                        buttonStart.setEnabled(false);

                        for (Button button : buttonTapArray)
                            button.setEnabled(true);

                        for (Button button : buttonTapArrayTransparent)
                            button.setEnabled(true);


                        times.clear();
                        adapter.notifyDataSetChanged();

                        beginNewRound();
                        break;
                    }
                    case R.id.buttonTap1:
                    {
                        buttonTap(adapter);
                        break;
                    }
                    case R.id.buttonTap2:
                    {
                        isFail.add(times.size(), false);
                        beginNewRound();
                    }
                }
            }
        };

        buttonStart.setOnClickListener(onClickListener);

        for (Button button : buttonTapArray)
            button.setOnClickListener(onClickListener);
    }

    private void buttonTap(ArrayAdapter<Long> adapter)
    {
        timeCounter.finish();
        times.add(timeCounter.getLeadTime());
        adapter.notifyDataSetChanged();
        timeCounter.reset();

        if (times.size() >= TriesCount)
        {
            buttonStart.setEnabled(true);

            for (Button button : buttonTapArray)
                button.setEnabled(false);

            for (Button button : buttonTapArrayTransparent)
                button.setEnabled(false);

            Intent intent = new Intent(ActivityRound1.this, ActivityWin.class);
            startActivity(intent);
            return;
        }

        beginNewRound();
    }

    private void beginNewRound()
    {
        for (Button button : buttonTapArray)
            button.setVisibility(View.INVISIBLE);

        for (Button button : buttonTapArrayTransparent)
            button.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                int index = (int) (Math.random() * buttonTapArray.length);
                buttonTapArray[index].setVisibility(View.VISIBLE);
                buttonTapArrayTransparent[index].setVisibility(View.INVISIBLE);
                timeCounter.start();
            }
        }, (int) (1000 * Math.random()));
    }
}