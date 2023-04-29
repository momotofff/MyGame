package com.example.yourreaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;


public class RoundColorImplementation extends RoundClickImplementation
{
    public RoundColorImplementation(int round, int quantityButtons)
    {
        super(round, quantityButtons);
    }

    public void beginNewRound()
    {
        for (Button button : buttonMain)
            button.setVisibility(View.INVISIBLE);

        for (Button button : buttonFalseStartCatcher)
            button.setVisibility(View.VISIBLE);

        for (Button button : buttonMain) button.setEnabled(true);

        helpColor.setText(R.string.help2);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                int indexButton = (int) (Math.random() * buttonMain.length);
                buttonMain[indexButton].setBackgroundColor(Color.parseColor(colorsCode[indexButton]));

                int checker = buttonMain.length - 1;
                int indexColor = indexButton + 1;
                int c = indexButton;
                ++indexButton;

                while (checker > 0)
                {
                    if (indexButton >= buttonMain.length)
                        indexButton = 0;

                    if (indexColor >= colorsCode.length)
                        indexColor = 0;

                    buttonMain[indexButton].setBackgroundColor(Color.parseColor(colorsCode[indexColor]));

                    --checker;
                    ++indexColor;
                    ++indexButton;
                }

                for (int i = 0; i < buttonMain.length; ++i)
                {
                    buttonMain[i].setVisibility(View.VISIBLE);
                    buttonFalseStartCatcher[i].setVisibility(View.INVISIBLE);

                    if (i != c)
                        buttonMain[i].setEnabled(false);
                }

                helpColor.setText(colorsType[c]);
                helpColor.setTextColor(Color.parseColor(colorsCode[c]));
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
