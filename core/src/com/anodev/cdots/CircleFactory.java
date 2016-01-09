package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

/**
 * Created by abdullah on 1/9/16.
 */
public class CircleFactory {
    private static final HashMap<Color, Circles> circleByColor = new HashMap<Color, Circles>();

    public static Circles getCircle(Color color) {
        Circles circle = circleByColor.get(color);
        if (circle == null) {
            circleByColor.put(color, circle);
        }
        return circle;
    }
}
