package com.example.yourreaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class RoundClickImplementation
{
    public String[] colorType;
    public int[] idMyColors = new int[12];
    int round;
    int quantityButtons;
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

    public RoundClickImplementation(int round, int quantityButtons)
    {
        this.round = round;
        this.quantityButtons = quantityButtons;
        buttonMain = new Button[quantityButtons];
        buttonFalseStartCatcher = new Button[quantityButtons];
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

    void beginNewRound()
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
        }, (int) ((Math.random() * 3000 + 500)));
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
        int tipIndex = (int) (Math.random() * tips.length);
        Toast.makeText(activityRound.getApplicationContext(),tips[tipIndex], Toast.LENGTH_SHORT).show();
    }

    public void getMyColors(Resources resources)
    {
        idMyColors[0] = resources.getColor(R.color.Gray, null);
        idMyColors[1] = resources.getColor(R.color.Black, null);
        idMyColors[2] = resources.getColor(R.color.Red, null);
        idMyColors[3] = resources.getColor(R.color.Blue, null);
        idMyColors[4] = resources.getColor(R.color.LightBlue, null);
        idMyColors[5] = resources.getColor(R.color.Green, null);
        idMyColors[6] = resources.getColor(R.color.Yellow, null);
        idMyColors[7] = resources.getColor(R.color.Orange, null);
        idMyColors[8] = resources.getColor(R.color.Violet, null);
        idMyColors[9] = resources.getColor(R.color.LightGreen, null);
        idMyColors[10] = resources.getColor(R.color.Pink, null);
        idMyColors[11] = resources.getColor(R.color.Brown, null);
    }
}
