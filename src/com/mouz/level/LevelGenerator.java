package com.mouz.level;

import com.mouz.game.Game;
import com.mouz.model.Circle;
import com.mouz.model.TimeBar;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fabriece on 31.01.2015.
 */
public class LevelGenerator {
    public static int startY = 10;

    private static final Random randRad = new Random(), randY = new Random();

    static Rectangle2D.Double gameBounds;
    static {
        gameBounds = new Rectangle2D.Double(Circle.Radius, TimeBar.HEIGHT + Circle.Radius, Game.GAME_WIDTH - Circle.Radius * 2, Game.GAME_HEIGHT - Circle.Radius - (TimeBar.HEIGHT + Circle.Radius));
    }

    /**
     *
     * @param circles list with circles to update or list to fill with new circles
     * @param l level
     * @return
     */
    public static void generateLevel(ArrayList<Circle> circles, Level l){
        Circle old = circles.remove(0);
        old.setInterceptMouse(false);
        Color color = new Color(randRad.nextFloat(), randRad.nextFloat(), randRad.nextFloat());
        old.setColor(color);
        old.setIsTarget(false);

        //remove others
        circles.clear();

        //make target
        Circle target = makeCircle(color);
        /*while (!isCircleClear(circles, target)){
            target = makeCircle(color);
        }*/
        target.setIsTarget(true);
        circles.add(target);

        //re-add old in scond place
        circles.add(old);
        for (int i = 2; i < l.getCircleNum(); i++) {
            Circle c = makeCircle();
            while (!isCircleClear(circles, c)){
                c = makeCircle();
            }
            circles.add(makeCircle());
            //check that color doesn't collide with target color.
        }
    }

    private static Circle makeCircle(Color color) {
        return new Circle(Circle.Radius, color, makePoint(), true, false);
    }

    private static Circle makeCircle() {
        Color color = new Color(randRad.nextFloat(), randRad.nextFloat(), randRad.nextFloat());

        return new Circle(Circle.Radius, color, makePoint(), true, false);
    }

    private static Point2D makePoint(){
        Point2D.Double p = new Point2D.Double(randRad.nextDouble() * Game.GAME_WIDTH, randRad.nextDouble() * Game.GAME_HEIGHT + TimeBar.HEIGHT + Circle.Radius);
        while(!gameBounds.contains(p)){
            p = new Point2D.Double(randRad.nextDouble() * Game.GAME_WIDTH, randRad.nextDouble() * Game.GAME_HEIGHT + TimeBar.HEIGHT + Circle.Radius);
        }
        return p;
    }

    private static boolean isCircleClear(ArrayList<Circle> circles, Circle circle){
        for (Circle c : circles){
            if (c.intersects(circle)){
                return false;
            }
        }

        return true;
    }
    /*private static Circle targetCircle(Circle circle) {

    }


    private static Circle generateRandomCircle() {

        return new Circle(5, );
    }

    private static Circle[] generateTargetCircle(){

    }

    private static Circle startCircle(){
        Color color =
        return new Circle(Circle.Radius, );
    }*/
}
