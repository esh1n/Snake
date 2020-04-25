package com.zetcode;

import javax.swing.*;
import java.awt.*;



public class Board extends JPanel {

    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;

    private final int FPS = 60;
    private final int PERIOD = 1000 / FPS;

    private boolean inGame = true;

    private Timer timer;


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
