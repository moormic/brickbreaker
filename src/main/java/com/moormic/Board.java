package com.moormic;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.moormic.Direction.*;
import static java.awt.Color.*;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;

class Board extends JPanel implements ActionListener {

    private static final int DELAY = 50;
    private static final int BOARD_HEIGHT = 750;
    private static final int BOARD_WIDTH = 750;

    private boolean gameRunning = false;
    private Timer timer;
    private int score = 0;

    private Image ballImage;
    private Ball ball;
    private Paddle paddle;

    Board() {
        ballImage = new ImageIcon("src/main/resources/ball.png").getImage();

        ball = new Ball(
                new Coordinate((BOARD_WIDTH / 2), (3 * BOARD_HEIGHT) / 5),
                new Vector(0,1));
        paddle = new Paddle(
                new Coordinate(BOARD_WIDTH / 2, BOARD_HEIGHT - 75)
        );

        timer = new Timer(DELAY, this);
        initialise();
        startGame();
    }

    private void initialise() {
        addKeyListener(new LRKeyAdapter());

        setBackground(BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    private void startGame() {
        gameRunning = true;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            ball.move();
            checkWallCollision();
            checkPaddleHit();
            checkBrickHit();
            checkBallEscaped();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImages(g);
    }

    private void drawImages(Graphics g) {
        if (gameRunning) {
            // bricks

            // paddle
            g.setColor(WHITE);
            g.fillRect(
                    paddle.getCoordinate().getX() - (paddle.getWidth() / 2),
                    paddle.getCoordinate().getY(),
                    paddle.getWidth(),
                    paddle.getHeight());

            // ball
            g.setColor(GREEN);
            g.fillOval(
                    ball.getCoordinate().getX() - ball.getRadius(),
                    ball.getCoordinate().getY(),
                    ball.getRadius() * 2,
                    ball.getRadius() * 2);
        } else {
            gameOver(g);
        }

    }

    private void checkWallCollision() {
        final int xCoord = ball.getCoordinate().getX();
        final int yCoord = ball.getCoordinate().getY();

        if (xCoord <= 0) {
            ball.deflect(RIGHT);
        } else if (xCoord >= BOARD_WIDTH) {
            ball.deflect(LEFT);
        } else if (yCoord <=0) {
            ball.deflect(DOWN);
        }
    }

    private void checkPaddleHit() {
        final int ballX = ball.getCoordinate().getX();
        final int ballY = ball.getCoordinate().getY();
        final Integer paddleLeftEdge = paddle.getCoordinate().getX() - (paddle.getWidth()/2);
        final Integer paddleRightEdge = paddle.getCoordinate().getX() + (paddle.getWidth()/2);
        final Integer paddleUpperEdge = paddle.getCoordinate().getY() - (paddle.getHeight()/2);
        final Integer paddleBottomEdge = paddle.getCoordinate().getY() + (paddle.getHeight()/2);

        Range<Integer> xRange = Range.range(paddleLeftEdge, BoundType.CLOSED, paddleRightEdge, BoundType.CLOSED);
        Range<Integer> yRange = Range.range(paddleUpperEdge, BoundType.CLOSED, paddleBottomEdge, BoundType.CLOSED);

        if (xRange.contains(ballX) && yRange.contains(ballY)) {
            // TODO: take into account the movement of the paddle as well
            ball.deflect(UP);
        }
    }

    private void checkBrickHit() {

    }

    private void checkBallEscaped() {
        if (ball.getCoordinate().getY() >= BOARD_HEIGHT) {
            gameRunning = false;
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, (BOARD_HEIGHT - 25) / 2);
        g.drawString(String.format("Score: %d", score), (BOARD_WIDTH- metr.stringWidth(msg)) / 2, (BOARD_HEIGHT + 25) / 2);
    }

    private class LRKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case VK_LEFT: paddle.move(LEFT); break;
                case VK_RIGHT: paddle.move(RIGHT); break;
            }
        }
    }

}
