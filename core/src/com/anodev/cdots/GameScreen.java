package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by 84170 on 05/01/2016.
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
        grid = new GridCircles(viewport, difficulty, game);
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
        Constants.setBG();
        grid.update(delta, viewport);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setAutoShapeType(true);
        grid.render(renderer, delta);
        renderer.end();
    }
}
