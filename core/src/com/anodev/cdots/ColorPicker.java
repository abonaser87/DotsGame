package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by abdullah on 1/29/16.
 */
public class ColorPicker {
    private Array<Color> colors = new Array<Color>();
    private Color tempColor;
    private Random rand = new Random();
    private int tempRand;
    private int oldrow=1;
    public ColorPicker() {
        addColors();
    }

    private void addColors() {
        colors.add(Color.valueOf("#4d5b73ff"));
        colors.add(Color.valueOf("#8a3c42ff"));
        colors.add(Color.valueOf("#a39b8fff"));
        colors.add(Color.valueOf("#545859ff"));
    }
    public Color getRandColor(int coloumns, int row) {
        if(oldrow == row){
            if (colors.size < 1) {
                addColors();
            }
            reduceColors(coloumns);
            tempRand = rand.nextInt(colors.size);
            tempColor = colors.get(tempRand);
            colors.removeIndex(tempRand);
            return tempColor;
        }else{
            addColors();
            reduceColors(coloumns);
            oldrow=row;
            tempRand = rand.nextInt(colors.size);
            tempColor = colors.get(tempRand);
            colors.removeIndex(tempRand);
            return tempColor;
        }
    }

    private void reduceColors(int coloumns) {
        if (colors.size != coloumns-2) {
            for (int size = colors.size-1; size >= coloumns - 1; size--) {
                colors.removeIndex(size);
            }
        }
    }
}
