package com.example.yourreaction;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //тут надо написать код который будет хавать результаты прошлых игр
        //короче говоря учетная запись игрока, для того что бы улучшать свои результаты
        //было бы не плохо написать новый класс save для сохранения прогресса и данных игрока

        Button startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, RoundsActivity.class);
                startActivity(intent);
            }
        });
    }
}