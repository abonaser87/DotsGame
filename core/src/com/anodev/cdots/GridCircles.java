package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;


/**
 * Created by 84170 on 05/01/2016.
 */
public class GridCircles extends InputAdapter {
    int score = 0;
    int topScore = 4;
    private Constants.Difficulty difficulty;
    private Array<Color> colors = new Array<Color>();
    private Random rand = new Random();
    private DelayedRemovalArray<CirclesClient> circle;
    private FitViewport viewport;
    private CirclesClient x;
    private float newY;
    private float xStep;
    private GameState currentState;
    private BitmapFont font;
    private SpriteBatch batch;
    private DotsGame test;
    public GridCircles(FitViewport viewport, Constants.Difficulty difficulty) {
        // TODO : ADD MENU
        test = new DotsGame();
        currentState = GameState.GAMEOVER;
        batch = new SpriteBatch();
        this.viewport = viewport;
        this.difficulty = difficulty;
        circle = new DelayedRemovalArray<CirclesClient>();
        colors.add(Color.valueOf("#4d5b73ff"));
        colors.add(Color.valueOf("#8a3c42ff"));
        colors.add(Color.valueOf("#a39b8fff"));
        Gdx.input.setInputProcessor(this);
        xStep = Constants.screenWidth / difficulty.coloumns;
        createGrid(Constants.rows, -Constants.screenHeight, new Vector2(0, -50));
        font = new BitmapFont(Gdx.files.internal("data/modenine.fnt"));
        font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE / 1.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
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
        // Make a seprate class to handel the screens and states
        switch (currentState) {

            case RUNNING:
                updateRunning(delta, viewport);
                break;
            case GAMEOVER:
                gameOver(delta, viewport);
                break;

        }


    }

    private void gameOver(float delta, FitViewport viewport) {
        if (topScore < score) topScore = score;
    }

    private void updateRunning(float delta, FitViewport viewport) {
        for (int i = 0; i < circle.size; i++) {
            CirclesClient x = circle.get(i);
            x.update(delta);
        }
        if (ColorChecker.getLines().size > 0) {
            if (ColorChecker.getLines().get(ColorChecker.getLines().size - 1).isNotInScreen()) {
                currentState = GameState.GAMEOVER;
                System.out.println("Line out Game OVer");
            }
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
                    score += 1;
                    System.out.println("Matched");
                } else {
                    if (score > 0) score -= 1;
                    // Screen feedback that the choice was wrong ?
                    System.out.println("No match");
                }
            }
        }
        if (currentState == GameState.GAMEOVER) {
            if (worldClick.dst(new Vector2(xStep, Constants.screenHeight / 2 - Constants.yStep / 2)) < Constants.radius) {
                restart();
            }
            if (worldClick.dst(new Vector2(xStep * 2, Constants.screenHeight / 2 - Constants.yStep / 2)) < Constants.radius) {
                test.showDifficultyScreen();
            }
        }
        return true;
    }

    private void restart() {
        circle.clear();
        ColorChecker.clearAll();
        score = 0;
        currentState = GameState.RUNNING;
        createGrid(Constants.rows, -Constants.screenHeight, new Vector2(0, -50));
    }

    public void render(ShapeRenderer renderer, float delta) {

        for (CirclesClient x : circle) {
            x.render(renderer);
        }
        for (LineShape line : ColorChecker.getLines()) {
            line.render(renderer);
        }
        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, String.valueOf(score), Constants.screenWidth - xStep / 8, Constants.screenHeight - xStep / 8, 0, Align.center, false);
        batch.end();
        if (currentState == GameState.GAMEOVER) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            renderer.begin();
            renderer.set(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(new Color(0, 0, 0, 0.6f));
            renderer.rect(0, 0, Constants.screenWidth, Constants.screenHeight);
            renderer.end();

            batch.begin();
            font.setColor(Color.WHITE);
            font.draw(batch, "Game Over", Constants.screenWidth / 2, Constants.screenHeight / 2 + Constants.yStep / 8, 0, Align.center, false);
            font.draw(batch, "Connected Dots:" + String.valueOf(score), Constants.screenWidth / 2, Constants.screenHeight / 2, 0, Align.center, false);
            font.draw(batch, "Highest Score:" + String.valueOf(topScore), Constants.screenWidth / 2, Constants.screenHeight / 2 - Constants.yStep / 8, 0, Align.center, false);
            font.draw(batch, "Restart", xStep, Constants.screenHeight / 2 - Constants.yStep / 2, 0, Align.center, false);
            font.draw(batch, "Main Menu", xStep * 2, Constants.screenHeight / 2 - Constants.yStep / 2, 0, Align.center, false);
            batch.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }

    }

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER
    }


}
