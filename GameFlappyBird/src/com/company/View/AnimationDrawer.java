package com.company.View;

import com.company.Models.GameConfig;
import com.company.Models.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Ngoc on 5/15/2016.
 */
public class AnimationDrawer implements GameDrawer {
    private Vector<Image> imageVector;
    private int imageIdx = 0;
    private int count = 0;

    public AnimationDrawer(String[] imageUrls) {
        imageVector = new Vector<Image>();
        for(String imageUrl : imageUrls) {
            try {
                Image image = ImageIO.read(new File(imageUrl));
                imageVector.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public AnimationDrawer(Image[] images){
        this.imageVector = new Vector<Image>();
        for(Image image:images){
            this.imageVector.add(image);
        }
    }
    @Override
    public void paint(GameObject gameObject, Graphics g) {
        Image image = imageVector.get(imageIdx);
        g.drawImage(image,
                gameObject.getX(), gameObject.getY(),
                gameObject.getWidth(), gameObject.getHeight(),
                null);
        count++;
        if (GameConfig.getInst().durationInMiliseconds(count) >= 50) {
            count = 0;
            imageIdx++;
            if(imageIdx >= imageVector.size()) {
                imageIdx = 0;
            }
        }
    }
}
