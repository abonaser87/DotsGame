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
    private Color color1;
    private Vector2 point1;
    private Vector2 point2;

    public LineShape(Color color, Color color1, Vector2 point1, Vector2 point2) {
        this.color = color;
        this.color1 = color1;
        this.point1 = point1;
        this.point2 = point2;
    }

    public void render(ShapeRenderer renderer) {
        renderer.begin();
        Gdx.gl20.glLineWidth(50.0f);
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.line(point1.x, point1.y, point2.x, point2.y, color, color1);
        renderer.end();
    }

    public boolean isNotInScreen() {
        return point2.y > Gdx.graphics.getHeight();
    }
}
