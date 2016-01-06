package com.anodev.cdots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by 84170 on 05/01/2016.
 */
public class GameScreen extends ScreenAdapter {


    private static final float WORLD_SIZE = 480;

    ShapeRenderer renderer;
    ExtendViewport viewport;


    GridCircles grid;

    @Override
    public void show() {

        renderer = new ShapeRenderer();
        viewport = new ExtendViewport(540, 960);
        grid = new GridCircles();

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        grid.update(delta, viewport);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        grid.render(renderer);

        renderer.end();

    }
}
