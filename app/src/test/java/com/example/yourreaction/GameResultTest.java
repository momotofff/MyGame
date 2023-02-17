package com.example.yourreaction;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameResultTest {
    @Test
    public void getValues_ValidData_ExpectCorrect()
    {
        GameResult result = new GameResult();

        result.times.add(1L);
        result.times.add(130L);
        result.times.add(120L);
        result.times.add(110L);
        result.times.add(1000L);

        assertEquals(result.min(), 110L);
        assertEquals(result.max(), 130L);
        assertEquals(result.avg(), 120L);
    }

    @Test
    public void getValues_NoData_ExpectCorrect()
    {
        GameResult result = new GameResult();
        assertEquals(result.avg(), 0L);
        assertEquals(result.min(), 0L);
        assertEquals(result.max(), 0L);
    }
}