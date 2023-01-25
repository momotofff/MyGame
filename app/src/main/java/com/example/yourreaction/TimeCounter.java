package com.example.yourreaction;

public class TimeCounter
{
    static long start;
    static long end;

    public static void start() {
        start = System.currentTimeMillis();
    }
    public static void finish() { end = System.currentTimeMillis(); }
    public static long getLeadTime() { return end - start; }
    public static void reset() { start = 0; end = 0; }
}
