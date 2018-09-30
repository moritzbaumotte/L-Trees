package com.baumotte;

import com.baumotte.ctrl.Game;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Game game = new Game("L-Systems", new Dimension(800, 600));
        game.start();

    }

}
