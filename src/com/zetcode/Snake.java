package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Snake {

    private Image head;
    private Image body;
    private Direction direction = Direction.RIGHT;

    private int nOfDots = 3;

    public Snake() {

        initSnake();
    }

    private void initSnake() {

        loadImages();
    }

    private void loadImages() {

        var iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();

        var iid = new ImageIcon("src/resources/dot.png");
        body = iid.getImage();
    }

    public void grow() {

        nOfDots++;
    }

    public Image getHead() {

        return head;
    }

    public Image getBody() {

        return body;
    }

    public int size() {

        return nOfDots;
    }

    public Direction getDirection() {

        return direction;
    }

    public void setDirection(Direction direction) {

        this.direction = direction;
    }
}
