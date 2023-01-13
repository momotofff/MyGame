package com.example.yourreaction;

public class TimeCounter
{
    long start;
    long end;

    public void start() {
        this.start = System.currentTimeMillis();
    }
    public void finish() { this.end = System.currentTimeMillis(); }
    public long getLeadTime() { return end - start; }
    public void reset() { this.start = 0; this.end = 0; }
}
