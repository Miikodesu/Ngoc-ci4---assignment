package com.company;

import java.awt.*;

/**
 * Created by Ngoc on 4/25/2016.
 */
public class Bullet {
    private int x;
    private int y;

    public static final int WIDTH = 12;
    public static final int HEIGHT = 33;

    private Image image;

    public Bullet(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void run() {
        y -= 5;
    }

    public void panit(Graphics graphics) {
        graphics.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
}

