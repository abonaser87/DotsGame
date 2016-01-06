package com.anodev.cdots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by 84170 on 05/01/2016.
 */
public class Circles {
    private static final float SPEED = -50.0f;
    private static final float radius = 50.0f;
    private static final int SEGMENTS = 500;
    Vector2 position;
    Vector2 velocity;
    Color color;

    public Circles(float xOffset, float yOffset, Color color) {
        position = new Vector2();
        this.color = color;
        position.x = xOffset;
        position.y = yOffset;
        velocity = new Vector2(0, 0);
    }

    public void update(float delta) {
        velocity.y += delta * SPEED;
        position.y += delta * velocity.y;
    }

    public void render(ShapeRenderer renderer) {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.circle(position.x, position.y, radius, SEGMENTS);
    }

    public boolean isNotInScreen() {
        return position.y < -radius;
    }
}
