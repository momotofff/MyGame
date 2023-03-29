package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class RoundsActivity extends AppCompatActivity
{
    Long newAverage;
    Long loadAverage;
    SharedPreferences sPref;
    int[] idResRound;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);

        @SuppressLint("NonConstantResourceId") View.OnClickListener onClickListener = view -> {
            switch (view.getId())
            {
                case R.id.start_round1: { startThisActivity(ActivityRound1.class); break; }
                case R.id.start_round2: { startThisActivity(ActivityRound2.class); break; }
                case R.id.start_round3: { startThisActivity(ActivityRound3.class); break; }
                case R.id.start_round4: { startThisActivity(ActivityRound4.class); break; }
            }
        };

        findViewById(R.id.start_round1).setOnClickListener(onClickListener);
        findViewById(R.id.start_round2).setOnClickListener(onClickListener);
        findViewById(R.id.start_round3).setOnClickListener(onClickListener);
        findViewById(R.id.start_round4).setOnClickListener(onClickListener);

        Intent intent = getIntent();
        newAverage = intent.getLongExtra("avg", 0);
        int round = intent.getIntExtra("round", 0);
        idResRound = new int[]{0, R.id.result_round1, R.id.result_round2, R.id.result_round3, R.id.result_round4};

        switch (round)
        {
            case 0: { firstLoad(); break; }
            case 1: { refreshResults(R.id.result_round1, round); break; }
            case 2: { refreshResults(R.id.result_round2, round); break; }
            case 3: { refreshResults(R.id.result_round3, round); break; }
            case 4: { refreshResults(R.id.result_round4, round); break; }
        }
    }

    private void firstLoad()
    {
        sPref = getPreferences();

        for (int i = 1; i < idResRound.length; ++i)
        {
            long res = sPref.getLong("round" + i, 0);
            if (res != 0)
            {
                TextView result_round = findViewById(idResRound[i]);
                result_round.setText(String.format(Locale.getDefault(), "%d", load(i)));
            }
        }
    }

    public void refreshResults(int viewId, int round)
    {
        TextView result_round = findViewById(viewId);
        loadAverage = load(round);

        if (loadAverage == 0)
        {
            result_round.setText(String.format(Locale.getDefault(), "%d", newAverage));
            save(newAverage, round);
            return;
        }

        if (loadAverage - newAverage >= 0  )
        {
            result_round.setText(String.format(Locale.getDefault(), "%d", newAverage));
            save(newAverage, round);
        }
        else
            result_round.setText(String.format(Locale.getDefault(), "%d", loadAverage));

    }

    private void startThisActivity(Class<?> activityRoundNumberClass)
    {
        Intent intent = new Intent(RoundsActivity.this, activityRoundNumberClass);
        startActivity(intent);
        finish();
    }

    private long load(int round)
    {
        sPref = getPreferences();

        return sPref.getLong("round" + round, 0);
    }

    private SharedPreferences getPreferences()
    {
        String name = getApplicationContext().getPackageName();
        return getSharedPreferences(name, MODE_PRIVATE);
    }

    private void save(long newAverage, int round)
    {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong("round" + round, newAverage);
        editor.apply();
    }
}