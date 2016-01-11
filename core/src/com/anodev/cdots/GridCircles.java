package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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
public class GridCircles extends InputAdapter {
    private static final int coulmns = 3;
    private static final int rows = 9;
    Array<Color> colors = new Array<Color>();
    Random rand = new Random();
    DelayedRemovalArray<CirclesClient> circle;
    FitViewport viewport;
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    int xStep = screenWidth / coulmns;
    int yStep = screenHeight / 7;
    CirclesClient x;
    ColorChecker check;
    Array<Color> colorChecker = new Array<Color>();
    public GridCircles(FitViewport viewport) {
        this.viewport = viewport;
        circle = new DelayedRemovalArray<CirclesClient>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        createGrid(rows, 0, new Vector2(0, 0));
        Gdx.input.setInputProcessor(this);
//        createGrid(rows, - circle.get(0).getPosition().y ,circle.get(circle.size-1).getVelocity());
//        createGrid(rows, -yStep * (rows - 1),new Vector2(0,0));
    }

    private void createGrid(int rows, float offset, Vector2 velocity) {
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < coulmns; col++) {
                float xOffset = col * xStep;
                float yOffset = yStep * row;
                createCircle(xOffset, yOffset + offset, velocity);
            }
        }
    }

    private void createCircle(float xOffset, float yOffset, Vector2 velocity) {
        x = new CirclesClient(xOffset, yOffset, colors.get(rand.nextInt(3)), viewport);
        x.setVelocity(velocity);
        circle.add(x);
    }

    public void update(float delta, FitViewport viewport) {
        for (int i = 0; i < circle.size; i++) {
            CirclesClient x = circle.get(i);
            x.update(delta);
        }
//        if (circle.get(circle.size - 1).isNotInScreen()) {
//            createGrid(rows, - circle.get(0).getPosition().y ,circle.get(circle.size-1).getVelocity());
//        }
//        circle.begin();
//        for (int i = 0; i < circle.size; i++) {
//            CirclesClient x = circle.get(i);
//            if (x.isNotInScreen()) {
//                circle.removeIndex(i);
//            }
//        }
//        circle.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        for (CirclesClient x : circle) {
            if (worldClick.dst(x.getPosition()) < CirclesClient.radius) {
                colorChecker.add(x.color);
                if (colorChecker.size > 1) {

                    if (x.color.equals(colors.get(colorChecker.size - 2))) {
                        System.out.println("Matching");
                        colorChecker.removeIndex(colorChecker.size - 1);
                    } else {

                        System.out.println("Not Matching" + colorChecker.size);
                    }
                }
            }
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    public void render(ShapeRenderer renderer, float delta) {
        for (CirclesClient x : circle) {
            x.render(renderer);
        }
    }
}
