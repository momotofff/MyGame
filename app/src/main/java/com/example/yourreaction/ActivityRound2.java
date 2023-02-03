package com.example.yourreaction;

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
    Button[] buttonTapArray = new Button[2];
    Button[] buttonTapArrayTransparent = new Button[2];
    ListView lvResults;

    final ArrayList<Long> times = new ArrayList<>();
    final TimeCounter timeCounter = new TimeCounter();
    final int TriesCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round2);

        buttonStart = findViewById(R.id.buttonBack);
        buttonTapArray[0] = findViewById(R.id.buttonTap1);
        buttonTapArray[1] = findViewById(R.id.buttonTap2);
        //buttonTapArrayTransparent[0] = findViewById(R.id.buttonTap1_1);
        //buttonTapArrayTransparent[1] = findViewById(R.id.buttonTap2_2);

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


                        times.clear();
                        adapter.notifyDataSetChanged();

                        beginNewRound();
                        break;
                    }
                    case R.id.buttonTap1:
                    case R.id.buttonTap2:
                    {
                        buttonTap(adapter);
                        break;
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

            return;
        }

        beginNewRound();
    }

    private void beginNewRound()
    {
        for (Button button : buttonTapArray)
            button.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                int index = (int) (Math.random() * buttonTapArray.length);
                buttonTapArray[index].setVisibility(View.VISIBLE);
                timeCounter.start();
            }
        }, (int) (3000 * Math.random() + 500));
    }
}
