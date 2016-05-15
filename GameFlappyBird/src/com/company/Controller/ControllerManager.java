package com.company.Controller;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Tran Tuan An on 5/11/2016.
 */
public class ControllerManager implements Controller {
    protected Vector<SingleController> singleControllerVector;

    public ControllerManager() {
        this.singleControllerVector = new Vector<>();
    }

    public void add(SingleController singleController){
        singleControllerVector.add(singleController);
    }

    public int size(){
        return singleControllerVector.size();
    }

    @Override
    public void run() {
        Iterator<SingleController> iterator = singleControllerVector.iterator();
        while (iterator.hasNext()){
            SingleController singleController = iterator.next();
            if (!singleController.getGameObject().isAlive()){
                iterator.remove();
            }else {
                singleController.run();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        for (SingleController singleController:singleControllerVector){
            singleController.paint(g);
        }
    }
}
