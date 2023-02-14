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

    public long avg(ArrayList<Long> times )
    {
        long result = 0;

        for (int i = 1; i < times.size() - 1; ++i)
        {
            result += (long) times.get(i);
        }
        return result / 8;
    }

    public long min() { return times.get(1);}
    public long max() { return times.get(times.size() - 1);}
}
