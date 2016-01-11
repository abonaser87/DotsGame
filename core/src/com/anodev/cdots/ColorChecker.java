package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

/**
 * Created by abdullah on 1/12/16.
 */
public class ColorChecker {
    Array<Color> colors = new Array<Color>();
    Color temp;

    public ColorChecker(Color temp) {
        this.temp = temp;
    }

    public void isMatching(Color color) {
        System.out.println(colors.size);
        temp = color;
        colors.add(color);
        if (colors.size != 0) {
            if (temp.equals(colors.get(0))) {
                System.out.println("Matching");
            } else {


                System.out.println("Not Matching");
            }
        }

    }
}
