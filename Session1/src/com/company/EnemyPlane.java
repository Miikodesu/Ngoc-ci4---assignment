package com.company;

import java.awt.*;

/**
 * Created by Ngoc on 4/26/2016.
 */
public class EnemyPlane {
    private int x;
    private int y;

    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;

    private Image image;

    public EnemyPlane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void run() {
        y += 1;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
}
