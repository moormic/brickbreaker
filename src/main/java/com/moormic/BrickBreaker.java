package com.moormic;

import javax.swing.*;
import java.awt.*;

public class BrickBreaker extends JFrame {

    private BrickBreaker() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setResizable(false);
        pack();

        setTitle("BrickBreaker");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame game = new BrickBreaker();
            game.setVisible(true);
        });
    }

}
