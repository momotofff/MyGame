package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class RoundsActivity extends AppCompatActivity
{
    Button round1, round2, round3, round4;
    TextView result_round1, result_round2, result_round3, result_round4;
    Intent intent;
    long newAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);

        intent = getIntent();
        round1 = findViewById(R.id.start_round1);   result_round1 = findViewById(R.id.result_round1);
        round2 = findViewById(R.id.start_round2);   result_round2 = findViewById(R.id.result_round2);
        round3 = findViewById(R.id.start_round3);   result_round3 = findViewById(R.id.result_round3);
        round4 = findViewById(R.id.start_round4);   result_round4 = findViewById(R.id.result_round4);

        newAverage = intent.getLongExtra("avg", 0);

        switch (getIntent().getIntExtra("round", 0))
        {
            case 1: { refreshResults(result_round1); break;}
            case 2: { refreshResults(result_round2); break;}
            case 3: { refreshResults(result_round3); break;}
            case 4: { refreshResults(result_round4); break;}
        }

        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.start_round1: { startThisActivity(ActivityRound1.class); break;}
                    case R.id.start_round2: { startThisActivity(ActivityRound2.class); break;}
                    case R.id.start_round3: { startThisActivity(ActivityRound3.class); break;}
                    case R.id.start_round4: { startThisActivity(ActivityRound4.class); break;}
                }
            }
        };

        round1.setOnClickListener(onClickListener);
        round2.setOnClickListener(onClickListener);
        round3.setOnClickListener(onClickListener);
        round4.setOnClickListener(onClickListener);
    }

    private void refreshResults(TextView result_round)
    {
        Long oldAverage = Long.MAX_VALUE;

        try
        {
            oldAverage = Long.parseLong(result_round.getText().toString());
        }
        catch (Exception ignored) {}

        if (oldAverage.compareTo(newAverage) > 0)
            result_round.setText(String.format(Locale.getDefault(), "%d", newAverage));
    }

    private void startThisActivity(Class activityRoundNumberClass)
    {
        Intent intent = new Intent(RoundsActivity.this, activityRoundNumberClass);
        startActivity(intent);
    }

    private void load()
    {

    }
}