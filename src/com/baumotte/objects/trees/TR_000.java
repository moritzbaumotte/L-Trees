package com.baumotte.objects.trees;

import java.awt.*;

public class TR_000 extends Tree {

    public TR_000(Dimension position) {
        super("[--------A]B", position);
    }

    @Override
    public void update() {
        StringBuffer next = new StringBuffer();

        for(int i = 0; i < super.current.length(); i++){
            char c = super.current.charAt(i);

            switch(c){
                case 'A':
                    next.append("A[-A+A][+A-A]");
                    break;
                case 'B':
                    next.append("F-[[B]+BC]+F[+FBC]-B");
                    break;
                case 'C':
                    next.append("[--D++DD++D][++D--DD--D]");
                    break;
                case 'G':
                    next.append("[H][--H][----H][------H][--------H][----------H][------------H]--------------H");
                    break;
                case 'H':
                    next.append("[--E++EE++E][++E--EE--E]");
                    break;
                case 'F':
                    next.append("FF");
                    break;
                default:
                    next.append(c);
            }
        }

        super.current = next.toString();
    }

}
