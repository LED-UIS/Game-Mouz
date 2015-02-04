package com.mouz;

import com.mouz.game.Game;
import com.mouz.menu.MenuPanel;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	    JFrame frame = new JFrame("MOUZ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        showOnScreen(1, frame);
        frame.add(new Game(frame));
        //frame.add(new MenuPanel());
        //frame.setSize(Game.GAME_WIDTH+10, Game.GAME_HEIGHT+10);
        frame.pack();
        frame.setVisible(true);
    }

    public static void showOnScreen( int screen, JFrame frame ) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        if( screen > -1 && screen < gd.length ) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().width/2, frame.getY()/2);
        } else if( gd.length > 0 ) {
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().width/2, frame.getY()/2);
        } else {
            throw new RuntimeException( "No Screens Found" );
        }
    }
}
