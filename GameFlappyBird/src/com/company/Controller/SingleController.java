package com.company.Controller;

import com.company.Models.GameObject;
import com.company.Models.GameVector;
import com.company.View.GameDrawer;

import java.awt.*;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class SingleController implements Controller {

    protected GameObject gameObject;
    protected GameDrawer gameDrawer;
    protected GameVector gameVector;

    public SingleController(GameObject gameObject, GameDrawer gameDrawer) {
        this.gameObject = gameObject;
        this.gameDrawer = gameDrawer;
        this.gameVector = new GameVector();
    }

    @Override
    public void run() {
        gameObject.move(gameVector);
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    @Override
    public void paint(Graphics g) {
        gameDrawer.paint(this.gameObject, g);
    }
}
