package com.example.yourreaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MechanicsRound
{
    int round;
    Button buttonStart;
    Button[] buttonMain;
    Button[] buttonFalseStartCatcher;
    ListView lvResults;
    ArrayAdapter<Long> adapter;
    boolean isCheater = false;
    String[] tips;
    final GameResult gameResult = new GameResult();
    final TimeCounter timeCounter = new TimeCounter();
    final int TriesCount = 10;

    public MechanicsRound(int round)
    {
        this.round = round;
        buttonMain = new Button[round];
        buttonFalseStartCatcher = new Button[round];
    }

    public void onBtnStart()
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
        }, (int) ((Math.random() * 1000 + 500)));
    }

    public void onBtnMain(Activity activityRound)
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

            activityRound.startActivity(new Intent(activityRound, ActivityWin.class).
                    putExtra("avg", gameResult.avg()).
                    putExtra("max", gameResult.max()).
                    putExtra("min", gameResult.min()).
                    putExtra("falseStarts", gameResult.falseStarts).
                    putExtra("round", round));
            return;
        }

        beginNewRound();
    }

    public void onBtnFalseStart(Activity activityRound)
    {
        ++gameResult.falseStarts;
        isCheater = true;
        Toast.makeText(activityRound.getApplicationContext(),tips[(int) (Math.random() * 5)], Toast.LENGTH_SHORT).show();
    }


}
