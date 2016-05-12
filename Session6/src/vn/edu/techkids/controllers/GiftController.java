package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by Ngoc on 5/13/2016.
 */
public class GiftController extends SingleController implements Colliable {
    public GiftController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            Bullet.setDamge(2);
            this.getGameObject().setAlive(false);
        }
    }

    private static GiftController inst;
    public static GiftController getInst(){
        if(inst == null){
            GameObject gameObject = new GameObject(400,500,60,60);
            ImageDrawer imageDrawer = new ImageDrawer("resources/gift.png");
            inst = new GiftController(gameObject,imageDrawer);
        }
        return inst;
    }

    @Override
    public void paint(Graphics g) {
        if(this.getGameObject().isAlive()){
            super.paint(g);
        }
    }
}
