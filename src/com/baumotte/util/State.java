package com.baumotte.util;

import java.awt.*;

public class State {

    public final double viewAngle;
    public final Dimension position;

    public State(double viewAngle, Dimension position) {
        this.viewAngle = viewAngle;
        this.position = position;
    }
}
