package com.example.yourreaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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

        helpColor.setText(R.string.help2);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                int startIndex = (int) (Math.random() * buttonMain.length);

                for (int i = 0; i < buttonMain.length; ++i)
                {
                    int buttonIndex = (i + startIndex) % buttonMain.length;
                    int colorIndex = (i + startIndex) % colorsCode.length;

                    buttonMain[buttonIndex].setBackgroundColor(Color.parseColor(colorsCode[colorIndex]));
                    buttonMain[i].setVisibility(View.VISIBLE);
                    buttonMain[i].setEnabled(i == startIndex);
                    buttonFalseStartCatcher[i].setVisibility(View.INVISIBLE);
                }

                helpColor.setText(colorsType[startIndex]);
                helpColor.setTextColor(Color.parseColor(colorsCode[startIndex]));
                timeCounter.start();
            }
        }, (int) ((Math.random() * 3000 + 500)));
    }
}
