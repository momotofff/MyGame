package com.example.yourreaction;

public class TimerClass
{
    private static int[] timeSleep ;

    private TimerClass()
    {
        timeSleep = new int[10];
        for (int i = 0; i < timeSleep.length; ++i)
        {
            timeSleep[i] = (int) (Math.random() * 3000);
        }
    }

    public static int[] getTimer()
    {
        TimerClass instance = new TimerClass();
        return timeSleep;
    }
}
