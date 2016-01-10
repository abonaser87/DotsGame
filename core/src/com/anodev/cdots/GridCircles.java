package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

/**
 * Created by 84170 on 05/01/2016.
 */
public class GridCircles {
    private static final int coulmns = 3;
    private static final int rows = 9;
    Array<Color> colors = new Array<Color>();
    Random rand = new Random();
    DelayedRemovalArray<Circles> circle;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    int xStep = screenWidth / coulmns;
    int yStep = screenHeight / 7;
    Array<Vector2> coord = new Array<Vector2>();
    public GridCircles() {
        circle = new DelayedRemovalArray<Circles>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        createGrid(rows, 0);

        createGrid(rows, -yStep * (rows - 1));
    }

    private void createGrid(int rows, float offset) {
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < coulmns; col++) {
                float xOffset = col * xStep;
                float yOffset = yStep * row;
                coord.add(new Vector2(xOffset, yOffset + offset));
                createCircle(xOffset, yOffset);
            }
        }
    }

    private void createCircle(float xOffset, float yOffset) {
        Circles bigCircle = CircleFactory.getCircle(colors.get(rand.nextInt(3)));
        bigCircle.setPosition(new Vector2(xOffset, yOffset));
        circle.add(bigCircle);
//        circle.add(new Circles(xOffset, yOffset, colors.get(rand.nextInt(3))));
    }

    public void update(float delta, FitViewport viewport) {
        for (int i = 0; i < circle.size; i++) {
            Circles x = circle.get(i);
            x.update(delta);
        }
//        if (circle.get(0).isNotInScreen()) {
//            createGrid(rows, -circle.get(circle.size - 1).getPosition().y + yStep * (rows - 1));
//            for (Circles c : circle) {
//                c.setVelocity(circle.get(0).getVelocity());
//            }
//        }
//        circle.begin();
//        for (int i = 0; i < circle.size; i++) {
//            Circles x = circle.get(i);
//            if (x.isNotInScreen()) {
//                circle.removeIndex(i);
//            }
//        }
//        circle.end();
    }

    public void render(ShapeRenderer renderer) {
        for (int i = 0; i < circle.size; i++) {
            Circles x = circle.get(i);
            Vector2 coordinate = coord.get(i);
            x.render(coordinate.x, coordinate.y, renderer);
            System.out.println(x.getVelocity());

        }
//        for (Circles x : circle) {
//            x.render(x.getPosition().x,x.getPosition().y,renderer);
//        }
    }
}
