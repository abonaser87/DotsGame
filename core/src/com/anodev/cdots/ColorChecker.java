package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

/**
 * Created by abdullah on 1/12/16.
 */
public class ColorChecker {
    private static final Array<Color> colors = new Array<Color>();
    private static final Array<Vector2> postions = new Array<Vector2>();
    private static final DelayedRemovalArray<LineShape> lines = new DelayedRemovalArray<LineShape>();
    private static LineShape connecter;
    private static Color mainColor;
    public Color getMainColor() {
        return mainColor;
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }


    public static DelayedRemovalArray<LineShape> getLines() {
        return lines;
    }

    public static void addColor(Color color, Vector2 postion) {
        if(color.equals(mainColor)) {
            colors.add(color);
            postions.add(postion);
        }
    }
    public static boolean isMatching() {

        if (colors.size > 1) {
            if (colors.get(colors.size - 1).equals(mainColor)) {
                connecter = new LineShape(colors.get(colors.size - 1), postions.get(0), postions.get(postions.size - 1));
                lines.add(connecter);
                postions.removeIndex(0);
                colors.removeIndex(colors.size - 1);
                return true;
            } else {
                postions.removeIndex(colors.size - 1);
                colors.removeIndex(colors.size - 1);
                return false;
            }
        }
        System.out.println(colors.size + postions.size);
        return false;
    }

    public static void clearAll() {
        colors.clear();
        postions.clear();
        lines.clear();
    }
}
