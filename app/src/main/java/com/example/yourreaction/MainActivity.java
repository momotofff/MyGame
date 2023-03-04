package com.example.yourreaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    final String CHECKED = "CHECKED";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();

        findViewById(R.id.startGame).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                save();
                Intent intent = new Intent(MainActivity.this, RoundsActivity.class);
                startActivity(intent);
            }
        });
    }

    private SharedPreferences getPreferences()
    {
        String name = getApplicationContext().getPackageName();
        return getSharedPreferences(name, MODE_PRIVATE);
    }

    private void save()
    {
        SharedPreferences.Editor editor = getPreferences().edit();

        CheckBox startCheckBox = findViewById(R.id.startedCheckBox);
        editor.putBoolean(CHECKED, startCheckBox.isChecked());
        editor.apply();
    }

    private void load()
    {
        SharedPreferences preferences = getPreferences();
        boolean check = preferences.getBoolean(CHECKED, false);

        if (check)
        {
            Intent intent = new Intent(MainActivity.this, RoundsActivity.class);
            startActivity(intent);
        }
    }
}