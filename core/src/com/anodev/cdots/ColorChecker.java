package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by abdullah on 1/12/16.
 */
public class ColorChecker {
    private static final Array<Color> colors = new Array<Color>();
    private static final Array<Vector2> postions = new Array<Vector2>();
    private static final Array<LineShape> lines = new Array<LineShape>();
    private static LineShape connecter;

    public static Array<LineShape> getLines() {
        return lines;
    }

    public static void addColor(Color color, Vector2 postion) {
        colors.add(color);
        postions.add(postion);
    }
    public static boolean isMatching() {
        if (colors.size > 1) {
            if (colors.get(0).equals(colors.get(colors.size - 1))) {
                connecter = new LineShape(colors.get(0), postions.get(0), postions.get(postions.size - 1));
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
        return false;
    }

    public static void clearAll() {
        colors.clear();
        postions.clear();
        lines.clear();
    }
}
