package com.mouz.model;

import com.mouz.game.Game;

import java.awt.*;
import java.awt.geom.*;

/**
 * Created by fabriece on 31.01.2015.
 */
public class Circle implements Shape {
    public static final double Radius = 10;
    private double radius;
    private Color color;
    private Point2D center;
    private boolean visible;
    private boolean isTarget;
    private boolean interceptMouse = true;

    public Circle(){
        radius = 20;
        color = Color.WHITE;
        center = new Point2D.Double(Game.GAME_WIDTH/2, Game.GAME_HEIGHT/2);
        visible = true;
        isTarget = true;
    }

    public Circle(double radius, Color color, Point2D center, boolean visible, boolean isTarget) {
        this.radius = radius;
        this.color = color;
        this.center = center;
        this.visible = visible;
        this.isTarget = isTarget;
    }

    public void setInterceptMouse(boolean interceptMouse) {
        this.interceptMouse = interceptMouse;
    }

    public boolean isInterceptMouse() {
        return interceptMouse;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setIsTarget(boolean isTarget) {
        this.isTarget = isTarget;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public void drawCircle(Graphics2D g){
        if (!visible){
            return;
        }
        Rectangle bounds = getBounds();
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        g.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(color);

        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public Rectangle getBounds() {
        int x = (int) (center.getX() - radius);
        int y = (int) (center.getY() - radius);
        int width = (int) (radius * 2); // same for height
        return new Rectangle(x, y, width, width);
    }

    @Override
    public Rectangle2D getBounds2D() {
        double x = center.getX() - radius;
        double y = center.getY() - radius;
        double width = radius * 2; // same for height
        return new Rectangle2D.Double(x, y, width, width);
    }

    @Override
    public boolean contains(double x, double y) {

        return new Point((int)x, (int)y).distance(center) < radius;
    }

    @Override
    public boolean contains(Point2D p) {
        return p.distance(center) < radius;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return new Area(this).intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return new Area(this).intersects(r);
    }

    public boolean intersects(Circle c){
        return (c.center.distance(this.center)) < radius * 2;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return contains(x, y) && contains(x + w, y) && contains(x, y + h) && contains(x + w, y + h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return null;
    }
}
