package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by 84170 on 11/01/2016.
 */
public class CirclesClient {
    private FitViewport viewport;
    private Circles circle;
    private Color color;
    private Vector2 position;
    private Vector2 velocity;
    public CirclesClient(float xOffset, float yOffset, Color color, FitViewport viewport) {
        this.circle = CircleFactory.getCircle(color);
        this.position = new Vector2(xOffset, yOffset);
        this.velocity = new Vector2(0, 0);
        this.viewport = viewport;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void update(float delta) {
        velocity.mulAdd(new Vector2(0, Constants.SPEED), delta);
        if (velocity.y > Constants.MAX_SPEED) {
            velocity.y = Constants.MAX_SPEED;
        }
        position.y += delta * velocity.y;
    }

    public void render(ShapeRenderer renderer) {
        circle.render(position.x, position.y, Constants.radius, Constants.SEGMENTS, renderer);
    }

    public boolean isNotInScreen() {
        return position.y > Gdx.graphics.getHeight() + Constants.radius;
    }
}
