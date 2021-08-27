package com.kscm.heapdump.analysis.mat;

public class StringStore
{
    static String str = "";
    public static void main (String[] args) throws Exception
    {
        CalendarStore calendarStore = new CalendarStore();
        calendarStore.start();
        for(int i = 0; i < 1000000; i++)
        {
            str += "a";
        }
        Thread.sleep(10000000);
    }
}

