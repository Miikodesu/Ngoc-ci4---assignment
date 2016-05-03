package vn.edu.techkids.views;

import vn.edu.techkids.models.GameObject;

import java.awt.*;

public interface GameDrawer {
    void paint(GameObject gameObject, Graphics g);
}
