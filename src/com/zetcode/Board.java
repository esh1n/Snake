package com.zetcode;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

class MyPoint extends Point {

    public MyPoint(int x, int y) {
        super(x, y);
    }

    public int deltaX = 0;
    public int deltaY = 0;
    //this.point = (5,3) newPoint = (5,4)
    public Direction getHorizontalDirectionTo(MyPoint point) {
        if (point.x == x) {
            return Direction.NO_DIRECTION;
        } else if (point.x > x) {
            return Direction.RIGHT;
        } else {
            return Direction.LEFT;
        }
    }

    public Direction getVerticalDirectionTo(MyPoint point) {
        if (point.y == y) {
            return Direction.NO_DIRECTION;
        } else if (point.y > y) {
            return Direction.DOWN;
        } else {
            return Direction.UP;
        }
    }

    public boolean isCollide(MyPoint point) {
        return x == point.x && y == point.y;
    }

    private Pair<Direction, Direction> calculateFollowerDirection(MyPoint next) {
        return new Pair<>(getHorizontalDirectionTo(next), getVerticalDirectionTo(next));
    }
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

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        initGame();
    }

    private void initGame() {

        snake = new Snake();
        apple = new Apple();
        anim = new Animation();
        //TODO make random initialization
        for (int y = 0; y < ALL_DOTS; y++) {

            p[y] = new MyPoint(0, 0);
        }

        for (int z = 0; z < snake.size(); z++) {

            p[z] = new MyPoint(50 - z * DOT_SIZE, 50);
        }

        locateApple();

        timer = new Timer(PERIOD, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (inGame) {

            drawApple(g);
            drawSnake(g);

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }
    }

    private void drawSnake(Graphics g) {

        for (int z = 0; z < snake.size(); z++) {
            var image = z == 0 ? snake.getHead() : snake.getBody();
            g.drawImage(image, p[z].x, p[z].y, this);
        }
    }

    private void drawApple(Graphics g) {
        g.drawImage(apple.getApple(), apple.getX(), apple.getY(), this);
    }

    private void gameOver(Graphics g) {

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        var msg = "Game Over";
        var smallFont = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = getFontMetrics(smallFont);

        g2d.setColor(Color.white);
        g2d.setFont(smallFont);
        g2d.drawString(msg, (BOARD_WIDTH -
                fontMetrics.stringWidth(msg)) / 2, BOARD_HEIGHT / 2);
    }

    private void checkApple() {

        MyPoint head = p[0];
        if (head.isCollide(apple.getPoint())) {

            Point last = p[snake.size() - 1];

            MyPoint newPoint = p[snake.size()];
            //TODO
            //TODO 1 - direction beforePrev to Prev
            //TODO 2 - funtion return MyPoint: x = last.x + DOT_SIZE *  HORIZONTAL_DIRECTION
            //TODO 3 y = last.y + DOT_SIZE *  VERTICAL_DIRECTION
            if (snake.getDirection() == Direction.RIGHT
                    || snake.getDirection() == Direction.LEFT) {

                newPoint.x = last.x;
                newPoint.y = last.y - DOT_SIZE;

            } else {
                newPoint.x = last.x - DOT_SIZE;
                newPoint.y = last.y;
            }

            snake.grow();
            locateApple();
        }
    }

//    private MyPoint calculateNewTail(){
//        Point beforeLast = p[snake.size()];
//        Point last = p[snake.size() - 1];
//    (5,3) (5,4)
//     1
//     2
//     3
//     4
//
//    }


    private void move() {

        if (!anim.finished()) {
            anim.inc();

            //TODO make move -вынести в функцию
            for (int z = 0; z < snake.size(); z++) {
                //TODO apply смещение
                p[z].translate(p[z].deltaX, p[z].deltaY);
            }
        } else {

            anim.reset();

            MyPoint head = p[0];
            locateHead(head, snake.getDirection());

            for (int z = 0; z < snake.size(); z++) {
                MyPoint next = p[z + 1];
                MyPoint prev = p[z];
                var horizontalDirection = next.getHorizontalDirectionTo(prev);
                var verticalDirection = next.getVerticalDirectionTo(prev);
                next.deltaX = horizontalDirection.getDelta();
                next.deltaY = verticalDirection.getDelta();
            }
        }
    }

    private void locateHead(MyPoint head, Direction direction) {
        Direction hDirection = direction.isHorizontal() ? direction : Direction.NO_DIRECTION;
        Direction vDirection = direction.isVertical() ? direction : Direction.NO_DIRECTION;
        head.deltaX = hDirection.getDelta();
        head.deltaY = vDirection.getDelta();
    }

    private void checkCollision() {

        for (int z = snake.size(); z > 0; z--) {
            //TODO use funtion check collizion checkCollizion(head,element)
            if (z > 4 && p[0].x == p[z].x && p[0].y == p[z].y) {

                inGame = false;
                break;
            }
        }
        //TODO вынести всю функцию - столкнулись ли с головой

        if (p[0].y >= BOARD_HEIGHT) {

            inGame = false;
        }

        if (p[0].y < 0) {

            inGame = false;
        }

        if (p[0].x >= BOARD_WIDTH) {

            inGame = false;
        }

        if (p[0].x < 0) {

            inGame = false;
        }

        //TODO не вышли за переделы доски

        if (!inGame) {

            timer.stop();
        }
    }

    private void locateApple() {
        //TODO вынести функцию создания координат и распологать яблоко по координате MyPoint
        var random = new Random();
        int r = random.nextInt(RAND_POS);
        apple.setX(r * DOT_SIZE);
        r = random.nextInt(RAND_POS);
        apple.setY(r * DOT_SIZE);
    }

    private void doGameCycle() {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
//            keyDirection = IsHorizontal
//            snake.getDirection().isHorizontal()
//            boolean shallDriveNext = IsHorizontal xor horizontal;
//            ||, and, xor
            if (key == KeyEvent.VK_LEFT && snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }

            if (key == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.LEFT) {

                snake.setDirection(Direction.RIGHT);
            }

            if (key == KeyEvent.VK_UP &&
                    snake.getDirection() != Direction.DOWN) {

                snake.setDirection(Direction.UP);
            }

            if (key == KeyEvent.VK_DOWN &&
                    snake.getDirection() != Direction.UP) {

                snake.setDirection(Direction.DOWN);
            }
        }
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }
}
