package com.company.Controller;

import com.company.Models.GameObject;
import com.company.View.GameDrawer;

import java.awt.*;

/**
 * Created by Tran Tuan An on 5/11/2016.
 */
public class BulletController extends SingleController implements Colliable{
    public BulletController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    @Override
    public void onColliable(Colliable c) {

    }
}
