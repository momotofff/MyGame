package com.example.yourreaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


public class RoundColorImplementation extends RoundClickImplementation
{
    public RoundColorImplementation(int round, int quantityButtons)
    {
        super(round, quantityButtons);
    }

    public void beginNewRound(Resources resources)
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

                for (int i = 0; i < quantityButtons; ++i)
                {
                    buttonMain[i].getBackground().setColorFilter(activity., PorterDuff.Mode.MULTIPLY);
                }
                buttonFalseStartCatcher[index].setVisibility(View.INVISIBLE);
                timeCounter.start();
            }
        }, (int) ((Math.random() * 3000 + 500)));
    }

    @Override
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

    public void getMyColors()
    {

    }
}
