package com.company;

import com.company.Controller.BirdController;
import com.company.Controller.BirdDirection;
import com.company.Controller.enemycontroller.ChimneyControllerManager;
import com.company.Controller.CollisionPool;
import com.company.Models.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Tran Tuan An on 5/11/2016.
 */
public class GameWindow extends Frame implements Runnable{

    Image backgroundImage;
    Thread thread;
    Image backbufferImage;
    BirdController birdController;
    GameConfig gameConfig;
    int count = 0;

    public GameWindow () {
        this.gameConfig = GameConfig.getInst();

        this.setVisible(true);
        this.setSize(gameConfig.getScreenWidth(),
                gameConfig.getScreenHeight());

        this.birdController = BirdController.getBirdController();


        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);

            }

            @Override
            public void windowClosed(WindowEvent e) {

                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
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
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("keyPressed");
//                System.out.println(e.getKeyCode());

                BirdDirection birdDirection = BirdDirection.NONE;
                switch (e.getKeyCode()) {
//
                    case KeyEvent.VK_SPACE:
                        birdDirection = BirdDirection.SPACE;
                        break;
                }
                birdController.move(birdDirection);
                /*TODO static explanation*/
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                System.out.println("keyReleased");

                BirdDirection birdDirection = BirdDirection.NONE;
//                switch (e.getKeyCode()) {
//                    case KeyEvent.VK_UP:
//                    case KeyEvent.VK_DOWN:
//                        planeDirection = PlaneDirection.STOP_Y;
//                        break;
//                    case KeyEvent.VK_LEFT:
//                    case KeyEvent.VK_RIGHT:
//                        planeDirection = PlaneDirection.STOP_X;
//                        break;
//                }
                birdController.move(birdDirection);
            }
        });
        this.addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                dx2 = e.getX();
//                dy2 = e.getY();
//x2 => e.getX(); y2 => e.getY()

//                if(e.getX() - 5 > x2) {
//                    dx2 = 5;
//                } else if(e.getX() +5 < x2) {
//                    dx2 = -5;
//                } else {
//                    dx2 = 0;
//                }
                // System.out.println("mouseMoved");
            }
        });
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void update(Graphics g) {

        if(backbufferImage == null){
            backbufferImage =  new BufferedImage(gameConfig.getScreenWidth(),
                    gameConfig.getScreenHeight(), 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        backbufferGraphics.drawImage(backgroundImage, 0, 0, gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);
        if (birdController.getGameObject().isAlive())
            birdController.paint(backbufferGraphics);
        ChimneyControllerManager.getInst().paint(backbufferGraphics);


        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        long count = 0;

        while(true){

            try {

                Point mousePoint = MouseInfo.getPointerInfo().getLocation();

                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;

                /* TODO player2 moving */
//                if(mousePoint.x - 5 > plane2.x) {
//                    plane2.dx = 5;
//                } else if(mousePoint.x + 5 < plane2.x) {
//                    plane2.dx = -5;
//                } else {
//                    plane2.dx = 0;
//                }
//
//                if(mousePoint.y - 5 > plane2.y) {
//                    plane2.dy = 5;
//                } else if(mousePoint.y + 5 < plane2.y) {
//                    plane2.dy = -5;
//                } else {
//                    plane2.dy = 0;
//                }
                //               plane2.run();

                CollisionPool.getInst().run();

                birdController.run();
                ChimneyControllerManager.getInst().run();


                repaint();

                Thread.sleep(gameConfig.getThreadDelay());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
