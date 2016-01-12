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
    Array<Color> colors = new Array<Color>();
    Random rand = new Random();
    DelayedRemovalArray<CirclesClient> circle;
    FitViewport viewport;
    CirclesClient x;
    LineShape line;
    Array<LineShape> lines = new Array<LineShape>();
    Array<Vector2> postions = new Array<Vector2>();
    float newY;
    public GridCircles(FitViewport viewport) {
        this.viewport = viewport;
        circle = new DelayedRemovalArray<CirclesClient>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        Gdx.input.setInputProcessor(this);
        createGrid(Constants.rows, 0, new Vector2(0, 0));
//        createGrid(Constants.rows, - circle.get(0).getPosition().y ,circle.get(circle.size-1).getVelocity());
//        createGrid(Constants.rows, -Constants.yStep * (Constants.rows - 1), new Vector2(0, 0));
    }

    private void createGrid(int rows, float offset, Vector2 velocity) {
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < Constants.coulmns; col++) {
                float xOffset = col * Constants.xStep;
                float yOffset = Constants.yStep * row;
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
        // TODO : SCROLLING CIRCLES
        if (circle.get(circle.size - 1).isNotInScreen()) {
            System.out.println(circle.get(circle.size - 1).getPosition());
            System.out.println(circle.get(0).getPosition());
            newY = circle.get(0).getPosition().y - 2 * Constants.yStep - (circle.get(circle.size - 1).getPosition().y - circle.get(0).getPosition().y);
            System.out.println(newY);
            createGrid(Constants.rows, newY, circle.get(0).getVelocity());
            System.out.println((-circle.get(circle.size - 1).getPosition().y - Constants.yStep * delta) * delta);
        }
        circle.begin();
        for (int i = 0; i < circle.size; i++) {
            CirclesClient x = circle.get(i);
            if (x.isNotInScreen()) {
                circle.removeIndex(i);
            }
        }
        circle.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        for (CirclesClient x : circle) {
            if (worldClick.dst(x.getPosition()) < Constants.radius) {
                ColorChecker.addColor(x.getColor(), x.getPosition());
                if (ColorChecker.isMatching()) {
                    System.out.println("Matched");
                } else {
                    System.out.println("No match");
                }
            }
        }
        return true;
    }

    public void render(ShapeRenderer renderer, float delta) {
        for (CirclesClient x : circle) {
            x.render(renderer);
        }
        for (LineShape line : ColorChecker.getLines()) {
            line.render(renderer);
        }
    }
}
