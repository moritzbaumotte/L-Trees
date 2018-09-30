package com.baumotte.view;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private Dimension windowSize;

    public Display(String title, Dimension windowSize) {
        this.title = title;
        this.windowSize = windowSize;
        init();
    }

    private void init(){
        frame = new JFrame(title);
        frame.setSize(windowSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(windowSize);
        canvas.setMaximumSize(windowSize);
        canvas.setMinimumSize(windowSize);
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
