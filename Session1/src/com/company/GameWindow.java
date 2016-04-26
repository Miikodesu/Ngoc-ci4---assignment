package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ngoc on 4/24/2016.
 */
public class GameWindow extends Frame implements Runnable {
    Image backgroundImage;
    Plane plane1;
    Plane plane2;
    EnemyPlane enemyPlane;

    Thread thread;
    Image backbufferImage;

    public GameWindow() {
        this.setVisible(true);//hien or an
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);

        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
            plane1 = new Plane(100, 500, ImageIO.read(new File("resources/plane4.png")));
            plane2 = new Plane(200, 500, ImageIO.read(new File("resources/plane4.png")));
            enemyPlane = new EnemyPlane(100,0,ImageIO.read(new File("resources/plane1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                System.out.println("windowClosed");

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

//        this.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent mouseEvent) {
//                System.out.println("mouseClicked");
//            }
//
//            @Override
//            public void mousePressed(MouseEvent mouseEvent) {
//                System.out.println("mousePressed");
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent mouseEvent) {
//                System.out.println("mouseReleased");
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent mouseEvent) {
//                System.out.println("mouseEntered");
//            }
//
//            @Override
//            public void mouseExited(MouseEvent mouseEvent) {
//                System.out.println("mouseExited");
//            }
//        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
//                dx1 = mouseEvent.getX();
//                dy1 = mouseEvent.getY();
////                x1 => e.getX()
//                if(mouseEvent.getX() - 2*5 > x1){
//                    dx1 = 5;
//                } else if(mouseEvent.getX() + 2*5 < x1){
//                    dx1 = -5;
//                } else dx1 = 0;
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        plane1.dy = -5;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        plane1.dy = 5;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        plane1.dx = -5;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        plane1.dx = 5;
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        plane1.shot();
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        plane1.dy = 0;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        plane1.dy = 0;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        plane1.dx = 0;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        plane1.dx = 0;
                        break;
                    }
                }
            }
        });

        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void update(Graphics graphics) {
//        super.update(graphics);
        if (backbufferImage == null) {
            backbufferImage = new BufferedImage(400, 600, 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        backbufferGraphics.drawImage(backgroundImage, 0, 0, null);//0,0 la toa do cua anh. 0 0 la goc tren ben trai
        plane1.paint(backbufferGraphics);
        plane2.paint(backbufferGraphics);
        enemyPlane.paint(backbufferGraphics);
        graphics.drawImage(backbufferImage, 0, 0, null);//0,0 la toa do cua hinh.
    }

    //back buffer dung de ve lai ca hinh
    //lam mot may bay nua bat su kien chuot.
    @Override
    public void run() {
        while (true) {
            repaint();
            Point mousePoint = MouseInfo.getPointerInfo().getLocation();

            mousePoint.x -= getLocationOnScreen().x;
            mousePoint.y -= getLocationOnScreen().y;

            if (mousePoint.x - 5 > plane2.x) {
                plane2.dx = 5;
            } else if (mousePoint.x + 5 < plane2.x) {
                plane2.dx = -5;
            } else {
                plane2.dx = 0;
            }

            if (mousePoint.y - 5 > plane2.y) {
                plane2.dy = 5;
            } else if (mousePoint.y + 5 < plane2.y) {
                plane2.dy = -5;
            } else {
                plane2.dy = 0;
            }
            plane1.run();
            plane2.run();
            enemyPlane.run();

            if (plane1.x < -30) plane1.x = -30;
            if (plane1.y < 31) plane1.y = 31;
            if (plane1.x > 360) plane1.x = 360;
            if (plane1.y > 538) plane1.y = 538;
            try {
                thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
