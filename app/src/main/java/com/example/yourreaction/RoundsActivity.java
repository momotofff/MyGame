package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class RoundsActivity extends AppCompatActivity
{
    long newAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);

        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.start_round1: { startThisActivity(ActivityRound1.class); break; }
                    case R.id.start_round2: { startThisActivity(ActivityRound2.class); break; }
                    case R.id.start_round3: { startThisActivity(ActivityRound3.class); break; }
                    case R.id.start_round4: { startThisActivity(ActivityRound4.class); break; }
                }
            }
        };

        findViewById(R.id.start_round1).setOnClickListener(onClickListener);
        findViewById(R.id.start_round2).setOnClickListener(onClickListener);
        findViewById(R.id.start_round3).setOnClickListener(onClickListener);
        findViewById(R.id.start_round4).setOnClickListener(onClickListener);

        Intent intent = getIntent();
        newAverage = intent.getLongExtra("avg", 0);

        switch (intent.getIntExtra("round", 0))
        {
            case 1: { refreshResults(R.id.result_round1); break; }
            case 2: { refreshResults(R.id.result_round2); break; }
            case 3: { refreshResults(R.id.result_round3); break; }
            case 4: { refreshResults(R.id.result_round4); break; }
        }
    }

    private void refreshResults(int viewId)
    {
        TextView result_round = findViewById(viewId);
        Long oldAverage = Long.MAX_VALUE;

        try
        {
            oldAverage = Long.parseLong(result_round.getText().toString());
        }
        catch (Exception ignored) {}

        if (oldAverage.compareTo(newAverage) > 0)
            result_round.setText(String.format(Locale.getDefault(), "%d", newAverage));
    }

    private void startThisActivity(Class<?> activityRoundNumberClass)
    {
        Intent intent = new Intent(RoundsActivity.this, activityRoundNumberClass);
        startActivity(intent);
    }

    private void load()
    {
        // TODO: Implement
    }
}