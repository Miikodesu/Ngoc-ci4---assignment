package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ngoc on 4/25/2016.
 */
public class Plane {
    public int x;
    public int y;
    public int dx;
    public int dy;

    public final int WIDTH = 70;
    public final int HEIGHT = 70;

    Bullet bullet;

    private Image image;

    public Plane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y,WIDTH,HEIGHT, null);
        if (bullet != null) {
            bullet.panit(g);
        }
    }

    public void run() {
        x += dx;
        y += dy;
        if (bullet != null) {
            bullet.run();
        }
    }

    public void run2(){
        y += 5;
    }

    public void move(Movement movement) {
        if (movement.dx > 0) {
            dx = 5;
        } else if (movement.dx < 0) {
            dx = -5;
        } else dx = 0;
        if (movement.dy > 0) {
            dy = 5;
        } else if (movement.dy < 0) {
            dy = -5;
        } else dy = 0;

    }

//    public void setImage(Image image) {
//        if(image != null && this.image == null) {
//            this.image = image;
//        }
//    }

    public Image getImage() {
        return image;
    }

    public void shot() {
        try {
            this.bullet = new Bullet(this.x+WIDTH/2 - Bullet.WIDTH/2, this.y, ImageIO.read(new File("resources/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
