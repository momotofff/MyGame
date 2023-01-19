package com.example.yourreaction;

public class PlayingField
{
    final private int WidthHeightArea = 5;
    int[][] winPosition = new int[10][2];


    PlayingField()
    {
        for (int i = 0; i < winPosition.length; ++i)
        {
            for (int j = 0; j < winPosition[i].length; ++j)
            {
                winPosition[i][j] = (int) (Math.random() * WidthHeightArea);
            }
        }
    }
}
