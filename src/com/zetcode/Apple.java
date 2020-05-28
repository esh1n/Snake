package com.zetcode;

import javax.swing.*;
import java.awt.*;

public class Apple {

    private int x;
    private int y;
    private Image apple;

    public Apple() {

        loadImage();
    }

    private void loadImage() {

        var iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();
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

    public MyPoint getPoint() {
        return new MyPoint(x, y);
    }

    public Image getApple() {

        return apple;
    }

    public void setApple(Image apple) {

        this.apple = apple;
    }
}
