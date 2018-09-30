package com.baumotte.objects.trees;

import java.awt.*;

public abstract class Tree {
    public String current;
    public Dimension position;
    public final Dimension finalPosition;

    protected Tree(String current, Dimension position) {
        this.current = current;
        this.position = position;
        this.finalPosition = new Dimension(this.position.width, this.position.height);
    }

    public abstract void update();

}
