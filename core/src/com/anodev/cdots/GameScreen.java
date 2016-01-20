package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by 84170 on 05/01/2016.
 */
/*
Todo:
1-Add algorthim for colors randomness
*2-Add Difficulty
3-Add HUD and score
*4-Add GameOver
4a- Add restart option
5-Add Leaderboard
6-Pick the color for the player and then transtion to another color and maybe another shape.
7- Polishing - transiotins and animation
 */
public class GameScreen extends ScreenAdapter {
    Constants.Difficulty difficulty;
    ShapeRenderer renderer;
    FitViewport viewport;
    GridCircles grid;
    DotsGame game;

    public GameScreen(DotsGame game, Constants.Difficulty difficulty) {
        this.game = game;
        this.difficulty = difficulty;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        grid = new GridCircles(viewport, difficulty);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(245.0f / 255.0f, 233.0f / 255.0f, 208.0f / 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        grid.update(delta, viewport);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setAutoShapeType(true);
        grid.render(renderer, delta);

        renderer.end();
    }
}
