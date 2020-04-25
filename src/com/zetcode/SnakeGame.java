package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Java Sname game
 *
 * Author: Jan Bodnar
 * Website: http://zetcode.com
 */

public class SnakeGame extends JFrame {

    public SnakeGame() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var game = new SnakeGame();
            game.setVisible(true);
        });
    }
}
