package com.moormic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.BLACK;


public class Board extends JPanel implements ActionListener {

    static final int BOARD_HEIGHT = 500;
    static final int BOARD_WIDTH = 500;

    private boolean gameRunning = false;
    private Timer timer;

    private Ball ball;

    public Board() {
        Image ballImage = new ImageIcon("src/main/resources/ball.png").getImage();

        ball = new Ball(ballImage, new Coordinate((BOARD_WIDTH / 2), (3 * BOARD_HEIGHT) / 5));
        timer = new Timer(140, this);

        initialise();
    }

    private void initialise() {
        // add key listener

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

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImages(g);
    }

    private void drawImages(Graphics g) {
        // bricks

        // paddle

        // ball
        g.drawImage(ball.getImage(), ball.getCoordinate().getX(), ball.getCoordinate().getY(), this);
    }
}
