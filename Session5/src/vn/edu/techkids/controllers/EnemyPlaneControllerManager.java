package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyPlaneControllerManager extends ControllerManager {

    private int count = 0;
    private int sleep = 0;

    private EnemyPlaneControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        sleep++;
        if (GameConfig.getInst().durationInSeconds(count) > 4 && GameConfig.getInst().durationInSeconds(sleep) >4 && GameConfig.getInst().durationInSeconds(sleep) < 6) {
            count = 0;
            for (int x = 40; x < GameConfig.getInst().getScreenWidth() - 40; x += 100) {
                EnemyPlane enemyPlane = new EnemyPlane(x, 0, 32, 32);
                ImageDrawer imageDrawer =
                        new ImageDrawer("resources/plane1.png");
                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                        enemyPlane,
                        imageDrawer
                );
                this.singleControllerVector.add(enemyPlaneController);
            }
        }
    }

    public void run2() {
        super.run();
        count++;
        sleep++;
        if (GameConfig.getInst().durationInSeconds(count) > 4 && GameConfig.getInst().durationInSeconds(sleep) > 6) {
            sleep = 0;
            count = 0;
            for (int x = 4; x < GameConfig.getInst().getScreenWidth() - 40; x += 100) {
                EnemyPlane enemyPlane = new EnemyPlane(x, 0, 32, 32);
                ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_plane_white_1.png");
                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(enemyPlane, imageDrawer);
                enemyPlaneController.gameVector.dx = 2;
                EnemyBulletController.dx = -5;
                this.singleControllerVector.add(enemyPlaneController);
            }
        }
    }

    private static EnemyPlaneControllerManager inst;

    public static EnemyPlaneControllerManager getInst() {
        if (inst == null) {
            inst = new EnemyPlaneControllerManager();
        }

        return inst;
    }
}
