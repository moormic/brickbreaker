package com.moormic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Color.BLACK;

public class Board extends JPanel implements ActionListener {

    private static final int DELAY = 75;
    static final int BOARD_HEIGHT = 750;
    static final int BOARD_WIDTH = 750;

    private boolean gameRunning = false;
    private Timer timer;
    private int score = 0;

    private Ball ball;

    public Board() {
        Image ballImage = new ImageIcon("src/main/resources/ball.png").getImage();

        ball = new Ball(ballImage, new Coordinate((BOARD_WIDTH / 2), (3 * BOARD_HEIGHT) / 5));
        timer = new Timer(DELAY, this);

        initialise();
        startGame();
    }

    private void initialise() {
        // add key listener
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

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
            checkCollision();
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

            // ball
            g.drawImage(ball.getImage(), ball.getCoordinate().getX(), ball.getCoordinate().getY(), this);
        } else {
            gameOver(g);
        }

    }

    private void checkCollision() {

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

}
