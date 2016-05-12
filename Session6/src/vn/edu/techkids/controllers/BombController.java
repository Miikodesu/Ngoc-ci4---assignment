package vn.edu.techkids.controllers;

import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneControllerManager;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Ngoc on 5/13/2016.
 */
public class BombController extends SingleController implements Colliable {
    public BombController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    private static BombController inst;
    public static BombController getInst(){
        if(inst == null){
            GameObject gameObject = new GameObject(300,200,50,50);
            ImageDrawer imageDrawer = new ImageDrawer("resources/bomb.png");
            inst = new BombController(gameObject,imageDrawer);
        }
        return inst;
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController) {
            EnemyPlaneControllerManager enemyPlaneControllerManager = EnemyPlaneControllerManager.getInst();
            Vector<SingleController> enemyVector = enemyPlaneControllerManager.getSingleControllerVector();
            Iterator<SingleController> enemyIterator = enemyVector.iterator();
            while (enemyIterator.hasNext()) {
                SingleController singleController = enemyIterator.next();
                enemyIterator.remove();
            }
            this.getGameObject().setAlive(false);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.getGameObject().isAlive()) {
            super.paint(g);
        }
    }
}
