package com.example.yourreaction;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
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

        long newAverage = intent.getLongExtra("avg", 0);

        switch (getIntent().getIntExtra("round", 0))
        {
            case 1:
            {
                Long oldAverage = Long.MAX_VALUE;
                try
                {
                    oldAverage = Long.parseLong(result_round1.getText().toString());
                }
                catch (Exception ignored) {}

                if (oldAverage.compareTo(newAverage) > 0)
                    result_round1.setText(String.format(Locale.getDefault(), "%d", newAverage));

                break;
            }
            case 2:
            case 3:
            case 4:
            {
                result_round1.setText(String.format(Locale.getDefault(),"%d", newAverage));
                break;
            }
        }

        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.start_round1: {
                        Intent intent = new Intent(RoundsActivity.this, ActivityRound1.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.start_round2: {
                        Intent intent = new Intent(RoundsActivity.this, ActivityRound2.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.start_round3: {
                        Intent intent = new Intent(RoundsActivity.this, ActivityRound3.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.start_round4: {
                        Intent intent = new Intent(RoundsActivity.this, ActivityRound4.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        };



        round1.setOnClickListener(onClickListener);
        round2.setOnClickListener(onClickListener);
        round3.setOnClickListener(onClickListener);
        round4.setOnClickListener(onClickListener);
    }
}