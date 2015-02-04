package com.mouz.time;

import java.util.ArrayList;

/**
 * Created by fabriece on 31.01.2015.
 */
public class Timer implements Runnable{
    public static int MAX_TIME = 20000;

    public volatile int remainingTime = MAX_TIME;
    public volatile boolean isRunning = false;
    public volatile boolean isAlive = false;

    public interface TimeListener{
        public void timeUpdate(int time);
        public void timeOut();
    }

    public ArrayList<TimeListener> listeners = new ArrayList<TimeListener>();

    public void addTimeListener(TimeListener listener){
        listeners.add(listener);
    }

    @Override
    public void run() {
        isRunning = true;
        isAlive = true;
        while(remainingTime > 0 && isAlive){
            remainingTime -= 10;
            for (TimeListener l : listeners){
                l.timeUpdate(remainingTime);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(TimeListener l : listeners){
            l.timeOut();
        }
    }
}
