package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.EnemyBulletController;
import vn.edu.techkids.controllers.EnemyBulletType;

/**
 * Created by Ngoc on 5/13/2016.
 */
public class EnemyDiagonalShotBehavior implements EnemyShotBehavior {
    @Override
    public EnemyBulletController doShot(int x, int y) {
        return EnemyBulletController.createEnemyType(EnemyBulletType.DIAGONAL, x, y);
    }
}
