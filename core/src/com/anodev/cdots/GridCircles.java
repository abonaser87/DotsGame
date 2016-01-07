package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * Created by 84170 on 05/01/2016.
 */
public class GridCircles {
    private static final int coulmns = 3;
    private static final int rows = 7;
    Array<Color> colors = new Array<Color>();
    Random rand = new Random();
    DelayedRemovalArray<Circles> circle;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    int xStep = screenWidth / (coulmns);
    int yStep = screenHeight / (rows);
    public GridCircles() {
        circle = new DelayedRemovalArray<Circles>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        createGrid(rows, -50);
    }

    private void createGrid(int rows, float offset) {
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < coulmns; col++) {
                float xOffset = col * xStep;
                float yOffset = yStep * row;
                createCircle(xOffset, yOffset + offset);
            }
        }
    }

    private void createCircle(float xOffset, float yOffset) {
        circle.add(new Circles(xOffset, yOffset, colors.get(rand.nextInt(3))));
    }

    public void update(float delta, ExtendViewport viewport) {
        for (Circles x : circle) {
            x.update(delta);
        }
        if (circle.get(circle.size - 1).isNotInScreen()) {
            createGrid(rows, -circle.get(0).getPosition().y - 50 - yStep);
            for (Circles c : circle) {
                c.setVelocity(new Vector2(0, 500));
            }
        }
        circle.begin();
        for (int i = 0; i < circle.size; i++) {
            Circles x = circle.get(i);
            if (x.isNotInScreen()) {
                circle.removeIndex(i);
            }
        }
        circle.end();
    }

    public void render(ShapeRenderer renderer) {
        for (Circles x : circle) {
            x.render(renderer);
        }
    }
}
