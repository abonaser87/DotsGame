package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * Created by 84170 on 05/01/2016.
 */
public class GridCircles {
    private static final int coulmns = 3;
    private static final int rows = 2;
    Array<Color> colors = new Array<Color>();
    Random rand = new Random();
    Array<Circles> circle;

    public GridCircles() {
        circle = new Array<Circles>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        createGrid(rows, Gdx.graphics.getHeight() / 2);

    }

    private void createGrid(int rows, float offset) {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        int xStep = screenWidth / (coulmns);
        int yStep = screenHeight / (rows);
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < coulmns; col++) {
                float xOffset = col * xStep;
                float yOffset = yStep * row;
                circle.add(new Circles(xOffset, yOffset + offset, colors.get(rand.nextInt(3))));
            }
        }
    }

    public void update(float delta, ExtendViewport viewport) {
        for (int i = 0; i < circle.size; i++) {
            Circles x = circle.get(i);
            x.update(delta);
            if (x.isNotInScreen()) {
                createGrid(2, 500 * i);
                circle.removeIndex(i);
            }
        }
//        for(Circles x:circle){
//            x.update(delta);
//        }

    }

    public void render(ShapeRenderer renderer) {
        for (Circles x : circle) {
            x.render(renderer);
        }
    }
}
