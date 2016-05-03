package vn.edu.techkids.controllers;


import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;
import java.util.Vector;

public class PlaneController extends SingleController {

    public final int SPEED = 10;
    public final int MAX_BULLET_COUNT = 3;
    public static boolean dead;
    private Vector<BulletController> bulletControllerVector;

    private PlaneController(Plane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        bulletControllerVector = new Vector<BulletController>();
    }

    public Vector<BulletController> getBulletControllerVector() {
        return bulletControllerVector;
    }

    public void setBulletControllerVector(Vector<BulletController> bulletControllerVector) {
        this.bulletControllerVector = bulletControllerVector;
    }

    public void move(PlaneDirection planeDirection) {
        switch (planeDirection) {
            case NONE:
                break;
            case UP:
                this.gameVector.dy = -SPEED;
                break;
            case DOWN:
                this.gameVector.dy = SPEED;
                break;
            case LEFT:
                this.gameVector.dx = -SPEED;
                break;
            case RIGHT:
                this.gameVector.dx = SPEED;
                break;
            case STOP_X:
                this.gameVector.dx = 0;
                break;
            case STOP_Y:
                this.gameVector.dy = 0;
                break;
        }
    }

    public void shot() {
        if(bulletControllerVector.size() < MAX_BULLET_COUNT) {
            Bullet bullet = new Bullet(
                    this.gameObject.getX() + this.gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 2,
                    this.gameObject.getY(),
                    Bullet.DEFAULT_WIDTH,
                    Bullet.DEFAULT_HEIGHT
            );
            ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
            BulletController bulletController = new BulletController(
                    bullet,
                    imageDrawer
            );
            bulletController.move2();
            this.bulletControllerVector.add(bulletController);
            for(int i=0 ; i<bulletControllerVector.size() ; i++){
                if(bulletControllerVector.get(i).gameObject.getY()<0) this.bulletControllerVector.remove(i);
            }
        }
        for(int i=0 ; i<bulletControllerVector.size() ; i++){
            if(bulletControllerVector.get(i).gameObject.getY()<0) this.bulletControllerVector.remove(i);
        }
    }


    private static PlaneController planeController1;
    public static PlaneController getPlaneController1() {
        if(planeController1 == null) {
            Plane plane = new Plane(100, 500, 70, 60);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane4.png");
            planeController1 = new PlaneController(plane, planeDrawer);
        }
        return planeController1;
    }

    @Override
    public void run() {
        super.run();
        for(BulletController bulletController : this.bulletControllerVector) {
            bulletController.run();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(BulletController bulletController : this.bulletControllerVector) {
            bulletController.paint(g);
        }
    }
    public void destroy(){
        if(gameObject.isDead() == false) this.gameObject = null;
    }
    public void setDead(boolean dead){
        this.gameObject.setDead(dead);
    }
    public boolean getDead(){
        return dead;
    }
    /* TODO: Work on the second plane */

}
