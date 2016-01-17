package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by 84170 on 12/01/2016.
 */
public class LineShape {
    private Color color;
    private Vector2 point1;
    private Vector2 point2;

    public LineShape(Color color, Vector2 point1, Vector2 point2) {
        this.color = color;
        this.point1 = point1;
        this.point2 = point2;
    }

    public void render(ShapeRenderer renderer) {
        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.rectLine(point1, point2, Constants.THICKNESS);
        renderer.end();
    }

    public boolean isNotInScreen() {
        return point2.y > Gdx.graphics.getHeight();
    }
}
