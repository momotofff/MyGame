package com.example.yourreaction;

public class TimeCounter
{
    long start;
    long end;

    public void start() { start = System.currentTimeMillis();}
    public void finish() { end = System.currentTimeMillis(); }
    public long getLeadTime() { return end - start; }
    public void reset() { start = 0; end = 0; }
}
