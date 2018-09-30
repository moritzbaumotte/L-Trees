package com.baumotte.objects.trees;

import java.awt.*;

public class Flower extends Tree {

    public Flower(Dimension position) {
        super("[A][--A][----A][------A][--------A][----------A][------------A]--------------A", position);
    }

    @Override
    public void update() {
        StringBuffer next = new StringBuffer();

        for(int i = 0; i < current.length(); i++){
            char c = current.charAt(i);

            switch(c){
                case 'A':
                    next.append("[--E++EE++E][++E--EE--E]");
                    break;
                case 'E':
                    next.append("EE");
                default:
                    next.append(c);
            }
        }
        current = next.toString();
    }

}
