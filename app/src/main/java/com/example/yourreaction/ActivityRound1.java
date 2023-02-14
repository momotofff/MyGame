package com.example.yourreaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;

// TODO: Add results processing (min, max, avg, median maybe?)
// TODO: Show first screen only at first run
// TODO: move all common game logic to separate class
// TODO: Maybe it is a good idea to calc min/max/avg/etc in class, containing results?
// TODO: Cover this class with unit tests bleat

public class ActivityRound1 extends AppCompatActivity
{
    Button buttonStart;
    Button[] buttonMain = new Button[1];
    Button[] buttonFalseStartCatcher = new Button[1];
    ListView lvResults;
    ArrayAdapter<Long> adapter;
    boolean isCheater = false;

    final GameResult gameResult = new GameResult();
    final TimeCounter timeCounter = new TimeCounter();
    final int TriesCount = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);

        buttonStart = findViewById(R.id.btnStart);
        buttonMain[0] = findViewById(R.id.btnMain);
        buttonFalseStartCatcher[0] = findViewById(R.id.btnFalseStartCatcher);

        lvResults = findViewById(R.id.results);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gameResult.times);
        lvResults.setAdapter(adapter);

        buttonStart.setOnClickListener(view -> onBtnStart());

        for (Button button : buttonMain)
            button.setOnClickListener(view -> onBtnMain());

        for (Button button : buttonFalseStartCatcher)
            button.setOnClickListener(view -> onBtnFalseStart());
    }

    private void onBtnStart()
    {
        buttonStart.setEnabled(false);

        for (Button button : buttonMain)
            button.setEnabled(true);

        for (Button button : buttonFalseStartCatcher)
            button.setEnabled(true);

        gameResult.clear();
        adapter.notifyDataSetChanged();

        beginNewRound();
    }

    private void onBtnMain()
    {
        timeCounter.finish();

        if (!isCheater)
        {
            gameResult.times.add(timeCounter.getLeadTime());
            adapter.notifyDataSetChanged();
        }

        isCheater = false;
        timeCounter.reset();

        if (gameResult.times.size() >= TriesCount)
        {
            buttonStart.setEnabled(true);

            for (Button button : buttonMain)
                button.setEnabled(false);

            for (Button button : buttonFalseStartCatcher)
                button.setEnabled(false);

            Collections.sort(gameResult.times);

            startActivity(new Intent(ActivityRound1.this, ActivityWin.class).
                                                  putExtra("avg", gameResult.avg(gameResult.times)).
                                                  putExtra("max", gameResult.max()).
                                                  putExtra("min", gameResult.min()).
                                                  putExtra("falseStarts", gameResult.falseStarts));
            return;
        }

        beginNewRound();
    }



    private void onBtnFalseStart()
    {
        ++gameResult.falseStarts;
        isCheater = true;
        Toast.makeText(getApplicationContext(),"Читак!", Toast.LENGTH_SHORT).show();
    }

    private void beginNewRound()
    {
        for (Button button : buttonMain)
            button.setVisibility(View.INVISIBLE);

        for (Button button : buttonFalseStartCatcher)
            button.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                int index = (int) (Math.random() * buttonMain.length);
                buttonMain[index].setVisibility(View.VISIBLE);
                buttonFalseStartCatcher[index].setVisibility(View.INVISIBLE);
                timeCounter.start();
            }
        }, (int) (Math.random()) + 500);
    }
}