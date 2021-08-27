package com.kscm.heapdump.analysis.mat;

import java.util.Calendar;
import java.util.HashMap;


public class CalendarStore extends Thread
{
    static HashMap<String, Calendar> calMap = new HashMap<String, Calendar>();

    public void run()
    {
        for(int i = 0; i < 1000000; i++) {
            Calendar cal = Calendar.getInstance();
            calMap.put(cal.toString(), cal);
        }

        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
