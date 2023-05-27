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
                case R.id.start_round5: { startThisActivity(ActivityRound5.class); break; }
                case R.id.start_round6: { startThisActivity(ActivityRound6.class); break; }
                case R.id.start_round7: { startThisActivity(ActivityRound7.class); break; }
                case R.id.start_round8: { startThisActivity(ActivityRound8.class); break; }
                case R.id.start_round9: { startThisActivity(ActivityRound9.class); break; }
                case R.id.start_round10: { startThisActivity(ActivityRound10.class); break; }
            }
        };

        findViewById(R.id.start_round1).setOnClickListener(onClickListener);
        findViewById(R.id.start_round2).setOnClickListener(onClickListener);
        findViewById(R.id.start_round3).setOnClickListener(onClickListener);
        findViewById(R.id.start_round4).setOnClickListener(onClickListener);
        findViewById(R.id.start_round5).setOnClickListener(onClickListener);
        findViewById(R.id.start_round6).setOnClickListener(onClickListener);
        findViewById(R.id.start_round7).setOnClickListener(onClickListener);
        findViewById(R.id.start_round8).setOnClickListener(onClickListener);
        findViewById(R.id.start_round9).setOnClickListener(onClickListener);
        findViewById(R.id.start_round10).setOnClickListener(onClickListener);

        Bundle bundle = this.getIntent().getExtras();
        int round;

        if (bundle != null)
        {
            GameResult gameResult = (GameResult) bundle.getSerializable(GameResult.class.getName());
            newAverage = gameResult.avg();
            round = gameResult.round;
        }
        else
        {
            newAverage = 0L;
            round = 0;
        }

        switch (round)
        {
            case 0: { firstLoad(); break; }
            case 1: { refreshResults(R.id.result_round1, round); break; }
            case 2: { refreshResults(R.id.result_round2, round); break; }
            case 3: { refreshResults(R.id.result_round3, round); break; }
            case 4: { refreshResults(R.id.result_round4, round); break; }
            case 5: { refreshResults(R.id.result_round5, round); break; }
            case 6: { refreshResults(R.id.result_round6, round); break; }
            case 7: { refreshResults(R.id.result_round7, round); break; }
            case 8: { refreshResults(R.id.result_round8, round); break; }
            case 9: { refreshResults(R.id.result_round9, round); break; }
            case 10: { refreshResults(R.id.result_round10, round); break; }
        }
    }

    private void firstLoad()
    {
        int[] idResRound = new int[]{0, R.id.result_round1, R.id.result_round2, R.id.result_round3, R.id.result_round4,
                                        R.id.result_round5, R.id.result_round6, R.id.result_round7, R.id.result_round8,
                                        R.id.result_round9};
                                        R.id.result_round9, R.id.result_round10};

        for (int i = 1; i < idResRound.length; ++i)
        {
            if (getPreferences().getLong("round" + i, 0) != 0)
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
            FlashClass.getFlashClass(this);
        }

        if (loadAverage - newAverage >= 0  )
        {
            result_round.setText(String.format(Locale.getDefault(), "%d", newAverage));
            save(newAverage, round);
            FlashClass.getFlashClass(this);
        }
        else
            result_round.setText(String.format(Locale.getDefault(), "%d", loadAverage));

        firstLoad();
    }

    private void startThisActivity(Class<?> activityRoundNumberClass)
    {
        Intent intent = new Intent(RoundsActivity.this, activityRoundNumberClass);
        startActivity(intent);
        finish();
    }

    private long load(int round)
    {
        return getPreferences().getLong("round" + round, 0);
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

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
}