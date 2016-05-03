package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EnemyPlaneController extends SingleController {
    private Timer t = new Timer(1000, new ShotTimer());
    private final int HEIGHT_BACKGROUND = 500;

    private final int BULLET_MAX_COUNT = 5;
    private final int SPEED = 1;

    private Thread thread = new Thread();
    private Vector<BulletController> bulletControllerVector = new Vector<BulletController>();

    public EnemyPlaneController(EnemyPlane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }
    public Vector<BulletController> getBullets(){
        return bulletControllerVector;
    }
    public void shot() {
        if (this.gameObject.getY() < 505) {
            Bullet bullet = new Bullet(
                    gameObject.getX() + gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 2,
                    gameObject.getY() + Bullet.DEFAULT_HEIGHT ,
                    Bullet.DEFAULT_WIDTH ,
                    Bullet.DEFAULT_HEIGHT
            );
            ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
            BulletController bulletController = new BulletController(bullet, imageDrawer);
            bulletController.move1();
            this.bulletControllerVector.add(bulletController);
            for (int i = 0; i < bulletControllerVector.size(); i++)
                if (bulletControllerVector.get(i).gameObject.getY() > 505) this.bulletControllerVector.remove(i);
        }
        for (int i = 0; i < bulletControllerVector.size(); i++)
            if (bulletControllerVector.get(i).gameObject.getY() > 505) this.bulletControllerVector.remove(i);
    }

    public static EnemyPlaneController enemyPlaneController;

    public static EnemyPlaneController getEnemyPlaneController() {
        if (enemyPlaneController == null) {
            EnemyPlane enemyPlane = new EnemyPlane(5, 5, 30, 40);
            ImageDrawer imageDrawer = new ImageDrawer("resources/plane1.png");
            enemyPlaneController = new EnemyPlaneController(enemyPlane, imageDrawer);

        }
        return enemyPlaneController;
    }

    public void move() {
        this.gameVector.dy = SPEED;
    }

    @Override
    public void run() {
        if (!t.isRunning()) {
            t.start();


        }
        super.run();
        for (BulletController bulletController : this.bulletControllerVector) {
            bulletController.run();
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (BulletController bulletController : this.bulletControllerVector) {
            bulletController.paint(g);
        }
    }

    private class ShotTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            shot();
        }
    }
}
//day