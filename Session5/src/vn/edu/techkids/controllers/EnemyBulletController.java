package vn.edu.techkids.controllers;


import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {

    public static int dame;
    public static int dx = 0;

    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 5;
        this.gameVector.dx = dx;
        CollisionPool.getInst().add(this);
    }

    public void setDame(int dame) {
        EnemyPlaneController.dame = dame;
    }

    @Override
    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController) {
            Plane plane = (Plane) c.getGameObject();
            plane.decreaseHP();
            if (plane.getHp() <= 0) {
                plane.setAlive(false);
            }
        }
    }
}
