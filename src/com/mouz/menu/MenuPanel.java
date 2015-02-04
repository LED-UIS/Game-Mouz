package com.mouz.menu;

import com.mouz.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fabriece on 31.01.2015.
 */
public class MenuPanel extends JPanel{

    Rectangle start;
    public MenuPanel() {
        setPreferredSize(new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2 - (getWidth()/4/2), getHeight()/2 - (getHeight()/4/2));
        g2d.drawRect(0, 0, getWidth() / 4, getHeight() / 4);
        g2d.drawString("START", getWidth()/12 , getHeight() / 8);
        g2d.translate(0,0);
    }
}
