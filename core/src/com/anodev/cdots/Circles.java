package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by 84170 on 05/01/2016.
 */
public class Circles {
    private static final float SPEED = 50.0f;
    private static final float radius = 1.0f / 12 * Math.min(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    private static final int SEGMENTS = 500;
    private static final float MAX_SPEED = 800.0f;
    private Vector2 position;
    private Vector2 velocity;
    private Color color;

    public Circles(Color color) {
        this.color = color;
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
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
//        velocity.mulAdd(new Vector2(0,SPEED),delta);
//        if (velocity.y > MAX_SPEED) {
//            velocity.y = MAX_SPEED;
//        }
//        position.y += delta * velocity.y;
    }

    public void render(float xOffset, float yOffset, ShapeRenderer renderer) {
        position = new Vector2(xOffset, yOffset);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        velocity.mulAdd(new Vector2(0, SPEED), delta);
        if (velocity.y > MAX_SPEED) {
            velocity.y = MAX_SPEED;
        }
        position.y += delta * velocity.y;
        renderer.circle(xOffset, yOffset, radius, SEGMENTS);
    }

    public boolean isNotInScreen() {
        return position.y > Gdx.graphics.getHeight() + radius;
    }
}
