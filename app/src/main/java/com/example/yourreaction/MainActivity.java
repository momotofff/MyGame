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
    SharedPreferences sharedPreferences;
    CheckBox startCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        load();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGame = findViewById(R.id.startGame);
        startCheckBox = findViewById(R.id.startedCheckBox);



        startGame.setOnClickListener(new View.OnClickListener()
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

    private void save()
    {
        sharedPreferences = getSharedPreferences("SaveGame", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CHECKED, startCheckBox.isChecked());
        editor.apply();
    }

    private void load()
    {
        sharedPreferences = getSharedPreferences("SaveGame", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean(CHECKED, false);

        if(check)
        {
            Intent intent = new Intent(MainActivity.this, RoundsActivity.class);
            startActivity(intent);
        }
    }


}