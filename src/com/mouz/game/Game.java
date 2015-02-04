package com.mouz.game;

import com.mouz.level.Level;
import com.mouz.level.LevelGenerator;
import com.mouz.level.Round;
import com.mouz.model.Circle;
import com.mouz.model.TimeBar;
import com.mouz.mouse.MousePointChecker;
import com.mouz.time.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by fabriece on 31.01.2015.
 */
public class Game extends JPanel implements MousePointChecker.TargetListener, MousePointChecker.WrongTargetListener, Timer.TimeListener{
    public static final int GAME_WIDTH = 400;
    public static final int GAME_HEIGHT = 300;

    private TimeBar timeBar;

    private MousePointChecker mousePointChecker;

    private Level level;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private Timer timer;
    private Thread thread;

    private JFrame frame;
    private int correct = 0;

    private boolean gameOver = false;

    public Game(JFrame frame) {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.frame = frame;

        init();
        addMouseMotionListener(mousePointChecker);

    }

    void init(){
        correct = 0;
        timeBar = new TimeBar();
        level = new Level();
        circles.clear();
        circles.add(new Circle());

        mousePointChecker = new MousePointChecker(circles);
        mousePointChecker.addTargetListener(this);
        mousePointChecker.addWrongTargetListener(this);
        timer = new Timer();
        timer.addTimeListener(this);

        thread = new Thread(timer);
    }
    void start(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOver){
            return;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        timeBar.drawTimeBar(g2d);
        for (Circle c : circles){
            c.drawCircle(g2d);
        }
    }

    @Override
    public void onTarget() {
        correct++;
        System.out.println("on target");
        timeBar.setScore(correct);
        LevelGenerator.generateLevel(circles, level);
        if (!timer.isRunning) {
            thread.start();
        }

        repaint();
    }

    @Override
    public void wrongTarget() {
        System.out.println("Wrong!");
        gameOver();
        showPane();

    }


    @Override
    public void timeUpdate(int time) {
        double timeWidth = (time * GAME_WIDTH)/ Timer.MAX_TIME;
        timeBar.setTimeWidth((int) timeWidth);
        timeBar.setTime(time);
        repaint();
    }

    @Override
    public void timeOut() {
        System.out.println("timeout: ");
        gameOver();
        showPane();
    }

    private void showPane(){
        int an = JOptionPane.showConfirmDialog(this, "Your Score is: " + correct + "\nDo you want to try again?", "RESTART", JOptionPane.YES_NO_OPTION);
        if (an == JOptionPane.NO_OPTION){
            System.exit(0);
        }

        init();
        gameOver = false;
        timer.isRunning = false;
        this.setEnabled(true);
        this.setVisible(true);
        repaint();
    }

    public void gameOver(){
        timer.isAlive = false;
        thread.interrupt();
        gameOver = true;
        this.setEnabled(false);
        this.setVisible(false);

    }
}
