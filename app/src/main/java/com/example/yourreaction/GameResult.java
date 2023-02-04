package com.example.yourreaction;

import java.util.ArrayList;

public class GameResult
{
    public ArrayList<Long> times = new ArrayList<>();
    public int falseStarts = 0;

    public void clear()
    {
        times.clear();
        falseStarts = 0;
    }
}
