package vn.edu.techkids.models;

public class Bullet extends GameObject {
    public static final int DEFAULT_WIDTH = 13;
    public static final int DEFAULT_HEIGHT = 33;
    private static int damge = 1;

    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public static void setDamge(int damge1) {
        damge = damge1;
    }


}
