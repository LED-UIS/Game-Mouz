package com.mouz.mouse;

import com.mouz.game.Game;
import com.mouz.model.Circle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by fabriece on 31.01.2015.
 */
public class MousePointChecker extends MouseMotionAdapter{


    public interface TargetListener{
        public void onTarget();
    }

    public interface WrongTargetListener{
        public void wrongTarget();
    }



    public ArrayList<TargetListener> targetListeners = new ArrayList<TargetListener>();
    public ArrayList<WrongTargetListener> wrongTargetListeners = new ArrayList<WrongTargetListener>();
    public ArrayList<Circle> circles;

    public MousePointChecker(ArrayList<Circle> circles) {
        this.circles = circles;
    }

    public void addTargetListener(TargetListener listener){
        targetListeners.add(listener);
    }

    public void addWrongTargetListener(WrongTargetListener listener){
        wrongTargetListeners.add(listener);
    }

    public void removeListener(TargetListener listener) {
        for (TargetListener t : targetListeners){
            if (t == listener){
                targetListeners.remove(listener);
                break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println(e.getPoint());
        Point p = e.getPoint();
        for (Circle c : circles){
            if (!c.isInterceptMouse()){
                continue;
            }
            if (c.contains(p)){
                if (c.isTarget()){
                    for (TargetListener l : targetListeners){
                        l.onTarget();
                    }
                }else{
                    for (WrongTargetListener l : wrongTargetListeners){
                        l.wrongTarget();
                    }
                }
                break;
            }
        }
    }
}
