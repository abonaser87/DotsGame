package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by abdullah on 1/29/16.
 */
public class ColorPicker {
    private Array<Color> colors = new Array<Color>();
    private Random rand = new Random();

    public ColorPicker() {
        colors.add(Color.valueOf("#4d5b73ff"));
        colors.add(Color.valueOf("#8a3c42ff"));
        colors.add(Color.valueOf("#a39b8fff"));
        colors.add(Color.valueOf("#545859ff"));
    }

    public Color getRandColor(int coloumns) {
        return colors.get(rand.nextInt(coloumns - 1));
    }
}
