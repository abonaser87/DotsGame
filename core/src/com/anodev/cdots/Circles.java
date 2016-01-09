package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by 84170 on 05/01/2016.
 */
public class Circles {
    private static final float SPEED = 100.0f;
    private static final float radius = 1.0f / 12 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    private static final int SEGMENTS = 500;
    private static final float MAX_SPEED = 800.0f;
    private Vector2 position;
    private Vector2 velocity;
    private Color color;

    public Circles(float xOffset, float yOffset, Color color) {
        position = new Vector2();
        this.color = color;
        position.x = xOffset;
        position.y = yOffset;
        velocity = new Vector2();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void update(float delta) {
//        velocity.y += delta * SPEED;
//        if (velocity.y > MAX_SPEED) {
//            velocity.y = MAX_SPEED;
//        }
//        position.y += delta * velocity.y;
    }

    public void render(ShapeRenderer renderer) {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.circle(position.x, position.y, radius, SEGMENTS);
    }

    public boolean isNotInScreen() {
        return position.y > Gdx.graphics.getHeight() + radius;
    }
}
