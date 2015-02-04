package com.mouz.model;

import com.mouz.game.Game;

import java.awt.*;

/**
 * Created by fabriece on 31.01.2015.
 */
public class TimeBar {
    public static final int HEIGHT = 50;
    public int timeWidth;
    private int time = 0;
    private int score = 0;

    public TimeBar(){
       timeWidth = Game.GAME_WIDTH;
    }

    public void setTimeWidth(int timeWidth) {
        if (timeWidth > Game.GAME_WIDTH){
            this.timeWidth = Game.GAME_WIDTH;
            return;
        }
        this.timeWidth = timeWidth;
    }

    public void setTime(int time){
        this.time = time;
    }

    public void drawTimeBar(Graphics2D g){
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, Game.GAME_WIDTH, HEIGHT);
        g.setColor(new Color(0, 255, 255, 50));
        g.fillRect(0, 0, timeWidth, HEIGHT);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Time: " + (time/1000), Game.GAME_WIDTH/3, HEIGHT/1.50f);
        g.drawString("score: " + score, Game.GAME_WIDTH * 0.75f, HEIGHT/1.50f);
    }

    public void setScore(int score) {
        this.score = score;
    }
}
