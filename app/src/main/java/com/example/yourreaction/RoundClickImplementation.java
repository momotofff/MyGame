package com.example.yourreaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;

public class RoundClickImplementation
{
    public String[] colorsType;
    public String[] colorsCode;
    int quantityButtons;
    Button buttonStart;
    Button[] buttonMain;
    Button[] buttonFalseStartCatcher;
    ListView lvResults;
    ArrayAdapter<Long> adapter;
    boolean isCheater = false;
    String[] tips;
    GameResult gameResult = new GameResult();
    final TimeCounter timeCounter = new TimeCounter();
    final int TriesCount = 10;
    TextView helpColor;

    public RoundClickImplementation(int round, int quantityButtons)
    {
        gameResult.round = round;
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

            gameResult.activity = activityRound;
            gameResult.min = gameResult.min();
            gameResult.max = gameResult.max();
            gameResult.avg = gameResult.avg();
            Bundle bundle = new Bundle();
            bundle.putSerializable("gameResult", gameResult);

            activityRound.startActivity(new Intent(activityRound, ActivityWin.class).putExtras(bundle));
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

    public void onBack(androidx.activity.ComponentActivity owner)
    {
        OnBackPressedCallback onBackPressed = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                owner.startActivity(new Intent(owner, RoundsActivity.class));
            }
        };

        owner.getOnBackPressedDispatcher().addCallback(owner, onBackPressed);
    }
}
