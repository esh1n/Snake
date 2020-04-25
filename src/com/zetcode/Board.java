package com.zetcode;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.Timer;

class MyPoint extends Point {

    public MyPoint(int x, int y) {
        super(x, y);
    }

    public int deltaX = 0;
    public int deltaY = 0;
}

public class Board extends JPanel {

    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;

    private final int FPS = 60;
    private final int PERIOD = 1000 / FPS;

    private final MyPoint p[] = new MyPoint[ALL_DOTS];

    private boolean inGame = true;

    private Timer timer;

    private Snake snake;
    private Apple apple;

    private Animation anim;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
