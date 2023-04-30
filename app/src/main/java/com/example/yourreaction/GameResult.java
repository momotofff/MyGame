package com.example.yourreaction;
import android.app.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class GameResult implements Serializable
{
    public ArrayList<Long> times = new ArrayList<>();
    public int falseStarts = 0;
    public int round;
    public Class<?> caller;

    public void clear()
    {
        times.clear();
        falseStarts = 0;
    }

    public long avg()
    {
        if (!isValid())
            return 0;

        long result = 0;

        for (int i = 0; i < times.size(); ++i)
            result += (long) times.get(i);


        result -= Collections.min(times);
        result -= Collections.max(times);

        return result / (times.size() - 2);
    }

    public long min()
    {
        if (!isValid())
            return 0;

        Collections.sort(times);
        return times.get(1);
    }

    public long max()
    {
        if (!isValid())
            return 0;

        Collections.sort(times);
        return times.get(times.size() - 2);
    }

    private boolean isValid()
    {
        return times.size() > 2;
    }
}
