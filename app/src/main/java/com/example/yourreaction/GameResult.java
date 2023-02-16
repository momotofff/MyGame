package com.example.yourreaction;
import java.util.ArrayList;
import java.util.Collections;

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
        Collections.sort(times);

        long result = 0;

        for (int i = 1; i < times.size() - 1; ++i)
        {
            result += (long) times.get(i);
        }
        return result / (times.size() - 2);
    }

    public long min() { return times.get(1);}
    public long max() { return times.get(times.size() - 2);}
}
