package com.baumotte.ctrl;

//import com.baumotte.objects.trees.Flower;
import com.baumotte.util.State;
import com.baumotte.objects.trees.TR_000;
import com.baumotte.objects.trees.Tree;
import com.baumotte.view.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game implements Runnable {

    private Thread thread;
    private boolean running = false;

    private Display display;
    public final Dimension windowSize;
    public final String title;

    private BufferStrategy bufferStrategy;
    private Graphics g;

    private final ArrayList<Tree> trees;

    private double viewAngle;
    private final double ANGLE_CHANGE = 22.5 * Math.PI / 180;
    private final int STEP_SIZE_ADF = 5;
    private final int STEP_SIZE_E = 3;
    private final int STEP_SIZE_f = 5;

    private final ArrayList<State> stack;

    private int generation = 0;

    public Game(String title, Dimension windowSize){
        this.windowSize = windowSize;
        this.title = title;
        this.trees = new ArrayList<>();
        this.stack = new ArrayList<>();
    }

    /**update game data**/
    private void tick(){
        for(Tree tree : trees) {
            tree.update();
            tree.position = new Dimension(tree.finalPosition.width, tree.finalPosition.height);
        }
    }

    /**render updated game data**/
    private void render() throws InterruptedException {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if(bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            bufferStrategy = display.getCanvas().getBufferStrategy();
        }
        g = bufferStrategy.getDrawGraphics();
        /**clear screen**/
        g.clearRect(0,0, windowSize.width, windowSize.height);
        g.drawString("Generation: " + ++generation, 0, 12);

        g.setColor(Color.GREEN);
        g.fillOval(200, 450, 10, 10);

        /**drawing**/
        Dimension oldPosition;
        for(Tree tree : trees) {
            viewAngle = 270 * Math.PI / 180;
            for (int i = 0; i < tree.current.length(); i++)
                switch (tree.current.charAt(i)) {
                    case 'A':
                        oldPosition = new Dimension(tree.position.width, tree.position.height);
                        tree.position.width = tree.position.width + (int) (STEP_SIZE_ADF * Math.cos((double) viewAngle));
                        tree.position.height = tree.position.height + (int) (STEP_SIZE_ADF * Math.sin((double) viewAngle));
                        g.setColor(Color.BLACK);
                        g.drawLine(oldPosition.width, oldPosition.height, tree.position.width, tree.position.height);
                        break;
                    case 'F':
                        oldPosition = new Dimension(tree.position.width, tree.position.height);
                        tree.position.width = tree.position.width + (int) (STEP_SIZE_ADF * Math.cos((double) viewAngle));
                        tree.position.height = tree.position.height + (int) (STEP_SIZE_ADF * Math.sin((double) viewAngle));
                        g.setColor(Color.BLACK);
                        g.drawLine(oldPosition.width, oldPosition.height, tree.position.width, tree.position.height);
                        break;
                    case 'D':
                        oldPosition = new Dimension(tree.position.width, tree.position.height);
                        tree.position.width = tree.position.width + (int) (STEP_SIZE_ADF * Math.cos((double) viewAngle));
                        tree.position.height = tree.position.height + (int) (STEP_SIZE_ADF * Math.sin((double) viewAngle));
                        g.setColor(Color.GREEN);
                        g.drawLine(oldPosition.width, oldPosition.height, tree.position.width, tree.position.height);
                        break;
                    case 'E':
                        oldPosition = new Dimension(tree.position.width, tree.position.height);
                        tree.position.width = tree.position.width + (int) (STEP_SIZE_E * Math.cos((double) viewAngle));
                        tree.position.height = tree.position.height + (int) (STEP_SIZE_E * Math.sin((double) viewAngle));
                        g.setColor(Color.RED);
                        g.drawLine(oldPosition.width, oldPosition.height, tree.position.width, tree.position.height);
                        break;
                    case 'f':
                        tree.position.width = tree.position.width + (int) (STEP_SIZE_f * Math.cos((double) viewAngle));
                        tree.position.height = tree.position.height + (int) (STEP_SIZE_f * Math.sin((double) viewAngle));
                        break;
                    case '+':
                        viewAngle -= ANGLE_CHANGE;
                        break;
                    case '-':
                        viewAngle += ANGLE_CHANGE;
                        break;
                    case '[':
                        stack.add(new State(viewAngle, new Dimension(tree.position.width, tree.position.height)));
                        break;
                    case ']':
                        tree.position = stack.get(stack.size() - 1).position;
                        viewAngle = stack.get(stack.size() - 1).viewAngle;
                        stack.remove(stack.get(stack.size() - 1));
                        break;
                    default:

                }
        }
        /**end drawing**/
        bufferStrategy.show();
        g.dispose();
    }

    public void run() {
        init();

        while(running){
            try {
                Thread.sleep(2000);
                tick();
                render();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stop();

    }

    private void init(){
        display = new Display(title, windowSize);
        trees.add(new TR_000(new Dimension(200,450)));
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
