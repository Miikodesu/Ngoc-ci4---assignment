package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.views.ImageDrawer;

public class BulletController extends SingleController {

    public static final int SPEED = 15;

    public BulletController(Bullet gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }

    public void move1() {
        this.gameVector.dy = SPEED;
    }

    public void move2() {
        this.gameVector.dy = -SPEED;
    }
}
