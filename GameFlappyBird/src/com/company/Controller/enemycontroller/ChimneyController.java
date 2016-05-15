package com.company.Controller.enemycontroller;

import com.company.Controller.BirdController;
import com.company.Controller.Colliable;
import com.company.Controller.CollisionPool;
import com.company.Controller.SingleController;
import com.company.Models.Enemy;
import com.company.Models.GameObject;
import com.company.Models.GameVector;
import com.company.View.GameDrawer;
import com.company.View.ImageDrawer;

import java.util.Vector;

/**
 * Created by Tran Tuan An on 5/11/2016.
 */
public class ChimneyController extends SingleController implements Colliable {

    public ChimneyController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dx = -1;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        if (gameObject.getX() < 0 - Enemy.DEFAULT_CHIMNEY_WIDTH){
            gameObject.setAlive(false);
        }
    }

    @Override
    public void onColliable(Colliable c) {
        if (c instanceof BirdController){
            c.getGameObject().setAlive(false);
        }
    }

//    private static ChimneyController chimneyController;
//    public static ChimneyController getChimneyController() {
//        if(chimneyController == null) {
//            Enemy enemy = new Enemy(100, 500, 70, 60);
//            ImageDrawer enemyDrawer = new ImageDrawer("resources/plane4.png");
//            chimneyController = new ChimneyController(enemy, enemyDrawer);
//        }
//        return chimneyController;
//    }
}
