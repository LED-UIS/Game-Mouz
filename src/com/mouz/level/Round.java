package com.mouz.level;

import sun.misc.ConditionLock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fabriece on 31.01.2015.
 */
public class Round{
    public static final int timeReduction = 250; // millisekunder
    public static final int maxRounds = 5;
    public static final int MAX_TIME = 2000; // millis
    public static final HashMap<Integer, Integer> timeMap = new HashMap<Integer, Integer>();
    private int roundNr;
    private volatile int remaingTime;
    private volatile boolean isRunning = false;
    private Lock lock = new ReentrantLock(true);

    static {
        timeMap.put(1, 4000);
        timeMap.put(2, 3500);
        timeMap.put(3, 3000);
        timeMap.put(4, 3000);
        timeMap.put(5, 3000);
    }


    public Round(int num) {
        roundNr = num;
    }

    /*public interface TimeListener{
        public void timeUpdate(int time);
        public void timeOut();
    }

    public ArrayList<TimeListener> listeners = new ArrayList<TimeListener>();

    public void addTimeListener(TimeListener listener){
        listeners.add(listener);
    }

    public void startRound(){
        isRunning = true;
        lock.lock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                remaingTime = timeMap.get(roundNr);
                while(remaingTime > 0 && isRunning){
                    remaingTime -= 10;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                lock.unlock();
            }
        }).start();
    }*/
}
