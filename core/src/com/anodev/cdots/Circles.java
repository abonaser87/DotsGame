package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by 84170 on 05/01/2016.
 */
public class Circles {

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color;

    public Circles(Color color) {
        this.color = color;
    }

    public void render(float xOffset, float yOffset, float radius, int SEGMENTS, ShapeRenderer renderer) {
        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.circle(xOffset, yOffset, radius, SEGMENTS);
        renderer.end();
    }
}
