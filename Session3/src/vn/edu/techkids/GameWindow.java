package vn.edu.techkids;
/* TODO packaage exanplation */

import vn.edu.techkids.controllers.BulletController;
import vn.edu.techkids.controllers.EnemyPlaneController;
import vn.edu.techkids.controllers.PlaneController;
import vn.edu.techkids.controllers.PlaneDirection;
import vn.edu.techkids.models.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameWindow extends Frame implements Runnable {
    Image backgroundImage;
    Thread thread;
    Image backbufferImage;
    PlaneController planeController1;
    EnemyPlaneController enemyPlaneController;
    Vector<BulletController> enemyBullets, yourBullets;

    public GameWindow() {
        this.setVisible(true);
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);

        this.planeController1 = PlaneController.getPlaneController1();

        this.enemyPlaneController = EnemyPlaneController.getEnemyPlaneController();

        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                PlaneDirection planeDirection = PlaneDirection.NONE;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        planeDirection = PlaneDirection.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        planeDirection = PlaneDirection.DOWN;
                        break;
                    case KeyEvent.VK_LEFT:
                        planeDirection = PlaneDirection.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        planeDirection = PlaneDirection.RIGHT;
                        break;
                    case KeyEvent.VK_SPACE:
                        planeController1.shot();
                        break;
                }

                planeController1.move(planeDirection);
            }

            @Override
            public void keyReleased(KeyEvent e) {

                PlaneDirection planeDirection = PlaneDirection.NONE;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        planeDirection = PlaneDirection.STOP_Y;
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        planeDirection = PlaneDirection.STOP_X;
                        break;
                }
                planeController1.move(planeDirection);
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void update(Graphics g) {

        if (backbufferImage == null) {
            backbufferImage = new BufferedImage(400, 600, 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        backbufferGraphics.drawImage(backgroundImage, 0, 0, null);

        planeController1.paint(backbufferGraphics);
        enemyPlaneController.paint(backbufferGraphics);

        g.drawImage(backbufferImage, 0, 0, null);
        enemyBullets = enemyPlaneController.getBullets();
//        System.out.println(enemyBullets.size()+"TETS");
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (enemyBullets.get(i).getRect().intersects(planeController1.getRect())) {
                System.out.println("You Loser");
            }
        }
        yourBullets = planeController1.getBulletControllerVector();
        for (int i = 0; i < yourBullets.size(); i++)
        if (yourBullets.get(i).getRect().intersects(enemyPlaneController.getRect())) {
            System.out.println("Enemy Plane die");
        }
        if(enemyPlaneController.getRect().intersects(planeController1.getRect())) System.out.println("You Loser");
    }

    @Override
    public void run() {
        while (true) {
            try {

                Point mousePoint = MouseInfo.getPointerInfo().getLocation();

                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;
                planeController1.run();
                // enemyPlaneController.shot();
                enemyPlaneController.move();
                enemyPlaneController.run();


                repaint();


                Thread.sleep(17);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
