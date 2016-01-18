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
    Constants.Difficulty difficulty;
    Array<Color> colors = new Array<Color>();
    Random rand = new Random();
    DelayedRemovalArray<CirclesClient> circle;
    FitViewport viewport;
    CirclesClient x;
    float newY;
    private float xStep;

    public GridCircles(FitViewport viewport, Constants.Difficulty difficulty) {
        this.viewport = viewport;
        this.difficulty = difficulty;
        circle = new DelayedRemovalArray<CirclesClient>();
        colors.add(Color.valueOf("#4d5b73ff"));
        colors.add(Color.valueOf("#8a3c42ff"));
        colors.add(Color.valueOf("#a39b8fff"));
        Gdx.input.setInputProcessor(this);
        xStep = Constants.screenWidth / difficulty.coloumns;
        createGrid(Constants.rows, -Constants.screenHeight, new Vector2(0, -50));
    }

    private void createGrid(int rows, float offset, Vector2 velocity) {
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < difficulty.coloumns; col++) {
                float xOffset = col * xStep;
                float yOffset = Constants.yStep * row;
                createCircle(xOffset, yOffset + offset, velocity);
            }
        }
    }

    private void createCircle(float xOffset, float yOffset, Vector2 velocity) {
        x = new CirclesClient(xOffset, yOffset, colors.get(rand.nextInt(difficulty.coloumns - 1)), viewport);
        x.setVelocity(velocity);
        circle.add(x);
    }

    public void update(float delta, FitViewport viewport) {
        for (int i = 0; i < circle.size; i++) {
            CirclesClient x = circle.get(i);
            x.update(delta);
        }
        if (circle.get(circle.size - 1).isNotInScreen()) {
            newY = circle.get(0).getPosition().y - 2 * Constants.yStep - (circle.get(circle.size - 1).getPosition().y - circle.get(0).getPosition().y);
            createGrid(Constants.rows, newY, circle.get(0).getVelocity());
        }
        circle.begin();
        for (int i = 0; i < circle.size; i++) {
            CirclesClient x = circle.get(i);
            if (x.isNotInScreen()) {
                circle.removeIndex(i);
            }
        }
        circle.end();
        if (ColorChecker.getLines().size > 0) {
            if (ColorChecker.getLines().get(ColorChecker.getLines().size - 1).isNotInScreen()) {
                // TODO : GAMEOVER PAY COINS TO CONTINUE
                System.out.println("Last line out GAME OVER");
            }
        }
        for (int i = 0; i < ColorChecker.getLines().size; i++) {
            LineShape x = ColorChecker.getLines().get(i);
            if (x.isNotInScreen()) {
                ColorChecker.getLines().removeIndex(i);
            }
        }

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
