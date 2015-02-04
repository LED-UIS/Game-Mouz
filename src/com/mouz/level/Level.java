package com.mouz.level;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fabriece on 31.01.2015.
 */
public class Level {
    public static final int INITIAL_CIRCLE_NUM = 4;
    public static final int INCREASE_CIRCLE_NUM = 2; //number of circleNum to increase per level increase.
    private Round[] rounds;
    private int circleNum;
    private int level;
    public int currentRound;
    public boolean alive = false;

    public Level() {
        currentRound = 0;
        rounds = new Round[Round.maxRounds];
        for (int i = 0, time = Round.MAX_TIME; i < Round.maxRounds; i++, time -= Round.timeReduction){
            rounds[i] = new Round(i + 1);
        }

        circleNum = INITIAL_CIRCLE_NUM;
        level = 1;
    }

    public Level(int circleNum, int levelNr){
        this();
        this.circleNum = circleNum;
        level = levelNr;

    }

    public int getCircleNum() {
        return circleNum;
    }



    public int getLevel() {
        return level;
    }

    public Round[] getRounds() {
        return Arrays.copyOf(rounds, rounds.length);
    }
}
