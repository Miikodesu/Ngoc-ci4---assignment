package vn.edu.techkids;
/* TODO packaage exanplation */

import vn.edu.techkids.controllers.*;
import vn.edu.techkids.models.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by qhuydtvt on 4/24/2016.
 */
public class GameWindow extends Frame implements Runnable {
    Image backgroundImage;
    Thread thread;
    Image backbufferImage;
    PlaneController planeController1;
    GameConfig gameConfig;

    public GameWindow () {
        this.gameConfig = GameConfig.getInst();

        this.setVisible(true);
        this.setSize(gameConfig.getScreenWidth(),
                gameConfig.getScreenHeight());

        this.planeController1 = PlaneController.getPlaneController1();


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
                /*TODO static explanation*/
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
        this.addMouseMotionListener(new MouseMotionListener(){

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

        if(backbufferImage == null){
            backbufferImage =  new BufferedImage(gameConfig.getScreenWidth(),
                    gameConfig.getScreenHeight(), 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        backbufferGraphics.drawImage(backgroundImage, 0, 0,
                gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);

        planeController1.paint(backbufferGraphics);
        EnemyPlaneControllerManager.getInst().paint(backbufferGraphics);


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

                planeController1.run();
                EnemyPlaneControllerManager.getInst().run();
                EnemyPlaneControllerManager.getInst().run2();


                repaint();


                Thread.sleep(gameConfig.getThreadDelay());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
